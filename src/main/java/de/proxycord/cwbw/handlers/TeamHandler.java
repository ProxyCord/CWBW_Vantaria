// This class is created by LookIP
package de.proxycord.cwbw.handlers;

import de.proxycord.cwbw.CWBW;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.*;

@SuppressWarnings("all")
public class TeamHandler {

    public enum Team {
        RED(DataHandler.team_red),
        BLUE(DataHandler.team_blue),
        GREEN(DataHandler.team_green),
        YELLOW(DataHandler.team_yellow),
        WHITE(DataHandler.team_white),
        BLACK(DataHandler.team_black),
        PINK(DataHandler.team_pink),
        CYAN(DataHandler.team_cyan),
        NONE( "");

        private ItemStack itemstack;
        private String name;

        Team(final String name){
            this.itemstack = itemstack;
            this.name = name;
        }

        public ItemStack getItemStack() {
            if(itemstack == null){
                itemstack = new ItemManager(Material.LEATHER_CHESTPLATE).setDisplayName(this.getName()).build();
            }

            return itemstack;
        }

        public String getName() {
            return name;
        }
    }

    public Map<Team, List<Player>> teams = new HashMap<>();
    public Inventory teaminventory;

    public void load(){
        teaminventory = Bukkit.createInventory(null, 5 * 9, "Ivn");

        teaminventory.setItem(10, Team.RED.getItemStack());
        teaminventory.setItem(12, Team.BLUE.getItemStack());
        teaminventory.setItem(14, Team.GREEN.getItemStack());
        teaminventory.setItem(16, Team.YELLOW.getItemStack());
        teaminventory.setItem(28, Team.WHITE.getItemStack());
        teaminventory.setItem(30, Team.BLACK.getItemStack());
        teaminventory.setItem(32, Team.PINK.getItemStack());
        teaminventory.setItem(34, Team.CYAN.getItemStack());
    }

    public enum TeamResult {
        TRUE,
        FULL,
        ALREADYIN;
    }

    public void updateInventory(final Team now, final Team old) {
        final Integer slot = getTeamItemSlot(now);

        ItemStack itemstack = teaminventory.getItem(slot);

        final List<Player> players = getPlayersOfTeam(now);
        final Integer teamsize = players.size();

        if(teamsize == 0) {
            itemstack = now.getItemStack();
        } else {
            itemstack.setAmount(teamsize);
        }

        String playerstring = "§7" + getPlayersInString(players, "§7,§7");

        final List<String> lore = Arrays.asList(playerstring.split(","));

        new ItemManager(itemstack).addLoreAll(lore).build();

        teaminventory.setItem(slot, itemstack);

        /*
         *
         */

        if(old != null) {
            final Integer slot1 = getTeamItemSlot(old);

            ItemStack itemstack1 = teaminventory.getItem(slot1);

            final List<Player> players1 = getPlayersOfTeam(old);
            final Integer teamsize1 = players1.size();

            if(teamsize1 == 0) {
                itemstack1 = old.getItemStack();
            } else {
                itemstack1.setAmount(teamsize1);
            }

            String playerstring1 = "§7" + getPlayersInString(players1, "§7,§7");

            final List<String> lore1 = Arrays.asList(playerstring1.split(","));

            new ItemManager(itemstack1).addLoreAll(lore1).build();

            teaminventory.setItem(slot1, itemstack1);
        }
    }

    public Integer getTeamItemSlot(final Team team) {
        for(int i = 0; i < teaminventory.getSize(); i++) {
            try {
                final ItemMeta itemmeta = teaminventory.getItem(i).getItemMeta();

                if(itemmeta.getDisplayName().toUpperCase().contains(team.getName().toUpperCase())) {
                    return i;
                }
            } catch (NullPointerException e) {}
        }

        return 0;
    }

