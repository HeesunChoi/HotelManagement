import java.io.*;
import java.util.*;
/*
 * The Main class implements an program that reads a text file. 
 */
public class Main{
    public static void main(String[] args) throws FileNotFoundException{
        Scanner input = new Scanner(System.in);
        Hotel hotels = new Hotel("SampleHotelData3.txt");
        do{
            hotels.addGuest();
            // implements the program until user wants to stop.
        } while(Guest.yesOrNoQuestions("\nDo you want to quit the program?", input) == false);
        
      
      System.out.println("\n\t--------------------------------------");
      System.out.println("\t|    Thank you for using! Good Bye!   |");
      System.out.println("\t--------------------------------------");
      }
         
  }