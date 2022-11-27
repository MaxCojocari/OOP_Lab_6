package actors;

public class ContractAccount extends Account {
    public String storageHash;
    public String codeHash;

    public ContractAccount(
            String address,
            int balanceETH,
            String storageHash,
            String codeHash) {
        super(address, 0, balanceETH);
        this.storageHash = storageHash;
        this.codeHash = codeHash;
    }

    public String address() {
        return address;
    };

    public int nonce() {
        return nonce;
    };

    public double balance() {
        return balanceETH;
    };

    public String storageHash() {
        return storageHash;
    }

    public String codeHash() {
        return codeHash;
    }

    public void incNonce() {
        nonce++;
    };

    public void sendETH(double amount, String to) {
        if (amount <= 0)
            return;
        balanceETH -= amount;
    }

    public void receiveETH(double amount) {
        if (amount <= 0)
            return;
        balanceETH += amount;
    }
}
