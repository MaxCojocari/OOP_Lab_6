import java.io.Console;
import java.util.*;

import actors.EOAccount;
import core.Block;
import core.Blockchain;
import core.TransactionPool;
import utils.LockVaultTxUseCase;
import utils.SwapTxUseCase;
import utils.TransferTxUseCase;

public class Model {
    public final int maxNrUsers;
    public final int maxNrLiqPools;
    public final int maxNrVaults;
    public final int maxAmountAssetsRand;
    public final int maxAmountAssetsUser;
    public final int poolSize;
    public final int nrBlocks;
    public final int difficulty;

    public Random random = new Random();
    public ArrayList<EOAccount> users;
    public ArrayList<EOAccount> liquidityPools;
    public ArrayList<EOAccount> vaults;

    public TransferTxUseCase transferTxCase = new TransferTxUseCase();
    public SwapTxUseCase swapTxUseCase = new SwapTxUseCase();
    public LockVaultTxUseCase lockVaultTxUseCase = new LockVaultTxUseCase();

    Block block;
    Blockchain blockchain = new Blockchain();
    TransactionPool TxPool = new TransactionPool();
    int q = 0;

    public Model(
            int poolSize,
            int maxNrUsers,
            int maxNrLiqPools,
            int maxNrVaults,
            int maxAmountAssetsRand,
            int maxAmountAssetsUser,
            int nrBlocks,
            int difficulty) {
        this.poolSize = poolSize;
        this.maxNrUsers = maxNrUsers;
        this.maxNrLiqPools = maxNrLiqPools;
        this.maxNrVaults = maxNrVaults;
        this.maxAmountAssetsRand = maxAmountAssetsRand;
        this.maxAmountAssetsUser = maxAmountAssetsUser;
        this.nrBlocks = nrBlocks;
        this.difficulty = difficulty;

        users = new ArrayList<EOAccount>();
        liquidityPools = new ArrayList<EOAccount>();
        vaults = new ArrayList<EOAccount>();

        transferTxCase = new TransferTxUseCase();
        swapTxUseCase = new SwapTxUseCase();
        lockVaultTxUseCase = new LockVaultTxUseCase();

        blockchain = new Blockchain();
        TxPool = new TransactionPool();
        TxPool.setPoolSize(this.poolSize);
    }

    public void genActors() {
        genUsers(users, maxNrUsers, maxAmountAssetsUser);
        genSwaps(liquidityPools, maxNrLiqPools);
        genVaults(vaults, maxNrVaults);
    }

    public void genStateTransferTx() {
        transferTxCase.genState(maxNrUsers, maxAmountAssetsRand, TxPool, users);
    }

    public void genStateLockTx() {
        lockVaultTxUseCase.genState(maxNrUsers, maxNrLiqPools, maxAmountAssetsRand, TxPool, users, liquidityPools);
    }

    public void genStateSwapTx() {
        swapTxUseCase.genState(maxNrUsers, maxNrLiqPools, maxAmountAssetsRand, TxPool, users, liquidityPools);
    }

    public void genUsers(ArrayList<EOAccount> a, int maxNrUsers, int maxAmountAssetsUser) {
        for (int i = 0; i < maxNrUsers; ++i) {
            EOAccount newUser = new EOAccount(
                    getRandomAddress(40),
                    random.nextInt(maxAmountAssetsUser),
                    random.nextInt(maxAmountAssetsUser));
            a.add(newUser);
        }
    }

    public void genSwaps(ArrayList<EOAccount> a, int maxNrLiqPools) {
        for (int i = 0; i < maxNrLiqPools; ++i) {
            EOAccount newLiquidityPool = new EOAccount(
                    getRandomAddress(40),
                    random.nextInt(100000),
                    random.nextInt(100000));
            a.add(newLiquidityPool);
        }
    }

    public void genVaults(ArrayList<EOAccount> a, int maxNrVaults) {
        for (int i = 0; i < maxNrVaults; ++i) {
            EOAccount newVault = new EOAccount(
                    getRandomAddress(40),
                    random.nextInt(10),
                    random.nextInt(10));
            a.add(newVault);
        }
    }

    private String getRandomAddress(int numchars) {
        StringBuffer sb = new StringBuffer();
        while (sb.length() < numchars) {
            sb.append(Integer.toHexString(random.nextInt()));
        }

        return "0x" + sb.toString().substring(0, numchars);
    }

    public void addAndMineBlock() {
        if (TxPool.isPoolFull()) {
            if (q == 0)
                block = new Block(q++, null, TxPool.getPool());
            else
                block = new Block(q++, block.currHash, TxPool.getPool());
            block.mineBlock(difficulty);
            blockchain.addBlock(block);
        }
    }

}
