package com.adamuseq.aqmousestone.listeners;

import com.adamuseq.aqmousestone.Main;
import com.adamuseq.aqmousestone.configs.Database;
import com.adamuseq.aqmousestone.configs.Messages;
import com.adamuseq.aqmousestone.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MouseStoneListeners implements Listener {

    private Main main;

    public MouseStoneListeners(Main main){
        this.main = main ;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void setStone(PlayerInteractEvent e) {
        if(e.isCancelled()) return;
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.STONE) && e.getPlayer().getItemInHand() != null && (e.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_PICKAXE) || e.getPlayer().getItemInHand().getType().equals(Material.IRON_PICKAXE) || e.getPlayer().getItemInHand().getType().equals(Material.STONE_PICKAXE) || e.getPlayer().getItemInHand().getType().equals(Material.GOLD_PICKAXE) || e.getPlayer().getItemInHand().getType().equals(Material.WOOD_PICKAXE))){
            if(Database.LIST_DIGINGPLAYERS.contains(e.getPlayer())){
                Database.LIST_DIGINGPLAYERS.remove(e.getPlayer());
                Database.save();
                ChatUtils.sendMessage(e.getPlayer(), Messages.MESSAGES_OFFMESSAGE);
            }else{
                Database.LIST_DIGINGPLAYERS.add(e.getPlayer());
                Database.save();
                ChatUtils.sendMessage(e.getPlayer(), Messages.MESSAGES_ONMESSAGE);
            }
        }
    }

    @EventHandler
    public void digStone(final BlockBreakEvent e){
        final Block block = e.getBlock();
        if(e.isCancelled()) return;
        if(Database.LIST_DIGINGPLAYERS.contains(e.getPlayer()) && block.getType().equals(Material.STONE)){
            new BukkitRunnable() {
                public void run() {
                    if(e.getBlock().getLocation().getBlock().getType() != Material.AIR || e.getBlock().getLocation().getBlock() != null) {

                        block.setType(Material.STONE);
                    }
                }
            }.runTaskLater((Plugin) Main.getInst(), 30L);
        }
    }
}