    public TeamResult setPlayerTeam(final Player player, final Team team) {
        if(teams.containsKey(team)) {
            final List<Player> tplayers = teams.get(team);

            if(tplayers.contains(player)) {
                return TeamResult.ALREADYIN;
            }

            if(tplayers.size() >= CWBW.getInstance().getDataHandler().getMaxPlayersPerTeam()) {
                return TeamResult.FULL;
            } else {
                final Team oldteam = getTeamOfPlayer(player);


                if(oldteam != null) {
                    final List<Player> tplayers1 = teams.get(oldteam);

                    if(tplayers1.contains(player)) tplayers1.remove(player);

                    teams.replace(oldteam, tplayers1);
                }

                tplayers.add(player);
                teams.replace(team, tplayers);

                updateInventory(team, oldteam);

                return TeamResult.TRUE;
            }
        } else {
            final Team oldteam = getTeamOfPlayer(player);


            if(oldteam != null) {
                final List<Player> tplayers = teams.get(oldteam);

                if(tplayers.contains(player)) tplayers.remove(player);

                teams.replace(oldteam, tplayers);
            }

            final List<Player> tplayers = new ArrayList<>();

            tplayers.add(player);

            teams.put(team, tplayers);

            updateInventory(team, oldteam);

            return TeamResult.TRUE;
        }
    }

    public boolean playWith(final Player player) {
        return CWBW.getInstance().getDataHandler().getIngamePlayers().contains(player);
    }

    public boolean isInATeam(final Player player) {
        for(final Team team : Team.values()) {
            if(teams.containsKey(team)) {
                final List<Player> tplayers = teams.get(team);

                if(tplayers.contains(player)) return true;
            }
        }

        return false;
    }

    public boolean isInTeam(final Team team, final Player player) {
        if(teams.containsKey(team)) {
            final List<Player> tplayers = teams.get(team);
            return tplayers.contains(player);
        }

        return false;
    }

    public Team getTeamOfPlayer(final Player player) {
        for(final Team pteams : Team.values()) {
            if(teams.containsKey(pteams)) {
                final List<Player> tplayers = teams.get(pteams);

                if(tplayers.contains(player)) {
                    return pteams;
                }
            }
        }

        return null;
    }

    public List<Player> getPlayersOfTeam(final Team team) {
        if(teams.containsKey(team)) return teams.get(team);

        return new ArrayList<>();
    }

    public boolean isInOneTeam(final Player playerone, final Player playertwo) {
        for(final Team pteams : Team.values()) {
            if(teams.containsKey(pteams)) {
                final List<Player> tplayers = teams.get(pteams);

                if(tplayers.contains(playerone) && tplayers.contains(playertwo)) return true;
            }
        }

        return false;
    }

    public boolean isTeamEmpty(final Team team) {
        if(teams.containsKey(team)) {
            final List<Player> tplayers = teams.get(team);

            return tplayers.size() == 0;
        }

        return true;
    }

    public void randomTeam() {
        List<Player> playerin = new ArrayList<>();

        for(final Player player : CWBW.getInstance().getDataHandler().getIngamePlayers()) {
            playerin.add(player);
            if(isInATeam(player)) playerin.remove(player);
        }

        for(final Team team : Team.values()) {
            List<Player> players = null;

            if(teams.containsKey(team)) {
                players = teams.get(team);
            } else {
                players = new ArrayList<>();
            }

            if(players.size() < CWBW.getInstance().getDataHandler().getMaxPlayersPerTeam()) {
                if(playerin.size() > 0) {
                    Player current = playerin.get(0);
                    playerin.remove(0);

                    players.add(current);

                    if(teams.containsKey(team)) {
                        teams.replace(team, players);
                    } else {
                        teams.put(team, players);
                    }

                    if(players.size() < CWBW.getInstance().getDataHandler().getMaxPlayersPerTeam()) {
                        if(playerin.size() > 0) {
                            Player current1 = playerin.get(0);
                            playerin.remove(0);

                            players.add(current1);

                            if(teams.containsKey(team)) {
                                teams.replace(team, players);
                            } else {
                                teams.put(team, players);
                            }
                        }
                    }
                }
            }
        }
    }

