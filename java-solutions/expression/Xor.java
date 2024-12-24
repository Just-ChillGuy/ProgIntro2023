package expression;

public class Xor extends Binary{
    private final static String operation = "^";
    public Xor(ExpressionImpl left, ExpressionImpl right) {
        super(left, right, operation);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return left.evaluate(x, y, z) ^ right.evaluate(x, y, z);
    }
}
