package expression;

public abstract class ExpressionImpl implements MultiExpression {
    protected abstract void toExpression(StringBuilder str);

    @Override
    public String toString() {
        StringBuilder strExpression = new StringBuilder();
        toExpression(strExpression);
        return strExpression.toString();
    }
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ExpressionImpl expr) {
            return (expr.toString().equals(this.toString()));
        }
        return false;
    }
}
