import java.util.*;

import actors.EOAccount;
import core.SwapTx;
import core.TransactionPool;

import static java.util.Map.entry;

public class SwapTxUseCase {
    protected static Random random = new Random();
    private static Map<Integer, String> assets = Map.ofEntries(entry(0, "ETH"), entry(1, "USDT"));

    public void genState(
        int maxNrUsers, 
        int maxNrLiqPools,
        int maxAmountAssetsRand,
        TransactionPool TxPool,
        ArrayList<EOAccount> users,
        ArrayList<EOAccount> liquidityPools
    ) {
        EOAccount user = users.get(random.nextInt(maxNrUsers));
        EOAccount exchange = liquidityPools.get(random.nextInt(maxNrLiqPools));
        
        int iAssetIn = random.nextInt(2);
        int iAssetOut = 1 - iAssetIn;
        int amount = random.nextInt(maxAmountAssetsRand);
        
        SwapTx stx = new SwapTx(user, exchange, assets.get(iAssetIn), assets.get(iAssetOut), amount);
        
        TxPool.addTransaction(stx);
        
        System.out.println(stx.transactionInfo());

        System.out.println("------------------------------------------------------");
    }
}
