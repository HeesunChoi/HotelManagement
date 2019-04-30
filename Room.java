        import java.io.*;
import java.util.*;

// The Room object to be associated with the Hotel
public class Room {
    private int roomNum;
    private int nodeNum;
    private int roomSize;
    private String bedSize;
    private int bedNum;
    private double cost;
    private Guest guest;
    private boolean allowsPets;


    // the default constructor for the Room object
    public Room() {
        this.nodeNum = 0;
        this.roomNum = 0;
        this.roomSize = 300;
        this.bedSize = "twin";
        this.bedNum = 1;
        this.cost = 89.99;
        // changed the Guest value to default so the toString method would work properly
        this.guest = null; //now guest variable, so a guest is added to the guests arrayList
        this.allowsPets = false;
    }

    // the constructor that will typically be used for Rooms
    public Room(int roomNum, int nodeNum, int roomSize, String bedSize, int bedNum,
                double cost, Guest guest, boolean allowPets) {
        this.roomNum = roomNum;
        this.nodeNum = nodeNum;
        this.roomSize = roomSize;
        this.bedSize = bedSize;
        this.bedNum = bedNum;
        this.cost = cost;
        this.guest = guest;
        this.allowsPets = allowPets;
    }

    // gets the room number
    public int getRoomNum() {
        return this.roomNum;
    }

    // gets the node number of the Room
    public int getNodeNum(){
      return this.nodeNum;
    }

    // gets the size of the Room
    public int getRoomSize() {
        return this.roomSize;
    }

    // gets the type of bed for the Room
    public String getBedSize() {
        return this.bedSize;
    }

    // gets the number of beds for the Room
    public int getBedNum() {
        return this.bedNum;
    }

    // gets the cost of the Room
    public double getCost() {
        return this.cost;
    }

    // gets the Guest
    public Guest getGuest(){
        return this.guest;
    }

    // gets whether a Room allows for pets
    public boolean getAllowPets() {
        return this.allowsPets;
    }

    // sets the number for the Room
    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    // sets the size of the Room
    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    // sets type of bed for the Room
    public void setBedSize(String bedSize) {
        this.bedSize = bedSize;
    }

    // sets the number of beds in the Room
    public void setBedNum(int bedNum) {
        this.bedNum = bedNum;
    }

    // sets the cost of the Room
    public void setCost(double cost) {
        this.cost = cost;
    }

    // sets the Guest of the Room
    public void setGuest(Guest guest){
        this.guest = guest;
    }

    // sets whether a Room allows for pets
    public void setAllowPets(boolean allowPets) {
        this.allowsPets = allowPets;
    }

    // determines if the Room is empty
    public boolean isEmpty() {
        if (guest != null){
            return false;
        }
        return true;
    }

    // sets the Guest empty
    public void makeEmpty(){
        this.guest = null;
    }

    // creates a String to resemble the Object
    public String toString() {
        return "\n\n\tRoom Number: " + roomNum +
                "\n\tRoom Cost: " + cost +
                "\n\tRoom Size: " + roomSize +
                "\n\tBed Number: " + bedNum +
                "\n\tBed Size: " + bedSize +
                "\n\tPet allowed: " + allowsPets + "\n";
    }
}
