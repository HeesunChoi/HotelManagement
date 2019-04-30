import java.util.*;
import java.io.*;

/*
 * Guest class that stores customer's information and preferences
 */
public class Guest {

    // all discounts apply 0.8 to total coast
    private boolean isMembership;
    private boolean isMilitary;
    private boolean isGovernment;

    // the additional cost of persons
    private int numOfSeniors; //*4.99
    private int numOfAdults; //*9.99
    private int numOfChildren; //*3.99

    // fields that are attached to who the guest is
    private String name;
    private long cardNum;
    private String bedType;
    private int bedNum;
    private int roomSize = 0;
    private boolean hasPets;

    private Room room; // stores Room objects, if not, null
    private double totalCost = 0;

    // creates a default version of a Guest
    public Guest() {
        this.isMembership = false;
        this.isMilitary = false;
        this.isGovernment = false;
        this.numOfSeniors = 0;
        this.numOfAdults = 0;
        this.numOfChildren = 0;
        this.hasPets = false;

        this.name = "";
        this.cardNum = 0;

        this.bedType = "";
        this.bedNum = 0;
        this.roomSize = 0;

        this.room = null;
    }

    // creates a specified version of a Guest with every field to be initialized
    public Guest(boolean isMembership, boolean isMilitary, boolean isGovernment,
               int numOfSeniors, int numOfAdults, int numOfChildren,
               boolean hasPets, String name, int cardNum, String bedType
                 , int bedNum, int roomSize) {
        this.isMembership = isMembership;
        this.isMilitary = isMilitary;
        this.isGovernment = isGovernment;
        this.numOfSeniors = numOfSeniors;
        this.numOfAdults = numOfAdults;
        this.numOfChildren = numOfChildren;
        this.hasPets = hasPets;

        this.name = name;
        this.cardNum = cardNum;

        this.bedType = bedType;
        this.bedNum = bedNum;
        this.roomSize = roomSize;

    }



    // Guest creation through scanner, the ideal Guest creation.
    public Guest(Scanner console) {
        // Begins the information gathering process
        System.out.print("\n--Registration process--\n");
        System.out.print("What is your full name?\n");
        this.name = console.nextLine().trim().toLowerCase().replaceAll("[^a-z]", " ");
        System.out.println();

        // Gives further instructions
        System.out.println("For the next few questions answer with a single integer.");
        // initializes fields for every question
        this.cardNum = getCreditCard(console);
        this.numOfAdults = numberOfQuestions("How many adults?\n", console);
        this.numOfSeniors = numberOfQuestions("How many seniors?\n", console);
        this.numOfChildren = numberOfQuestions("How many children?\n", console);
        askRoomSize(console);
        askBedNum(console);
        askBedType(console);

        // Changes the instructions but still initializes the fields
        System.out.println("\nFor the next few questions answer with Y or N.");
        this.isMembership = yesOrNoQuestions("Do you have a hotel membership?\n", console);
        this.isMilitary = yesOrNoQuestions("Are you a veteran?\n", console);
        this.isGovernment = yesOrNoQuestions("Are you a government employee?\n", console);
        this.hasPets = yesOrNoQuestions("Do you have pets?\n", console);

    }

    // Asks a guest what bed they would like
    private void askBedType(Scanner console) {
        int bedType;
        while(this.bedType == null) {
            // uses switch, case to find what bed a Guest would like
            bedType = numberOfQuestions("What bed type? (king = 1, queen = 2, twin = 3)\n", console);
            switch(bedType) {
                case 1:
                    this.bedType = "king";
                    break;
                case 2:
                    this.bedType = "queen";
                    break;
                case 3:
                    this.bedType = "twin";
                    break;
                case 4:
                    this.bedType = "full";
                    break;
                default:
                    // prompts for invalid responses
                    System.out.println("Invalid number. Please try again.");
            }
        }
    }

    // asks a guest what room size they would like
        private void askRoomSize(Scanner console) {
        int roomSize;
        while(this.roomSize == 0) {
            // uses switch, case to find what room size a Guest would like
            roomSize = numberOfQuestions("How big does your room need to be? (square feet)\n"+
                        " Suite is 600\n Deluxe is 500\n Standard is 300\n", console);
            switch(roomSize) {
                case 600:
                    this.roomSize = 600;
                    break;
                case 500:
                    this.roomSize = 500;
                    break;
                case 300:
                    this.roomSize = 300;
                    break;
                default:
                    // prompts for invalid responses
                    System.out.println("Invalid number. Please try again.");
            }
        }
    }

    // asks a guest what bed numbers they would like
        private void askBedNum(Scanner console) {
        int bedNum;
        while(this.bedNum == 0) {
            // uses switch, case to find what bed numbers a Guest would like
            bedNum = numberOfQuestions("How many beds? (1 or 2)\n", console);
            switch(bedNum) {
                case 1:
                    this.bedNum = 1;
                    break;
                case 2:
                    this.bedNum = 2;
                    break;
                default:
                    // prompts for invalid responses
                    System.out.println("Invalid number. Please try again.");
            }
        }
    }

