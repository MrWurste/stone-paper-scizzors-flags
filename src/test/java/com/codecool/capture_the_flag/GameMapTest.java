package com.codecool.capture_the_flag;

import com.codecool.capture_the_flag.actors.*;
import com.codecool.capture_the_flag.util.Vector;
import org.junit.Assert;
import org.junit.Test;

public class GameMapTest {

    @Test
    public void ctorActorMatrix_TwoFlagsTest() {
        String charMatrix = "F..." + System.lineSeparator() + "...F";
        Actor[][] expectedActorMatrix = new Actor[][]{{new Flag(null), null, null, null}, {null, null, null, new Flag(null)}};

        GameMap gameMap = new GameMap(charMatrix);
        Actor[][] actorMatrix = gameMap.getActorMatrix();

        Assert.assertEquals(expectedActorMatrix.length, actorMatrix.length);
        Assert.assertEquals(expectedActorMatrix[0].length, actorMatrix[0].length);

        for (int y = 0; y < actorMatrix.length; y++) {
            for (int x = 0; x < actorMatrix[0].length; x++) {

                Actor expectedActor = expectedActorMatrix[y][x];
                Actor actualActor = actorMatrix[y][x];

                if (expectedActor == null) {
                    Assert.assertNull(actualActor);
                } else {
                    Assert.assertTrue(expectedActor.getClass().isInstance(actualActor));
                }
            }
        }
    }

    @Test
    public void ctorActorMatrix_PaperScissorsRockTest() {
        String charMatrix = "P..." + System.lineSeparator() + ".R.." + System.lineSeparator() + "..S.";
        Actor[][] expectedActorMatrix = new Actor[][]{{new Paper("", null), null, null, null}, {null, new Rock("", null), null, null}, {null, null, new Scissors("", null), null}};

        GameMap gameMap = new GameMap(charMatrix);
        Actor[][] actorMatrix = gameMap.getActorMatrix();

        Assert.assertEquals(expectedActorMatrix.length, actorMatrix.length);
        Assert.assertEquals(expectedActorMatrix[0].length, actorMatrix[0].length);

        for (int y = 0; y < actorMatrix.length; y++) {
            for (int x = 0; x < actorMatrix[0].length; x++) {

                Actor expectedActor = expectedActorMatrix[y][x];
                Actor actualActor = actorMatrix[y][x];

                if (expectedActor == null) {
                    Assert.assertNull(actualActor);
                } else {
                    Assert.assertTrue(expectedActor.getClass().isInstance(actualActor));
                }
            }
        }
    }

    @Test
    public void toString1Test() {
        String charMatrix = "F..P" + System.lineSeparator() + "S..F";
        GameMap gameMap = new GameMap(charMatrix);

        Assert.assertEquals(charMatrix + System.lineSeparator(), gameMap.toString());
    }

    @Test
    public void toString2Test() {
        String charMatrix = "PSR.." + System.lineSeparator() + "S.S.F";
        GameMap gameMap = new GameMap(charMatrix);

        Assert.assertEquals(charMatrix + System.lineSeparator(), gameMap.toString());
    }

    @Test
    public void getPosition_PresentActor_ReturnsPosition1Test() {
        String charMatrix = "P..." + System.lineSeparator() + ".R.." + System.lineSeparator() + "..S.";
        GameMap gameMap = new GameMap(charMatrix);
        Rock rock = null;

        for (int y = 0; y < gameMap.getActorMatrix().length; y++) {
            for (int x = 0; x < gameMap.getActorMatrix()[0].length; x++) {
                if (gameMap.getActorMatrix()[y][x] instanceof Rock) {
                    rock = (Rock) gameMap.getActorMatrix()[y][x];
                }
            }
        }


        Assert.assertEquals(new Vector(1, 1), gameMap.getPosition(rock));
    }

    @Test
    public void getPosition_PresentActor_ReturnsPosition2Test() {
        String charMatrix = "P..." + System.lineSeparator() + ".R.." + System.lineSeparator() + "..S.";
        GameMap gameMap = new GameMap(charMatrix);
        Scissors scissors = null;

        for (int y = 0; y < gameMap.getActorMatrix().length; y++) {
            for (int x = 0; x < gameMap.getActorMatrix()[0].length; x++) {
                if (gameMap.getActorMatrix()[y][x] instanceof Scissors) {
                    scissors = (Scissors) gameMap.getActorMatrix()[y][x];
                }
            }
        }


        Assert.assertEquals(new Vector(2, 2), gameMap.getPosition(scissors));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setPosition_OccupiedPosition_ThrowsIllegalArgumentExceptionTest() {
        GameMap gameMap = new GameMap(".F.");
        Rock actor = new Rock("", null);

        gameMap.setPosition(actor, new Vector(0, 1));
        //gameMap.setPosition(actor, new Vector(1, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getActor_PositionOutsideBounds_ThrowsIllegalArgumentExceptionTest() {
        GameMap gameMap = new GameMap("...");

        gameMap.getActor(new Vector(0, 1));
    }

    @Test
    public void getActor_PresentActor_ReturnsActor1Test() {
        String charMatrix = "P..." + System.lineSeparator() + ".R.." + System.lineSeparator() + "..S.";

        GameMap gameMap = new GameMap(charMatrix);
        Actor actor = gameMap.getActor(new Vector(0, 0));

        Assert.assertTrue(actor instanceof Paper);
    }

    @Test
    public void getActor_PresentActor_ReturnsActor2Test() {
        String charMatrix = "P..." + System.lineSeparator() + ".R.." + System.lineSeparator() + "..S.";

        GameMap gameMap = new GameMap(charMatrix);
        Actor actor = gameMap.getActor(new Vector(1, 1));

        Assert.assertTrue(actor instanceof Rock);
    }

    @Test
    public void getNearestFlagPosition1Test() {
        String charMatrix = "P...." + System.lineSeparator() + ".F..." + System.lineSeparator() + "....F" + System.lineSeparator() + "....R" + System.lineSeparator() + ".....";
        GameMap gameMap = new GameMap(charMatrix);

        Player player = (Player) gameMap.getActor(new Vector(0, 0));
        Vector flagPosition = gameMap.getNearestFlagPosition(player);

        Assert.assertEquals(new Vector(1, 1), flagPosition);
    }

    @Test
    public void getNearestFlagPosition2Test() {
        String charMatrix = "P...." + System.lineSeparator() + ".F..." + System.lineSeparator() + "....F" + System.lineSeparator() + "....R" + System.lineSeparator() + ".....";
        GameMap gameMap = new GameMap(charMatrix);

        Player player = (Player) gameMap.getActor(new Vector(4, 3));
        Vector flagPosition = gameMap.getNearestFlagPosition(player);

        Assert.assertEquals(new Vector(4, 2), flagPosition);
    }
}
