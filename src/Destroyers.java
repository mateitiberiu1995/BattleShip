public class Destroyers{
	private int position[][]=new int[5][2];
	private int length=0;
	private int hitpoints=0;
	public Destroyers()
	{
		
	}
	public void addPosition(int positionX,int positionY)
	{
		position[length][0]= positionX;
		position[length][1]= positionY;
		length++;
		hitpoints++;
	}
	public void removePosition(int positionX,int positionY)
	{
		for(int i=0;i<length;i++)
		{
			if(position[i][0]==positionX && position[i][1]==positionY)
				hitpoints--;
		}
		if(hitpoints==0)
			System.out.println("Ship has been destroyed");
	}
	public int getHp()
	{
		return hitpoints;
	}
}