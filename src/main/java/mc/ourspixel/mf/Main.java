package mc.ourspixel.mf;

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
		Bukkit.getLogger().info("金币&经验飞行插件已经启动.");
        saveDefaultConfig();
        instance = this;
        if (!initVault()) {
			Bukkit.getLogger().info("未找到经济插件.插件自动关闭.");
			Bukkit.getPluginManager().disablePlugin(this);
		}
	}
	public static Main getInstance() {
		return instance;
	}
	private boolean initVault(){
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
