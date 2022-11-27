package merkletree.interfaces;

import merkletree.Leaf;

public interface ILeaf {
  public String getHash();

  public Leaf leftChild();

  public Leaf rightChild();
}
