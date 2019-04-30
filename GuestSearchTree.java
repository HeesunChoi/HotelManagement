/*
 * This class stores Guest values in a binary search tree. Each node of the tree
 * has the binary search tree property
 */public class GuestSearchTree{
  private GuestTreeNode overallRoot;

  // constructs an empty tree
  public GuestSearchTree(){
    overallRoot = null;
  }

  // Add Guest value to overall tree so as to preserve the binary search tree proberty
  public void add(Guest data){
    overallRoot = add(overallRoot, data);
  }
 
  // Add Guest value; support add method 
  private GuestTreeNode add(GuestTreeNode root, Guest data){
    if(root == null){
      root = new GuestTreeNode(data);
    }else if(data.getName().compareTo(root.data.getName())<0){
      root.left = add(root.left, data);
    }else{
      root.right = add(root.right, data);
    }
    return root;
  }

  // returns true if overall tree contains value
  public boolean contains(String name){
    return contains(overallRoot, name);
  }
  
  // returns true if the given tree contains value
  private boolean contains(GuestTreeNode root, String name){
    if(root == null){
      return false;
    }else if(name.equals(root.data.getName())){
      return true;
    }else if(name.compareTo(root.data.getName())<0){
      return contains(root.left, name);
    }else{
      return contains(root.right, name);
    }
  }

  // returns Guest data from overall tree by name of the Guest
  public Guest getExistInfo(String name){
    return getExistInfo(overallRoot, name);
  }

  // returns Guest data from the given tree by the name of the Guest
  private Guest getExistInfo(GuestTreeNode root, String name){
    if(root == null){
      return null;
    }else if(name.equals(root.data.getName())){
      return root.data;
    }else if(name.compareTo(root.data.getName())<0){
      return getExistInfo(root.left, name);
    }else{
      return getExistInfo(root.right, name);
    }
  }
  
  // prints the tree contents using an inorder traversal
  public void print(){
      printInorder(overallRoot);
      System.out.println();
    }
  
  // prints contents of the tree with given root using an inorder traversal
  private void printInorder(GuestTreeNode root){
      if(root != null){
          printInorder(root.left);  
          System.out.print(root.data + " ");
          printInorder(root.right);
        }
    }


}
