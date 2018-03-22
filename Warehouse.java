import java.util.*;
import java.io.*;

/**
 * Warehouse contains the different items in stock
 * 
 * @author {CJ Dulay}
 *
 */
public class Warehouse
{
    // instance variables (fields)  
    private final static int MAX = 60;
    private ArrayList <Item> stock = new ArrayList <Item>();
    private int arraySize;

    // the constructor
    public Warehouse() throws IOException
    {
        stock = new ArrayList <Item> ();
        arraySize = stock.size();
    }

    // processing methods
    // validate item number and if valid, returns the found item.  Will be used only by this class and hence declared as private
    public Item validateNum(String num)
    {
        Item foundItem = null;
        boolean found = false;

        int i = 0;
        while (i < arraySize && !found)
        {
            if (stock.get(i).getItemNo().equals(num))
            {
                foundItem = stock.get(i);
                found = true;
            }
            i++;
        }

        return foundItem;
    }

    // Inventory Information Inquiry (Option 1)
    public void invQuery(String num)
    {
        Item foundItem = null;
        //String text;

        foundItem = validateNum(num);
        if (foundItem != null)
            System.out.println (foundItem.printDetails());
        else
            System.out.println ("The item number " + num + " does not exist.");

    }

    // ordering items from supplier
    public void order (String num, int amt)
    {
        Item foundItem = null;
        int orderAmt;

        foundItem = validateNum (num);
        if (foundItem != null)
        {
            orderAmt = foundItem.orderItems(amt);
            System.out.println ("Ordered " + orderAmt + " of item " + foundItem.getItemNo() + "  " + foundItem.getItemName());
            System.out.println ("on order amount is " + foundItem.getOnOrder());
        }
        else 
            System.out.println ("The item number " + num + " does not exist.");     

    }

    // Receiving shipment from supplier
    public void receive(String num, int amt)
    {
        Item foundItem = null;
        int recAmt;

        foundItem = validateNum (num);
        if (foundItem != null)
        {
            recAmt = foundItem.receiveItems(amt);
            System.out.println ("Received " + recAmt + " of item " + foundItem.getItemNo() + "  " + foundItem.getItemName());
            System.out.println ("on order amount is " + foundItem.getOnOrder()); 
            System.out.println ("on hand amount is " + foundItem.getOnHand());
        }
        else
            System.out.println ("The item number " + num + " does not exist.");            
    }

    // returning items to supplier
    public void returns(String num, int amt)
    {
        Item foundItem = null;
        int retAmt;

        foundItem = validateNum (num);
        if (foundItem != null)
        {
            retAmt = foundItem.returnItems(amt);
            System.out.println ("Returned " + retAmt + " of item " + foundItem.getItemNo() + "  " + foundItem.getItemName());
            System.out.println ("on hand amount is " + foundItem.getOnHand());
        }
        else
            System.out.println ("The item number " + num + " does not exist."); 

    }

    // shipping items to customers
    public void shipCust(String num, int amt)
    {
        Item foundItem = null;
        int shipAmt;

        foundItem = validateNum (num);
        if (foundItem != null)
        {
            shipAmt = foundItem.shipItems(amt);
            System.out.println ("Shipped " + shipAmt + " of item " + foundItem.getItemNo() + "  " + foundItem.getItemName());
            System.out.println ("committed is " + foundItem.getCommitted());
            System.out.println ("on hand amount is " + foundItem.getOnHand());
        }
        else
            System.out.println ("The item number " + num + " does not exist."); 

    }

    // processing customer orders
    public void orderCust(String num, int amt)
    {
        Item foundItem = null;
        int ordCustAmt;

        foundItem = validateNum (num);
        if (foundItem != null)
        {
            ordCustAmt = foundItem.custOrder(amt); 
            System.out.println ("Customer ordered " + ordCustAmt + " of item " + foundItem.getItemNo() + "  " + foundItem.getItemName());
            System.out.println ("committed is " + foundItem.getCommitted());
            System.out.println ("on hand amount is " + foundItem.getOnHand());  
        }
        else
            System.out.println ("The item number " + num + " does not exist."); 

    }

    // processing customer returns
    public void returnCust(String num, int amt)
    {
        Item foundItem = null;
        int retCustAmt;

        foundItem = validateNum (num);
        if (foundItem != null)
        {
            retCustAmt = foundItem.custReturn(amt);
            System.out.println ("Customer returned " + retCustAmt + " of item " + foundItem.getItemNo() + "  " + foundItem.getItemName());
            System.out.println ("on hand amount is " + foundItem.getOnHand());
        }
        else
            System.out.println ("The item number " + num + " does not exist."); 

    }

    // This adds a new item in the array list
    public void addItem(String itemNo, String itemName, int onHand, double unitPrice, int reOrderPoint, int econOrderQty){
        Item add = (new Item(itemNo, itemName, onHand, unitPrice, reOrderPoint, econOrderQty));
        stock.add(add);
        System.out.println("New item has been added.");
        arraySize = stock.size();
        System.out.print(stock);
        System.out.print(arraySize);
    }

    // Removes an item in the array list
    public void removeItem(String itemNo){
        int i = 0;
        Item foundItem = null;
        foundItem = validateNum(itemNo);

        if (foundItem != null){
            for (i = 0; i < arraySize; i++){
                if (stock.get(i).getItemNo().equals(itemNo)){
                    stock.remove(i);
                }
            }
            System.out.print("Item removed.");
        }
        else {
            System.out.println("Item not removed as its not a valid item number.");
        }
        arraySize = stock.size();
        System.out.print(stock);
        System.out.print(arraySize);
    }

