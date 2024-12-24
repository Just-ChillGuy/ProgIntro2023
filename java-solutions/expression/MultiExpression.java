package expression;

public interface MultiExpression extends Expression, TripleExpression {
    @Override
    default int evaluate(int x) {
        return evaluate(x, 0, 0);
    }
}
