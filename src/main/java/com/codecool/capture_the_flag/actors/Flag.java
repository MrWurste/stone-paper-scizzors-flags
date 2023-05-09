package com.codecool.capture_the_flag.actors;

import com.codecool.capture_the_flag.GameMap;

/**
 * Flag actor class
 */
public class Flag extends Actor {

    private boolean captured;

    public Flag(GameMap mapReference) {
        super(mapReference);
        this.captured = false;
    }

    /**
     * Returns true after being captured by a player
     *
     * @return
     */
    public boolean isCaptured() {
        return captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }
}
