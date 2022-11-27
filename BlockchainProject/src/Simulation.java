import java.util.*;

import actors.EOAccount;
import core.Block;
import core.Blockchain;
import core.TransactionPool;

public class Simulation {
    static int maxNrUsers = 10; 
    static int maxNrLiqPools = 10; 
    static int maxNrVaults = 5; 
    static int maxAmountAssetsRand = 10000;
    static int maxAmountAssetsUser = 10000;
    static int nrBlocks = 31;
    static int poolSize = 20;
    static int difficulty = 3;
    
    protected static Random random = new Random(); 
    private static ArrayList<EOAccount> users = new ArrayList<EOAccount>();
    private static ArrayList<EOAccount> liquidityPools = new ArrayList<EOAccount>();
    private static ArrayList<EOAccount> vaults = new ArrayList<EOAccount>();
    public static Queue<Object> queue = new LinkedList<Object>();

    public static TransferTxUseCase transferTxCase = new TransferTxUseCase();
    public static SwapTxUseCase swapTxUseCase = new SwapTxUseCase();
    public static LockVaultTxUseCase lockVaultTxUseCase = new LockVaultTxUseCase();

    public static void main(String[] args) {
        genUsers(users);
        genSwaps(liquidityPools);
        genVaults(vaults);

        Block block = null;
        Blockchain blockchain = new Blockchain();
        TransactionPool TxPool = new TransactionPool();
        TxPool.setPoolSize(poolSize);

        int k = 0;
        int q = 0;
        System.out.println("Balances before");
        for (EOAccount a: users) {
            System.out.println(a.address);
            System.out.println(a.balanceETH + " ETH");
            System.out.println(a.balanceUSDT + " USDT");
        }

        System.out.println("\n");

        while (k < nrBlocks * poolSize) {
            int option = random.nextInt(3);
            if (option == 0) {
                transferTxCase.genState(
                    maxNrUsers, 
                    maxAmountAssetsRand, 
                    TxPool, 
                    users
                );
            } else if (option == 1) {
                swapTxUseCase.genState(
                    maxNrUsers, 
                    maxNrLiqPools,
                    maxAmountAssetsRand, 
                    TxPool, 
                    users, 
                    liquidityPools
                );
            } else {
                lockVaultTxUseCase.getState(
                    maxNrUsers,
                    maxNrLiqPools,
                    maxAmountAssetsRand,
                    TxPool,
                    users,
                    liquidityPools
                );
            }
            k++;
            if (TxPool.isPoolFull()) {
                if (q == 0) 
                    block = new Block(q++, null, TxPool.getPool());
                else block = new Block(q++, block.currHash, TxPool.getPool());
                block.mineBlock(difficulty);
                blockchain.addBlock(block);
            }
        }  
        blockchain.getBlocks();

        System.out.println("Balances after");
        for (EOAccount a: users) {
            System.out.println(a.address);
            System.out.println(a.balanceETH + " ETH");
            System.out.println(a.balanceUSDT + " USDT");
        }
    }

    public static void genUsers(ArrayList<EOAccount> a) {
        for (int i = 0; i < maxNrUsers; ++i) {
            EOAccount newUser = new EOAccount(
                getRandomAddress(40),
                random.nextInt(maxAmountAssetsUser), 
                random.nextInt(maxAmountAssetsUser)
            );
            a.add(newUser);
        }
    }
    
    public static void genSwaps(ArrayList<EOAccount> a) {
        for (int i = 0; i < maxNrLiqPools; ++i) {
            EOAccount newLiquidityPool = new EOAccount(
                getRandomAddress(40),
                random.nextInt(100000), 
                random.nextInt(100000)
            );
            a.add(newLiquidityPool);
        }
    }

    public static void genVaults(ArrayList<EOAccount> a) {
        for (int i = 0; i < maxNrVaults; ++i) {
            EOAccount newVault = new EOAccount(
                getRandomAddress(40),
                random.nextInt(10), 
                random.nextInt(10)
            );
            a.add(newVault);
        }
    }
    
    private static String getRandomAddress(int numchars){
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(random.nextInt()));
        }
        
        return "0x" + sb.toString().substring(0, numchars);
    }
}