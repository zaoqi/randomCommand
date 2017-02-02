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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author zaoqi
 */
public class API {

    public static boolean takeMoney(JavaPlugin that, String player, int amount) {
        return getPlayerPoints(that).take(player, amount);
    }

    private static PlayerPointsAPI getPlayerPoints(JavaPlugin that) {
        return PlayerPoints.class.cast(
                that.getServer().getPluginManager().getPlugin("PlayerPoints")
        ).getAPI();
    }

    public static String readFile(String path) throws IOException {
        beFile(path);
        File file = new File(path);
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
        String s;
        while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
            result.append(System.lineSeparator()).append(s);
        }
        br.close();
        return result.toString();
    }

    public static void beFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public static String[][] cut(String src) {
        List<String[]> list = Arrays.asList(src.split("\n")).stream()
                .map(line -> line.split(":"))
                .filter(line -> line.length > 1)
                .collect(Collectors.toList());
        return list.toArray(new String[list.size()][]);
    }

    public static boolean runCommand(String command) {
        return Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
    }
}
