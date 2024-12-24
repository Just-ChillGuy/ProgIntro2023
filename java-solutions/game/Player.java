package game;

import java.rmi.NoSuchObjectException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Player {
    Move move(Position position, Cell cell) throws NoSuchObjectException;


}
