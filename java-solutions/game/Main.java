package game;

import java.rmi.NoSuchObjectException;
import java.util.*;

public class Main {
    public static double log2(int x) {
        return Math.log(x) / Math.log(2);
    }

    public static void main(String[] args) throws NoSuchObjectException {
        try {
            System.out.println("Игра --на баллы-- m, n, k ");
            System.out.println("Enter n, m, k, count of players one at a time");
            Scanner scanner = new Scanner(System.in);
            String n = scanner.next();
            String m = scanner.next();
            String k = scanner.next();
            String pl = scanner.next();
            while (true) {
                if (!isNumber(n) || !isNumber(m) || !isNumber(k) || !isNumber(pl)) {
                    System.out.println("You didn't enter numbers");
                    n = scanner.next();
                    m = scanner.next();
                    k = scanner.next();
                    pl = scanner.next();
                    continue;
                }
                if (Integer.parseInt(n) < 1 || Integer.parseInt(m) < 1 || Integer.parseInt(n) < 1 || Integer.parseInt(pl) < 1) {
                    System.out.println("n, m, k, count of players must be natural numbers");
                    n = scanner.next();
                    m = scanner.next();
                    k = scanner.next();
                    pl = scanner.next();
                    continue;
                }
                if (Integer.parseInt(n) < Integer.parseInt(k) || Integer.parseInt(m) < Integer.parseInt(k)) {
                    System.out.println("pls, get another choice of k");
                    k = scanner.next();
                    continue;
                }
                break;
            }
            List<Integer> players = new ArrayList<>();
            for (int i = 1; i <= Integer.parseInt(pl); i++) {
                players.add(i);
            }
            Collections.shuffle(players);
            OlympicSystem olymp = new OlympicSystem(Integer.parseInt(n), Integer.parseInt(m),
                    Integer.parseInt(k), Integer.parseInt(pl),
                    (int) log2(Integer.parseInt(pl)), players);
            olymp.play();
        } catch (NoSuchElementException e) {
            System.err.println("You have closed the input");
        }
    }

    static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ignored) {
        }
        return false;
    }
}