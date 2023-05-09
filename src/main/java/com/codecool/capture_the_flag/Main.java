package com.codecool.capture_the_flag;

/**
 * Application's main class, for starting the console project (DON'T MODIFY)
 */
public class Main {

    /**
     * Test map, that is used by Unit Tests for predefined game scenario testing
     */
    /*public static final String testMap = "FFFFFFFFFFFFFFFF" + System.lineSeparator() + "F..............F" + System.lineSeparator() + "F..............F" + System.lineSeparator() + "F..PRSPRSPRSP..F" + System.lineSeparator() + "F..S........R..F"
            + System.lineSeparator() + "F..R........S..F" + System.lineSeparator() + "F..P..FFFF..P..F" + System.lineSeparator() + "F..S..FFFF..R..F" + System.lineSeparator() + "F..R..FFFF..S..F" + System.lineSeparator() + "F..P..FFFF..P..F" + System.lineSeparator() + "F..S........R..F"
            + System.lineSeparator() + "F..R........S..F" + System.lineSeparator() + "F..PSRPSRPSRP..F" + System.lineSeparator() + "F..............F" + System.lineSeparator() + "F..............F" + System.lineSeparator() + "FFFFFFFFFFFFFFFF";*/

    public static final String testMap = "FFFFFFFFFFFFFFFF" + System.lineSeparator() + "F..............F" + System.lineSeparator() + "F..............F" + System.lineSeparator() + "F..............F" + System.lineSeparator() + "F..............F"
            + System.lineSeparator() + "F..............F" + System.lineSeparator() + "F..P........P..F" + System.lineSeparator() + "F..............F" + System.lineSeparator() + "F..............F" + System.lineSeparator() + "F..............F" + System.lineSeparator() + "F..............F"
            + System.lineSeparator() + "F..............F" + System.lineSeparator() + "F..............F" + System.lineSeparator() + "F..............F" + System.lineSeparator() + "F..............F" + System.lineSeparator() + "FFFFFFFFFFFFFFFF";

    /*public static final String testMap = "................" + System.lineSeparator() + "................" + System.lineSeparator() + "................" + System.lineSeparator() + "................" + System.lineSeparator() + "................"
            + System.lineSeparator() + "..PS............" + System.lineSeparator() + ".R.........F...F" + System.lineSeparator() + "................" + System.lineSeparator() + "................" + System.lineSeparator() + "................" + System.lineSeparator() + "................"
            + System.lineSeparator() + "................" + System.lineSeparator() + "................" + System.lineSeparator() + "................" + System.lineSeparator() + "................" + System.lineSeparator() + "................";*/
    //public static final String testMap = ".PRF";
    /**
     * main method
     *
     * @param args
     */
    public static void main(String[] args) {
        GameMap gameMap = new GameMap(testMap);
        Game game = new Game(gameMap);

        game.simulateGame();
    }
}
