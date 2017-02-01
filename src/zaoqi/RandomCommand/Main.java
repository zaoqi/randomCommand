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
import java.io.IOException;
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
            getLogger().info(Arrays.deepToString(cut(readFile(System.getProperty("user.dir")+File.separator+"luck.conf"))));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onDisable() {
    }
}
