import java.util.*;
import java.io.*;
public class HOSMenu
{
	private HOS hos;
	private Scanner keyboard;
	public HOSMenu()
	{
		hos = null;
		keyboard = new Scanner(System.in);
		run();
	} 
	
	public static void main(String[] args)
	{
		HOSMenu menu = new HOSMenu();
	}
	
	public void run()
	{
		try{
			hos = new HOS();
			System.out.println("--------------------------------------------------------");
			System.out.println("Hotel Booking System - Loading data");
			System.out.println("--------------------------------------------------------");
			System.out.print("Enter the name for ITEMS data file (including the extension): ");
			String itemFile = keyboard.nextLine();
			hos.loadItemList(itemFile);
			System.out.print("Enter the name for ROOMS data file (including the extension): ");
			String roomFile = keyboard.nextLine();
			hos.loadRoomList(roomFile);
			System.out.println("Successfully loaded data");
			String input;
			do{
				System.out.println();
				System.out.println();
				System.out.println("--------------------------------------------------------");
				System.out.println("Hotel Booking System - Main Menu");
				System.out.println("--------------------------------------------------------");
				System.out.println("1. Set (or Remove) Filter");
				System.out.println("2. Create a New Order");
				System.out.println("3. Add an Item to the Current Order");
				System.out.println("4. Remove an Item from the Current Order");
				System.out.println("5. Display the Current Order");
				System.out.println("6. Finalize the Order");
				System.out.println();
				System.out.println("S: Show the state of the system for inspection");
				System.out.println("E: Exit");
				System.out.printf("Enter your option (1-6 or S or E): ");
				input = keyboard.nextLine();
				System.out.println();
				switch(input){
					case "1":
					setFilter();
					break;
					case "2":
					createOrder();
					break;
					case "3":
					addItemToOrder();
					break;
					case "4":
					removeItemFromOrder();
					break;
					case "5":
					displayOrder();
					break;
					case "6":
					finalizeOrder();
					break;
					case "S":
					case "s":
					showState();
					break;
					case "E":
					case "e":
					break;
					default: 
					System.out.println("Please enter valid input");
				}
			}while(!input.equalsIgnoreCase("E"));
		}catch(Exception e){
			System.out.println("Exception occured: " + e.getMessage());
			System.out.print("Do you want to continue? (yes): ");
			String cont = keyboard.nextLine();
			if(cont.equalsIgnoreCase("yes")){
				run();
			}
			
		}
	}
	
	public void setFilter()
	{
		try{
			System.out.println("--------------------------------------------------------");
			System.out.println("Hotel Ordering System - Set Filter");
			System.out.println("--------------------------------------------------------");
			System.out.println("1. ENTREE");
			System.out.println("2. MAIN_COURSE");
			System.out.println("3. SIDE_DISH");
			System.out.println("4. DESERT");
			System.out.println("5. DRINK");
			System.out.println("A. All (remove filter)");
			System.out.println("--------------------------------------------------------");
			System.out.print("Enter your option (1-5 or A): ");
			String ch = keyboard.nextLine();
			switch(ch){
				case "1":
				hos.setFilter(Category.ENTREE);
				break;
				case "2":
				hos.setFilter(Category.MAIN_COURSE);
				break;
				case "3":
				hos.setFilter(Category.SIDE_DISH);
				break;
				case "4":
				hos.setFilter(Category.DESERT);
				break;
				case "5":
				hos.setFilter(Category.DRINK);
				break;
				case "A":
				case "a":
				hos.removeFilter();
				break;
				default: 
				throw new Exception("Please enter valid input");
			}
			System.out.println("Filter has been saved.");
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.print("Do you want to set filter again?(yes): ");
			String input = keyboard.nextLine();
			if(input.equalsIgnoreCase("yes")){
				setFilter();
			}
		}
	}
	
	public void createOrder()
	{
		try{
			if(this.hos.getCurrentOrder()!=null){
				throw new Exception("Please save your order first before creating new order.");
			}
			System.out.println("--------------------------------------------------------");
			System.out.println("Hotel Booking System - Create New Order");
			System.out.println("--------------------------------------------------------");
			System.out.print("Enter the room number: ");
			int roomNr = keyboard.nextInt();
			keyboard.nextLine();
			hos.createNewOrder(roomNr);
			System.out.println("Your order has been created.");
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.print("Do you want to create try again?(yes): ");
			String input = keyboard.nextLine();
			if(input.equalsIgnoreCase("yes")){
				createOrder();
			}
		}
	}
	
