package mc.ourspixel.mf;


import org.bukkit.Bukkit;
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
		who.setFlying(false);
		if (!isPlayerFlyingWithExp(who)) {
			return false;
		}
		BukkitRunnable runnable = expCache.get(who.getName());
		runnable.cancel();
		expCache.remove(who.getName());
		return true;
	}
	public static boolean stopByMoney(Player who) {
		who.setFlying(false);
		if (!isPlayerFlyingWithMoney(who)) {
			return false;
		}
		BukkitRunnable runnable = moneyCache.get(who.getName());
		runnable.cancel();
		moneyCache.remove(who.getName());
		return true;
	}
	public static void startByMoney(Player who) {
		BukkitRunnable runnable = new BukkitRunnable() {
			@Override
			public void run() {
				int needMoney = Main.getInstance().getConfig().getInt("MoneyPerMinute");
				if (Main.economy.has(who, needMoney)) {
					if (!who.isFlying()) {
						who.setFlying(true);
					}
					Main.economy.depositPlayer(who, needMoney);
				} else {
					who.setFlying(false);
					stopByMoney(who);
					who.sendMessage(StringUtil.me(Main.getInstance().getConfig().getString("NoMoneyNotice")));
				}
			}
		};
		moneyCache.put(who.getName(), runnable);
        runnable.runTaskTimer(Main.getInstance(), 0, 20 * 60L);
	}
	public static void startByExp(Player who) {
		BukkitRunnable runnable = new BukkitRunnable() {
			@Override
			public void run() {
				int needExp = Main.getInstance().getConfig().getInt("ExpPerMinute");
				if (Bukkit.getPlayer(who.getName()).getTotalExperience() >= needExp) {
					if (!who.isFlying()) {
						who.setFlying(true);
					}
					who.setTotalExperience(who.getTotalExperience() - needExp);
				} else {
					who.setFlying(false);
					stopByExp(who);
					who.sendMessage(StringUtil.me(Main.getInstance().getConfig().getString("NoExpNotice")));
				}
			}
		};
        expCache.put(who.getName(), runnable);
		runnable.runTaskTimer(Main.getInstance(), 0, 20 * 60L);
	}
}
