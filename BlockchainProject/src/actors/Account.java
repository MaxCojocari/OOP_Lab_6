package actors;

import actors.interfaces.IAccount;

abstract class Account implements IAccount {
    public String address;
    public int nonce;
    public double balanceETH;

    public Account(String address, int nonce, double balanceETH) {
        this.address = address;
        this.nonce = nonce;
        this.balanceETH = balanceETH;
    }
}
