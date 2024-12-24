package game;

import java.util.Random;

///**
// * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
// */
public class RandomPlayer implements Player {
    private final Random random;
    private final int n;
    private final int m;

    public RandomPlayer(final Random random, int n, int m) {
        this.random = random;
        this.n = n;
        this.m = m;
    }

    public RandomPlayer(int n, int m) {
        this(new Random(), n, m);
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(n);
            int c = random.nextInt(m);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
