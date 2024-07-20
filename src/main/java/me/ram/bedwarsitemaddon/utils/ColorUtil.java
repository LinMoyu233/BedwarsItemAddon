package me.ram.bedwarsitemaddon.utils;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class ColorUtil {

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> colorList(List<String> list) {
        List<String> colorList = new ArrayList<>();
        for (String l : list) {
            colorList.add(ChatColor.translateAlternateColorCodes('&', l));
        }
        return colorList;
    }

    public static String removeColor(String s) {
        return ChatColor.stripColor(s);
    }

    public static List<String> removeListColor(List<String> list) {
        List<String> colorList = new ArrayList<>();
        for (String l : list) {
            colorList.add(ChatColor.stripColor(l));
        }
        return colorList;
    }

    public static Material getWoolMaterial(DyeColor dyeColor) {
        switch (dyeColor) {
            case WHITE:
                return Material.WHITE_WOOL;
            case ORANGE:
                return Material.ORANGE_WOOL;
            case MAGENTA:
                return Material.MAGENTA_WOOL;
            case LIGHT_BLUE:
                return Material.LIGHT_BLUE_WOOL;
            case YELLOW:
                return Material.YELLOW_WOOL;
            case LIME:
                return Material.LIME_WOOL;
            case PINK:
                return Material.PINK_WOOL;
            case GRAY:
                return Material.GRAY_WOOL;
            case LIGHT_GRAY:
                return Material.LIGHT_GRAY_WOOL;
            case CYAN:
                return Material.CYAN_WOOL;
            case PURPLE:
                return Material.PURPLE_WOOL;
            case BLUE:
                return Material.BLUE_WOOL;
            case BROWN:
                return Material.BROWN_WOOL;
            case GREEN:
                return Material.GREEN_WOOL;
            case RED:
                return Material.RED_WOOL;
            case BLACK:
                return Material.BLACK_WOOL;
            default:
                return Material.WHITE_WOOL; // 默认返回白色羊毛
        }
    }

    public static Material getCarpetMaterial(DyeColor color) {
        switch (color) {
            case ORANGE:
                return Material.ORANGE_CARPET;
            case MAGENTA:
                return Material.MAGENTA_CARPET;
            case LIGHT_BLUE:
                return Material.LIGHT_BLUE_CARPET;
            case YELLOW:
                return Material.YELLOW_CARPET;
            case LIME:
                return Material.LIME_CARPET;
            case PINK:
                return Material.PINK_CARPET;
            case GRAY:
                return Material.GRAY_CARPET;
            case LIGHT_GRAY:
                return Material.LIGHT_GRAY_CARPET;
            case CYAN:
                return Material.CYAN_CARPET;
            case PURPLE:
                return Material.PURPLE_CARPET;
            case BLUE:
                return Material.BLUE_CARPET;
            case BROWN:
                return Material.BROWN_CARPET;
            case GREEN:
                return Material.GREEN_CARPET;
            case RED:
                return Material.RED_CARPET;
            case BLACK:
                return Material.BLACK_CARPET;
            case WHITE:
            default:
                return Material.WHITE_CARPET;
        }
    }
}
