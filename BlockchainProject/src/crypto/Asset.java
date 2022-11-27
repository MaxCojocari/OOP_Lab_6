package crypto;

import crypto.interfaces.IAsset;

abstract class Asset implements IAsset {
  private String name;
  private String symbol;

  public Asset(String name, String symbol) {
    this.name = name;
    this.symbol = symbol;
  }

  public String name() {
    return name;
  }

  public String symbol() {
    return symbol;
  }

  public int decimals() {
    return 18;
  }
}
