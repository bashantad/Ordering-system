public class Room {
	private int roomNr;
	private String occupant;
	private int nrAdults;
	private int nrChildren;

	public Room(int roomNr, String occupant, int nrAdults, int nrChildren)
	{
		this.roomNr = roomNr;
		this.occupant = occupant;
		this.nrAdults = nrAdults;
		this.nrChildren = nrChildren;
	}
	public int getRoomNr()
	{
		return this.roomNr;
	}

	public String getOccupant()
	{
		return this.occupant;
	}

	public int getNrAdults()
	{
		return this.nrAdults;
	}

	public int getNrChildren()
	{
		return this.nrChildren;
	}

	public String toString()
	{
		return getClass().getName() + "[roomNr: " + this.roomNr +
			", occupant: " + this.roomNr + 
			", nrAdults: " + this.nrAdults + 
			", nrChildren: " + this.nrChildren + "]";
	}
}