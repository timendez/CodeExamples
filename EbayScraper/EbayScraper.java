/* EbayScraper
 *
 * Searches eBay's newly listed Buy It Now items
 * for keywords entered by the user under a certain
 * price threshold. Emails the user a link to any item
 * found.
 *
 * @author Tim Mendez
 * @date 2013
 **/

import java.net.*;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EbayScraper {

   /* Handles all command line I/O
    * 
    * Sends a unique, randomly generated code
    * to the user's email to prevent EbayScraper
    * from being used to spam
    **/
   public static void main(String[] args) {
      int begidx, endidx, time;
      double price, threshold, authent;
      boolean exitstatus = true;
      String listingid, webpage, input, searchingfor, listings = "", tmppage = "", useremail;
      Scanner scan = new Scanner(System.in);

      System.out.print("Enter a price threshold: $");
      threshold = scan.nextDouble();
      System.out.print("\nEnter an item to search for: ");
      
      authent = Math.random()*70+12;

      webpage = "http://www.ebay.com/sch/i.html?LH_BIN=1&_sacat=0&_from=R40&_nkw=";

      scan.nextLine();
      input = scan.nextLine();
      searchingfor = input;

      if(!input.contains(" "))
         webpage = webpage.concat(input + "+");
      else {
         while(true) {
            endidx = input.indexOf(" ");

            if(endidx == -1) {
               webpage = webpage.concat(input + "+");
               break;
            }

            tmppage = input.substring(0, endidx);
            input = input.substring(endidx+1);
            webpage = webpage.concat(tmppage + "+");
         }
      }

      System.out.print("\nEnter how often (in minutes, minimum 1) you would like to check for new listings: ");
      try {
         time = scan.nextInt() * 60000;
      }
      catch(InputMismatchException e) {
         time = 1;
	  }

      if(time < 60000)
         time = 60000;
      
      System.out.println("\nChecking for new listings every " + time/60000 + " minutes.");

      System.out.print("\nPlease enter your email: ");
      scan.nextLine();
      useremail = scan.nextLine();
      sendEmail(useremail, "EbayWebparser Authentication Code", "EbayWebparser Authentication Code: " + authent + "\n\nEnter this code exactly as you see it to confirm your usage of EbayWebparser. If you feel that you have incorrectly received this email, please disregard it\n\nCopyright EbayWebparser 2013\nTim Mendez");

      while(true) {
         System.out.print("\nPlease enter the code you have received by email exactly: ");
         if(scan.nextDouble() != authent)
            System.out.println("I'm sorry, that is the incorrect code. Make sure you enter it exactly as it is in the email.");
         else {
            System.out.println("Confirmed!");
            break;
         }
      }

      System.out.println("Running... Press Ctrl+C to cancel.");

      webpage = webpage.substring(0, webpage.length()-1);
      webpage = webpage.concat("&_sop=10");

      while(exitstatus) {
         tmppage = getPage(webpage);
         tmppage = tmppage.trim();

         while(true) {
            begidx = tmppage.indexOf("itemprop=\"price\">");
            if(begidx == -1)
               break;
  
            listingid = tmppage.substring(tmppage.indexOf("listingId")+11);
            listingid = listingid.substring(0, listingid.indexOf("\""));

            if(listings.contains(listingid))
               break;
            else
               listings = listings.concat(listingid);

            if(listings.length() > 1200){
               System.out.println("Listings Recycled.");
               listings = "";
            }

            tmppage = tmppage.substring(begidx+23);
            
            try {
               price = Double.parseDouble(tmppage.substring(0, tmppage.indexOf("<")));
            
               if(price < threshold) {
                  sendEmail(useremail, "Found " + searchingfor + "! - $" + price, "www.ebay.com/itm/" + listingid);
               }
            }
            catch(NumberFormatException e) {
               System.out.println("Parsing $1000+ still in development.");
            }
         }
         try {
            Thread.sleep(time);
			Runtime.getRuntime().gc();
         }
         catch(InterruptedException e) {
            System.out.println("Something system-interrupted the program!");
         }
      }
   }

   /* Grabs the webpage and returns it in a String
    **/
   static public String getPage(String urlString){
      String result = "";
      try {
         URL url = new URL(urlString);
         BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
         String str;
         while ((str = in.readLine()) != null) {
            result += str;
         }
         in.close();             
      }
      catch (MalformedURLException e) {
         System.out.println("ERROR: Please enter a valid URL. Example: www.example.com");
      }
      catch (IOException e) {
      }          
     return result;
   }

   /* Sends an email from a Gmail account
    *
    * NOTE: Be sure to change the 'from' and 'pass'
    * strings to be accurate.
    * If uncomfortable about hardcoding in, feel free
    * to scan them in at the time of execution.
    **/
   private static void sendEmail(String towhom, String subject, String body) {
      String host = "smtp.gmail.com", from = "USERNAME", pass = "PASSWORD";
      Properties props = System.getProperties();
      Scanner scan = new Scanner(System.in);

      try {
         props.put("mail.smtp.starttls.enable", "true");
         props.put("mail.smtp.host", host);
         props.put("mail.smtp.user", from);
         props.put("mail.smtp.password", pass);
         props.put("mail.smtp.port", "587");
         props.put("mail.smtp.auth", "true");

         Session session = Session.getDefaultInstance(props, null);
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));

         InternetAddress toAddress = new InternetAddress(towhom);

         message.addRecipient(Message.RecipientType.TO, toAddress);
         message.setSubject(subject);
         message.setText(body);

         Transport transport = session.getTransport("smtp");
         transport.connect(host, from, pass);
         transport.sendMessage(message, message.getAllRecipients());
         transport.close();
      }
      catch(AddressException e) {
         System.out.println("Invalid Email Address.");
      }
      catch(MessagingException e) {
         System.out.println("\nIf you are running Avast Antivirus, please go to Security>ANTIVIRUS>MailShield>Settings and then untick \"Scan Outbound Mail (SMTP)\"");
         System.out.print("\nInvalid Email Address, please reenter it: ");
         sendEmail(scan.nextLine(), subject, body);
      }
   }
}
