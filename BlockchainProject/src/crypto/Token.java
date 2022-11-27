package crypto;

abstract class Token extends Asset {
    public Token(String name, String symbol) {
        super(name, symbol);
    }

    public abstract void approve(String owner, String spender, int value);

    public abstract int allowance(String owner, String spender);

}
