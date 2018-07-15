package mc.ourspixel.mf.anti;


import mc.ourspixel.mf.MoneyFlyingAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author LocyDragon
 */
public class AntiListener implements Listener {
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if (MoneyFlyingAPI.isPlayerFlyingWithExp(e.getPlayer())) {
			MoneyFlyingAPI.stopByExp(e.getPlayer());
		}
		if (MoneyFlyingAPI.isPlayerFlyingWithMoney(e.getPlayer())) {
			MoneyFlyingAPI.stopByMoney(e.getPlayer());
		}
	}

	@EventHandler
	public void onKickByServer(PlayerKickEvent e) {
		if (MoneyFlyingAPI.isPlayerFlyingWithExp(e.getPlayer())) {
			MoneyFlyingAPI.stopByExp(e.getPlayer());
		}
		if (MoneyFlyingAPI.isPlayerFlyingWithMoney(e.getPlayer())) {
			MoneyFlyingAPI.stopByMoney(e.getPlayer());
		}
	}
}
