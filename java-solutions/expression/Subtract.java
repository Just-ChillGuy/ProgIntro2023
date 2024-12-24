package expression;

public class Subtract extends Binary{
    private final static String operation = "-";
    public Subtract(ExpressionImpl left, ExpressionImpl right) {
        super(left, right, operation);
    }

    @Override
    public int evaluate(int x, int y, int z) {

        return left.evaluate(x, y, z) - right.evaluate(x, y, z);
    }
}
