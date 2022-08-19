package cn.nukkit.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.lang.TranslationContainer;
import org.jetbrains.annotations.NotNull;

/**
 * Created on 2015/11/11 by xtypr.
 * Package cn.nukkit.command.defaults in project Nukkit .
 */
public class ListCommand extends VanillaCommand {

    public ListCommand(String name) {
        super(name, "%nukkit.command.list.description", "%commands.players.usage");
        this.setPermission("nukkit.command.list");
        this.commandParameters.clear();
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, String @NotNull [] args) {
        if (!this.testPermission(sender)) {
            return true;
        }
        StringBuilder online = new StringBuilder();
        int onlineCount = 0;
        for (Player player : sender.getServer().getOnlinePlayers().values()) {
            if (player.isOnline() && (!(sender instanceof Player) || ((Player) sender).canSee(player))) {
                online.append(player.getDisplayName()).append(", ");
                ++onlineCount;
            }
        }

        if (online.length() > 0) {
            online = new StringBuilder(online.substring(0, online.length() - 2));
        }

        sender.sendMessage(new TranslationContainer("commands.players.list",
                String.valueOf(onlineCount), String.valueOf(sender.getServer().getMaxPlayers())));
        sender.sendMessage(online.toString());
        return true;
    }
}
