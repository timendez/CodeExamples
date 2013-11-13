import java.net.URL;
import java.net.MalformedURLException;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Properties;

public class Scraper {
   public static String getPage(String urlString){
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
         System.out.println("ERROR: Please enter a valid URL. Example: http://www.example.com");
      }
      catch (IOException e) {
      }          
     return result;
   }

  /**
   * towhom must be in 'example@email.com' format
   * emailaddress must be in 'example' format assuming gmail due to host
   */
   public static void sendEmail(String towhom, String subject, String body, String emailaddress, String password) {
      String host = "smtp.gmail.com", from = emailaddess, pass = password;
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