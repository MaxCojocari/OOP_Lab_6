package merkletree.interfaces;

import merkletree.Leaf;

public interface IMerkleTree {
  public Leaf getMerkleRoot();

  public void getLeaves();

  public String computeHash(String input);
}
