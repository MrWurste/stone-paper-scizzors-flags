package com.codecool.capture_the_flag;

import com.codecool.capture_the_flag.actors.Paper;
import com.codecool.capture_the_flag.actors.Player;
import com.codecool.capture_the_flag.actors.Rock;
import com.codecool.capture_the_flag.actors.Scissors;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ScoreboardTest {

    @Test
    public void TestGetRankedPlayers() {
        Rock rock1 = new Rock("", null);
        rock1.setCapturedFlags(1);
        rock1.setKilledPlayers(5);

        Rock rock2 = new Rock("", null);
        rock2.setCapturedFlags(3);
        rock2.setKilledPlayers(0);

        Paper paper1 = new Paper("", null);
        paper1.setCapturedFlags(2);
        paper1.setKilledPlayers(1);

        Paper paper2 = new Paper("", null);
        paper2.setCapturedFlags(3);
        paper2.setKilledPlayers(3);

        Scissors scissors1 = new Scissors("", null);
        scissors1.setCapturedFlags(0);
        scissors1.setKilledPlayers(10);

        Scissors scissors2 = new Scissors("", null);
        scissors2.setCapturedFlags(1);
        scissors2.setKilledPlayers(2);


        Player[] players = new Player[]{rock1, rock2, paper1, paper2, scissors1, scissors2};
        Player[] expected = new Player[]{scissors1, paper2, rock1, rock2, paper1, scissors2};

        Assert.assertEquals(Arrays.asList(expected), Scoreboard.getRankedPlayers(Arrays.asList(players)));
    }

    @Test
    public void getRankedPlayersInTeamTest() {
        Rock rock1 = new Rock("", null);
        rock1.setCapturedFlags(1);
        rock1.setKilledPlayers(5);

        Rock rock2 = new Rock("", null);
        rock2.setCapturedFlags(3);
        rock2.setKilledPlayers(0);

        Paper paper1 = new Paper("", null);
        paper1.setCapturedFlags(2);
        paper1.setKilledPlayers(1);

        Paper paper2 = new Paper("", null);
        paper2.setCapturedFlags(3);
        paper2.setKilledPlayers(3);

        Scissors scissors1 = new Scissors("", null);
        scissors1.setCapturedFlags(0);
        scissors1.setKilledPlayers(10);

        Scissors scissors2 = new Scissors("", null);
        scissors2.setCapturedFlags(1);
        scissors2.setKilledPlayers(2);

        Player[] players = new Player[]{rock1, rock2, paper1, paper2, scissors1, scissors2};
        Player[] expectedScissors = new Player[]{scissors1, scissors2};
        Player[] expectedPaper = new Player[]{paper2, paper1};
        Player[] expectedRock = new Player[]{rock1, rock2};

        Assert.assertEquals(Arrays.asList(expectedScissors), Scoreboard.getRankedPlayersInTeam(Arrays.asList(players), Player.PlayerTeam.SCISSORS));
        Assert.assertEquals(Arrays.asList(expectedPaper), Scoreboard.getRankedPlayersInTeam(Arrays.asList(players), Player.PlayerTeam.PAPER));
        Assert.assertEquals(Arrays.asList(expectedRock), Scoreboard.getRankedPlayersInTeam(Arrays.asList(players), Player.PlayerTeam.ROCK));
    }

    @Test
    public void getWinningTeamTest() {
        Rock rock1 = new Rock("", null);
        rock1.setCapturedFlags(1);
        rock1.setKilledPlayers(5);

        Rock rock2 = new Rock("", null);
        rock2.setCapturedFlags(3);
        rock2.setKilledPlayers(0);

        Paper paper1 = new Paper("", null);
        paper1.setCapturedFlags(2);
        paper1.setKilledPlayers(1);

        Paper paper2 = new Paper("", null);
        paper2.setCapturedFlags(3);
        paper2.setKilledPlayers(3);

        Scissors scissors1 = new Scissors("", null);
        scissors1.setCapturedFlags(0);
        scissors1.setKilledPlayers(10);

        Scissors scissors2 = new Scissors("", null);
        scissors2.setCapturedFlags(10);
        scissors2.setKilledPlayers(20);

        Player[] players = new Player[]{rock1, rock2, paper1, paper2, scissors1, scissors2};

        Assert.assertEquals(Player.PlayerTeam.SCISSORS, Scoreboard.getWinningTeam(Arrays.asList(players)));
    }

    @Test
    public void getDeadPlayersAmountTest() {
        Rock rock1 = new Rock("", null);
        rock1.setAlive(false);

        Rock rock2 = new Rock("", null);
        rock2.setAlive(false);

        Paper paper1 = new Paper("", null);
        paper1.setAlive(false);

        Paper paper2 = new Paper("", null);
        paper2.setAlive(false);

        Scissors scissors1 = new Scissors("", null);
        Scissors scissors2 = new Scissors("", null);

        Player[] players = new Player[]{rock1, rock2, paper1, paper2, scissors1, scissors2};

        Assert.assertEquals(4, Scoreboard.getDeadPlayersAmount(Arrays.asList(players)));
    }

    @Test
    public void getScoreboardTest() {
        Rock rock1 = new Rock("A", null);
        rock1.setCapturedFlags(1);
        rock1.setKilledPlayers(5);
        rock1.setAlive(false);

        Rock rock2 = new Rock("B", null);
        rock2.setCapturedFlags(3);
        rock2.setKilledPlayers(0);

        Paper paper1 = new Paper("C", null);
        paper1.setCapturedFlags(2);
        paper1.setKilledPlayers(1);

        Paper paper2 = new Paper("D", null);
        paper2.setCapturedFlags(3);
        paper2.setKilledPlayers(3);
        paper2.setAlive(false);

        Scissors scissors1 = new Scissors("E", null);
        scissors1.setCapturedFlags(0);
        scissors1.setKilledPlayers(10);

        Scissors scissors2 = new Scissors("F", null);
        scissors2.setCapturedFlags(10);
        scissors2.setKilledPlayers(20);
        scissors2.setAlive(false);

        Player[] players = new Player[]{rock1, rock2, paper1, paper2, scissors1, scissors2};
        String expectedScoreboard = "Team SCISSORS F Points: 200 DEAD" + System.lineSeparator() +
                "Team SCISSORS E Points: 50" + System.lineSeparator() +
                "Team PAPER D Points: 45 DEAD" + System.lineSeparator() +
                "Team ROCK A Points: 35 DEAD" + System.lineSeparator() +
                "Team ROCK B Points: 30" + System.lineSeparator() +
                "Team PAPER C Points: 25" + System.lineSeparator();

        Assert.assertEquals(expectedScoreboard, Scoreboard.getScoreboard(Arrays.asList(players)));
    }

}