    // Changes the price in an item
    public void changePrice(String itemNo, double price){
        int i = 0;
        Item foundItem = null;
        foundItem = validateNum(itemNo);

        if(foundItem != null){
            for (i = 0; i < arraySize; i++){
                if (stock.get(i).getItemNo().equals(itemNo)){
                    stock.get(i).setUnitPrice(price);
                }          
            }
            System.out.println("Price updated.");
        }
        else {
            System.out.println("Price of item not updated, because item number is invalid.");
        }
    }

    //Reads in the transaction file
    public void tFile() throws IOException{
        String itemNo = "";
        int type = 0;
        int quantity;

        File inFile = new File("transaction.txt");
        Scanner sc = new Scanner (inFile);

        while (sc.hasNext()){
            itemNo = sc.next();
            type = sc.nextInt();
            quantity = sc.nextInt();
            sc.hasNextLine();
        }
        System.out.println("Processed from file.");
    }

    // Processes the transaction file
    public void doTransactions(String itemNo, int type, int quantity){
        if(type == 2){
            order(itemNo, quantity);           
        }
        else if (type == 3){
            receive(itemNo, quantity);
        }
        else if (type == 4){
            returns(itemNo, quantity);    
        }
        else if (type == 5){
            shipCust(itemNo, quantity);    
        }
        else if (type == 6){
            orderCust(itemNo, quantity);    
        }
        else if (type == 7){
            returnCust(itemNo, quantity);    
        }
        else {
            System.out.println(type + " is an invalid code.");
        }

    }

    // End of Day processing
    public void endOfDay()
    {
        printInvReport();
        processAuto();
    }

    // Output file
    public void output(String outFileName) throws IOException {
        PrintWriter out = new PrintWriter(outFileName);

        String itemNo;
        String itemName;
        int onHand;
        int committed;
        int onOrder;
        double unitPrice;
        int reOrderPoint;
        int econOrderQty;

        for (int i = 0; i < arraySize; i++){
            itemNo = stock.get(i).getItemNo();
            itemName = stock.get(i).getItemName();
            onHand = stock.get(i).getOnHand();
            committed = stock.get(i).getCommitted();
            onOrder = stock.get(i).getOnOrder();
            unitPrice = stock.get(i).getUnitPrice();
            reOrderPoint = stock.get(i).getReorderPoint();
            econOrderQty = stock.get(i).getEconOrderQty();

            out.println(itemNo + " " + itemName + " " + onHand + " " + committed + " " + onOrder + " " + unitPrice + " " + reOrderPoint + " " + econOrderQty);
        }

        out.close();
    }

    //Reads in an input file and creates a new Item object and stores it into an arraylist
    /**
     * This is the hardcoded data to be loaded into the instance variables.  
     */
    public void loadData(String fileName) throws IOException
    {
        String itemNo = "";
        String itemName = "";
        int onHand;
        int committed;
        int onOrder;
        double unitPrice;
        int reorderPoint; 
        int econOrderQty;
        int i = 0;

        File inFile = new File(fileName);
        Scanner sc = new Scanner (inFile);

        while (sc.hasNext()){
            itemNo = sc.next();
            itemName = sc.next();
            onHand = sc.nextInt();
            committed = sc.nextInt();
            onOrder = sc.nextInt();
            unitPrice = sc.nextDouble();
            reorderPoint = sc.nextInt();
            econOrderQty = sc.nextInt(); 

            Item item = new Item(itemNo, itemName, onHand, committed, onOrder, unitPrice, reorderPoint, econOrderQty);
            stock.add(item);
            i++;
        }

        sc.close();

    }

    public void arraySize() {
        arraySize = stock.size();  
    }

    private void printInvReport()
    {
        System.out.printf("%-8s %-10s %-10s %-10s %-10s %-9s %-11s %n", "Item", "Item", " ",
            " ", " ", "Unit", "Item");

        System.out.printf("%-8s %-10s %-10s %-10s %-10s %-9s %-11s %n%n","Number", "Name", "On Hand",
            "Committed", "On Order", "Price", "Value");

        for (int i = 0; i <arraySize; i++)
        {
            System.out.printf ("%-8s %-10s %-10d %-10d %-10d $ %-7.2f $ %-10.2f %n", 
                stock.get(i).getItemNo(), stock.get(i).getItemName(), stock.get(i).getOnHand(), 
                stock.get(i).getCommitted(), stock.get(i).getOnOrder(), stock.get(i).getUnitPrice(),
                stock.get(i).getUnitPrice() * (stock.get(i).getOnHand() + stock.get(i).getCommitted()));           
        }   
    }

    private void processAuto()
    {
        boolean autoOrd;
        for (int i = 0; i < arraySize; i++)
        {
            autoOrd = stock.get(i).autoOrder(); 
            if (autoOrd)
            {
                System.out.println ("Ordered " + stock.get(i).getEconOrderQty() + " of item " + stock.get(i).getItemNo() + "  " + stock.get(i).getItemName());
                System.out.println ("on order amount is " + stock.get(i).getOnOrder());
            }

        }   
    }
}
