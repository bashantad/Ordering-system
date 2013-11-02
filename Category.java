import java.util.ArrayList;
public enum Category {
	ENTREE,
	MAIN_COURSE,
	SIDE_DISH,
	DESERT,
	DRINK;
	public static ArrayList<Category> getValueList() {
		ArrayList<Category> list = new ArrayList<Category>();
		Category [] categories = Category.values(); 
		for(Category c: categories)
		{
			list.add(c); 

		}
		return list;
	}
}