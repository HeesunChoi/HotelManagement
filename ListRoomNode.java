// ListRoomNode is a class for storing a single node of a LinkedRoomList.
// This node class is for a list of Room values.
public class ListRoomNode{
  public Room data;
  public ListRoomNode next;

  // constructs a node with null data and null link
  public ListRoomNode(){
    this(null, null);
  }

  // constructs a node with given data and null link
  public ListRoomNode(Room data){
    this(data, null);
  }

  // constructs a node with given data and given link
  public ListRoomNode(Room data, ListRoomNode next){
    this.data = data;
    this.next = next;
  }

 }
