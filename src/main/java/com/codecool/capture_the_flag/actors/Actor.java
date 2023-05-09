package com.codecool.capture_the_flag.actors;

import com.codecool.capture_the_flag.GameMap;

/**
 * Base class for all Actors - Players and Flags
 */
public abstract class Actor {

    /**
     * Reference to Game's map
     */
    protected GameMap mapReference;

    public Actor(GameMap mapReference) {
        this.mapReference = mapReference;
    }

    public GameMap getMapReference() {
        return mapReference;
    }
}
