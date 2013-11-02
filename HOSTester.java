public class HOSTester {
	public static void main(String [] args) throws Exception {
		HOS hos = new HOS();
		// System.out.println(hos); // initially filter should be null
		hos.loadItemList("ItemList.txt");
		
		hos.loadRoomList("RoomList.txt");
		// System.out.println(hos);
		hos.createNewOrder(111); 
		// 111 is the room number
		// System.out.println(hos);
		hos.setFilter(Category.MAIN_COURSE);
		// System.out.println(hos);
		hos.removeFilter();
		// System.out.println(hos);
		hos.addItemToOrder(hos.getFilteredItemList().get(0));
		hos.addItemToOrder(hos.getFilteredItemList().get(0)); 
		hos.addItemToOrder(hos.getFilteredItemList().get(1)); 
		// System.out.println(hos);
		hos.removeItemFromOrder(1);
		System.out.println(hos);
		hos.saveOrder(); 
	}
}