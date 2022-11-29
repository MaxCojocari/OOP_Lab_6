package utils;

import java.util.*;

import actors.EOAccount;
import core.TransactionPool;
import core.TransferTx;

import static java.util.Map.entry;

public class TransferTxUseCase {
    private int i = 0;
    private int j = 0;
    protected static Random random = new Random();
    private static Map<Integer, String> assets = Map.ofEntries(entry(0, "ETH"), entry(1, "USDT"));

    public void genState(
            int maxNrUsers,
            int maxAmountAssetsRand,
            TransactionPool TxPool,
            ArrayList<EOAccount> users) {
        while (i == j) {
            i = random.nextInt(maxNrUsers);
            j = random.nextInt(maxNrUsers);
        }
        EOAccount user1 = users.get(i);
        EOAccount user2 = users.get(j);

        String asset = assets.get(random.nextInt(2));
        int amount = random.nextInt(maxAmountAssetsRand);

        TransferTx tx = new TransferTx(user1, user2, asset, amount);
        TxPool.addTransaction(tx);
        System.out.println(tx.transactionInfo());

        System.out.println("------------------------------------------------------");
    }
}
