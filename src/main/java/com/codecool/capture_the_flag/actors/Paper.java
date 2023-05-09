package com.codecool.capture_the_flag.actors;

import com.codecool.capture_the_flag.GameMap;
import com.codecool.capture_the_flag.util.Direction;
import com.codecool.capture_the_flag.util.GameUtils;
import com.codecool.capture_the_flag.util.Vector;

import static com.codecool.capture_the_flag.actors.Player.PlayerTeam.*;

/**
 * Paper player class
 */
public class Paper extends Player {

    public Paper(String name, GameMap mapReference) {
        super(name, mapReference);
    }

    @Override
    public void onGameCycle() {
        if (!isAlive()) return;
        Vector myPos = mapReference.getPosition(this);
        Vector nearestFlagPos = mapReference.getNearestFlagPosition(this);
        mapReference.tryMovePlayer(this, myPos, getMoveDirection(myPos, nearestFlagPos));
//        System.out.println("Gracz " + getName() + " z teamu " + getTeam() + " wykonał ruch");
        //throw new RuntimeException("Method not implemented!");
    }

    @Override
    public int decideWhatToDo(Player otherPlayer, int attempt) {
        if (!otherPlayer.getTeam().equals(PAPER) && attempt == 1) {
            return -1;
        }
        if (!otherPlayer.getTeam().equals(PAPER) && attempt == 2) {
            return 1;
        }
        return 0;
    }

    @Override
    public int Fight(Player otherPlayer) {
        if (otherPlayer.getTeam().equals(SCISSORS)) {
            return 0;
        } else if (otherPlayer.getTeam().equals(ROCK)) {
            return 1;
        } else {
            return -1;
        }
        //throw new RuntimeException("Method not implemented!");
    }

    @Override
    public PlayerTeam getTeam() {
        return PlayerTeam.PAPER;
        //throw new RuntimeException("Method not implemented!");
    }
}
