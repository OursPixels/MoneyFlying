package mc.ourspixel.mf;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class MoneyFlyingAPI {
	public static HashMap<String,BukkitRunnable> expCache = new HashMap<>();
	public static HashMap<String,BukkitRunnable> moneyCache = new HashMap<>();
	public static boolean isPlayerFlyingWithExp(Player who) {
		return expCache.keySet().contains(who.getName());
	}
	public static boolean isPlayerFlyingWithMoney(Player who) {
		return moneyCache.keySet().contains(who.getName());
	}
	public static boolean stopByExp(Player who) {
		if (!isPlayerFlyingWithExp(who)) {
			return false;
		}
		BukkitRunnable runnable = expCache.get(who.getName());
		runnable.cancel();
		expCache.remove(who.getName());
		return true;
	}
	public static boolean stopByMoney(Player who) {
		if (!isPlayerFlyingWithMoney(who)) {
			return false;
		}
		BukkitRunnable runnable = moneyCache.get(who.getName());
		runnable.cancel();
		moneyCache.remove(who.getName());
		return true;
	}
}
