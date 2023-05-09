package com.codecool.capture_the_flag;

import com.codecool.capture_the_flag.actors.*;
import com.codecool.capture_the_flag.util.Direction;
import com.codecool.capture_the_flag.util.GameUtils;
import com.codecool.capture_the_flag.util.Vector;

import javax.management.openmbean.ArrayType;
import java.rmi.UnexpectedException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.codecool.capture_the_flag.util.GameUtils.inverted;
import static com.codecool.capture_the_flag.util.GameUtils.toVector;

/**
 * GameMap class
 */
public class GameMap {

    /**
     * A 2D matrix of all actor references (null if the field is empty)
     */
    //private final Actor[][] actorMatrix =  new Actor[0][0];
    private final Actor[][] actorMatrix;

    /**
     * Contains all players present on the map (also dead ones)
     */
    private final List<Player> players = new ArrayList<>();

    /**
     * Contains all players present on the map (also captured ones)
     */
    private final List<Flag> flags = new ArrayList<>();
    private final ActorFactory factory = new ActorFactory();


    /**
     * Returns a new GameMap instance, constructed from given char matrix
     */
    public GameMap(String charMatrix) {
        String[] lines = charMatrix.split(System.getProperty("line.separator"));
        //actorMatrix = new Actor[lines.length][lines[0].length()];
        actorMatrix = new Actor[lines[0].length()][lines.length];
        int x = 0, y = 0;
        for (String line : lines) {
            char[] map = line.toCharArray();
            for (char ch : map) {
                Vector vector = new Vector(x, y);
                if (ch == 'R') {
                    Player p = factory.createPlayer(Player.PlayerTeam.ROCK, this);
                    actorMatrix[vector.getX()][vector.getY()] = p;
                    players.add(p);
                } else if (ch == 'P') {
                    Player p = factory.createPlayer(Player.PlayerTeam.PAPER, this);
                    actorMatrix[vector.getX()][vector.getY()] = p;
                    players.add(p);
                } else if (ch == 'S') {
                    Player p = factory.createPlayer(Player.PlayerTeam.SCISSORS, this);
                    actorMatrix[vector.getX()][vector.getY()] = p;
                    players.add(p);
                } else if (ch == 'F') {
                    Flag f = factory.createFlag(this);
                    actorMatrix[vector.getX()][vector.getY()] = f;
                    flags.add(f);
                }
                x++;
            }
            x = 0;
            y++;
        }
        //throw new RuntimeException("Method not implemented!");
    }

    /**
     * Returns a char matrix of map's current state
     * Expected look
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int x, y;
        for (y = 0; y < actorMatrix[0].length; y++) {
            for (x = 0; x < actorMatrix.length; x++) {
                Vector vector = new Vector(x, y);
                //EXPECTED MAP
                if (actorMatrix[vector.getX()][vector.getY()] == null) {
                    builder.append(".");
                } else if (actorMatrix[vector.getX()][vector.getY()] instanceof Rock) {
                    builder.append("R");
                } else if (actorMatrix[vector.getX()][vector.getY()] instanceof Paper) {
                    builder.append("P");
                } else if (actorMatrix[vector.getX()][vector.getY()] instanceof Scissors) {
                    builder.append("S");
                } else if (actorMatrix[vector.getX()][vector.getY()] instanceof Flag) {
                    builder.append("F");
                }
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    /**
     * Returns a char matrix of map's current state
     * Nice and readable look
     *
     * @return
     */
    /*public String toString() {
        StringBuilder builder = new StringBuilder();
        int x, y;
        builder.append("    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15\n");
        for (y = 0; y < actorMatrix[0].length; y++) {
            if (y < 10) {
                builder.append(" " + y + " ");
            } else {
                builder.append(y + " ");
            }
            for (x = 0; x < actorMatrix.length; x++) {
                Vector vector = new Vector(x, y);

                if (actorMatrix[vector.getX()][vector.getY()] == null) {
                    builder.append(" . ");
                } else if (actorMatrix[vector.getX()][vector.getY()] instanceof Rock) {
                    builder.append(" R ");
                } else if (actorMatrix[vector.getX()][vector.getY()] instanceof Paper) {
                    builder.append(" P ");
                } else if (actorMatrix[vector.getX()][vector.getY()] instanceof Scissors) {
                    builder.append(" S ");
                } else if (actorMatrix[vector.getX()][vector.getY()] instanceof Flag) {
                    builder.append(" F ");
                }
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }*/

