package core;

import actors.EOAccount;

public class TransferTx extends Transaction {
    private String assetSymbol;
    private double amount;

    public TransferTx(EOAccount sender, EOAccount receiver, String assetSymbol, double amount) {
        super(sender, receiver);
        this.assetSymbol = assetSymbol;
        this.amount = amount;

        if (assetSymbol.equals("ETH")) {
            if (sender.sendETH(amount, assetSymbol))
                receiver.receiveETH(amount);
        } else {
            if (sender.sendUSDT(amount, assetSymbol))
                receiver.receiveUSDT(amount);
        }
    }

    public String transactionInfo() {
        String s = "TransferTx \n";
        s += "Sender:\t\t" + super.sender().address + "\n";
        s += "Receiver:\t" + super.receiver().address + "\n";
        s += "Amount:\t\t" + amount + " " + assetSymbol + "\n";
        return s;
    }

    public double amount() {
        return amount;
    }

    public String assetSymbol() {
        return assetSymbol;
    }
}
