/*********************************************************************************************************
 * Name: Shanini kumari
 * StudentId: 2150728
 * Description: This class is used for creating the product stock. 
 * Product stock is combination of product and its number in stock.
 * @version 1.0
 ************************************************************************************************************/
package inventory;


public class ProductStock {
	private Product product;
	private int quantity;
	
	
	// Constructor method for initializing .ProductStock instance variable.
	public ProductStock(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
		
	}
	
	//It returns total price value of stock
	public double getStockValue() {
		return (product.getPrice() * quantity);
	}
	
	// Increase the stock quantity by the number provided in argument
	public void addToStock(int number){
		quantity += number;
	}
	
	// Reduce the stock quantity by the number provided in argument
	public void removeFromStock(int number){
		quantity -= number;
	}
	
	//Return the product as well as reduce the stock number by the number provided in argument.
	public Product getFromStock(int number){
		quantity -= number;
		return product;
	}
	
	//Returns the number of quantity in stock
	public int getStockQuantity(){
		return quantity;
	}
	
	//Returns the product name.
	public String getProductName() {
		return product.getName();
	}
	
	//Returns the product id.
	public int getProductId() {
		return product.getProductid();
	}

}
