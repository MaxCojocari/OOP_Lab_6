package core;

import java.security.*;
import java.util.ArrayList;
import core.interfaces.IBlock;
import core.interfaces.ITransaction;
import merkletree.MerkleTree;

public class Block implements IBlock {
    public int index;
    public long timestamp;
    public String currHash;
    public String prevHash;
    public String merkleRoot;
    public ArrayList<ITransaction> transactions;
    public int nonce;

    public Block(int index, String prevHash, ArrayList<ITransaction> transactions) {
        this.index = index;
        this.timestamp = System.currentTimeMillis();
        this.prevHash = prevHash;
        this.transactions = transactions;
        this.merkleRoot = (new MerkleTree(transactions)).getMerkleRoot().getHash();
        currHash = computeHash();
    }

    public String computeHash() {
        String input = index + timestamp + prevHash + nonce;

        for (ITransaction t : transactions) {
            input += t.transactionInfo();
        }

        try {
            MessageDigest SHA256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = SHA256.digest(input.getBytes("UTF-8"));

            StringBuffer hexString = new StringBuffer();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void mineBlock(int difficulty) {
        nonce = 0;
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!currHash.substring(0, difficulty).equals(target)) {
            nonce++;
            currHash = computeHash();
        }
    }

    public String toString() {
        String s = "Block # " + index + "\n";
        s = s + "PreviousHash:\t" + prevHash + "\n";
        s = s + "Timestamp:\t" + timestamp + "\n";
        s = s + "Nonce:\t\t" + nonce + "\n";
        s = s + "CurrentHash:\t" + currHash + "\n";
        s = s + "Merkle root:\t" + merkleRoot + "\n";
        return s;
    }
}
