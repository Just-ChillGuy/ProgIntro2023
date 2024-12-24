package game;

import java.io.PrintStream;
import java.rmi.NoSuchObjectException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static game.Main.isNumber;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {

        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            String in1 = null;
            String in2 = null;
            try {
                in1 = in.next();
                in2 = in.next();
                while (true) {
                    if (!isNumber(in1) || !isNumber(in2)) {
                        out.println("The values entered must be integers at least 0");
                        in1 = in.next();
                        in2 = in.next();
                        continue;
                    }
                    break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Congratulations, you were able to finish the tournament ahead of schedule, do not come to us anymore");
            }
            while (true) {
                if (!isNumber(in1) || !isNumber(in2)) {
                    out.println("The values entered must be integers at least 0");
                    in1 = in.next();
                    in2 = in.next();
                    continue;
                }
                break;
            }
            Move move = new Move(Integer.parseInt(in1), Integer.parseInt(in2), cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }


    }

}
