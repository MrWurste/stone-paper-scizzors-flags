package com.codecool.capture_the_flag.util;

import com.codecool.capture_the_flag.actors.Flag;
import org.junit.Assert;
import org.junit.Test;

public class GameUtilTest {

    @Test
    public void getChar_NullActor_ReturnsDotTest() {
        Assert.assertEquals('.', GameUtils.getChar(null));
    }

    @Test
    public void getChar_Flag_ReturnsFTest() {
        Assert.assertEquals('F', GameUtils.getChar(new Flag(null)));
    }

    @Test
    public void getChar_Scissors_ReturnsSTest() {
        Assert.assertEquals('F', GameUtils.getChar(new Flag(null)));
    }

    @Test
    public void directionToVector_UpTest() {
        Assert.assertEquals(new Vector(0, -1), GameUtils.toVector(Direction.UP));
    }

    @Test
    public void directionToVector_RightTest() {
        Assert.assertEquals(new Vector(1, 0), GameUtils.toVector(Direction.RIGHT));
    }

    @Test
    public void directionInverted_Up_ReturnsDownTest() {
        Assert.assertEquals(Direction.DOWN, GameUtils.inverted(Direction.UP));
    }

    @Test
    public void directionInverted_Right_ReturnsLeftTest() {
        Assert.assertEquals(Direction.LEFT, GameUtils.inverted(Direction.RIGHT));
    }

    @Test
    public void getDistance1Test() {
        Assert.assertEquals(10, GameUtils.getDistance(new Vector(5, 0), new Vector(15, 0)));
    }

    @Test
    public void getDistance2Test() {
        Assert.assertEquals(2, GameUtils.getDistance(new Vector(2, 2), new Vector(3, 3)));
    }


}
