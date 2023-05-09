package com.codecool.capture_the_flag.actors;

import com.codecool.capture_the_flag.util.Direction;
import com.codecool.capture_the_flag.util.Vector;
import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void getMoveDirection_FlagDirectlyBelowPlayer_ReturnsDownTest(){
        Assert.assertEquals(Direction.DOWN, Player.getMoveDirection(new Vector(0, 0), new Vector(0, 5)));
    }

    @Test
    public void getMoveDirection_FlagDirectlyRightPlayer_ReturnsRightTest(){
        Assert.assertEquals(Direction.RIGHT, Player.getMoveDirection(new Vector(0, 0), new Vector(7, 0)));
    }
    @Test
    public void getMoveDirection_FlagDiagonallyLeftPlayer_ReturnsLeftTest(){
        Assert.assertEquals(Direction.LEFT, Player.getMoveDirection(new Vector(1, 1), new Vector(0, 0)));
    }

    @Test
    public void fight_RockFightsRock_DrawTest(){
        Player attacker = new Rock("", null);
        Player other = new Rock("", null);

        Assert.assertEquals(-1, attacker.Fight(other));
    }

    @Test
    public void fight_RockFightsScissors_WinTest(){
        Player attacker = new Rock("", null);
        Player other = new Scissors("", null);

        Assert.assertEquals(1, attacker.Fight(other));
    }

    @Test
    public void fight_PaperFightsScissors_LoseTest(){
        Player attacker = new Paper("", null);
        Player other = new Scissors("", null);

        Assert.assertEquals(0, attacker.Fight(other));
    }

    @Test
    public void fight_PaperFightsRock_WinTest(){
        Player attacker = new Paper("", null);
        Player other = new Rock("", null);

        Assert.assertEquals(1, attacker.Fight(other));
    }
}
