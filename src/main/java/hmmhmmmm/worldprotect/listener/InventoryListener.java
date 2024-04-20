package hmmhmmmm.worldprotect.listener;


import hmmhmmmm.worldprotect.WorldProtect;
import hmmhmmmm.worldprotect.data.Language;

import cn.nukkit.event.Listener;


public class InventoryListener implements Listener{
   private WorldProtect plugin;
   private Language lang;

   public InventoryListener(WorldProtect plugin){
      this.plugin = plugin;
      this.lang = (Language) plugin.getLanguage();
   }
   public WorldProtect getPlugin(){
      return plugin;
   }
   public String getPrefix(){
      return plugin.getPrefix();
   }
}