package com.codecool.capture_the_flag;

import com.codecool.capture_the_flag.actors.Flag;
import com.codecool.capture_the_flag.actors.Player;

import java.io.IOException;

/**
 * Game simulation class
 */
public class Game {

    /**
     * Reference to game's map
     */
    public GameMap map;

    /**
     *  the amount of iterations (cycles) the simulation has made
     */
    public int iterations;

    public Game(GameMap map) {
        this.map = map;
    }

    /**
     *  Invokes onGameCycle on all players (DO NOT MODIFY)
     */
    public void cyclePlayers() {
        for (Player player : map.getPlayers()) {
            if (map.getFlags().stream().allMatch(Flag::isCaptured))
                break;
            player.onGameCycle();
        }
    }

    /**
     * Simulates the whole game, giving output to the Console (DO NOT MODIFY)
     */
    public void simulateGame() {
        iterations = 0;

        while (ongoingGame()) {
            iterations++;

            try {
                Runtime.getRuntime().exec("cls  || clear");
            } catch (IOException ignored) {
            }
            System.out.println(map);
            System.out.println();
            System.out.println(Scoreboard.getScoreboard(map.getPlayers()));

            try {
                System.in.read();
            } catch (IOException ignore) {
            }

            cyclePlayers();
        }

        try {
            Runtime.getRuntime().exec("cls  || clear");
        } catch (IOException ignored) {
        }

        System.out.println(map);
        System.out.println();
        System.out.println(Scoreboard.getScoreboard(map.getPlayers()));
        System.out.println();
        System.out.println("Game Over [" + iterations + "]");
        System.out.println("Team " + Scoreboard.getWinningTeam(map.getPlayers()) + " has won");

        try {
            System.in.read();
        } catch (IOException ignore) {
        }
    }

    /**
     * Simulates the whole game without output to the Console (used for testing) (DO NOT MODIFY)
     */
    public void simulateGameNoOutput() {
        iterations = 0;

        while (ongoingGame()) {
            iterations++;
            cyclePlayers();
        }
    }

    /**
     * Returns true if the game simulation should still be ongoing
     * @return
     */
    public boolean ongoingGame() {
      boolean someoneAlive = false;
      boolean flagNotCaptured = false;
       for (Player player : map.getPlayers()) {
          if(player.isAlive()) {
            someoneAlive = true;
          }
       }
       for (Flag flag : map.getFlags()) {
          if(!flag.isCaptured()) {
            flagNotCaptured = true;
          }
       }
      if (someoneAlive && flagNotCaptured) {
        return true;
      } else if (!flagNotCaptured) {
        return false;
      } else { return false;}
        //throw new RuntimeException("Method not implemented!");
    }

    public GameMap getMap() {
        return map;
    }

    public int getIterations() {
        return iterations;
    }

    private void setIterations(int iterations) {
        this.iterations = iterations;
    }
}
