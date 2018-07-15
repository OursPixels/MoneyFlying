package mc.ourspixel.mf;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author LocyDragon
 */
public class CommandsMain implements CommandExecutor {
	private static final String reloadCmd = "reload";
	private static final String adminPermission = "MoneyFlying.admin";
	private static final String moneyCmd = "money";
	private static final String expCmd = "exp";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("控制台是不能用这个指令哒~");
			return false;
		}
		if (args[0].equalsIgnoreCase(reloadCmd) && sender.hasPermission(adminPermission)) {
			sender.sendMessage(StringUtil.me("&a&l>>> &c正在重载插件中..."));
			Main.getInstance().saveConfig();
			Main.getInstance().reloadConfig();
			sender.sendMessage(StringUtil.me("&a&l>>> &c重载插件完成..."));
		} else if (args[0].equalsIgnoreCase(moneyCmd)) {
			sender.sendMessage(StringUtil.me(Main.getInstance().getConfig().getString("RunMoneyNotice")));
			MoneyFlyingAPI.startByMoney((Player)sender);
		} else if (args[0].equalsIgnoreCase(expCmd)) {
			sender.sendMessage(StringUtil.me(Main.getInstance().getConfig().getString("RunExpNotice")));
			MoneyFlyingAPI.startByExp((Player)sender);
		}
		return true;
	}
}
