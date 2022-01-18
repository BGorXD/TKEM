package hu.BGorXD.tkem1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener, CommandExecutor {
	
	HashMap<Player,Entity> target = new HashMap<Player, Entity>();
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onUse(PlayerInteractEvent e) {
		Player player = e.getPlayer();
				if(player.getItemInHand().getType() != Material.AIR) {
					if(player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§c |- §6Chunk elszívó mikkentyûizé §c -|")) {
						Chunk loc = player.getLocation().getChunk();
						e.setCancelled(true);
						
						int chunkx = loc.getX();
						int chunkz = loc.getZ();
						
						int x = 16*chunkx;
						int max = x + 16;
						while(x < max) {
							int y = 0;
							while(y < 255) {
								int z = 16*chunkz;
								int zmax = z + 17;
								while(z < zmax) {
									Block b = new Location(player.getWorld(), x, y, z).getBlock();
									b.setType(Material.AIR);
									z++;
								}
								y++;
							}
							x++;
					}
				
			}
		}
				
				if(player.getItemInHand().getType() != Material.AIR) {
					if(player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§c |- §eÕrláng por §c -|")) {
						Location loc = player.getLocation();
						int x = -1;
						int z = -1;
						for(int i = 0;i<9; i++) {
							if(i%3 == 0) {
								x++;
								z=-1;
							}else {
							z++;
							}
							loc.setX(player.getLocation().getBlockX()+x);
							loc.setZ(player.getLocation().getBlockZ()+z);
							loc.getBlock().setType(Material.FIRE);
							}
						
					}
				}
				
				if(player.getItemInHand().getType() != Material.AIR) {
					if(player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§c |- §6Ollóóóóó §c -|")) {
						World world = player.getWorld();
						
						Location loc = player.getLocation();
						int locX = loc.getBlockX()+10;
						int locY = loc.getBlockY()+10;
						int locZ = loc.getBlockZ()+10;
						
						int locX1 = loc.getBlockX()-10;
						int locY1 = loc.getBlockY()-10;
						int locZ1 = loc.getBlockZ()-10;
						
				        Location edgeMin = new Location(world, locX, locY, locZ);
				        Location edgeMax = new Location(world, locX1, locY1, locZ1);

				        List<Material> removedMaterials = new ArrayList<Material>();
				        removedMaterials.add(Material.LEAVES);
				        removedMaterials.add(Material.LEAVES_2);

				        for (int x = edgeMin.getBlockX(); x <= edgeMax.getBlockX(); x ++) {
				            for (int y = edgeMin.getBlockY(); y <= edgeMax.getBlockY(); y ++) {
				                for (int z = edgeMin.getBlockZ(); z <= edgeMax.getBlockZ(); z ++) {
				                    Block b = world.getBlockAt(x, y, z);
				                    if(removedMaterials.contains(b.getType())){
				                        b.setType(Material.AIR);
				                    }
				                }
				            }
				        }
					}
				}
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("chunkelszivo")) {
			Player p = (Player) sender;
			ItemStack is = new ItemStack(Material.MOB_SPAWNER,1);
			ItemMeta meta = is.getItemMeta();
			meta.setDisplayName("§c |- §6Chunk elszívó mikkentyûizé §c -|");
			is.setItemMeta(meta);
			p.getInventory().addItem(is);
		}
		if(label.equalsIgnoreCase("orlangpor")) {
			Player p = (Player) sender;
			ItemStack is1 = new ItemStack(Material.BLAZE_POWDER,1);
			ItemMeta meta1 = is1.getItemMeta();
			meta1.setDisplayName("§c |- §eÕrláng por §c -|");
			is1.setItemMeta(meta1);
			p.getInventory().addItem(is1);
		}
		if(label.equalsIgnoreCase("ollo")) {
			Player p = (Player) sender;
			ItemStack is2 = new ItemStack(Material.SHEARS);
			ItemMeta meta2 = is2.getItemMeta();
			meta2.setDisplayName("§c |- §6Ollóóóóó §c -|");
			meta2.setUnbreakable(true);
			is2.setItemMeta(meta2);
			p.getInventory().addItem(is2);
		}
		return false;
	}
}