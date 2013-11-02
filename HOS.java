import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
public class HOS {
	private ArrayList<Item> itemList;
	private ArrayList<Room> roomList;
	private Order currentOrder;
	private Category filter;
	private ArrayList<Item> filteredItemList;
	public HOS()
	{
		this.itemList = new ArrayList<Item>();
		this.roomList = new ArrayList<Room>();
		this.filteredItemList = new ArrayList<Item>();
		this.currentOrder = null;
		this.filter = null;
	}
	
	public void loadRoomList(String fileName) throws IOException
	{
		Scanner fileInput = new Scanner(new File(fileName));
		while(fileInput.hasNextLine()){
			int roomNr = fileInput.nextInt();
			fileInput.nextLine();
			String occupant = fileInput.nextLine();
			int nrAdults = fileInput.nextInt();
			fileInput.nextLine();
			int nrChildren = fileInput.nextInt();
			fileInput.nextLine();
			fileInput.nextLine();
			Room room = new Room(roomNr, occupant, nrAdults, nrChildren);
			roomList.add(room);
		}
	}
	
	public void loadItemList(String fileName) throws IOException
	{
		Scanner fileInput = new Scanner(new File(fileName));
		while(fileInput.hasNextLine()){
			String id = fileInput.nextLine();
			String name = fileInput.nextLine();
			double price = fileInput.nextDouble();
			fileInput.nextLine();
			String category = fileInput.nextLine();
			fileInput.nextLine();
			Item item = new Item(id, name, price, Category.valueOf(category));
			itemList.add(item);
		}
	}
	
	public void createNewOrder(int roomNr) throws Exception
	{
		boolean isRoomFound = false;
		for(Room room:roomList)
		{
			if(room.getRoomNr() == roomNr){
				this.currentOrder = new Order(room);
				isRoomFound = true;
			}
		}
		if(!isRoomFound){
			throw new Exception("Room not found.");
		}	
	}
	
	public void setFilter(Category category) throws Exception
	{
		this.filter = category;
		this.filteredItemList.clear();
		boolean isItemFound = false;
		for(Item item: itemList)
		{
			if(item.getCategory().equals(filter))
			{
				this.filteredItemList.add(item);
				isItemFound = true;
			}
		}
		if(!isItemFound)
		{
			throw new Exception("Filter has been saved. However, there are no items in this category.");
		}
	}
	
	public void removeFilter()
	{
		this.filter = null;
		this.filteredItemList.clear();
		for(Item item: this.itemList)
		{
			this.filteredItemList.add(item);
		}
	}
	
	public void addItemToOrder(Item item)
	{
		this.currentOrder.addItem(item);
	}
	
	public void removeItemFromOrder(int num)
	{
		this.currentOrder.removeItem(num);
	}
	
	public ArrayList<Item> getFilteredItemList()
	{
		return this.filteredItemList;
	}
	
	public Order getCurrentOrder()
	{
		return this.currentOrder;
	}
	
	public void setCurrentOrderToNull()
	{
		this.currentOrder = null;
	}
	
	public void saveOrder() throws IOException
	{
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("HH-mm-ss");
		SimpleDateFormat format3 = new SimpleDateFormat("dd/mm/yyyy HH:mm");
		Date date = new Date();
		String fileName = "MealOrder-Room-" +  this.currentOrder.getRoom().getRoomNr() + "-Date-" + format1.format(date) + "-Time-" + format2.format(date) + ".txt"; 
		System.out.println(fileName);
		File f = new File(fileName);
		f.createNewFile(); 
		PrintWriter printWriter = new PrintWriter(fileName);
		printWriter.println(format3.format(date));
		printWriter.println("Room Number: " + this.currentOrder.getRoom().getRoomNr());
		printWriter.println("Food items ordered:");
		double totalPrice = 0;
		for(Item item: currentOrder.getItems())
		{
			printWriter.println("\t" + item.getName() + " $" + item.getPrice());
			totalPrice += item.getPrice();
		}
		printWriter.println("Total: $"+ totalPrice);
		printWriter.close();
	}
	
	public String toString()
	{
		return getClass().getName() + "\n\tItemList: [  " + this.itemList + 
				"]\n\n\troomList: [" + this.roomList + "]" + 
				"\n\n\tcurrentOrder: [" + this.currentOrder + "]" +
				"\n\n\tfilter: [" + this.filter + "]" +
				"\n\n\tfilteredItemList" + this.filteredItemList + "]";
	}
}