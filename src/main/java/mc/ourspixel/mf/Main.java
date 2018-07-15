package mc.ourspixel.mf;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Ourspixel
 */
public class Main extends JavaPlugin {
	private static Main instance = null;
	@Override
	public void onEnable() {
		Bukkit.getLogger().info("金币&经验飞行插件已经启动.");
        saveDefaultConfig();
        instance = this;
	}
	public static Main getInstance() {
		return instance;
	}
}
