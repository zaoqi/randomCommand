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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import static zaoqi.RandomCommand.API.cut;
import static zaoqi.RandomCommand.API.readFile;

/**
 *
 * @author zaoqi
 */
public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            getLogger().info(Arrays.toString(choose(changeAdder(cut(readFile(System.getProperty("user.dir") + File.separator + "luck.conf"))))));
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        int r = Math.abs((int) Math.random()) % getAdderCount(src);
        for (String[] line : src) {
            if (Integer.parseInt(line[0]) <= r && r < Integer.parseInt(line[1])) {
                return line;
            }
        }
        throw new Exception("!");
    }
}
