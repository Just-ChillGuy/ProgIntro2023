package game;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OlympicSystem {
    private final int n;
    private final int m;
    private final int k;
    private final int countPlayers;
    private int countLevels;
    private int level = 0;

    private List<Integer> players = new ArrayList<>();
    private final List<Integer> winners = new ArrayList<>();
    private final List<ArrayList<Integer>> result = new ArrayList<>();

    public OlympicSystem(int n, int m, int k, int countPlayers, int countLevels, List<Integer> players) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.countPlayers = countPlayers;
        this.countLevels = countLevels;
        this.players = players;
    }

    public void play() throws NoSuchObjectException {
        int degree = 1;
        degree = bringingToTheDegree(degree, countPlayers);
        int res;
        int cntPlayersPlayoff = 0;
        boolean playOff = true;
        boolean afterPlayOff = false;
        if (countPlayers == exponentiation(2, degree)) {
            playOff = false;
        } else {
            cntPlayersPlayoff = countPlayers;
        }
        while (countLevels > 0) {
            result.add(new ArrayList<>());
            for (int i = 0; i < players.size(); i++) {
                if (playOff && cntPlayersPlayoff == exponentiation(2, degree)) {
                    playOff = false;
                    afterPlayOff = true;
                    break;
                }
                Random r = new Random();
                int f;
                int s;
                if (r.nextBoolean()) {
                    f = players.get(i);
                    s = players.get(i + 1);
                } else {
                    f = players.get(i + 1);
                    s = players.get(i);
                }
                System.out.println("First: " + f);
                System.out.println("Second: " + s);
                Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
                res = game.play(new TicTacToeBoard(n, m, k));
                if (res == 0) {
                    while (res == 0) {
                        res = game.play(new TicTacToeBoard(n, m, k));
                    }
                }
                if (res == 1) {
                    System.out.println("Game result: Player " + f + " WON");
                    System.out.println("--------------------------");
                    winners.add(f);
                    result.get(level).add(s);
                } else {
                    System.out.println("Game result: Player " + s + " WON");
                    System.out.println("--------------------------");
                    winners.add(s);
                    result.get(level).add(f);
                }
                if (playOff) {
                    cntPlayersPlayoff--;
                }
                i++;
            }

            if (!afterPlayOff) {
                if ((exponentiation(2, degree) / 2) == 1) {
                    System.out.println("Circle: final");
                } else {
                    System.out.println("Circle: 1/" + (exponentiation(2, degree) / 2) + " finals:");
                }
                degree--;
                countLevels--;

            } else {
                output(cntPlayersPlayoff, countPlayers,
                        "Pre tournament circle, participants:", afterPlayOff);
                afterPlayOff = false;
                continue;
            }
            output(cntPlayersPlayoff, countPlayers,
                    "participants of circle:", afterPlayOff);
        }

        for (int i = 0; i < result.size(); i++) {
            System.out.print("Place " + (level - i + 1) + ": ");
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j) + " ");
            }
            System.out.println();
        }

        System.out.println("Winner of tourment: " + players.get(0));
    }
    public Integer bringingToTheDegree (int degree, int countPlayers) {
        while (exponentiation(2, degree) < countPlayers) {
            degree++;
        }
        if (exponentiation(2, degree) > countPlayers) {
            degree--;
        }
      return degree;
    }
    public Integer exponentiation(int footing, int degree) {
        for (int i = 0; i < (degree - 1); i++) {
            footing *= 2;
        }
        return footing;
    }
    private void output(int cntPlayersPlayoff, int countPlayers, String forOutput,
                        boolean afterPlayoff) {
        System.out.println(forOutput);
        if (afterPlayoff) {
            for (int i = (countPlayers - cntPlayersPlayoff) * 2; i < players.size(); i++) {
                winners.add(players.get(i));
            }
        }
        for (Integer pl : players) {
            System.out.println(pl);
        }
        System.out.println("Winners of circle:");
        for (Integer winner : winners) {
            System.out.println(winner);
        }
        System.out.println("==========================");
        level++;
        players = new ArrayList<>(winners);
        winners.clear();
    }
}

