package com.codecool.capture_the_flag.actors;

import com.codecool.capture_the_flag.GameMap;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Static class for creating new actor instances
 */
public class ActorFactory {

    /**
     * A predefined collection of names for the players
     */
    private static final Queue<String> names = new LinkedList<>(Arrays.asList(
            "Marcel", "Moises", "Zane", "Dashawn", "Sean", "Rashad", "Seth", "Oliver", "Chris", "Quinton",
            "August", "Yusuf", "Jeramiah", "Bryce", "Rory", "Preston", "Eli", "Elisha", "Vicente", "Cristian",
            "Justice", "Eddie", "Allan", "Aarav", "Layne", "Owen", "Julio", "Soren", "Levi", "Mekhi", "Paul",
            "Griffin", "Agustin", "Johan", "Jaydin", "Skylar", "Rodrigo", "Brian", "John", "Davon", "Damari",
            "Ty", "Raymond", "Ronald", "Noah", "Abdiel", "Tyree", "Trent", "Rafael", "Jamarion"));


    /**
     * Use this method for getting names
     *
     * @return name
     */
    public static String getName() {
        return names.peek();
    }

    /**
     * Returns a new player instance, depending on given team
     *
     * @param team
     * @param mapReference
     * @return
     */
    public static Player createPlayer(Player.PlayerTeam team, GameMap mapReference) {
        //throw new RuntimeException("Method not implemented!");
        if (team == Player.PlayerTeam.ROCK) {
            return new Rock(names.peek(),mapReference);
        } else if (team == Player.PlayerTeam.PAPER) {
            return new Paper(names.poll(),mapReference);
        } else if (team == Player.PlayerTeam.SCISSORS) {
            return new Scissors(names.peek(),mapReference);
        } else {
            throw new RuntimeException("No player found");
        }
    }

    /**
     * Returns a new Flag instance
     *
     * @param mapReference
     * @return
     */
    public static Flag createFlag(GameMap mapReference) {
        //throw new RuntimeException("Method not implemented!");
        return new Flag(mapReference);
    }
    public static Empty createEmpty(GameMap mapReference) {
        //throw new RuntimeException("Method not implemented!");
        return new Empty(mapReference);
    }

    /**
     * Returns a new actor instance, depending on given character
     *
     * @param c
     * @param mapReference
     * @return
     */
    public static Actor createFromChar(char c, GameMap mapReference) {
        if (c == 'R') {
            return new Rock(names.peek(),mapReference);
        } else if (c == 'P') {
            return new Paper(names.poll(),mapReference);
        } else if (c == 'S') {
            return new Scissors(names.peek(), mapReference);
        } else if (c == '.') {
                return null;
        } else {
            throw new RuntimeException("No player found");
        }
    }
}
