
/**
 * This is the class that defines the Inventory Item of a company 
 * 
 * @author 
 * 
 */
public class Item
{
    // the instance variables (fields)
    private String itemNo;
    private String itemName;
    private int onHand;
    private int committed;
    private int onOrder;
    private double unitPrice;
    private int reorderPoint;
    private int econOrderQty;

    // the constructors
    // no arg constructor
    public Item()
    {
    }
    
    // arguments include all instance variables except committed and onOrder
    public Item(String num, String name, int amtOH, double price, int rop, int eoq)
    {
        itemNo = num;
        itemName = name;
        onHand = amtOH;
        unitPrice = price;
        reorderPoint = rop;
        econOrderQty = eoq;
        committed = 0;
        onOrder = 0;
    }
    
    // arguments include all instance variables
    public Item(String num, String name, int amtOH, int comm, int ord,  double price, int rop, int eoq)
    {
        itemNo = num;
        itemName = name;
        onHand = amtOH;
        committed = comm;
        onOrder = ord;
        unitPrice = price;
        reorderPoint = rop;
        econOrderQty = eoq;
        
    }
    
    // Copy Constructor (except itemNo, itemName, committed, and onOrder)
    public Item (Item anItem, String num, String name)
    {
        itemNo = num;
        itemName = name;
        onHand = anItem.getOnHand();
        unitPrice = anItem.getUnitPrice();
        reorderPoint = anItem.getReorderPoint();
        econOrderQty = anItem.getEconOrderQty();
        committed = 0;
        onOrder = 0;
    }
    
    //  the accessors   
    public String getItemNo()
    {
        return itemNo;
    }
    
    public String getItemName()
    {
        return itemName;
    }
    
    public int getOnHand()
    {
        return onHand;
    }
    
    public int getCommitted()
    {
        return committed;
    }
    
    public int getOnOrder()
    {
        return onOrder;
    }
    
    public double getUnitPrice()
    {
        return unitPrice;
    }
    
    public int getReorderPoint()
    {
        return reorderPoint;
    }
    
    public int getEconOrderQty()
    {
        return econOrderQty;
    }
    
    // the mutators
    public void setItemNo(String num)
    {
        itemNo = num;
    }
    
    public void setItemName(String name)
    {
        itemName = name;
    }
    
    public void setOnHand(int amtOH)
    {
        onHand = amtOH;
    }
          
    public void setUnitPrice(double price)
    {
        unitPrice = price;
    }
    
    public void setReorderPoint(int rop)
    {
        reorderPoint = rop;
    }
    
    public void setEconOrderQty(int eoq)
    {
        econOrderQty = eoq;
    }
   
    // place the processing methods here
    // ordering items from supplier
    public int orderItems(int amt)
    { 
        onOrder = onOrder + amt; 
        
        return amt;
    }
    
    // receiving items from supplier
    public int receiveItems(int amt)
    {
        onHand = onHand + amt;
        if (amt <= onOrder)
            onOrder = onOrder - amt;
        else
            onOrder = 0;
         
        return amt;
    }
    
    // returning items to supplier
    public int returnItems(int amt)
    {
        if (amt < onHand)
            onHand = onHand - amt;
        else 
        {
            amt = onHand;
            onHand = 0;                                                                                 
        }
        
        return amt;
    }
    
    // shipping items to cuistomers
    public int shipItems(int amt)
    {
        if (amt <= committed)
            committed = committed - amt;
        else if (amt <= committed + onHand)
        {
            onHand = onHand - (amt - committed);
            committed = 0;
        }
        else
        {
            System.out.println ("No shipment possible because amount is larger than onHand and committed combined");
            amt = 0;
        }
        
        return amt;
    }
    
    // processing customer order
    public int custOrder(int amt)
    {
        if (amt <= onHand)
        {
            onHand = onHand - amt;
            committed = committed + amt;
        }
        else
        {
            int backOrder = amt - onHand;
            amt = onHand;
            committed = committed + onHand;
            onHand = 0;
            orderItems (backOrder);
            System.out.println ("Backordered " + backOrder + " items of item " + itemNo + "  " + itemName);
        }
        
        return amt;
    }
    
    // processing customer returns
    public int custReturn (int amt)
    {
        onHand = onHand + amt;
        
        return amt;
    }
    
    // valuation of inventory
    public double calcInvValue()
    {
        double invVal;
        
        invVal = unitPrice *(onHand + committed);
        
        return invVal;
    }
    
    // automatice ordering
    public boolean autoOrder()
    {
        boolean auto = false;
        if (onHand < reorderPoint)
        {
            orderItems(econOrderQty);
            auto = true;
        }
        return auto;   
    }
    
    // place the prints and toString methods here
    // prints the details of the item, one line per detail
    public String printDetails()
    {
        String result;
        
        result = "Item Number: \t" + itemNo + "\n" +
                 "Item Name: \t" + itemName + "\n" +
                 "On Hand Amt:\t" + onHand + "\n" +
                 "Committed: \t" + committed + "\n" +
                 "On Order Amt: \t" + onOrder + "\n"+
                 "Unit Price: \t" + unitPrice + "\n"+
                 "Reorder Point: \t" + reorderPoint + "\n"+
                 "Economic Qty: \t" + econOrderQty;
        return result;
    }
    
    // prints details in one line
    public String toString()
    {
        String result;
        
        result = itemNo + "   " + itemName + "   " + onHand + "   " + committed + "   " + onOrder;
        result = result + "   " + unitPrice + "   " + reorderPoint + "   " + econOrderQty;
        
        return result;
    }
}
