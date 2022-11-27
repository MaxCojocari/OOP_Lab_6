package actors.interfaces;

public interface IAccount {
  public abstract String address();

  public abstract int nonce();

  public abstract void incNonce();
}
