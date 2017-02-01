/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        String s = null;
        while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
            result.append(System.lineSeparator() + s);
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
        String[] lines = src.split("\n");
        List<String> list = Arrays.asList(lines);
        return (String[][]) list.stream().map(line -> line.split(":"))
                .collect(Collectors.toList()).toArray();
    }
}
