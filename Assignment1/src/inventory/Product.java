/*********************************************************************************************************
 * Name: Shanini kumari
 * StudentId: 2150728
 * Description: Product class is an abstract class. 
 * Child class extends this class for making the object of type product.
 * @version 1.0
 ************************************************************************************************************/
package inventory;


public abstract class Product {
	private String name;
	private double price;
	private int productid;


// Default constructor uses other constructor to set the
// default state of the object.

public Product() {
        this(0,"Unknown",0.00);
}

// Constructor that user can specify a name, quantity, and price for for items.
public Product(int productId, String itemname,  double itemprice) {
        productid = productId;
        setName(itemname);
        setPrice(itemprice);
}

public void setProductid(int productid) {
    this.productid = productid;
}
//Get product id.
	public int getProductid() {
		return productid;
	}

//This method  Set the name of product
public void setName(String itemname) {
        name = itemname;
}


// Set price of a product and defaults it to zero if negative.
public void setPrice(double itemPrice) {
        if (itemPrice > 0.00) {
                price = itemPrice;
        }
        else { price = 0.00; }
}

// Get the product's name
public String getName() {
        return name;
}

//Get the product price
public double getPrice() {
        return price;
}


// String representation of the product
public String toString() {
        return name + " - " + price;
    }
}
