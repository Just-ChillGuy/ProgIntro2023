package expression;

public class Variable extends ExpressionImpl {
    private final String name;
    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (name.equals("x")) {
            return x;
        } else if (name.equals("y")) {
            return y;
        }
        return z;
    }

    @Override
    protected void toExpression(StringBuilder str) {
        str.append(name);
    }
}
