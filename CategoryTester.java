public class CategoryTester
{
	public static void main(String[] args)
	{
		//assign an enum value to a variable
		Category cat = Category.ENTREE;
		
		//display an enum
		System.out.println(cat);
		
		//convert a string to enum
		cat = Category.valueOf("MAIN_COURSE");
		System.out.println(cat);
		
		//compare enum values
		System.out.println(cat == Category.SIDE_DISH);
		System.out.println(cat == Category.MAIN_COURSE);
		
		//get the array of values of an enum type
		Category[] cats = Category.values();
		for(Category c:cats)
		{
			System.out.println(c);
		}
	}
}