    /**
     * Returns an actor instance present on given position
     * Should return null if no actor is present
     * Should throw an IllegalArgumentException if the position is outside map's boundaries
     *
     * @param position
     * @return
     */
    public Actor getActor(Vector position) {
        if (position.getX() < actorMatrix.length && position.getY() < actorMatrix[0].length) {
            return actorMatrix[position.getX()][position.getY()];
        }
        //throw new RuntimeException("Method not implemented!");
        throw new IllegalArgumentException("Position outside the bounds!");
    }

    /**
     * Returns a position of given actor instance
     * Should throw an IllegalArgumentException if actor is not found or no actor is given
     *
     * @param actor
     * @return
     */
    public Vector getPosition(Actor actor) {
        for (int y = 0; y < actorMatrix[0].length; y++) {
            for (int x = 0; x < actorMatrix.length; x++) {
                Vector v = new Vector(x, y);
                if (actorMatrix[v.getX()][v.getY()] instanceof Player) {
//                    System.out.println(x + " " + y + actorMatrix[v.getX()][v.getY()].getClass().getName());
                    Player playerGiven = (Player) actor;
                    Player playerFromMatrix = (Player) actorMatrix[v.getX()][v.getY()];
                    if (playerFromMatrix.getClass().isInstance(playerGiven)) {
                        if (playerFromMatrix.getName().equals(playerGiven.getName())) {
                            return v;
                        }
                    }
                }
            }
        }
        System.out.println(actor.getClass().getName());
        throw new RuntimeException("No such instance");
    }

    /**
     * Assignes given actor to given position
     * Should throw an IllegalArgumentException if the position is occupied by an another actor
     *
     * @param actor
     * @param position
     */
    public void setPosition(Actor actor, Vector position) {
        if (actorMatrix[position.getX()][position.getY()] == null) {
            System.out.println(actorMatrix[position.getX()][position.getY()] == null);
            actorMatrix[position.getX()][position.getY()] = actor;
        } else {
            throw new IllegalArgumentException("Possition Occupied");
        }
        //throw new RuntimeException("Method not implemented!");
    }

