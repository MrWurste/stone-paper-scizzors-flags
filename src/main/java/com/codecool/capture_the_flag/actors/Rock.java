package com.codecool.capture_the_flag.actors;

import com.codecool.capture_the_flag.GameMap;
import com.codecool.capture_the_flag.util.Direction;
import com.codecool.capture_the_flag.util.Vector;

import static com.codecool.capture_the_flag.actors.Player.PlayerTeam.*;


/**
 * Rock player class
 */
public class Rock extends Player {

    public Rock(String name, GameMap mapReference) {
        super(name, mapReference);
    }

    @Override
    public void onGameCycle() {
        if (!isAlive()) return;
        Vector myPos = mapReference.getPosition(this);
        Vector nearestFlagPos = mapReference.getNearestFlagPosition(this);
        mapReference.tryMovePlayer(this, myPos, getMoveDirection(myPos, nearestFlagPos));
        /*
        // Make next move
        Vector myPosition = mapReference.getPosition(this);
        Vector nearestFlagPosition = mapReference.getNearestFlagPosition(this);
        Direction targetDirection = getMoveDirection(myPosition, nearestFlagPosition);

        mapReference.tryMovePlayer(this, myPosition, targetDirection);*/
    }

    @Override
    public int decideWhatToDo(Player otherPlayer, int attempt) {
        if (!otherPlayer.getTeam().equals(ROCK)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int Fight(Player otherPlayer) {
        if (otherPlayer.getTeam().equals(PAPER)) {
            return 0;
        } else if (otherPlayer.getTeam().equals(SCISSORS)) {
            return 1;
        } else {
            return -1;
        }
        //throw new RuntimeException("Method not implemented!");
    }

    @Override
    public PlayerTeam getTeam() {
        return PlayerTeam.ROCK;
        //throw new RuntimeException("Method not implemented!");
    }
}
