package hmmhmmmm.worldprotect.listener;

import hmmhmmmm.worldprotect.WorldProtect;
import hmmhmmmm.worldprotect.data.Language;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerBedEnterEvent;
import cn.nukkit.event.player.PlayerBucketEmptyEvent;
import cn.nukkit.event.player.PlayerBucketFillEvent;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerEatFoodEvent;
import cn.nukkit.event.player.PlayerFoodLevelChangeEvent;
import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.event.player.PlayerTeleportEvent;
import cn.nukkit.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlayerListener implements Listener{
   private WorldProtect plugin;
   private Language lang;

   public PlayerListener(WorldProtect plugin){
      this.plugin = plugin;
      this.lang = (Language) plugin.getLanguage();
   }
   public WorldProtect getPlugin(){
      return plugin;
   }
   public String getPrefix(){
      return plugin.getPrefix();
   }
   
   @EventHandler
   public void onPlayerBedEnter(PlayerBedEnterEvent event){
      Player player = event.getPlayer();
      Set<String> wps = plugin.getWorld();
      for(String worldname : wps){
         if(plugin.isFlag(worldname, "bedenter")){
            List<String> whitelist = new ArrayList<String>(
               plugin.getWhiteList()
            );
            if(!whitelist.contains(player.getName().toLowerCase())
               && player.getLevel().getFolderName().equals(worldname)
               && plugin.getFlagBoolean(worldname, "bedenter")
            ){
               event.setCancelled(true);
               if (!plugin.getFlagBoolean(worldname, "show-message")) return;
               player.sendPopup(getPrefix()+" "+lang.getTranslate(
                  "listener.bedenter.error1"
               ));
            }
         }
      }
   }
   
   @EventHandler
   public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event){
      Player player = event.getPlayer();
      Set<String> wps = plugin.getWorld();
      for(String worldname : wps){
         if(plugin.isFlag(worldname, "bucketempty")){
            List<String> whitelist = new ArrayList<String>(
               plugin.getWhiteList()
            );
            if(!whitelist.contains(player.getName().toLowerCase())
               && player.getLevel().getFolderName().equals(worldname)
               && plugin.getFlagBoolean(worldname, "bucketempty")
            ){
               event.setCancelled(true);
               if (!plugin.getFlagBoolean(worldname, "show-message")) return;
               player.sendPopup(getPrefix()+" "+lang.getTranslate(
                  "listener.bucketempty.error1"
               ));
            }
         }
      }
   }
  
   @EventHandler
   public void onPlayerBucketFill(PlayerBucketFillEvent event){
      Player player = event.getPlayer();
      Set<String> wps = plugin.getWorld();
      for(String worldname : wps){
         if(plugin.isFlag(worldname, "bucketfill")){
            List<String> whitelist = new ArrayList<String>(
               plugin.getWhiteList()
            );
            if(!whitelist.contains(player.getName().toLowerCase())
               && player.getLevel().getFolderName().equals(worldname)
               && plugin.getFlagBoolean(worldname, "bucketfill")
            ){
               event.setCancelled(true);
               if (!plugin.getFlagBoolean(worldname, "show-message")) return;
               player.sendPopup(getPrefix()+" "+lang.getTranslate(
                  "listener.bucketfill.error1"
               ));
            }
         }
      }
   }
      
   @EventHandler
   public void onPlayerChat(PlayerChatEvent event){
      Player player = event.getPlayer();
      Set<String> wps = plugin.getWorld();
      for(String worldname : wps){
         if(plugin.isFlag(worldname, "chat")){
            List<String> whitelist = new ArrayList<String>(
               plugin.getWhiteList()
            );
            if(!whitelist.contains(player.getName().toLowerCase())
               && player.getLevel().getFolderName().equals(worldname)
               && plugin.getFlagBoolean(worldname, "chat")
            ){
               event.setCancelled(true);
               if (!plugin.getFlagBoolean(worldname, "show-message")) return;
               player.sendMessage(getPrefix()+" "+lang.getTranslate(
                  "listener.chat.error1"
               ));
            }
         }
      }
   }
      
   @EventHandler
   public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event){
      Player player = event.getPlayer();
      String message = event.getMessage();
      Set<String> wps = plugin.getWorld();
      for(String worldname : wps){
         if(plugin.isFlag(worldname, "bancmd")){
            List<String> cmdLists = plugin.getFlagList(
               worldname,
               "bancmd"
            );
            if(!(cmdLists.size() == 0)){
               for(String cmdList : cmdLists){
                  List<String> whitelist = new ArrayList<String>(
                     plugin.getWhiteList()
                  );
                  if(!whitelist.contains(player.getName().toLowerCase())
                     && player.getLevel().getFolderName().equals(worldname)
                     && message.startsWith(cmdList)
                  ){
                     event.setCancelled(true);
                     if (!plugin.getFlagBoolean(worldname, "show-message")) return;
                     player.sendMessage(getPrefix()+" "+lang.getTranslate(
                        "listener.bancmd.error1",
                        new String[]{message}
                     ));
                  }
               }
            }
         }
      }
   }
   
   @EventHandler
   public void onPlayerDeath(PlayerDeathEvent event){
      Player player = event.getEntity();
      Set<String> wps = plugin.getWorld();
      if(player instanceof Player){
         for(String worldname : wps){
            if(plugin.isFlag(worldname, "keepinventory")){
               if(player.getLevel().getFolderName().equals(worldname)
                  && plugin.getFlagBoolean(worldname, "keepinventory")
               ){
                  event.setKeepInventory(true);
               }
            }
            if(plugin.isFlag(worldname, "keepexperience")){
               if(player.getLevel().getFolderName().equals(worldname)
                  && plugin.getFlagBoolean(worldname, "keepexperience")
               ){
                  event.setKeepExperience(true);
               }
            }
         }
      }
   }
   
   @EventHandler
   public void onPlayerEatFood(PlayerEatFoodEvent event){
      Player player = event.getPlayer();
      Set<String> wps = plugin.getWorld();
      for(String worldname : wps){
         if(plugin.isFlag(worldname, "eatfood")){
            List<String> whitelist = new ArrayList<String>(
               plugin.getWhiteList()
            );
            if(!whitelist.contains(player.getName().toLowerCase())
               && player.getLevel().getFolderName().equals(worldname)
               && plugin.getFlagBoolean(worldname, "eatfood")
            ){
               event.setCancelled(true);
               if (!plugin.getFlagBoolean(worldname, "show-message")) return;
               player.sendPopup(getPrefix()+" "+lang.getTranslate(
                  "listener.eatfood.error1"
               ));
            }
         }
      }
   }
   
   @EventHandler
   public void onPlayerFoodLevelChange(PlayerFoodLevelChangeEvent event){
      Player player = event.getPlayer();
      Set<String> wps = plugin.getWorld();
      for(String worldname : wps){
         if(plugin.isFlag(worldname, "food")){
            if(player.getLevel().getFolderName().equals(worldname)
               && plugin.getFlagBoolean(worldname, "food")
            ){
               event.setCancelled(true);
            }
         }
      }
   }
   
   @EventHandler
   public void onPlayerDropItem(PlayerDropItemEvent event){
      Player player = event.getPlayer();
      Set<String> wps = plugin.getWorld();
      for(String worldname : wps){
         if(plugin.isFlag(worldname, "item-drop")){
            List<String> whitelist = new ArrayList<String>(
               plugin.getWhiteList()
            );
            if(!whitelist.contains(player.getName().toLowerCase())
               && player.getLevel().getFolderName().equals(worldname)
               && plugin.getFlagBoolean(worldname, "item-drop")
            ){
               event.setCancelled(true);
               if (!plugin.getFlagBoolean(worldname, "show-message")) return;
               player.sendPopup(getPrefix()+" "+lang.getTranslate(
                  "listener.item-drop.error1"
               ));
            }
         }
      }
   }
   
   @EventHandler
   public void onPlayerInteract(PlayerInteractEvent event){
      Player player = event.getPlayer();
      Item item = event.getItem();
      Set<String> wps = plugin.getWorld();
      List<String> whitelist;
      for(String worldname : wps){
         if(plugin.isFlag(worldname, "interact")){
            whitelist = new ArrayList<String>(
               plugin.getWhiteList()
            );
            if(!whitelist.contains(player.getName().toLowerCase())
               && player.getLevel().getFolderName().equals(worldname)
               && plugin.getFlagBoolean(worldname, "interact")
            ){
               event.setCancelled(true);
               if (!plugin.getFlagBoolean(worldname, "show-message")) return;
               player.sendPopup(getPrefix()+" "+lang.getTranslate(
                  "listener.interact.error1"
               ));
            }
         }
         if(plugin.isFlag(worldname, "banitem")){
            List<String> itemLists = plugin.getFlagList(
               worldname,
               "banitem"
            );
            if(!(itemLists.size() == 0)){
               for(String itemList : itemLists){
                  String[] items = itemList.split(":");
                  int id = Integer.parseInt(items[0]);
                  int damage = Integer.parseInt(items[1]);
                  whitelist = new ArrayList<String>(
                     plugin.getWhiteList()
                  );
                  if(!whitelist.contains(player.getName().toLowerCase())
                     && player.getLevel().getFolderName().equals(worldname)
                     && item.getId() == id 
                     && item.getDamage() == damage
                  ){
                     event.setCancelled(true);
                     if (!plugin.getFlagBoolean(worldname, "show-message")) return;
                     player.sendPopup(getPrefix()+" "+lang.getTranslate(
                        "listener.banitem.error1",
                        new String[]{item.getName()}
                     ));
                  }
               }
            }
         }
      }
   }
   
   @EventHandler
   public void onPlayerMoveEvent(PlayerMoveEvent event){
      Player player = event.getPlayer();
      Set<String> wps = plugin.getWorld();
      for(String worldname : wps){
         if(plugin.isFlag(worldname, "move")){
            List<String> whitelist = new ArrayList<String>(
               plugin.getWhiteList()
            );
            if(!whitelist.contains(player.getName().toLowerCase())
               && player.getLevel().getFolderName().equals(worldname)
               && plugin.getFlagBoolean(worldname, "move")
            ){
               event.setCancelled(true);
               if (!plugin.getFlagBoolean(worldname, "show-message")) return;
               player.sendPopup(getPrefix()+" "+lang.getTranslate(
                  "listener.move.error1"
               ));
            }
         }
      }
   }
   
   @EventHandler
   public void onPlayerTeleport(PlayerTeleportEvent event){
      Player player = event.getPlayer();
      Set<String> wps = plugin.getWorld();
      for(String worldname : wps){
         if(plugin.isFlag(worldname, "teleport")){
            List<String> whitelist = new ArrayList<String>(
               plugin.getWhiteList()
            );
            if(!whitelist.contains(player.getName().toLowerCase())
               && player.getLevel().getFolderName().equals(worldname)
               && plugin.getFlagBoolean(worldname, "teleport")
            ){
               event.setCancelled(true);
               if (!plugin.getFlagBoolean(worldname, "show-message")) return;
               player.sendPopup(getPrefix()+" "+lang.getTranslate(
                  "listener.teleport.error1"
               ));
            }
         }
      }
   }
   
}