    /**
     * Attempts to move given player to a new position
     * If necessary, restricts movement or simulates fights between players
     *
     * @param player
     * @param currentPosition
     * @param dir
     */
    public void tryMovePlayer(Player player, Vector currentPosition, Direction dir) {
        Vector dirVector = toVector(dir);
        Vector targetPosition = new Vector(currentPosition.getX() + dirVector.getX(), currentPosition.getY() + dirVector.getY());

        if (!withinBoundaries(targetPosition))
            return;

        Actor actorOnTargetPosition = getActor(targetPosition);

        if (actorOnTargetPosition == null) {
            actorMatrix[currentPosition.getX()][currentPosition.getY()] = null;
            setPosition(player, targetPosition);
        } else if (actorOnTargetPosition instanceof Flag) {
            actorMatrix[currentPosition.getX()][currentPosition.getY()] = null;
            actorMatrix[targetPosition.getX()][targetPosition.getY()] = null;
            setPosition(player, targetPosition);

            player.setCapturedFlags(player.getCapturedFlags() + 1);
            ((Flag) actorOnTargetPosition).setCaptured(true);
        } else if (actorOnTargetPosition instanceof Player) {
            Player otherPlayer = (Player) actorOnTargetPosition;
            if (player.decideWhatToDo(otherPlayer, 1) == 1) {
                //decide to fight
                fightLogic(player, otherPlayer, currentPosition, targetPosition);
            } else if (player.decideWhatToDo(otherPlayer,1) == -1) {
                //decide to retret
                retreetLogic(player, toVector(inverted(dir)), currentPosition, targetPosition);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void fightLogic(Player player, Player otherPlayer, Vector currentPosition, Vector targetPosition) {
        int fightResult = player.Fight(otherPlayer);

        if (fightResult == 1) {
            // Player has won, move to the target position
            otherPlayer.setAlive(false);
            actorMatrix[currentPosition.getX()][currentPosition.getY()] = null;
            actorMatrix[targetPosition.getX()][targetPosition.getY()] = null;
            setPosition(player, targetPosition);

            player.setKilledPlayers(player.getKilledPlayers() + 1);
        } else if (fightResult == 0) {
            // Other player has won
            player.setAlive(false);
            actorMatrix[currentPosition.getX()][currentPosition.getY()] = null;
            actorMatrix[targetPosition.getX()][targetPosition.getY()] = null;
            setPosition(otherPlayer, currentPosition);

            otherPlayer.setKilledPlayers(otherPlayer.getKilledPlayers() + 1);
        }
    }

    private void retreetLogic(Player player,Vector dirVector, Vector currentPosition, Vector targetPosition) {
        targetPosition = new Vector(currentPosition.getX() + dirVector.getX(), currentPosition.getY() + dirVector.getY());
        Actor actorOnTargetPosition = getActor(targetPosition);
        if (!withinBoundaries(targetPosition)) return;
        if (actorOnTargetPosition == null) {
            actorMatrix[currentPosition.getX()][currentPosition.getY()] = null;
            setPosition(player, targetPosition);
        } else if (actorOnTargetPosition instanceof Flag) {
            actorMatrix[currentPosition.getX()][currentPosition.getY()] = null;
            actorMatrix[targetPosition.getX()][targetPosition.getY()] = null;
            setPosition(player, targetPosition);

            player.setCapturedFlags(player.getCapturedFlags() + 1);
            ((Flag) actorOnTargetPosition).setCaptured(true);
        } else if (actorOnTargetPosition instanceof Player) {
            Player otherPlayer = (Player) actorOnTargetPosition;
            if (player.decideWhatToDo(otherPlayer, 2) == 1) {
                //decide to fight
                fightLogic(player, otherPlayer, currentPosition, targetPosition);
            }
        }
    }

    /**
     * Returns the position of an uncaptured flag that is closest to given player
     * Should throw IllegalArgumentException if there are no uncaptured flags
     *
     * @param player
     * @return
     */
    public Vector getNearestFlagPosition(Player player) {
        int x, y, i = 0;
        Vector odl = new Vector(100, 100);
        Vector nearestFlagPos = new Vector(0, 0);
        Vector playerPos = getPosition(player);
        for (y = 0; y < actorMatrix[0].length; y++) {
            for (x = 0; x < actorMatrix.length; x++) {
                i++;
                Vector v = new Vector(x, y);
                if (actorMatrix[v.getX()][v.getY()] instanceof Flag) {
                    Flag flag = (Flag) actorMatrix[v.getX()][v.getY()];
                    if (!flag.isCaptured()) {
                        Vector codl = new Vector(Math.abs(playerPos.getX() - v.getX()), Math.abs(playerPos.getY() - v.getY()));
                        if ((codl.getX() + codl.getY()) < (odl.getX() + odl.getY())) {
                            odl = codl;
                            nearestFlagPos = new Vector(x, y);
                        }
                    }
                }
            }
        }
        return nearestFlagPos;
        //throw new RuntimeException("Method not implemented!");
    }

    /**
     * Returns true if given position is within the map's boundaries
     *
     * @param position
     * @return
     */
    public boolean withinBoundaries(Vector position) {
        if (position.getY() < actorMatrix[0].length && position.getY() >= 0 && position.getX() < actorMatrix.length && position.getX() >= 0) {
            return true;
        }
        return false;
        //throw new RuntimeException("Method not implemented!");
    }

    public Actor[][] getActorMatrix() {
        return actorMatrix;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Flag> getFlags() {
        return flags;
    }
}