	public void addItemToOrder()
	{
		try{
			if(hos.getCurrentOrder()==null){
				throw new Exception("You need to create an order before adding an item to it");
			}
			System.out.println("--------------------------------------------------------");
			System.out.println("Hotel Booking System - Add an Item to the Order");
			System.out.println("--------------------------------------------------------");
			if(hos.getFilteredItemList().size()==0){
				throw new Exception("There are no items to add in order. Please change the filter criteria");
			}
			int i = 0;
			for(Item item: hos.getFilteredItemList())
			{
				i++;
				System.out.printf("%d: %s %.2f\n", i, item.getName(), item.getPrice());
			}
			System.out.println("--------------------------------------------------------");
			System.out.printf("Enter your choice of item to add (1-%d): ", i);
			int itemNo = keyboard.nextInt();
			keyboard.nextLine();
			if(itemNo < 0 || itemNo > i){
				throw new Exception("This order doesn't have this item to remove.");
			}
			hos.addItemToOrder(hos.getFilteredItemList().get(itemNo-1));
			System.out.println("An item has been added to current order");	
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void removeItemFromOrder()
	{
		try{
			if(hos.getCurrentOrder()==null){
				throw new Exception("You need to create an order before removing item from it");
			}
			System.out.println("--------------------------------------------------------");
			System.out.println("Hotel Booking System - Remove an Item from the Order");
			System.out.println("--------------------------------------------------------");
			int i = 0;
			if(hos.getCurrentOrder().getItems().size()==0)
			{
				throw new Exception("There are no items added to remove for this order");
			}
			for(Item item: hos.getCurrentOrder().getItems())
			{
				i++;
				System.out.printf("%d: %s %.2f\n", i, item.getName(), item.getPrice());
			}
			System.out.println("--------------------------------------------------------");
			System.out.printf("Enter your choice of item to remove (1-%d): ", i);
			int itemNo = keyboard.nextInt();
			keyboard.nextLine();
			if(itemNo < 0 || itemNo > i){
				throw new Exception("Please enter valid input to remove an item.");
			}
			hos.getCurrentOrder().removeItem(itemNo-1);
			System.out.println("This item has been removed from current order");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void displayOrder()
	{
		try{
			if(hos.getCurrentOrder()==null){
				throw new Exception("No order has been created to display");
			}
			System.out.println("--------------------------------------------------------");
			System.out.println("Hotel Booking System - Display the Current Order");
			System.out.println("--------------------------------------------------------");
			System.out.println("Room Number: " + hos.getCurrentOrder().getRoom().getRoomNr());
			System.out.println("Food items ordered:");
			double total = 0;
			if(hos.getCurrentOrder().getItems().size() > 0){
				for(Item item: hos.getCurrentOrder().getItems()){
					System.out.printf("\t\t%s $%.2f\n", item.getName(), item.getPrice());
					total += item.getPrice();
				}
				System.out.println("Total: $"+ total);
			}else{
				System.out.println("There are no items added for this order");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void finalizeOrder()
	{
		try{
			if(hos.getCurrentOrder() == null){
				throw new Exception("No order has been created to save.");
			}
			System.out.println("--------------------------------------------------------");
			System.out.println("Hotel Booking System - Finalize Order");
			System.out.println("--------------------------------------------------------");
			System.out.println("Room Number: " + hos.getCurrentOrder().getRoom().getRoomNr());
			System.out.println("Food items ordered:");
			double total = 0;
			for(Item item: hos.getCurrentOrder().getItems())
			{
				System.out.printf("\t\t%s $%.2f\n", item.getName(), item.getPrice());
				total += item.getPrice();
			}
			System.out.println("Total: $"+ total);
			System.out.print("Do you want to continue? (y/n): ");
			String input = keyboard.nextLine();
			if(input.equalsIgnoreCase("y"))
			{
				hos.saveOrder();
				hos.setCurrentOrderToNull();
				System.out.println("The order has been saved!");
			}
			else if(input.equalsIgnoreCase("n"))
			{
				hos.setCurrentOrderToNull();
				System.out.println("The order has been canceled!");
			}else{
				System.out.println("Your answer is invalid. No action taken!");
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void showState()
	{
		System.out.println(hos);
	}
}