    // Asks for credit card number (16 digits) uses a Scanner in the parameter
    public static long getCreditCard(Scanner console) {
        System.out.print("What is your card number? (16 digits)\n");
        long cardNumber;
        String input;
        while(true) {
            try {
                input = console.nextLine().replaceAll("[^0-9]", "");
                if(input.length() < 16) {
                    throw new NumberFormatException();
                }
                // stores the cardNumber to return
                cardNumber = Long.parseLong(input);
                break;
            } catch(NumberFormatException e) {
                // catches an invalid response and re-asks the question
                System.out.println("Invalid number. Try again.");
                System.out.print("What is your card number? (16 digits)\n");
            }
        }
        // returns the card number
        return cardNumber;
    }

    // Asks a question that requires yes or no response or y or n
    public static boolean yesOrNoQuestions(String question, Scanner console) {
        while(true) {
            try {
                // prints the question to be asked
                System.out.print(question);
                // stores the answer
                String answer = console.nextLine().toLowerCase();
                // determines if the answer was yes or no or y or n
                if(answer.contains("n")) {
                    return false;
                } else if(answer.contains("y")) {
                    return true;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch(IllegalArgumentException e) {
                // catches an answer that doesn't involve yes/no/y/n
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    // Asks a question that requires integer response
    public static int numberOfQuestions(String question, Scanner console) {
        // stores the return value
        int answer;
        // asks the question
        System.out.print(question);
        while (true) {
            try {
                // stores the value if an interger is entered
                answer = Integer.parseInt(console.nextLine());
                break;
            } catch (NumberFormatException e) {
                // catches an invalid response and re-asks question
                System.out.println("Invalid input. Try again.");
                System.out.print(question);
            }
        }
        return answer;
    }

    // calculates the total cost of the Guest
    public double costOfGuests() {
        double total = (numOfSeniors * 4.99 + numOfAdults * 9.99 + numOfChildren * 3.99);
        if (isMembership || isMilitary || isGovernment) {
            total *= 0.8;
        }
        return Math.round(total*100D)/100D;
    }

    // returns true if the Guest is membership
    public boolean isMembership() {
        return isMembership;
    }

    // returns true if the Guest is in military
    public boolean isMilitary() {
        return isMilitary;
    }

    // returns true if the Guest is government worker
    public boolean isGovernment() {
        return isGovernment;
    }

    // get number of seniors of the Guest
    public int getNumOfSeniors() {
        return numOfSeniors;
    }

    // get number of adults of the Guest
    public int getNumOfAdults() {
        return numOfAdults;
    }

    // get number of children of the Guest
    public int getNumOfChildren() {
        return numOfChildren;
    }

    // gets the name of the Guest
    public String getName(){
        return name;
    }

    // gets the card numbers of the Guest
    public long getCardNum() {
        return cardNum;
    }

    // gets the bed type of the Guest
    public String getBedType() {
        return bedType;
    }

    // gets the bed number of the Guest
    public int getBedNum() {
        return bedNum;
    }

    // gets the room size of the Guest
    public int getRoomSize() {
        return roomSize;
    }

    // returns true if the Guest has pets
    public boolean hasPets() {
        return hasPets;
    }

    // returns the Room of the Guest
    public Room getRoom() {
        return room;
    }

    // set the Guets's membership statement
    public void setIsMembership(boolean stat){
        this.isMembership = stat;
    }

    // sets the Guets's military statement
    public void setIsMilitary(boolean stat){
        this.isMilitary = stat;
    }

    // sets the Guets's government statement
    public void setIsGovernment(boolean stat){
        this.isGovernment = stat;
    }

    // sets the number of seniors of the Guest
    public void setNumOfSeniors(int stat){
        this.numOfSeniors = stat;
    }

    // sets the number of adults of the Guest
    public void setNumOfAdults(int stat){
        this.numOfAdults = stat;
    }

    // sets the number of children of the Guest
    public void setNumOfChildren(int stat){
        this.numOfChildren = stat;
    }

    // sets the name of the Guest
    public void setName(String name){
        this.name = name;
    }

    // sets the card number of the Guest
    public void setCardNum(long card){
        this.cardNum = card;
    }

    // sets the bed type of the Guest
    public void setBedType(String bed){
        this.bedType = bed;
    }

    // sets the bed numbers of the Guest
    public void setBedNum(int bed){
        this.bedNum = bed;
    }

    // sets hasPets value
    public void setHasPets(boolean pet){
        this.hasPets = pet;
    }

    // sets the Room value
    public void setRoom(Room room){
        this.room = room;
    }

    // sets the total cost of the Guest
    public void setTotalCost(double cost){
        this.totalCost = cost;
    }

    // Adds guest to room if room is empty
    public boolean checkIn(Room room) {
        // Guest check in (use Room class)
        if(this.room != null) { // this.room != null
            System.out.println(name + " already has a room");
            return false;
        }
        this.room = room;
        return true;
    }

    // Guest check out (use Room class)
    public void checkOut() {
        room = null;
    }


    // Creates a String representation of the object
    public String toString() {
        return "\n\t~Guest Info~\n" +
               "\n\tName: " + name +
               "\n\tCard Number: " + cardNum +
               "\n\tMembership: " + isMembership +
               "\n\tMilitary Discount: " + isMilitary +
               "\n\tGovernment Discount: " + isGovernment +
               "\n\tHas Pets: " + hasPets +
               "\n\tNumber of Seniors: " + numOfSeniors +
               "\n\tNumber of Adults: " + numOfAdults +
               "\n\tNumber of Children: " + numOfChildren +
               "\n\tBed Type: " + bedType +
               "\n\tBed Number: " + bedNum +
               "\n\tRoom Size: " + roomSize;
    }

}
