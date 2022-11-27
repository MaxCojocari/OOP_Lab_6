package core;

import java.util.*;

import actors.EOAccount;

public class LockVaultTx extends Transaction {
    private String assetSymbol;
    private Map<String, Integer> amountLocked = new HashMap<String, Integer>();
    private Map<String, Boolean> isLocked = new HashMap<String, Boolean>();
    private double timeLock = 10;
    private long startTime;
    private double annualInterestRate = 0.0005;

    public LockVaultTx(EOAccount sender, EOAccount vault, String assetSymbol, int amountLockedInVault) {
        super(sender, vault);
        this.assetSymbol = assetSymbol;
        timeLock = 10;
        this.startTime = System.currentTimeMillis();
        amountLocked.put(sender.address, 0);

        if (assetSymbol.equals("USDT")) {
            if (sender.sendUSDT(amountLockedInVault, vault.address)) {
                vault.receiveUSDT(amountLockedInVault);
                amountLocked.put(sender.address, amountLockedInVault);
                isLocked.put(sender.address, true);
            }
        } else if (assetSymbol.equals("ETH")) {
            if (sender.sendETH(amountLockedInVault, vault.address)) {
                vault.receiveETH(amountLockedInVault);
                amountLocked.put(sender.address, amountLockedInVault);
                isLocked.put(sender.address, true);
            }
        }
    }

    public String transactionInfo() {
        String s = "LockVaultTx \n";
        s += "Sender:\t\t" + super.sender().address + "\n";
        s += "Receiver:\t" + super.receiver().address + "\n";
        s += "Amount:\t\t" + amountLocked.get(super.sender().address) + " " + assetSymbol + "\n";
        return s;
    }

    public boolean isAmountLocked(EOAccount owner) {
        return isLocked.get(owner.address);
    }

    public void unlockAmount(EOAccount sender, EOAccount vault) {
        if (isLocked.get(sender.address) != null) {

            if ((startTime + timeLock < System.currentTimeMillis())) {
                isLocked.put(sender.address, false);

                if (assetSymbol.equals("USDT") &&
                        vault.sendUSDT(amountLocked.get(sender.address) * (1 + annualInterestRate), sender.address)) {
                    sender.receiveUSDT(amountLocked.get(sender.address) * (1 + annualInterestRate));
                    System.out.println("Amount USDT out (+ interest rate): "
                            + amountLocked.get(sender.address) * (1 + annualInterestRate));
                }

                else if (assetSymbol.equals("ETH") &&
                        vault.sendETH(amountLocked.get(sender.address) * (1 + annualInterestRate), sender.address)) {
                    sender.receiveETH(amountLocked.get(sender.address) * (1 + annualInterestRate));
                    System.out.println("Amount ETH out (+ interest rate): "
                            + amountLocked.get(sender.address) * (1 + annualInterestRate));
                }

            } else
                System.out.println("EARLY_WITHDRAWING" + "\n");
        }
    }
}
