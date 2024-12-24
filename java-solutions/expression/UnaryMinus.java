package expression;

public class UnaryMinus extends ExpressionImpl {
    private static final String operation = "-";
    private final ExpressionImpl expression;

    public UnaryMinus(ExpressionImpl expression) {
        this.expression = expression;
    }

    @Override
    protected void toExpression(StringBuilder str) {
        str.append(operation);
        str.append("(");
        expression.toExpression(str);
        str.append(")");
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -expression.evaluate(x, y, z);
    }
}
