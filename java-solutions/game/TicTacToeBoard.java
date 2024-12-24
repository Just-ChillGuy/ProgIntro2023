package game;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class TicTacToeBoard implements Board, Position {
    protected final int n;
    protected final int m;
    protected final int k;
    private int cntMoves = 1;
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;

    public TicTacToeBoard(int n, int m, int k) throws RuntimeException {
        this.n = n;
        this.m = m;
        this.k = k;

        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (move.getRow() == -1 && move.getColumn() == -1) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();

        if (checkVertical(move) || checkHorizontal(move)
                || checkSideDiagonal(move) || checkMainDiagonal(move)) {
            turn = turn == Cell.X ? Cell.O : Cell.X;

            return Result.WIN;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        if (cntMoves == m * n) {
            return Result.DRAW;
        }
        cntMoves++;
        return Result.UNKNOWN;
    }
    private boolean checkVertical(Move move) {
        int count = 0;

        int r = move.getRow();
        int c = move.getColumn();

        for (int i = r; i >= 0; i--) {
            if (cells[i][c] == turn) {
                count++;
            } else {
                break;
            }
        }

        for (int i = r + 1; i < n; i++) {
            if (cells[i][c] == turn) {
                count++;
            } else {
                break;
            }
        }
        return count >= k;
    }

    private boolean checkHorizontal(Move move) {
        int count = 0;

        int r = move.getRow();
        int c = move.getColumn();

        for (int i = c; i >= 0; i--) {
            if (cells[r][i] == turn) {
                count++;
            } else {
                break;
            }
        }

        for (int i = c + 1; i < n; i++) {
            if (cells[r][i] == turn) {
                count++;
            } else {
                break;
            }
        }
        return count >= k;
    }
    private boolean checkSideDiagonal(Move move) {
        int count = 0;
        int r = move.getRow();
        int c = move.getColumn();
        for (int i = r; i >= 0; i--, c++) {
            if (c == n) {
                break;
            }
            if (cells[i][c] == turn) {
                count++;
            } else {
                break;
            }
        }
        c = move.getColumn() - 1;
        for (int i = r + 1; i < n; i++, c--) {
            if (c < 0) {
                break;
            }
            if (cells[i][c] == turn) {
                count++;
            } else {
                break;
            }
        }
        return count >= k;
    }
    private boolean checkMainDiagonal(Move move) {
        int count = 0;
        int r = move.getRow();
        int c = move.getColumn();
        for (int i = r; i >= 0 && c >= 0; i--, c--) {
            if (cells[i][c] == turn) {
                count++;
            } else {
                break;
            }
        }
        c = move.getColumn() + 1;

        for (int i = r + 1; i < n && c != n; i++, c++) {
            if (cells[i][c] == turn) {
                count++;
            } else {
                break;
            }
        }
        return count >= k;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getColumn() && move.getColumn() < m
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }
    public boolean isBoard(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getColumn() && move.getColumn() < m;
    }
    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < Integer.toString(n).length(); i++) {
            sb.append(' ');
        }
        for (int i = 0; i < m; i++) {
            sb.append(i);
            for (int y = 0; y < Integer.toString(n).length(); y++) {
                sb.append(' ');
            }
        }
        for (int r = 0; r < n; r++) {
            sb.append(System.lineSeparator());
            sb.append(r);
            for (int y = 0; y < Integer.toString(n).length() - Integer.toString(r).length() + 1; y++) {
                sb.append(' ');
            }
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c])).append(" ".repeat(Integer.toString(c).length() + Integer.toString(m).length() - 1));

            }
        }
        return sb.toString();
    }
}
