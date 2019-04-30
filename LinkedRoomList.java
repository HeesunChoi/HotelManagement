// Class LinkedRoomList can be used to store a list of Rooms
public class LinkedRoomList{
  private ListRoomNode front; // first value in the list

  // a default constructor of linked room list 
  public LinkedRoomList(){
    front = null;
  }

  // add a Room into the list from the Room parameter 
  public void add(Room data){
    if(front == null){
      front = new ListRoomNode(data);
    }else{
      ListRoomNode current = front;
      while(current.next != null){
        current = current.next;
      }
      current.next = new ListRoomNode(data);
    }

  }

  // return total numbers of rooms; size
  public int totalRoomNumbers(){ 
    int count = 0;
    ListRoomNode current = front;
    while(current != null){
      current = current.next;
      count++;
    }
    return count;
  } 
  
  // returns a reference to the node at the given index
  public ListRoomNode roomNodeAt(int index){
    ListRoomNode current = front;
    for(int i=0; i<index; i++){
        current = current.next;
    }
    return current;
  }

  // return boolean data to see if the Room is reserved
  public boolean isReserved(int roomNum){
    return roomNodeAt(roomNum).data.isEmpty();
  }

  // String representation of Rooms of the list
  public String toString(){
    if(front == null){
      return "[]";
    }else{
      String result = "[" + front.data;
      ListRoomNode current = front.next;
      while(current != null){
        result += ", " + current.data;
        current = current.next;
      }
      result += "]";
      return result;
    }
  }
}
