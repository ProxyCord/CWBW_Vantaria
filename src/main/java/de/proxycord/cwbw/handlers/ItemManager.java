// This class is created by LookIP
package de.proxycord.cwbw.handlers;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private final ItemStack item;
    private final List<String> lore = new ArrayList();
    private final ItemMeta meta;

    public ItemManager(Material mat, short subid, int amount) {
        this.item = new ItemStack(mat, amount, subid);
        this.meta = this.item.getItemMeta();
    }

    public ItemManager(ItemStack item) {
        this.item = item;
        this.meta = item.getItemMeta();
    }

    public ItemManager(Material mat, short subid) {
        this.item = new ItemStack(mat, 1, subid);
        this.meta = this.item.getItemMeta();
    }

    public ItemManager(Material mat, int amount) {
        this.item = new ItemStack(mat, amount, (short) 0);
        this.meta = this.item.getItemMeta();
    }

    public ItemManager(Material mat) {
        this.item = new ItemStack(mat, 1, (short) 0);
        this.meta = this.item.getItemMeta();
    }

    public ItemManager setAmount(int value) {
        this.item.setAmount(value);
        return this;
    }

    public ItemManager setNoName() {
        this.meta.setDisplayName(" ");
        return this;
    }

    public ItemManager setGlow() {
        this.meta.addEnchant(Enchantment.DURABILITY, 1, true);
        this.meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemManager setData(short data) {
        this.item.setDurability(data);
        return this;
    }

    public ItemManager addLoreLine(String line) {
        this.lore.add(line);
        return this;
    }

    public ItemManager addLoreArray(String[] lines) {
        for (int x = 0; x < lines.length; ++x) {
            this.lore.add(lines[x]);
        }

        return this;
    }

    public ItemManager addLoreAll(List<String> lines) {
        this.lore.addAll(lines);
        return this;
    }

    public ItemManager setDisplayName(String name) {
        this.meta.setDisplayName(name);
        return this;
    }

    public ItemManager getSkullOwner(String owner) {
        ((SkullMeta) this.meta).setOwner(owner);
        return this;
    }

    public ItemManager setColor(Color c) {
        ((LeatherArmorMeta) this.meta).setColor(c);
        return this;
    }

    public ItemManager getBannerColor(DyeColor c) {
        ((BannerMeta) this.meta).setBaseColor(c);
        return this;
    }

    public ItemManager setUnbreakable(boolean value) {
        this.meta.spigot().setUnbreakable(value);
        return this;
    }

    public ItemManager addEnchantment(Enchantment ench, int lvl) {
        this.meta.addEnchant(ench, lvl, true);
        return this;
    }

    public ItemManager addItemFlag(ItemFlag flag) {
        this.meta.addItemFlags(flag);
        return this;
    }

    public ItemManager addLeatherColor(Color c) {
        ((LeatherArmorMeta) this.meta).setColor(c);
        return this;
    }

    public ItemStack build() {
        if (!this.lore.isEmpty()) {
            this.meta.setLore(this.lore);
        }

        this.item.setItemMeta(this.meta);
        return this.item;
    }
}
