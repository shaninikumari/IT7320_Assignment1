/*********************************************************************************************************
 * Name: Shanini kumari
 * StudentId: 2150728
 * Description: Inventory class is used for keeping the product inventory. 
 * It defines couple of collection instance for holding the product stock
 * against product Id as key. It also keeps mapping between product name and 
 * product Id. This helps in finding the product from inventory using its name.
 * @version 1.0
 ************************************************************************************************************/
package inventory;
// Import the format class to format values into currency format.
import java.text.DecimalFormat;
import java.util.HashMap;


public class Inventory {
	
	private HashMap<Integer, ProductStock> productInventory;
	private HashMap<String, Integer> productCodeMap;
	private DBManager dbm;
	
	//Constructor method. It initialize productInventory and productCodeMap HashMap.
	public Inventory(){
		this.productInventory = new HashMap<Integer, ProductStock>();
		this.productCodeMap = new HashMap<String, Integer>();
		this.dbm = new DBManager();
	}
	
		
	// Set the formatter to format values into currency form.
	DecimalFormat formatter = new DecimalFormat("$##,###.00");
//---------------------------------------------------------------------------------------------	
   /**
   * This method Adds a product and its quantity to product inventory. This method 
   * checks weather product already exist in inventory or not.If product already 
   * exist in inventory then this method increases its quantity by quantity passed
   * as an argument to this addProduct method. If product is a new product and it 
   * doesn't exists in inventory then this method creates a ProductStockobject and
   * adds ProductStock object in productInventory map against product id as key.
   * @param int Product item, int quantity
   * @return void.   
   * 
   */
//-----------------------------------------------------------------------------------------------

	
	public void addProduct(Product item, int quantity) {
		
		if(!productInventory.containsKey(item.getProductid())){
			ProductStock productStock = new ProductStock(item, quantity);
			productInventory.put(item.getProductid(), productStock);
			productCodeMap.put(item.getName(), item.getProductid());
		}else{
			productInventory.get(item.getProductid()).addToStock(quantity);
			productCodeMap.put(item.getName(), item.getProductid());
		}
		
	}
	
