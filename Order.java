import java.util.*;
public class Order {
	private Room room;
	private ArrayList<Item> items;
	
	public Order(Room room)
	{
		this.items  = new ArrayList<Item>(); 
		this.room = room;
	}
	
	public Room getRoom()
	{
		return this.room;
	}
	
	public void addItem(Item item)
	{
		this.items.add(item);
	}
	
	public void removeItem(int num)
	{
		this.items.remove(num);
	}
	
	public ArrayList<Item> getItems()
	{
		return this.items;
	}
	
	public String toString()
	{
		return getClass().getName() +"[room:  " +  this.room + ", items: " + this.items +  "]";
	}
}