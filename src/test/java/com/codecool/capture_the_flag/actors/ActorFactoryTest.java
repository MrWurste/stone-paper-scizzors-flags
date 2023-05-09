package com.codecool.capture_the_flag.actors;

import org.junit.Assert;
import org.junit.Test;


public class ActorFactoryTest {

    @Test
    public void createRockTest() {
        Actor actor = ActorFactory.createPlayer(Player.PlayerTeam.ROCK, null);

        Assert.assertTrue(actor instanceof Rock);
        Assert.assertEquals(Player.PlayerTeam.ROCK, ((Player) actor).getTeam());
    }

    @Test
    public void createPaperTest() {
        Actor actor = ActorFactory.createPlayer(Player.PlayerTeam.PAPER, null);

        Assert.assertTrue(actor instanceof Paper);
        Assert.assertEquals(Player.PlayerTeam.PAPER, ((Player) actor).getTeam());
    }

    @Test
    public void createScissorsTest() {
        Actor actor = ActorFactory.createPlayer(Player.PlayerTeam.SCISSORS, null);

        Assert.assertTrue(actor instanceof Scissors);
        Assert.assertEquals(Player.PlayerTeam.SCISSORS, ((Player) actor).getTeam());
    }

    @Test
    public void createNullFromCharTest() {
        Actor actor = ActorFactory.createFromChar('.', null);

        Assert.assertNull(actor);
    }

    @Test
    public void createRockFromCharTest() {
        Actor actor = ActorFactory.createFromChar('R', null);

        Assert.assertTrue(actor instanceof Rock);
    }

    @Test
    public void createPaperFromCharTest() {
        Actor actor = ActorFactory.createFromChar('P', null);

        Assert.assertTrue(actor instanceof Paper);
    }

    @Test
    public void createScissorsFromCharTest() {
        Actor actor = ActorFactory.createFromChar('S', null);

        Assert.assertTrue(actor instanceof Scissors);
    }

}