    public void teleportAllPlayers() {
        final LocationManager locationManager = CWBW.getInstance().getLocationManager();
        final Location red = locationManager.getLocation(CWBW.getInstance().getDataHandler().getMap() + ".team.red");
        final Location blue = locationManager.getLocation(CWBW.getInstance().getDataHandler().getMap() + ".team.blue");
        final Location green = locationManager.getLocation(CWBW.getInstance().getDataHandler().getMap() + ".team.green");
        final Location yellow = locationManager.getLocation(CWBW.getInstance().getDataHandler().getMap() + ".team.yellow");
        final Location pink = locationManager.getLocation(CWBW.getInstance().getDataHandler().getMap() + ".team.pink");
        final Location white = locationManager.getLocation(CWBW.getInstance().getDataHandler().getMap() + ".team.white");
        final Location black = locationManager.getLocation(CWBW.getInstance().getDataHandler().getMap() + ".team.black");
        final Location cyan = locationManager.getLocation(CWBW.getInstance().getDataHandler().getMap() + ".team.cyan");

        teams.keySet().forEach(e -> {
            String players = "";

            for(Player player : teams.get(e)) {
                players += players.equals("") ? player.getName() : ", " + player.getName();
            }
        });

        for(final Player ingameplayers : CWBW.getInstance().getDataHandler().getIngamePlayers()) {
            final Team team = getTeamOfPlayer(ingameplayers);

            if(team == Team.RED) {
                ingameplayers.teleport(red);

                if(!CWBW.getInstance().getDataHandler().getTeamsIngame().contains(team)) {
                    CWBW.getInstance().getDataHandler().getTeamsIngame().add(team);
                }
            }
            if(team == Team.BLUE) {
                ingameplayers.teleport(blue);

                if(!CWBW.getInstance().getDataHandler().getTeamsIngame().contains(team)) {
                    CWBW.getInstance().getDataHandler().getTeamsIngame().add(team);
                }
            }
            if(team == Team.GREEN) {
                ingameplayers.teleport(green);

                if(!CWBW.getInstance().getDataHandler().getTeamsIngame().contains(team)) {
                    CWBW.getInstance().getDataHandler().getTeamsIngame().add(team);
                }
            }
            if(team == Team.YELLOW) {
                ingameplayers.teleport(yellow);

                if(!CWBW.getInstance().getDataHandler().getTeamsIngame().contains(team)) {
                    CWBW.getInstance().getDataHandler().getTeamsIngame().add(team);
                }
            }
            if(team == Team.PINK) {
                ingameplayers.teleport(pink);

                if(!CWBW.getInstance().getDataHandler().getTeamsIngame().contains(team)) {
                    CWBW.getInstance().getDataHandler().getTeamsIngame().add(team);
                }
            }
            if(team == Team.WHITE) {
                ingameplayers.teleport(white);

                if(!CWBW.getInstance().getDataHandler().getTeamsIngame().contains(team)) {
                    CWBW.getInstance().getDataHandler().getTeamsIngame().add(team);
                }
            }
            if(team == Team.BLACK) {
                ingameplayers.teleport(black);

                if(!CWBW.getInstance().getDataHandler().getTeamsIngame().contains(team)) {
                    CWBW.getInstance().getDataHandler().getTeamsIngame().add(team);
                }
            }
            if(team == Team.CYAN) {
                ingameplayers.teleport(cyan);

                if(!CWBW.getInstance().getDataHandler().getTeamsIngame().contains(team)) {
                    CWBW.getInstance().getDataHandler().getTeamsIngame().add(team);
                }
            }
        }
    }

    private String getPlayersInString(final List<Player> playerlist, final String modifier) {
        String output = "";

        for(final Player player : playerlist) {
            output += output.equals("") ? player.getName() : modifier + player.getName();
        }

        return output;
    }
}
