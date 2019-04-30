// Class for storing a single node of a binary tree of Guests; GuestTreeNode
public class GuestTreeNode{
  public Guest data;
  public GuestTreeNode left;
  public GuestTreeNode right;

  // constructs a leaf node with given data
  public GuestTreeNode(Guest data){
    this(data, null, null);
  }

  // constructs a branch node with given data, left subtree, right subtree
  public GuestTreeNode(Guest data, GuestTreeNode left, GuestTreeNode right){
    this.data = data;
    this.left = left;
    this.right = right;
  }
}
