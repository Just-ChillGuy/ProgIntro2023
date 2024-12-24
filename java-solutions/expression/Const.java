package expression;

public class Const extends ExpressionImpl {
    private final int constant;
    public Const(int constant) {
        this.constant = constant;
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return this.constant;
    }

    @Override
    protected void toExpression(StringBuilder str) {
        str.append(constant == 0 ? "0" : constant);
    }
}