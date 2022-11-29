package utils;

import java.util.*;

import actors.EOAccount;
import core.LockVaultTx;
import core.TransactionPool;
import java.util.concurrent.TimeUnit;

import static java.util.Map.entry;

public class LockVaultTxUseCase {
    protected static Random random = new Random();
    private static Map<Integer, String> assets = Map.ofEntries(entry(0, "ETH"), entry(1, "USDT"));

    public void genState(
            int maxNrUsers,
            int maxNrLiqPools,
            int maxAmountAssetsRand,
            TransactionPool TxPool,
            ArrayList<EOAccount> users,
            ArrayList<EOAccount> liquidityPools) {
        EOAccount user = users.get(random.nextInt(maxNrUsers));
        EOAccount vault = liquidityPools.get(random.nextInt(maxNrLiqPools));

        int amountToLock = random.nextInt(maxAmountAssetsRand);
        int time = random.nextInt(20);
        int iAssetIn = random.nextInt(2);

        // View
        System.out.println("Amount to be locked " + amountToLock + " " + assets.get(iAssetIn));
        System.out.println("Time spent: " + time + " ms");

        // data
        LockVaultTx ltx = new LockVaultTx(user, vault, assets.get(iAssetIn), amountToLock);

        // controller
        TxPool.addTransaction(ltx);

        System.out.println(ltx.transactionInfo());

        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ltx.unlockAmount(user, vault);

        System.out.println("------------------------------------------------------");
    }

}
