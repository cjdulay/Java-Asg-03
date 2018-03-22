import java.util.Scanner;
import java.io.*;

/**
 * This is the interface that the user will use to process Inventory for Phase 2
 * CJ Dulay
 * @version 
 */
/*
 * Instructor: 
 * Assumptions: 
 * Known errors: None (or, a SPECIFIC explanation of what you know doesn't work)
 *                e.g. not "sometime bombs when reading file" bit "bombs when reading if > 20 lines")
 *
 * DELETE extraneous info from this comment
 */
public class Client1 
{
    /**
     *  Based on the user's choice, transactions are processed
     */
    public static void main(String args[]) throws IOException
    {
        int choice;
        int choiceTwo;
        String fileName = "";

        Scanner kb = new Scanner(System.in);
        Warehouse house = new Warehouse();
        String number = "";
        String numberTwo = "";
        String addItemName = "";
        int addOnHand;
        double addUnitPrice;
        int addReOrderPoint; 
        int addEconOrderQty;
        double priceChange = 0.0;

        System.out.println("Enter your filename: ");
        fileName = kb.next();
        house.loadData(fileName);
        house.arraySize();

        choice = 0;
        choiceTwo = 0;

        while (choice != 5)
        {
            mainMenu();
            System.out.println ("Please enter your choice or 5 to exit");
            choice = kb.nextInt();
            Item valid;
            kb.nextLine();
            switch (choice) // Choice for the main menu
            {
                case 1:
                System.out.println ("Please enter the inventory number");
                number = kb.nextLine();
                valid = house.validateNum(number); // validates the item number given by calling the method from the warehouse class
                house.invQuery(number);
                break;
                case 2:

                while (choiceTwo != 4){
                    invMenu();
                    System.out.println("Please enter your choice or 4 to exit: ");
                    choiceTwo = kb.nextInt();
                    kb.nextLine();
                    switch(choiceTwo) {
                        case 1:
                        System.out.println("Enter a new inventory number: ");
                        numberTwo = kb.nextLine();
                        valid = house.validateNum(numberTwo);
                        if (valid == null){ // If the item is not in the array list, add a new one
                            System.out.println("Enter the item name: ");
                            addItemName = kb.nextLine();
                            System.out.println("Enter the on hand amount: ");
                            addOnHand = kb.nextInt();
                            System.out.println("Enter the unit price: ");
                            addUnitPrice = kb.nextDouble();
                            System.out.println("Enter the re order point: ");
                            addReOrderPoint = kb.nextInt();
                            System.out.println("Enter the order quantity: ");
                            addEconOrderQty = kb.nextInt();
                            house.addItem(numberTwo, addItemName, addOnHand, addUnitPrice, addReOrderPoint, addEconOrderQty);
                        } // Adding the data into the warehouse class
                        
                        else {
                            System.out.println("Item already exists.");
                        }
                        break;
                        case 2:
                        System.out.println("Enter the inventory number you wish to remove: ");
                        numberTwo = kb.nextLine();
                        valid = house.validateNum(numberTwo);
                        if (valid != null){ // If the item is present, remove it
                            house.removeItem(numberTwo);
                        }
                        else {
                            System.out.println("Item already doesn't exist.");
                        }
                        break;
                        case 3:
                        System.out.println("Enter the inventory number you wish to change the price to: ");
                        numberTwo = kb.nextLine();
                        valid = house.validateNum(numberTwo);
                        
                        if (valid != null){
                            System.out.print("Enter the price change: ");
                            priceChange = kb.nextDouble();
                            house.changePrice(numberTwo, priceChange); // Gets the arguments which are the item number and the price change
                        }
                        else {
                            System.out.println("Invalid item number.");
                        }
                        break;               
                        default:
                        System.out.println ("That is not a valid choice.  Please re-enter");
                        break;
                    }
                }
                choiceTwo = 0;
                case 3:
                house.tFile();
                break;
                case 4: 
                house.endOfDay();
                System.out.println("");
                System.out.println("Enter output file name:");
                String outputName = kb.next();
                house.output(outputName);
                break;
                case 5:
                System.out.println ("Thank you for using the Inventory Processing System");
                break;
                default:
                System.out.println ("That is not a valid choice.  Please re-enter");

            }
        }
    }

    /**
     *  The Main menu
     */
    public static void mainMenu()
    {
        System.out.println("\nMAIN MENU:");
        System.out.println("1) Inventory item inquiry");
        System.out.println("2) Warehouse and Inventory Maintenance");
        System.out.println("3) Process transactions from the file");
        System.out.println("4) End of Day Processing");
        System.out.println();
        System.out.println("5) Exit");
    }

    /**
     *  The Inventory Maintenance menu
     */
    public static void invMenu()
    {
        System.out.println("\nINVENTORY PROCESSING MENU:");
        System.out.println("1) Adding an Item to the Warehoue");
        System.out.println("2) Removing an Item from the Warehouse");
        System.out.println("3) Changing the price of an Item in the Warehouse"); 
        System.out.println();
        System.out.println("4) Exit");
    } 
}
