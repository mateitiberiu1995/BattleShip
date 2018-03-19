import java.util.Scanner;
import java.util.ArrayList;

public class Ships
{
	private ArrayList<Destroyers> destroyersList = new ArrayList<Destroyers>();
	private int map[][]= new int[10][10];
	private Destroyers Ship1 = new Destroyers();
	private Destroyers Ship2 = new Destroyers();
	private Destroyers Ship3 = new Destroyers();
	
	//private int positionX;
	//private int positionY;
	private int bigShip =5;
	private int mediumShip =3 ;
	private int smallShip =2 ;
	//private int direction;
	public Ships()
	{
		
		for(int i = 0;i<10;i++)
			for(int j=0;j<10;j++)
				map[i][j]=0;

		destroyersList.add(Ship1);
		destroyersList.add(Ship2);
		destroyersList.add(Ship3);
	}
	public boolean checkPosition(int length, int firstPosition, int secondPosition, int direction)
	{
		switch(direction)
		{
		case 1:
			if(secondPosition+length>=10)
				return false;
			for(int i=0;i<length;i++)
				if(map[firstPosition][secondPosition+i]==1)
					return false;
			break;
		case 2:
			if(firstPosition+length>=10)
				return false;
			for(int i=0;i<length;i++)
				if(map[firstPosition+i][secondPosition]==1)
					return false;
			break;
		case 3:
			if(secondPosition-length<0)
				return false;
			for(int i=0;i<length;i++)
				if(map[firstPosition][secondPosition-i]==1)
					return false;
			break;
		case 4:
			if(firstPosition-length<0)
				return false;
			for(int i=0;i<length;i++)
				if(map[firstPosition-i][secondPosition]==1)
					return false;
			break;
		}
		return true;
	}
	public void changeMap(int length, int firstPosition, int secondPosition, int direction)
	{
		int ship=0;
		switch(length)
		{
		case 5:
			ship = 1;
			break;
		case 3:
			ship = 2;
			break;
		case 2:
			ship = 3;
			break;
		}
		switch(direction)
		{
		case 1: 
			for(int i=0;i<length;i++)
			{
				map[firstPosition][secondPosition+i]=1;
				destroyersList.get(ship-1).addPosition(firstPosition, secondPosition+i);
			}
			break;
		case 2: 
			for(int i=0;i<length;i++)
			{
				map[firstPosition+i][secondPosition]=1;
				destroyersList.get(ship-1).addPosition(firstPosition+i, secondPosition);
			}
			break;
		case 3: 
			for(int i=0;i<length;i++)
			{
				map[firstPosition][secondPosition-i]=1;
				destroyersList.get(ship-1).addPosition(firstPosition, secondPosition-i);
			}
			break;
		case 4: 
			for(int i=0;i<length;i++)
			{
				map[firstPosition-i][secondPosition]=1;
				destroyersList.get(ship-1).addPosition(firstPosition-i, secondPosition);
			}
			break;
		}
	}
	public void putBigShip(int positionX, int positionY, int direction)
	{
		while(checkPosition(bigShip,positionX,positionY,direction)!=true)
		{
			Scanner reader2 = new Scanner(System.in);
			System.out.println("The position of the bigShip is not available because it will go outside of the map.");
			System.out.println("Write the position X , Y and the direction of the battleship");
			positionX= reader2.nextInt();
			positionY= reader2.nextInt();
			direction = reader2.nextInt();
			reader2.close();
			
		}
		changeMap(bigShip,positionX,positionY,direction);
		
			
	}
	public void putMediumShip(int positionX, int positionY, int direction)
	{
		while(checkPosition(mediumShip,positionX,positionY,direction)==false)
		{
			Scanner reader3 = new Scanner(System.in);
			System.out.println("The position of the mediumShip is not available because it will go outside of the map.");
			System.out.println("Write the position X , Y and the direction of the battleship");
			positionX= reader3.nextInt();
			positionY= reader3.nextInt();
			direction = reader3.nextInt();
			reader3.close();
			
		}
		changeMap(mediumShip,positionX,positionY,direction);
	}
	public void putSmallShip(int positionX, int positionY, int direction)
	{
		while(checkPosition(smallShip,positionX,positionY,direction)==false)
		{
			Scanner reader4 = new Scanner(System.in);
			System.out.println("The position of the smallShip is not available because it will go outside of the map.");
			System.out.println("Write the position X , Y and the direction of the battleship");
			positionX= reader4.nextInt();
			positionY= reader4.nextInt();
			direction = reader4.nextInt();
			reader4.close();
			
		}
		changeMap(smallShip,positionX,positionY,direction);
	}
	public boolean checkHit(int positionX, int positionY)
	{
		while(map[positionX][positionY]==-1 || map[positionX][positionY]==-2)
		{
			Scanner reader5 = new Scanner(System.in);
			System.out.println("You already send a missile in that area. Check other area!!");
			positionX= reader5.nextInt();
			positionY = reader5.nextInt();
			reader5.close();
		}
		if(map[positionX][positionY]==1)
		{
			System.out.println("You hit someone.");
			for(int i=0;i<destroyersList.size();i++)
			{
				destroyersList.get(i).removePosition(positionX, positionY);
				if(destroyersList.get(i).getHp()==0)
					destroyersList.remove(i);
			}
			map[positionX][positionY]-=2;
			return true;
		}
		else
		{
			System.out.println("You missed. Do not worry, you got this private. Tomorrow is another day.");
			map[positionX][positionY] -=2;
		}
		return false;
	}
	public void print()
	{
		System.out.print("----------------------------------------------------------------------------------------------------");
		System.out.println();
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				switch(map[i][j])
				{
				case -1:
					System.out.print("|  Wreck |");
					break;
				case -2:
					System.out.print("|  Fish  |");
					break;
				default:
					System.out.print("|   X    |");
				}
			}
			
			System.out.println();
			System.out.print("----------------------------------------------------------------------------------------------------");
			System.out.println();
		}
	}
	public boolean anyoneThere()
	{
		if(destroyersList.size()==0)
		{
			return false;
		}
		return true;
	}
	public int returnNumberOfShips()
	{
		return destroyersList.size();
	}
}