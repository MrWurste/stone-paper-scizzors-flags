package com.codecool.capture_the_flag.util;

import com.codecool.capture_the_flag.actors.*;

import java.util.Objects;

public class GameUtils {

    /**
     * Returns a character representing given actor
     * Throws an IllegalArgumentException when given invalid input
     *
     * @param actor
     * @return
     */
    public static char getChar(Actor actor) {
        if (actor instanceof Rock) {
            return 'R';
        } else if (actor instanceof Paper) {
            return 'P';
        } else if (actor instanceof Scissors) {
            return 'S';
        } else if (actor instanceof Flag) {
            return 'F';
        } else {
            return '.';
        }
    }


    /**
     * Returns a vector representing given direction
     * Throws an IllegalArgumentException when given invalid input
     *
     * @param dir
     * @return
     */
    public static Vector toVector(Direction dir) {
        Vector vector;
        if (dir == Direction.UP) {
            return new Vector(0,-1);
        }
        if (dir == Direction.DOWN) {
            return new Vector(0,1);
        }
        if (dir == Direction.LEFT) {
            return new Vector(-1,0);
        }
        if (dir == Direction.RIGHT) {
            return new Vector(1,0);
        }
        throw new IllegalArgumentException("No such directon!");
    }

    /**
     * Returns a direction converted from given vector
     * Throws an IllegalArgumentException when given invalid input
     *
     * @param vector
     * @return
     */
    public static Direction toDirection(Vector vector) {

        if (vector.getX() == 0 && vector.getY() == -1)
            return Direction.UP;

        if (vector.getX() == 0 && vector.getY() == 1)
            return Direction.DOWN;

        if (vector.getX() == -1 && vector.getY() == 0)
            return Direction.LEFT;

        if (vector.getX() == 1 && vector.getY() == 0)
            return Direction.RIGHT;

        throw new IllegalArgumentException();
    }

    /**
     * Returns a direction that is opposite to given direction
     * Throws an IllegalArgumentException when given invalid input
     *
     * @param dir
     * @return
     */
    public static Direction inverted(Direction dir) {
        if (dir == Direction.UP) {
            return Direction.DOWN;
        } else if (dir == Direction.DOWN) {
            return Direction.UP;
        } else if (dir == Direction.LEFT) {
            return Direction.RIGHT;
        } else if (dir == Direction.RIGHT) {
            return Direction.LEFT;
        } else {
            throw new IllegalArgumentException("No such direction");
        }
    }

    /**
     * Returns the amount of steps a player has to make in order to get from pos1 to pos2
     *
     * @param pos1
     * @param pos2
     * @return
     */
    public static int getDistance(Vector pos1, Vector pos2) {
        return Math.abs(pos1.getX()- pos2.getX()) + Math.abs(pos1.getY()- pos2.getY());
        //throw new RuntimeException("Method not implemented!");
    }
}
