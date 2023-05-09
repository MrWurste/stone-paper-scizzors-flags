package com.codecool.capture_the_flag;

import com.codecool.capture_the_flag.actors.Paper;
import com.codecool.capture_the_flag.actors.Player;
import com.codecool.capture_the_flag.actors.Rock;
import com.codecool.capture_the_flag.actors.Scissors;
import com.codecool.capture_the_flag.util.Vector;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    @Test
    public void playerMovingTowardsTheFlagTest() {
        Game game = new Game(new GameMap("R.F"));
        game.cyclePlayers();

        Player rock = (Player) game.getMap().getActor(new Vector(1, 0));

        Assert.assertNotNull(rock);
    }

    @Test
    public void playerCapturingTheFlagTest() {
        Game game = new Game(new GameMap(".RF"));
        game.cyclePlayers();

        Player player = (Player) game.getMap().getActor(new Vector(2, 0));

        Assert.assertNotNull(player);
        Assert.assertEquals(1, player.getCapturedFlags());
    }

    @Test
    public void rockMovingTowardsTheFlag_LosingWithPaperTest() {
        Game game = new Game(new GameMap("RPF"));
        game.cyclePlayers();

        Paper paper = (Paper) game.getMap().getActor(new Vector(1, 0));

        Assert.assertNotNull(paper);
        Assert.assertEquals(1, paper.getKilledPlayers());
    }

    @Test
    public void paperMovingTowardsTheFlag_EvadingRockTest() {
        Game game = new Game(new GameMap(".PRF"));
        game.cyclePlayers();

        Paper paper = (Paper) game.getMap().getActor(new Vector(0, 0));

        Assert.assertNotNull(paper);
    }

    @Test
    public void paperMovingTowardsTheFlag_EvadingRock_BlockedByScissors_LosingWithScissorsTest() {
        Game game = new Game(new GameMap("S.PRF"));
        game.cyclePlayers();

        Scissors scissors = (Scissors) game.getMap().getActor(new Vector(2, 0));

        Assert.assertNotNull(scissors);
    }

    @Test
    public void scissorsMovingTowardsTheFlag_WinningWithPaperTest() {
        Game game = new Game(new GameMap(".SPF"));
        game.cyclePlayers();

        Scissors scissors = (Scissors) game.getMap().getActor(new Vector(2, 0));

        Assert.assertNotNull(scissors);
        Assert.assertEquals(1, scissors.getKilledPlayers());
    }

    @Test
    public void scissorsMovingTowardsTheFlag_EvadingRockTest() {
        Game game = new Game(new GameMap(".SRF"));
        game.cyclePlayers();

        Scissors scissors = (Scissors) game.getMap().getActor(new Vector(0, 0));

        Assert.assertNotNull(scissors);
    }

    @Test
    public void scissorsMovingTowardsTheFlag_EvadingRock_BlockedByRock_LosingWithRockTest() {
        Game game = new Game(new GameMap("R.SRF"));
        game.cyclePlayers();

        Rock rock = (Rock) game.getMap().getActor(new Vector(2, 0));

        Assert.assertNotNull(rock);
    }

    @Test
    public void ongoingGame_ReturnsTrueTest() {
        Game game = new Game(new GameMap("P..F"));

        Assert.assertTrue(game.ongoingGame());
    }

    @Test
    public void ongoingGame_AllFlagsCaptured_ReturnsFalseTest() {
        Game game = new Game(new GameMap("PS.F"));

        game.getMap().getFlags().forEach(f -> f.setCaptured(true));

        Assert.assertFalse(game.ongoingGame());
    }
}
