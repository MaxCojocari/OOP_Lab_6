package crypto.interfaces;

public interface IAsset {
    public abstract String name();

    public abstract String symbol();

    public abstract int decimals();

    public abstract void transfer(int amount, String from, String to);

    public abstract int balanceOf(String address);
}
