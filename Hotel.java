import java.io.*;
import java.util.*;


// The Hotel object to store information and data regarding guests and rooms
public class Hotel {
    Scanner console = new Scanner(System.in);

    // A hotel has a list of rooms and a lits of guests
    LinkedRoomList roomList = new LinkedRoomList();
    GuestSearchTree guestList = new GuestSearchTree();

    // creats a default version of a Hotel
    public Hotel(){
      roomList = null;
      guestList = null;
    }

    // creates rooms of Hotel as reading a text file that contains list of rooms in text format
    public Hotel(String hotelInfoText) throws FileNotFoundException{
      Scanner hotelData = new Scanner(new File(hotelInfoText));
      while(hotelData.hasNextLine()){
        String line = hotelData.nextLine();
        roomList.add(convertLineToRoom(line));
      }
    }

    // converts a text line to create a room
    private Room convertLineToRoom(String line){
      Scanner data = new Scanner(line);

      int roomNum = data.nextInt();
      int nodeNum = data.nextInt();
      double cost = data.nextDouble();
      String bedSize = data.next();
      String allowsPetsData = data.next();
      boolean allowPets = false;
        if(allowsPetsData.equals("no")){
          allowPets = false;
        }else if(allowsPetsData.equals("yes")){
          allowPets = true;
        }
      int bedNum = data.nextInt();
      int roomSize = data.nextInt();

      Room save = new Room(roomNum, nodeNum, roomSize, bedSize,
                          bedNum, cost, null, allowPets);

      return save;
    }


     Scanner guestData = new Scanner(System.in);

    public void addGuest(){
      System.out.println("\n\t--------------------------------------");
      System.out.println("\t|  Welcome to the new HEESUN's HOTEL  |");
      System.out.println("\t--------------------------------------");
      System.out.println("\n\nPlease enter your full name.");

      String name = guestData.nextLine().trim();

      // checks if the hotel alreay has the user info
      if(!guestList.contains(name)){
        // If the Hotel doesn't have info, implements registration process
        System.out.println("\n**You are a brand new customer**");
        Guest save = new Guest(guestData);
        guestList.add(save);
        finalizeReservation(save);
      }else{
        // Hotel has info
        System.out.println("\n**We already have your record**");
        Guest found = guestList.getExistInfo(name); // gets exist guest info

        // if the guest hasn't booked any rooms, ask options
        if(found.getRoom() == null){
            System.out.println("\nHowever, you don't have any booked rooms."+
            "\nEnter 1: Make a reservation by your preference you've already entered."+
            "\nEnter 2: Change your preference.");
            int choice = Guest.numberOfQuestions("", guestData);
            if(choice == 1){
              // makes a reservation by entered preference
               finalizeReservation(found);
            }else if(choice == 2){
              // changes the customer's exist info
               Guest save = new Guest(guestData);
               found.setName(save.getName());
               found.setCardNum(save.getCardNum());
               found.setBedType(save.getBedType());
               found.setBedNum(save.getBedNum());
               found.setHasPets(save.hasPets());
               found.setRoom(save.getRoom());
               found.setIsMembership(save.isMembership());
               found.setIsMilitary(save.isMilitary());
               found.setIsGovernment(save.isGovernment());
               found.setNumOfSeniors(save.getNumOfSeniors());
               found.setNumOfAdults(save.getNumOfAdults());
               found.setNumOfChildren(save.getNumOfChildren());
               found.setTotalCost(save.costOfGuests());

               System.out.println("\nHere are your changed preference data.\n" + found);
            }
        }else{
            // if the customer booked a room
            System.out.println("**You have booked the room num ["+found.getRoom().getRoomNum()+"]**");
            System.out.println("\nEnter 1: Cancel the reservation.\nEnter 2: Show the billing with your preference");
            System.out.println("(You can only modify the reservation after you cancel the current reservation)");
            int choice = Guest.numberOfQuestions("", guestData);
            if(choice == 1){
              // cancel the exist reservation
                Room cancelRoom = found.getRoom();
                cancelReservation(found, cancelRoom);
            }else if(choice == 2){
              // display the billing info with the preferences of the customer
                System.out.println(found);
                System.out.print("\tTotal amount of money you paid $" + totalCost(found, found.getRoom()) + "\n");
            }
        }
      }

    }

   // finds a room that suitables the guest's request
    public int findRoomForGuest(Guest guest){
      System.out.println("\n---Finding a room, just a moment---");
      for(int i=0; i<roomList.totalRoomNumbers(); i++){
        Room room = roomList.roomNodeAt(i).data;
        if(!room.isEmpty()){
          continue;
        } else if (guest.getBedNum() > room.getBedNum()) { // Check guest req for number of beds
            continue;
        } else if(!guest.getBedType().equals(room.getBedSize())) { // Check guest req for bed type
            continue;
        } else if (guest.getRoomSize() > room.getRoomSize()) { // Check guest req for room size
            continue;
        } else if(guest.hasPets() != room.getAllowPets()) { // Check guest req for pets
            continue;
        } else if(makeReservation(guest, room)) { // Check if guest accepted reservation
            return room.getNodeNum();
        }
      }
      return -1;
    }

    // makes a reservation after finding a room
    public boolean makeReservation(Guest guest, Room room){
      System.out.println("\nDo you want to reserve the room [" + room.getRoomNum()+"]?");
      System.out.println(room);
      System.out.print("\tGuest Cost: $");
      System.out.printf(guest.costOfGuests() +"\n", "%.2f");
      System.out.print("\tTotal Cost(Room + Guest): $");
      System.out.printf(totalCost(guest, room) + "\n", "%.2f");

      if (Guest.yesOrNoQuestions("\nYes or No: ", console)) {
          return true;
      } else {
        System.out.println("Looking for other rooms...");
        return false;
      }
   }


   // calculates the total cost that includes the cost of the room and the guest
   public double totalCost(Guest guest, Room room) {
       return Math.round((room.getCost() + guest.costOfGuests())*100D)/100D;
   }

   // finalize the reservation process based on nodes of LinkedRoomList
   public void finalizeReservation(Guest data){
       int foundRoomNode = findRoomForGuest(data);
       if(foundRoomNode == -1){
           System.out.println("**Sorry, no room found**");
       }else{
           Room info = roomList.roomNodeAt(foundRoomNode).data;
           info.setGuest(data);
           data.setRoom(info);
           System.out.println("**Successfully booked for [" +info.getRoomNum()+ "] room**");
       }
  }

  // canceles the exist reservation
  public void cancelReservation(Guest guest, Room room) {
      if(Guest.yesOrNoQuestions("Are you sure you want to cancel?(Y/N)", console)) {
          String receipt = receipt(guest);
          int dollarSignLocation = receipt.indexOf("$");
          receipt = receipt.substring(0, dollarSignLocation) + "-" + receipt.substring(dollarSignLocation); // adds negative sign to signify refund
          System.out.println(receipt);
          guest.checkOut();
          room.makeEmpty();
      } else {
          System.out.println("**Cancelled cancellation process**");
      }
  }

  // displays the receipt of a reservation
  public String receipt(Guest guest) {
      return "\n\t\t~Receipt~\n" +
      "\n\tGuest: " + guest.getName() +
      "\n\tBilling Info(card num): " + guest.getCardNum() +
      "\n\t" + guest.getRoom().toString() +
      "\n\tCost: $" + totalCost(guest, guest.getRoom()) + "\n";
  }
}
