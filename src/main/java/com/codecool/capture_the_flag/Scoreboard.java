package com.codecool.capture_the_flag;

import com.codecool.capture_the_flag.actors.Paper;
import com.codecool.capture_the_flag.actors.Player;
import com.codecool.capture_the_flag.actors.Rock;
import com.codecool.capture_the_flag.actors.Scissors;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Filter;
import java.util.stream.Collectors;

/**
 * Static class with extension methods for reports about players' performance during the game
 */
public class Scoreboard {
    private static int rockPoints,paperPoints,scissorsPoints;

    /**
     * Returns a collection of all players, sorted by their score (from highest to lowest)
     *
     * @param players
     * @return
     */
    public static List<Player> getRankedPlayers(List<Player> players) {
        List<Player> sorted = players.stream()
                .sorted(Comparator.comparingInt(Player::getCurrentScore).reversed())
                .collect(Collectors.toList());
        return sorted;
        //throw new RuntimeException("Method not implemented!");
    }

    /**
     * Returns a collection of all players, from given team, sorted by their score (from highest to lowest)
     *
     * @param players
     * @param team
     * @return
     */
    public static List<Player> getRankedPlayersInTeam(List<Player> players, Player.PlayerTeam team) {
        List<Player> sorted = players.stream()
                .filter(player -> player.getTeam().equals(team))
                .sorted(Comparator.comparingInt(Player::getCurrentScore).reversed())
                .collect(Collectors.toList());
        return sorted;
        // throw new RuntimeException("Method not implemented!");
    }

    /**
     * Returns the team that has the greatest amount of points scored by its players
     *
     * @param players
     * @return
     */
    public static Player.PlayerTeam getWinningTeam(List<Player> players) {
        int winningPoints = 0;
        Player.PlayerTeam winningTeam = null;
        //throw new RuntimeException("Method not implemented!");
        if (rockPoints > winningPoints) {
            winningPoints = rockPoints;
            winningTeam = Player.PlayerTeam.ROCK;
        }
        if (paperPoints > winningPoints) {
            winningPoints = paperPoints;
            winningTeam = Player.PlayerTeam.PAPER;
        }
        if (scissorsPoints > winningPoints) {
            winningPoints = paperPoints;
            winningTeam = Player.PlayerTeam.SCISSORS;
        }
        return winningTeam;
    }


    /**
     * Returns amount of dead players
     *
     * @param players
     * @return
     */
    public static int getDeadPlayersAmount(List<Player> players) {
        int i = 0;
        for (Player p: players) {
            if (!p.isAlive()) {
                i++;
            }
        }
        return i;
        //throw new RuntimeException("Method not implemented!");
    }

    /**
     * Returns full scoreboard string for current game (see the example)
     *
     * @param players
     * @return
     */
    public static String getScoreboard(List<Player> players) {
        StringBuilder scoreboard = new StringBuilder();
        // Team Rock Adam Points: 20
        // Team Paper Eve Points: 10
        // Team Scissors Abel Points: 5 DEAD
        rockPoints =0; paperPoints=0; scissorsPoints=0;
        for (Player p: getRankedPlayers(players)) {
            //System.out.println("Team " + p.getTeam() + " " + p.getName() + " Points: " + p.getCurrentScore());
            scoreboard.append("Team " + p.getTeam() + " " + p.getName() + " Points: " + p.getCurrentScore());
            if (p.isAlive()) {
                scoreboard.append(System.lineSeparator());
            } else {
                scoreboard.append(" DEAD" + System.lineSeparator());
            }
            if (p instanceof Rock) {
                rockPoints += p.getCurrentScore();
            } else if (p instanceof Paper) {
                paperPoints += p.getCurrentScore();
            } else if (p instanceof Scissors) {
                scissorsPoints += p.getCurrentScore();
            }
        }
        return scoreboard.toString();
        //return ("Team Rock: " + rockPoints + "\nTeam Paper: " + paperPoints +"\nTeam Scissors: " + scissorsPoints);
        //throw new RuntimeException("Method not implemented!");
    }

}
