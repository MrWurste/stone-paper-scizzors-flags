package com.codecool.capture_the_flag.actors;

import com.codecool.capture_the_flag.GameMap;
import com.codecool.capture_the_flag.util.Direction;
import com.codecool.capture_the_flag.util.Vector;

/**
 * Base class for all Player
 */
public abstract class Player extends Actor {

    private final String name;
    private boolean alive;
    private int capturedFlags;
    private int killedPlayers;

    protected Player(String name, GameMap mapReference) {
        super(mapReference);
        this.name = name;
        this.alive = true;
        this.capturedFlags = 0;
        this.killedPlayers = 0;
    }

    /**
     * Returns direction for the player's next move, depending on the nearest flag's position
     *
     * @param playerPosition
     * @param flagPosition
     * @return
     */
    public static Direction getMoveDirection(Vector playerPosition, Vector flagPosition) {
        if (flagPosition.getX() == playerPosition.getX()) {
            if (flagPosition.getY() - playerPosition.getY() <0) {
                return Direction.UP;
            } else {
                return Direction.DOWN;
            }
        } else {
            if (flagPosition.getX() - playerPosition.getX() <0) {
                return Direction.LEFT;
            } else {
                return Direction.RIGHT;
            }
        }
        //throw new RuntimeException("Method not implemented!");
    }

    /**
     * Returns this player's current score
     *
     * @return
     */
    public int getCurrentScore() {
        return getCapturedFlags()*10 + getKilledPlayers()*5;
        //throw new RuntimeException("Method not implemented!");
    }

    /**
     * Cycles this player's behavior
     */
    public abstract void onGameCycle();

    public abstract int decideWhatToDo(Player otherPlayer, int attempt);
    /**
     * Fight simulation between this and given player
     *
     * @param otherPlayer
     * @return 1 if this player won, 0 if the other player won, -1 if fight didn't happen (otherPlayer is a teammate)
     */
    public abstract int Fight(Player otherPlayer);

    /**
     * Returns this player's team
     *
     * @return
     */
    public abstract PlayerTeam getTeam();

    /**
     * Returns true after player was killed
     *
     * @return
     */
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Returns current amount of flags captured by this player
     *
     * @return
     */
    public int getCapturedFlags() {
        return capturedFlags;
    }

    public void setCapturedFlags(int capturedFlags) {
        this.capturedFlags = capturedFlags;
    }

    /**
     * Returns current amount of players killed by this player
     *
     * @return
     */
    public int getKilledPlayers() {
        return killedPlayers;
    }

    public void setKilledPlayers(int killedPlayers) {
        this.killedPlayers = killedPlayers;
    }

    /**
     * Returns this player's name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * PlayerTeam enum
     */
    public enum PlayerTeam {
        ROCK,
        PAPER,
        SCISSORS
    }
}