	public void addProductDb(Product item, int quantity) {
		
		if(!dbm.inventoryContainsProduct(new Integer(item.getProductid()).toString())){
			dbm.saveProduct(item, quantity);
			
		}else{
			int productStockCount=dbm.getProductStockCount(new Integer(item.getProductid()).toString());
			int totalQuantity=productStockCount+quantity;
			dbm.updateProductCount(item, totalQuantity);
			
		}
		
	}
//---------------------------------------------------------------------------------------------	
   /**
   * This method removeProduct is used for removing item from inventory. 
   * This method accepts product item and its quantity to be removed from inventory.
   * This method checks if product id is not in inventory hashmap, it throws the exception.
   * Else if product id exists but number of items are lower than quantity passed 
   * as an argument, this method throws the exception.Otherwise this method
   * reduces the quantity in inventory by the number passed in argument.
   * @param Product item, int quantity
   * @return void.
   * @exception InventoryException 
   * 
   */
//-----------------------------------------------------------------------------------------------
	
public void removeProduct(Product item, int quantity) throws InventoryException{
	if(!productInventory.containsKey(item.getProductid())){
            String errorMessage="Product item with product Id: "+ item.getProductid() + " does not exist in inventory," +
                            " so it could not be removed from inventory";
            System.out.println(errorMessage);
            //throw exception
            throw new InventoryException("Inv001", "NoSuchProductException", errorMessage);
    }else{
            int itemQuantity = productInventory.get(item.getProductid()).getStockQuantity();
            if(quantity>itemQuantity){
                    String errorMessage="Product item with product Id: "+ item.getProductid() + " exists in inventory," +
                                    " but its quantity is less than " + quantity+ " so it could not be removed from inventory";
                    System.out.println(errorMessage);
                    throw new InventoryException("Inv002", "ProductOutOfStockException", errorMessage);
            }else{
                    productInventory.get(item.getProductid()).removeFromStock(quantity);
            }
    }

}

public void removeProductDb(Product item, int quantity) throws InventoryException{
    if(!dbm.inventoryContainsProduct(new Integer(item.getProductid()).toString())){
            String errorMessage="Product item with product Id: "+ item.getProductid() + " does not exist in inventory," +
                            " so it could not be removed from inventory";
            System.out.println(errorMessage);
            //throw exception
            throw new InventoryException("Inv001", "NoSuchProductException", errorMessage);
    }else{
            int itemQuantity = dbm.getProductStockCount(new Integer(item.getProductid()).toString());
            if(quantity>itemQuantity){
                    String errorMessage="Product item with product Id: "+ item.getProductid() + " exists in inventory," +
                                    " but its quantity is less than " + quantity+ " so it could not be removed from inventory";
                    System.out.println(errorMessage);
                    throw new InventoryException("Inv002", "ProductOutOfStockException", errorMessage);
            }else{
            	
            	int totalQuantity=itemQuantity-quantity;
    			dbm.updateProductCount(item, totalQuantity);
            }
    }

}
//---------------------------------------------------------------------------------------------	
   /**
   * This method returns the product object based on the product id.
   * If product id does not exist in inventory hashmap, exception is thrown.
   * Otherwise if item quantity is zero, means item is out of stock and so exception is thrown.
   * Else product quantity is reduced by one and product is returned.
   * @param int productid
   * @return Product.
   * @exception InventoryException 
   * 
   */
//-----------------------------------------------------------------------------------------------

public Product getItemById(int productid) throws InventoryException{
    
        if(!productInventory.containsKey(productid)){
                String errorMessage="Product item with product Id: "+ productid + " does not exist in inventory";
                System.out.println(errorMessage);

                //throw exception
                throw new InventoryException("Inv001", "NoSuchProductException", errorMessage);
        }else{
                int itemQuantity = productInventory.get(productid).getStockQuantity();
                if(itemQuantity<=0){
                        String errorMessage="Product inventory is out of stock for product Id: "+ productid;
                        System.out.println(errorMessage);
                        //throw exception
                        throw new InventoryException("Inv002", "ProductOutOfStockException", errorMessage);
                }else{
                        return productInventory.get(productid).getFromStock(1);
                }
        }

}

public Product getItemByIdFromDb(int productid) throws InventoryException{
    
	if(!dbm.inventoryContainsProduct(new Integer(productid).toString())){
            String errorMessage="Product item with product Id: "+ productid + " does not exist in inventory";
            System.out.println(errorMessage);

            //throw exception
            throw new InventoryException("Inv001", "NoSuchProductException", errorMessage);
    }else{
            int itemQuantity = dbm.getProductStockCount(new Integer(productid).toString());
            if(itemQuantity<=0){
                    String errorMessage="Product inventory is out of stock for product Id: "+ productid;
                    System.out.println(errorMessage);
                    //throw exception
                    throw new InventoryException("Inv002", "ProductOutOfStockException", errorMessage);
            }else{
            	return dbm.getProductByProductId(new Integer(productid).toString());
                    
            }
    }

}
//-----------------------------------------------------------------------------------
   /**
   * This method returns the product object based on the product name. 
   * It first calls getProductIdForProductName method for getting product id 
   * corresponding to product name and then it calls getItemById for getting the
   * product corresponding to product id.
   * @param String productName.
   * @return Product.
   * @exception InventoryException 
   * 
   */
//-------------------------------------------------------------------------------------
	
public Product getItemByName(String productName) throws InventoryException{
		return getItemById(getProductIdForProductName(productName));
		
}
//---------------------------------------------------------------------------------------------	
   /**
   * This method is used for getting the product id corresponding to its product name.
   * It uses productCodeMap hashmap for getting the product id corresponding to product name.
   * If product name does not exists, exception is thrown.
   * @param String productName.
   * @return int.
   * @exception InventoryException 
   * 
   */
//-----------------------------------------------------------------------------------------------
private int getProductIdForProductName(String productName)throws InventoryException{
	if(productCodeMap.containsKey(productName)){
			return productCodeMap.get(productName);
	}else{
		String errorMessage="Product with product name "+ productName + " does not exists";
		System.out.println(errorMessage);
		//exception
		throw new InventoryException("Inv001", "NoSuchProductException", errorMessage);
	}
		
}
//-----------------------------------------------------------------------------------
   /**
   * This method is used Loop through our list of products and add up the total value.
   * Go item by item, get the product stock and call its  getStockValue for getting 
   * its total currency value and add it to accumulated value.
   * @param nothing.
   * @return double.   
   * 
   */
//-------------------------------------------------------------------------------------
public double getTotalInvValue() {
    double sumOfInventory = 0.0;
	for(ProductStock productStock : productInventory.values()){
		sumOfInventory += productStock.getStockValue();
	}
	return sumOfInventory;
}
 //-----------------------------------------------------------------------------
   /**
   * This method Prints the inventory list including name, 
   * quantity, price, and total stock value for each item.
   * @param nothing.
   * @return void.   
   * 
   */
 //-----------------------------------------------------------------------------
	
public void printInventory() {
    
    System.out.println("Printing items in inventory...\n");

    if(productInventory.size()>0){
            System.out.println("Product Id"+ "	"+"Product Name"+ "		"+ "Unit Price" + " 	"+ "Quantity"+ " 	"+ "Item Inventory Value" );
            for(ProductStock productStock : productInventory.values()){
                 System.out.println(productStock.getProductId()+" 	"+productStock.getProductName()+ " 	"+ productStock.getStockQuantity()+ " 	"+  productStock.getStockValue());
            }

            System.out.println("Inventory Total Value= "+ getTotalInvValue());
    }else{
            System.out.println("Inventory is empty ....\n");
        }
		
}
 //--------------------------------------------------------------------------------------------------------------
   /**
   * This method Prints the inventory list including name, quantity, price, 
   * and total stock value for each item.
   * @param nothing.
   * @return String.   
   * 
   */
 //------------------------------------------------------------------------------------------------------------
public String getInventoryReport() {
        String inventoryReport="Items in inventory...\n";
        System.out.println("Printing items in inventory...\n");

        if(productInventory.size()>0){
                inventoryReport=inventoryReport+"Product Id"+ "     |     "+"Product Name"+ "     |     "+"Quantity"+ "     |     "+ "Item Inventory Value" +"\n";
                System.out.println("Product Id"+ "\t"+"Product Name"+ "\t"+ "Unit Price" + "\t"+ "Quantity"+ "\t"+ "Item Inventory Value" );
                for(ProductStock productStock : productInventory.values()){
                    inventoryReport=inventoryReport+productStock.getProductId()+"                     |     "+productStock.getProductName()+"                 |     "+ productStock.getStockQuantity()+ "                 |     "+"$"+  productStock.getStockValue()+"\n";
                    System.out.println(productStock.getProductId()+"\t"+productStock.getProductName()+ "\t"+ productStock.getStockQuantity()+ "\t"+  productStock.getStockValue());
                }
                inventoryReport=inventoryReport+"Inventory Total Value= "+ getTotalInvValue()+"\n";
                System.out.println("Inventory Total Value= "+"$"+ getTotalInvValue());
        }else{
                inventoryReport=inventoryReport+"Inventory is empty ....\n";
                System.out.println("Inventory is empty ....\n");
        }
        return inventoryReport;
    }
}
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


