package com.codecool.capture_the_flag.actors;

import com.codecool.capture_the_flag.GameMap;
import com.codecool.capture_the_flag.util.Direction;
import com.codecool.capture_the_flag.util.GameUtils;
import com.codecool.capture_the_flag.util.Vector;

import static com.codecool.capture_the_flag.actors.Player.PlayerTeam.*;

/**
 * Scissors player class
 */
public class Scissors extends Player {

    public Scissors(String name, GameMap mapReference) {
        super(name, mapReference);
    }

    @Override
    public void onGameCycle() {
        if (!isAlive()) return;
        Vector myPos = mapReference.getPosition(this);
        Vector nearestFlagPos = mapReference.getNearestFlagPosition(this);
        mapReference.tryMovePlayer(this, myPos, getMoveDirection(myPos, nearestFlagPos));
        //throw new RuntimeException("Method not implemented!");
    }

    @Override
    public int decideWhatToDo(Player otherPlayer, int attempt) {
        if (!otherPlayer.getTeam().equals(SCISSORS) && attempt == 2) {
            return 1;
        }
        if (otherPlayer.getTeam().equals(ROCK)) {
            return -1;
        }
        if (otherPlayer.getTeam().equals(PAPER)) {
            return 1;
        }
        return 0;
    }


    @Override
    public int Fight(Player otherPlayer) {
        if (otherPlayer.getTeam().equals(ROCK)) {
            return 0;
        } else if (otherPlayer.getTeam().equals(PAPER)) {
            return 1;
        } else {
            return -1;
        }
        //throw new RuntimeException("Method not implemented!");
    }

    @Override
    public PlayerTeam getTeam() {
        return PlayerTeam.SCISSORS;
        //throw new RuntimeException("Method not implemented!");
    }
}
