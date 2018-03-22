import java.util.Scanner;
import java.io.*;

/**
 * This is the interface that the user will use to process Inventory
 * 
 * @CJ Dulay
 * @ Due Date
 */

public class Interface 
{
    public static void main(String args[]) throws IOException
    {
        // define necessary variables here
        int choice;
        String fileName = "";
        
        Scanner kb = new Scanner(System.in);
        Warehouse inStock = new Warehouse();
        String number = "";
        int amount = 0;
        
        System.out.println("Enter your filename: ");
        fileName = kb.next();
        inStock.loadData(fileName);
        
        choice = 0;

        while (choice != 9)
        {
            showMenu();
            System.out.println ("Please enter your choice or 9 to exit");
            choice = kb.nextInt();
            Item valid;
            kb.nextLine();

            if (choice > 0 && choice < 8)
            {
                System.out.println ("Please enter the inventory number");
                number = kb.nextLine();
                valid = inStock.validateNum(number);
                if (choice != 1 && valid != null)
                {
                    System.out.println ("Please enter the number of items of " + number);
                    amount = kb.nextInt(); 
                    kb.nextLine();
                }
            }

            switch (choice)
            {
                case 1:
                inStock.invQuery(number);
                break;
                case 2:
                inStock.order(number, amount);
                break;
                case 3:
                inStock.receive(number, amount);
                break;
                case 4:
                inStock.returns(number, amount);
                break;
                case 5:
                inStock.shipCust(number, amount);
                break;
                case 6:
                inStock.orderCust(number, amount);
                break;
                case 7:
                inStock.returnCust(number, amount);
                break;
                case 8: 
                inStock.endOfDay();
                break;
                case 9:
                System.out.println ("Thank you for using the Inventory Processing System");
                break;
                default:
                System.out.println ("That is not a valid choice.  Please re-enter");
            }

        }

       
    }
    /**
     *  The Inventory processing menu
     */
    public static void showMenu()
    {
        System.out.println("\nMENU:");
        System.out.println("1) Inventory Item Inquiry");
        System.out.println("2) Order inventory items from Supplier");
        System.out.println("3) Receive shipment from Suppliers");
        System.out.println("4) Return items to Supplier");
        System.out.println("5) Ship items to Customers");
        System.out.println("6) Process Customer Order");
        System.out.println("7) Process Customer Returns");
        System.out.println("8) End of Day Processing");
        System.out.println();
        System.out.println("9) Exit");
    }

}
