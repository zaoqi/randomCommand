//RandomCommand
//Copyright (C) 2017  zaoqi
//This program is free software: you can redistribute it and/or modify
//it under the terms of the GNU Affero General Public License as published
//by the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU Affero General Public License for more details.
//You should have received a copy of the GNU Affero General Public License
//along with this program.  If not, see <http://www.gnu.org/licenses/>.
package zaoqi.RandomCommand;

import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import static zaoqi.RandomCommand.API.cut;
import static zaoqi.RandomCommand.API.readFile;
import static zaoqi.RandomCommand.API.runCommand;
import static zaoqi.RandomCommand.API.takeMoney;

/**
 *
 * @author zaoqi
 */
public class Main extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    private static String[] addHeadString(String head, String[] array) {
        String[] r = new String[array.length + 1];
        r[0] = head;
        System.arraycopy(array, 0, r, 1, array.length);
        return r;
    }

    public static String[][] changeAdder(String[][] src) {
        int c = 0;
        for (int i = 0; i < src.length; i++) {
            src[i][0] = String.valueOf(Math.abs(Integer.parseInt(src[i][0])) + c);
            src[i] = addHeadString(String.valueOf(c), src[i]);
            c = Integer.parseInt(src[i][1]);
        }
        return src;
    }

    public static int getAdderCount(String[][] src) {
        return Integer.parseInt(src[src.length - 1][1]);
    }

    public static String[] choose(String[][] src) throws Exception {
        int r = Math.abs((int) (Math.random() * 123456789)) % getAdderCount(src);
        for (String[] line : src) {
            if (Integer.parseInt(line[0]) <= r && r < Integer.parseInt(line[1])) {
                return line;
            }
        }
        throw new Exception("!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equals("sunli")) {
            return false;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return false;
        }
        Player player = (Player) sender;
        if (!takeMoney(this, player.getUniqueId(), 50)) {
            sender.sendMessage("You don't have enough money.");
            return false;
        }
        try {
            String[] commands = choose(changeAdder(cut(
                    readFile(System.getProperty("user.dir") + File.separator + "luck.conf")
            )));
            for (int i = 2; i < commands.length; i++) {
                runCommand(commands[i]);
            }
        } catch (Exception ex) {
            getLogger().info(ex.toString());
        }
        return true;
    }
}
