package me.FatJack.God;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{
	public Inventory invGodTools;

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		createInvGodTools(); //when the server starts its gonna create the inventory 
	}

	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("god")){ 
	    	if(!(sender instanceof Player)){ //makes sure the sender is a player
				sender.sendMessage("You are not allowed to do this from the console");
				
				return true;
			}
	    	Player player = (Player) sender;
	    	player.openInventory(invGodTools);
	    	return true;
		}
		return false;
	}
	
	
	@EventHandler
    public void onJump(PlayerMoveEvent event) {
    	//the spigot api has no jump event which is a pain in the 
    	
    	Player player = (Player) event.getPlayer();
    	if(player.getInventory().getBoots() !=  null) //if they are not wearing boots this will stop
    		if(player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Boots of Leaping")) //contains instead of equals because there are the chatcolor tags
    			if(player.getInventory().getBoots().getItemMeta().hasLore()) //otherwise you can rename it to Boots of leaping with an anvil 
    				if(event.getFrom().getY()< event.getTo().getY() && player.getLocation().subtract(0,1,0).getBlock().getType() != Material.AIR
    				&& player.getLocation().subtract(0,1,0).getBlock().getType()!= Material.WATER && player.getLocation().subtract(0,0,0).getBlock().getType()!= Material.WATER
    				&& player.getLocation().subtract(0,1,0).getBlock().getType()!= Material.SEAGRASS && player.getLocation().subtract(0,1,0).getBlock().getType()!= Material.KELP
    				&& player.getLocation().subtract(0,1,0).getBlock().getType()!= Material.KELP_PLANT) { //these are to make sure they cant sneaky sneaky the jump effect 
    					
    					//now you can make the player fly 
    					player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2));
    				}
    }
	
	@EventHandler()
	public void onClick(InventoryClickEvent event) {
		if(!event.getInventory().equals(invGodTools))
			return;
		
		if(event.getCurrentItem() == null) return; //makes sure nothing happens
		if(event.getCurrentItem().getItemMeta() == null) return;
		if(event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
		event.setCancelled(true);		
		
		Player player = (Player) event.getWhoClicked();
		
		
		if(event.getSlot() == 0){ //bow 			
			if(player.getInventory().firstEmpty()==-1) { 
    			Location loc = player.getLocation();
    			World world = player.getWorld();
    			
    			world.dropItemNaturally(loc, bow());
        		player.sendMessage(ChatColor.GOLD + "Please take this" + ChatColor.DARK_RED + "《" + ChatColor.RED + ChatColor.BOLD + "JFK Assasination Weapon" + ChatColor.DARK_RED + "》");
    			player.closeInventory();
			}
    		
    		player.getInventory().addItem(bow());
    		player.sendMessage(ChatColor.GOLD + "Please take this" + ChatColor.DARK_RED + "《" + ChatColor.RED + ChatColor.BOLD + "JFK Assasination Weapon" + ChatColor.DARK_RED + "》");
			player.closeInventory();
		}

		if(event.getSlot() == 1) {//sword 
			if(player.getInventory().firstEmpty()==-1) {   
    			Location loc = player.getLocation();
    			World world = player.getWorld();
    			
    			world.dropItemNaturally(loc, sword()); 
        		player.sendMessage(ChatColor.GOLD + "Please take this" + ChatColor.BOLD + " Godly Sword");
    			player.closeInventory();
			}
    		
    		player.getInventory().addItem(sword());
    		player.sendMessage(ChatColor.GOLD + "Please take this" + ChatColor.BOLD + " Godly Sword");
			player.closeInventory();
		}
		
		if(event.getSlot() == 2) { //pickaxe 
			if(player.getInventory().firstEmpty()==-1) {    			
    			Location loc = player.getLocation();
    			World world = player.getWorld();
    			
    			world.dropItemNaturally(loc, pickaxe());
        		player.sendMessage(ChatColor.GOLD + "Please take this " + ChatColor.BOLD + "Godly Pickaxe");
    			player.closeInventory();
			}
    		
    		player.getInventory().addItem(pickaxe());
    		player.sendMessage(ChatColor.GOLD + "Please take this " + ChatColor.BOLD + "Godly Pickaxe");
			player.closeInventory();
		}
		
		if(event.getSlot() == 3) {//stick
			if(player.getInventory().firstEmpty()==-1) {    			
    			Location loc = player.getLocation();
    			World world = player.getWorld();
    			
    			world.dropItemNaturally(loc, stick()); 
        		player.sendMessage(ChatColor.GOLD + "Please take this " + ChatColor.LIGHT_PURPLE + "[PIG" + ChatColor.AQUA + "+++" + ChatColor.LIGHT_PURPLE + "]" + ChatColor.LIGHT_PURPLE + " Stick");
    			player.closeInventory();
			}
    		
    		player.getInventory().addItem(stick());
    		player.sendMessage(ChatColor.GOLD + "Please take this " + ChatColor.LIGHT_PURPLE + "[PIG" + ChatColor.AQUA + "+++" + ChatColor.LIGHT_PURPLE + "]" + ChatColor.LIGHT_PURPLE + " Stick");
			player.closeInventory();
		}
		//slot 4 is empty 
		
		if(event.getSlot() == 5) { //helmet 
			if(player.getInventory().firstEmpty()==-1) {
    			//inventory is full
    			Location loc = player.getLocation();
    			World world = player.getWorld();
    			
    			world.dropItemNaturally(loc, getHelmet()); //this will drop the item there 
        		player.sendMessage(ChatColor.GOLD + "Notch Has chosen you to take this" + ChatColor.BOLD + " Pig Crown");
    			player.closeInventory();
    		}
    		
    		player.getInventory().addItem(getHelmet());
    		player.sendMessage(ChatColor.GOLD + "Notch Has chosen you to take this" + ChatColor.BOLD + " Pig Crown");
			player.closeInventory();
		}
		
		if(event.getSlot() == 6) {
			if(player.getInventory().firstEmpty()==-1) {
    			//inventory is full
    			Location loc = player.getLocation();
    			World world = player.getWorld();
    			
    			world.dropItemNaturally(loc, getChestplate()); //this will drop the item there 
        		player.sendMessage(ChatColor.GOLD + "Notch Has chosen you to take this" + ChatColor.BOLD + " godly shirt");
    			player.closeInventory();
    		}
    		
    		player.getInventory().addItem(getChestplate());
    		player.sendMessage(ChatColor.GOLD + "Notch Has chosen you to take this" + ChatColor.BOLD + " godly shirt");
			player.closeInventory();
		}
		
		if(event.getSlot() == 7) { //leggings 
			if(player.getInventory().firstEmpty()==-1) {
    			//inventory is full
    			Location loc = player.getLocation();
    			World world = player.getWorld();
    			
    			world.dropItemNaturally(loc, getLeggings()); //this will drop the item there 
        		player.sendMessage(ChatColor.GOLD + "Notch Has chosen you to take this" + ChatColor.BOLD + " godly pair of pants");
    			player.closeInventory();
    		}
    		
    		player.getInventory().addItem(getLeggings());
    		player.sendMessage(ChatColor.GOLD + "Notch Has chosen you to take this" + ChatColor.BOLD + " godly pair of pants");
			player.closeInventory();
		}
		
		if(event.getSlot() == 8) { //boots 
			if(player.getInventory().firstEmpty()==-1) {
    			//inventory is full
    			Location loc = player.getLocation();
    			World world = player.getWorld();
    			
    			world.dropItemNaturally(loc, getItem()); //this will drop the item there 
        		player.sendMessage(ChatColor.GOLD + "Notch Has chosen you to take these" + ChatColor.BOLD + " God Boots");
    			player.closeInventory();
    		}
    		
    		player.getInventory().addItem(getItem());
    		player.sendMessage(ChatColor.GOLD + "Notch Has chosen you to take these" + ChatColor.BOLD + " God Boots");
			player.closeInventory();
		}
		
		if(event.getSlot() == 13){ //close the shop 
			player.closeInventory();
		}
		return;
	}
	
	
	public ItemStack sword() {
    	ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
    	ItemMeta meta = sword.getItemMeta(); //item meta is basically what you can do with the boots the name the enchantments etc...
    	
    	meta.setDisplayName(ChatColor.RED + "Toothpick of the " + ChatColor.BOLD + "PIGS");
    	
    	List<String> lore = new ArrayList<String>(); //list of strings
    	lore.add(""); //adds a space to the lore
    	lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "Dont mess with the people who have this peice of wood");
    	meta.setLore(lore); //takes the list of strings as the lore 
    	
    	meta.addEnchant(Enchantment.FIRE_ASPECT, 3, true); 
    	meta.addEnchant(Enchantment.SWEEPING_EDGE, 15, true);
    	meta.addEnchant(Enchantment.DAMAGE_ALL, 15, true); //i think this is sharpness
    	meta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 15, true);
    	meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);

    	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    	meta.setUnbreakable(true);
    	
    	sword.setItemMeta(meta);
    	
    	return sword;
    }
	
	public ItemStack pickaxe() {
    	ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
    	ItemMeta meta = pickaxe.getItemMeta(); //item meta is basically what you can do with the boots the name the enchantments etc...
    	
    	meta.setDisplayName(ChatColor.RED + "Toothpick 2.0 of the " + ChatColor.BOLD + "PIGS");
    	
    	List<String> lore = new ArrayList<String>(); //list of strings
    	lore.add(""); 
    	lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "Dont mess with the people who have this peice of wood");
    	meta.setLore(lore); 
    	
    	meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 15, true); 
    	meta.addEnchant(Enchantment.DIG_SPEED, 15, true);

    	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    	meta.setUnbreakable(true);
    	
    	pickaxe.setItemMeta(meta);
    	
    	return pickaxe;
    }
	
	public ItemStack stick() {
    	ItemStack stick = new ItemStack(Material.STICK);
    	ItemMeta meta = stick.getItemMeta(); //item meta is basically what you can do with the boots the name the enchantments etc...
    	
    	meta.setDisplayName(ChatColor.LIGHT_PURPLE + "[PIG" + ChatColor.AQUA + "+++" + ChatColor.LIGHT_PURPLE + "]" + ChatColor.LIGHT_PURPLE + " Stick");
    	
    	List<String> lore = new ArrayList<String>(); //list of strings
    	lore.add(""); 
    	lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "Dont mess with the people who have this peice of wood");
    	meta.setLore(lore); 
    	
    	meta.addEnchant(Enchantment.KNOCKBACK, 25, true); 
    	meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);

    	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    	meta.setUnbreakable(true);
    	
    	stick.setItemMeta(meta);
    	
    	return stick;
    }
	
	public ItemStack bow() {
    	ItemStack bow = new ItemStack(Material.BOW);
    	ItemMeta meta = bow.getItemMeta(); //item meta is basically what you can do with the boots the name the enchantments etc...
    	
    	meta.setDisplayName(ChatColor.DARK_RED + "《" + ChatColor.RED + ChatColor.BOLD + "JFK Assasination Weapon" + ChatColor.DARK_RED + "》");
    	
    	List<String> lore = new ArrayList<String>(); //list of strings
    	lore.add(""); 
    	lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "Dont mess with the people who have this peice of string");
    	meta.setLore(lore); 
    	
    	meta.addEnchant(Enchantment.ARROW_DAMAGE, 25, true); 
    	meta.addEnchant(Enchantment.ARROW_INFINITE, 25, true); 
    	meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 25, true); 
    	meta.addEnchant(Enchantment.ARROW_FIRE, 5, true); 


    	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    	meta.setUnbreakable(true);
    	
    	bow.setItemMeta(meta);
    	
    	return bow;
    }
   
    
    public ItemStack getItem() {
    	
    	ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
    	ItemMeta meta = boots.getItemMeta(); //item meta is basically what you can do with the boots the name the enchantments etc...
    	
    	meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Boots of Leaping");
    	
    	List<String> lore = new ArrayList<String>(); //list of strings
    	lore.add(""); //adds a space to the lore
    	lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "Boots made for only the best");
    	meta.setLore(lore); //takes the list of strings as the lore 
    	
    	meta.addEnchant(Enchantment.PROTECTION_FALL, 99999, true); //for now it doesn't matter as there is a listener that will make it fall infinity
    	meta.addEnchant(Enchantment.DEPTH_STRIDER, 50, true);
    	
    	//these two lines make the boots look better in game
    	meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	meta.setUnbreakable(true);
    	
    	boots.setItemMeta(meta);
    	
    	return boots;
    }
    
    public ItemStack getHelmet() {
    	
    	ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
    	ItemMeta meta = helmet.getItemMeta(); //item meta is basically what you can do with the boots the name the enchantments etc...
    	
    	meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + ChatColor.ITALIC+ "PIG" + ChatColor.GOLD + "" + ChatColor.BOLD + " Crown ♛");
    	
    	List<String> lore = new ArrayList<String>(); //list of strings
    	lore.add(""); //adds a space to the lore
    	lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "A helmet made for only the greatest of PIG gods");
    	meta.setLore(lore); //takes the list of strings as the lore 
    	
    	meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 50, true); 
    	meta.addEnchant(Enchantment.PROTECTION_FALL, 50, true);
    	meta.addEnchant(Enchantment.PROTECTION_FIRE, 50, true);
    	meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 50, true);
    	meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 50, true);
    	meta.addEnchant(Enchantment.THORNS, 50, true);
    	
    	//these two lines make the boots look better in game
    	meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	meta.setUnbreakable(true);
    	
    	helmet.setItemMeta(meta);
    	
    	return helmet;
    }
    
   public ItemStack getChestplate() {
    	
    	ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
    	ItemMeta meta = chestplate.getItemMeta(); //item meta is basically what you can do with the boots the name the enchantments etc...
    	
    	meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + ChatColor.ITALIC+ "PIG" + ChatColor.GOLD + "" + ChatColor.BOLD + " bodyarmour ♜");
    	
    	List<String> lore = new ArrayList<String>(); //list of strings
    	lore.add(""); //adds a space to the lore
    	lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "A chestplate made for only the greatest of PIG gods");
    	meta.setLore(lore); //takes the list of strings as the lore 
    	
    	meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 50, true); 
    	meta.addEnchant(Enchantment.PROTECTION_FALL, 50, true);
    	meta.addEnchant(Enchantment.PROTECTION_FIRE, 50, true);
    	meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 50, true);
    	meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 50, true);
    	meta.addEnchant(Enchantment.THORNS, 50, true);
    	
    	//these two lines make the boots look better in game
    	meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	meta.setUnbreakable(true);
    	
    	chestplate.setItemMeta(meta);
    	
    	return chestplate;
    }
   
   public ItemStack getLeggings() {
   	
   	ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
   	ItemMeta meta = leggings.getItemMeta(); //item meta is basically what you can do with the boots the name the enchantments etc...
   	
   	meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + ChatColor.ITALIC+ "PIG" + ChatColor.GOLD + "" + ChatColor.BOLD + " pants ♞");
   	
   	List<String> lore = new ArrayList<String>(); //list of strings
   	lore.add(""); //adds a space to the lore
   	lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "A pair of pants made for only the greatest of PIG gods");
   	meta.setLore(lore); //takes the list of strings as the lore 
   	
   	meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 50, true); 
   	meta.addEnchant(Enchantment.PROTECTION_FALL, 50, true);
   	meta.addEnchant(Enchantment.PROTECTION_FIRE, 50, true);
   	meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 50, true);
   	meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 50, true);
   	meta.addEnchant(Enchantment.THORNS, 50, true);
   	
   	//these two lines make the boots look better in game
   	meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
   	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
   	meta.setUnbreakable(true);
   	
   	leggings.setItemMeta(meta);
   	
   	return leggings;
   }
	
	public void createInvGodTools() { //creates the actual shop 
		invGodTools = Bukkit.createInventory(null,18, ChatColor.RED + "" + ChatColor.BOLD + "GodTools");
		
		ItemStack item = new ItemStack(bow());
		ItemMeta meta = item.getItemMeta();
		
		//items in shop
		meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "God Bow");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "" + ChatColor.BOLD + "Details: " + ChatColor.RED + "Snipe all your enemies like a god");
		meta.setLore(lore);
		item.setItemMeta(meta);
		invGodTools.setItem(0, item);
		
		item = new ItemStack(sword());
		meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "God Sword");
		lore.set(0, ChatColor.GRAY + "" + ChatColor.BOLD + "Details: " + ChatColor.RED + "Slice em up");
		meta.setLore(lore);
		item.setItemMeta(meta);
		invGodTools.setItem(1, item);
		
		item = new ItemStack(pickaxe());
		meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "God Pickaxe");
		lore.set(0, ChatColor.GRAY + "" + ChatColor.BOLD + "Details: " + ChatColor.RED + "Slice them ores ");
		meta.setLore(lore);
		item.setItemMeta(meta);
		invGodTools.setItem(2, item);
		
		item = new ItemStack(stick());
		meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "God Stick");
		lore.set(0, ChatColor.GRAY + "" + ChatColor.BOLD + "Details: " + ChatColor.RED + "Slap em up");
		meta.setLore(lore);
		item.setItemMeta(meta);
		invGodTools.setItem(3, item);
		
		item = new ItemStack(getHelmet());
		meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "God Boots");
		lore.set(0, ChatColor.GRAY + "" + ChatColor.BOLD + "Details: " + ChatColor.RED + "Nice Hat!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		invGodTools.setItem(5, item);
		
		item = new ItemStack(getChestplate());
		meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "God Shirt");
		lore.set(0, ChatColor.GRAY + "" + ChatColor.BOLD + "Details: " + ChatColor.RED + "Cover UP");
		meta.setLore(lore);
		item.setItemMeta(meta);
		invGodTools.setItem(6, item);
		
		item = new ItemStack(getLeggings());
		meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "God Leggings");
		lore.set(0, ChatColor.GRAY + "" + ChatColor.BOLD + "Details: " + ChatColor.RED + "Mouse back into the house");
		meta.setLore(lore);
		item.setItemMeta(meta);
		invGodTools.setItem(7, item);
		
		item = new ItemStack(getItem());
		meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "God Boots");
		lore.set(0, ChatColor.GRAY + "" + ChatColor.BOLD + "Details: " + ChatColor.RED + "Jump Around");
		meta.setLore(lore);
		item.setItemMeta(meta);
		invGodTools.setItem(8, item);
		
		lore.clear();
		item.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Close Shop");
		meta.setLore(lore);
		item.setItemMeta(meta);
		invGodTools.setItem(13, item);

	}
}
