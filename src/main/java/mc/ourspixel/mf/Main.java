package mc.ourspixel.mf;

import mc.ourspixel.mf.anti.AntiListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Ourspixel
 */
public class Main extends JavaPlugin {
	public static Economy economy = null;
	private static Main instance = null;

	@Override
	public void onEnable() {
		Bukkit.getLogger().info("金币&经验飞行插件正在加载.来自服务器OursPixel");
		saveDefaultConfig();
		Bukkit.getLogger().info("与Vault联通中...");
		if (!initVault()) {
			Bukkit.getLogger().info("未找到经济插件.插件自动关闭.");
			Bukkit.getPluginManager().disablePlugin(this);
		}
		Bukkit.getPluginManager().registerEvents(new AntiListener(), this);
		Bukkit.getLogger().info("注册事件完成!");
		Bukkit.getLogger().info("金币&经验飞行插件加载完成.");
		instance = this;
	}

	public static Main getInstance() {
		return instance;
	}

	private boolean initVault() {
		boolean hasNull = false;
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			if ((economy = economyProvider.getProvider()) == null) {
				hasNull = true;
			}
		}
		return !hasNull;
	}
}
