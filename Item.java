public class Item
{
	private String id;
    private String name;
    private double price;
    private Category category;
    
    public Item(String id, String name, double price, Category category)
    {
    	this.id = id;
    	this.name = name;
    	this.price = price;
    	this.category = category;
    }
    public String getId()
    {
    	return this.id;
    }
    
    public String getName()
    {
    	return this.name;
    }
    
    public double getPrice()
    {
    	return this.price;
    }
    
    public Category getCategory()
    {
    	return this.category;
    }
    
    public String toString()
    {
    	return getClass().getName() + "[id: "+ this.id +
    		", name: " + this.name + 
    		", price: " + this.price + 
    		", category: " + this.category + "]";
    }
}