import java.io.*;
import java.util.*;
/**
 * Randomly decides the six items you will be able to buy
 * during a game of Dota 2.
 * 
 * @author Tim Mendez 
 */
public class DotaDecide
{
   public static void main(String[] args) {
      String[] items = new String[] {"Clarity", "Town Portal Scroll", "Observer Ward, Tango", "Dust of Appearance", "Sentry Ward", "Healing Salve", "Animal Courier", "Bottle", "Smoke of Deceit", "Flying Courier", "Iron Branch", "Circlet", "Ultimate Orb", "Gauntlets of Strength", "Belt of Strength", "Ogre Club", "Slippers of Agility", "Band of Elvenskin", "Blade of Alacrity", "Mantle of Intelligence", "Robe of the Magi", "Staff of Wizardry", "Ring of Protection", "Chainmail", "Platemail", "Quelling Blade", "Qarterstaff", "Claymore", "Stout Shield", "Helm of the Iron Will", "Javelin", "Blades of Attack", "Broadsword", "Mithril Hammer", "Magic Stick", "Gloves of Haste", "Ghost Scepter", "Sage's Mask", "Cloak", "Shadow Amulet", "Ring of Regen", "Gem of True Sight", "Talisman of Evasion", "Boots of Speed", "Morbid Mask", "Blink Dagger", "Null Talisman", "Poor Man's Shield", "Oblivion Staff", "Wraith Band", "Soul Ring", "Perserverance", "Magic Wand", "Phase Boots", "Hand of Midas", "Bracer", "Power Treads", "Boots of Travel", "Ring of Basilius", "Tranquil Boots", "Drum of Endurance", "Headdress", "Ring of Aquila", "Vladmir's Offering", "Buckler", "Medallion of Courage", "Mekansm", "Urn of Shadows", "Arcane Boots", "Pipe of Insight", "Force Staff", "Dagon", "Refresher Orb", "Veil of Discord", "Rod of Atos", "Scythe of Vyse", "Eul's Scepter of Divinity", "Orchid Malevolence", "Necronomicon", "Aghanim's Scepter", "Crystalys", "Battle Fury", "Daedalus", "Armlet of Mordiggian", "Ethereal Blade", "Butterfly", "Skull Basher", "Radiance", "Divine Rapier", "Shadow Blade", "Monkey King Bar", "Abyssal Blade", "Hood of Defiance", "Black King Bar", "Linken's Sphere", "Blade Mail", "Shiva's Guard", "Assault Cuirass", "Vanguard", "Manta Style", "Heart of Tarrasque", "Soul Booster", "Bloodstone", "Helm of the Dominator", "Maelstrom", "Desolator", "Mask of Madness", "Diffusal Blade", "Mjollnir", "Sange", "Heaven's Halberd", "Eye of Skadi", "Yasha", "Sange and Yasha", "Satanic", "Orb of Venom", "Ring of Health", "Void Stone", "Energy Booster", "Vitality Booster", "Point Booster", "Hyperstone", "Demon Edge", "Mystic Staff", "Reaver", "Eaglesong", "Sacred Relic"};
      int cnt = 0;
      while(cnt < 6) {
         System.out.println(++cnt + ". " + items[(int)(Math.random() * 127)]);
      }
   }
}
