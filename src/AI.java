import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;

public class AI{
	private ArrayList<Destroyers> destroyersList = new ArrayList<Destroyers>();
	private int map[][]= new int[10][10];
	private int enemyMap[][] = new int[10][10];
	private Destroyers Ship1 = new Destroyers();
	private Destroyers Ship2 = new Destroyers();
	private Destroyers Ship3 = new Destroyers();
	private int firstPosition,secondPosition;
	private int numberStrategy=5;
	private Random rand = new Random();
	public AI()
	{
		
		destroyersList.add(Ship1);
		destroyersList.add(Ship2);
		destroyersList.add(Ship3);
		Scanner file =  new Scanner(System.in);
		try {
			file = new Scanner(new FileReader("strategy.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		numberStrategy = rand.nextInt(numberStrategy);
		//System.out.print(numberStrategy);
		int length ;
		int positionX ;
		int positionY;
		int direction ;
		int counter=0;
		for(int i=0;i<=numberStrategy;i++)
		{	
			counter=0;
			while(counter<=2)
			{
				length = file.nextInt();
				positionX = file.nextInt();
				positionY= file.nextInt();
				direction = file.nextInt();
				if(numberStrategy==i)
				{
					changeMap(length,positionX,positionY,direction);
				}
				
				counter++;
				//System.out.println(length+" "+ positionX + " " + positionY + " " +  direction);
			}
		}
		file.close();
		
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
	public void nextTurn()
	{
		firstPosition = rand.nextInt(10);
		secondPosition = rand.nextInt(10);
		while(enemyMap[firstPosition][secondPosition]==-1 || enemyMap[firstPosition][secondPosition]==-2)
		{
			firstPosition = rand.nextInt(10);
			secondPosition = rand.nextInt(10);
		}
		
	}
	public void changePositionMap(int x,int y,int variable)
	{
		enemyMap[x][y]=variable;
	}
	public void weHitSomething(int x, int y)
	{
		if(x>=1 && x<=8 && y>=1 && y<=8)
		if(enemyMap[x+1][y]==0 && enemyMap[x-1][y]==0 && enemyMap[x][y+1]==0 && enemyMap[x][y-1]==0)
		{
			firstPosition=x;
			secondPosition=y+1;
		}
		else {
				if(enemyMap[x+1][y]==-1 || enemyMap[x-1][y]==-1 || enemyMap[x][y+1]==-1 || enemyMap[x][y-1]==-1)
				{
					if(enemyMap[x+1][y]==-1)
					{
						if(x>=2 && x<=7)
							thirdCase(x,y);
						else
							if(x==1)
								fifthCase(x,y);
							else
								sixthCase(x,y);
					}
					if(enemyMap[x-1][y]==-1)
					{
						if(x>=2 && x<=7)
							forthCase(x,y);
						else
							if(x==1)
								seventhCase(x,y);
							else
								eigthCase(x,y);
					}
					if(enemyMap[x][y+1]==-1)
					{
						if(y>=2 && y<=7)
							firstCase(x,y);
						else
							if(y==1)
								ninthCase(x,y);
							else
								tenthCase(x,y);
					}
					if(enemyMap[x][y-1]==-1)
					{
						if(x>=2 && x<=7 && y>=2 && y<=7)
							secondCase(x,y);
					}
				}
				else
				{
					if(enemyMap[x+1][y]==0)
						firstPosition=x+1;
					else
						if(enemyMap[x-1][y]==0)
							firstPosition=x-1;
						else
							if(enemyMap[x][y+1]==0)
								secondPosition=y+1;
							else
								if(enemyMap[x][y-1]==0)
									secondPosition=y-1;
				}
			}
		if(checkIfPossible(firstPosition,secondPosition)== false)
			nextTurn();
			
	}
	public void firstCase(int x , int y)
	{
		if(enemyMap[x][y+1]==-1)
		{
			if(enemyMap[x][y-1]==0)
			{
				firstPosition=x;
				secondPosition=y-1;
			}
			else {
				if(enemyMap[x][y-1]!=0 && (enemyMap[x][y+2]==0 || enemyMap[x][y+2]==-1))
				{
					if( enemyMap[x][y+2]==0)
					{
						firstPosition=x;
						secondPosition=y+2;
					}
					else
					{
						int i=2;
						while(enemyMap[x][y+i]!=0)
						{
							i++;
						}
						if(enemyMap[x][y+i]==-2)
						{
							if(enemyMap[x+1][y]==0)
								firstPosition=x+1;
							else
								if(enemyMap[x-1][y]==0)
									firstPosition=x-1;
						}
						else
							secondPosition=y+i;
					}
						
				}
			}
		}
		if(checkIfPossible(firstPosition,secondPosition)== false)
			nextTurn();
	}
	public void secondCase(int x , int y)
	{
		if(enemyMap[x][y-1]==-1)
		{
			if(enemyMap[x][y+1]==0)
			{
				firstPosition=x;
				secondPosition=y+1;
			}
			else {
				if(enemyMap[x][y+1]!=0 && (enemyMap[x][y-2]==0 || enemyMap[x][y-2]==-1))
				{
					if( enemyMap[x][y-2]==0)
					{
						firstPosition=x;
						secondPosition=y-2;
					}
					else
					{
						int i=2;
						while(enemyMap[x][y-i]!=0)
						{
							i++;
						}
						if(enemyMap[x][y-i]==-2)
						{
							if(enemyMap[x+1][y]==0)
								firstPosition=x+1;
							else
								if(enemyMap[x-1][y]==0)
									firstPosition=x-1;
						}
						else
							secondPosition=y+i;
					}
						
				}
			}
		}
		if(checkIfPossible(firstPosition,secondPosition)== false)
			nextTurn();
	}
	public void thirdCase(int x, int y)
	{
		if(enemyMap[x+1][y]==-1)
		{
			if(enemyMap[x-1][y]==0)
			{
				firstPosition=x-1;
				secondPosition=y;
			}
			else {
				if(enemyMap[x-1][y]!=0 && (enemyMap[x-2][y]==0 || enemyMap[x-2][y]==-1))
				{
					if( enemyMap[x-2][y]==0)
					{
						firstPosition=x-2;
						secondPosition=y;
					}
					else
					{
						int i=2;
						while(enemyMap[x+i][y]!=0)
						{
							i++;
						}
						if(enemyMap[x+i][y]==-2)
						{
							if(enemyMap[x][y+1]==0)
								secondPosition=y+1;
							else
								if(enemyMap[x][y-1]==0)
									secondPosition=y-1;
						}
						else
							firstPosition=x+i;
					}
						
				}
			}
		}
		if(checkIfPossible(firstPosition,secondPosition)== false)
			nextTurn();
	}
	public void forthCase(int x,int y)
	{
		if(enemyMap[x-1][y]==-1)
		{
			if(enemyMap[x+1][y]==0)
			{
				firstPosition=x+1;
				secondPosition=y;
			}
			else {
				if(enemyMap[x+1][y]!=0 && (enemyMap[x+2][y]==0 || enemyMap[x+2][y]==-1))
				{
					if( enemyMap[x+2][y]==0)
					{
						firstPosition=x+2;
						secondPosition=y;
					}
					else
					{
						int i=2;
						while(enemyMap[x-i][y]!=0)
						{
							i++;
						}
						if(enemyMap[x-i][y]==-2)
						{
							if(enemyMap[x][y+1]==0)
								secondPosition=y+1;
							else
								if(enemyMap[x][y-1]==0)
									secondPosition=y-1;
						}	
						else
							firstPosition=x-i;
					}
						
				}
			}
		}
		if(checkIfPossible(firstPosition,secondPosition)== false)
			nextTurn();
	}
	public void fifthCase(int x ,int y)
	{
		if(enemyMap[x-1][y]==0)
		{
			firstPosition=x-1;
		}
		else
			if(enemyMap[x+2][y]==0)
			{
				firstPosition=x+2;
			}
			else
				if(enemyMap[x+2][y]==-1)
				{
					int i=2;
					while(enemyMap[x+i][y]!=0)
					{
						i++;
					}
					if(enemyMap[x+i][y]==-2)
					{
						if(enemyMap[x][y+1]==0)
							secondPosition=y+1;
						else
							if(enemyMap[x][y-1]==0)
								secondPosition=y-1;
					}
					else
						firstPosition=x+i;
				}
		if(checkIfPossible(firstPosition,secondPosition)== false)
			nextTurn();
	}
	public void sixthCase(int x , int y)
	{
		if(enemyMap[x-1][y]==0)
		{
			firstPosition=x-1;
		}
		else
		{
			if(enemyMap[x-1][y]==-1)
			{
				int i=2;
				while(enemyMap[x-i][y]!=0)
				{
					i++;
				}
				if(enemyMap[x-i][y]==-2)
				{
					if(enemyMap[x][y+1]==0)
						secondPosition=y+1;
					else
						if(enemyMap[x][y-1]==0)
							secondPosition=y-1;
				}
				else
					firstPosition=x-i;
			}
		}
	}
	public void seventhCase(int x,int y)
	{
		if(enemyMap[x+1][y]==0)
			firstPosition=x+1;
		else
			if(enemyMap[x+1][y]==-1)
			{
				int i=2;
				while(enemyMap[x+i][y]!=0)
				{
					i++;
				}
				if(enemyMap[x+i][y]==-2)
				{
					if(enemyMap[x][y+1]==0)
						secondPosition=y+1;
					else
						if(enemyMap[x][y-1]==0)
							secondPosition=y-1;
				}
				else
					firstPosition=x+i;
			}
		if(checkIfPossible(firstPosition,secondPosition)== false)
			nextTurn();
	}
	public void eigthCase(int x,int y)
	{
		if(enemyMap[x+1][y]==0)
			firstPosition=x+1;
		else
			if(enemyMap[x+1][y]==-1 || enemyMap[x+1][y]==-2)
			{
				int i=2;
				while(enemyMap[x-i][y]!=0)
				{
					i++;
				}
				if(enemyMap[x-i][y]==-2)
				{
					if(enemyMap[x][y+1]==0)
						secondPosition=y+1;
					else
						if(enemyMap[x][y-1]==0)
							secondPosition=y-1;
				}
				else
					firstPosition=x-i;
			}
		if(checkIfPossible(firstPosition,secondPosition)== false)
			nextTurn();
	}
	public void ninthCase(int x, int y)
	{
		if(enemyMap[x][y-1]==0)
		{
			secondPosition=y-1;
		}
		else
			if(enemyMap[x][y+2]==0)
			{
				secondPosition=y+2;
			}
			else
				if(enemyMap[x][y+2]==-1)
				{
					int i=2;
					while(enemyMap[x][y+i]!=0)
					{
						i++;
					}
					if(enemyMap[x][y+i]==-2)
					{
						if(enemyMap[x+1][y]==0)
							firstPosition=x+1;
						else
							if(enemyMap[x-1][y]==0)
								firstPosition=x-1;
					}
					else
						secondPosition=y+i;
				}
		if(checkIfPossible(firstPosition,secondPosition)== false)
			nextTurn();
	}
	public void tenthCase(int x,int y)
	{
		if(enemyMap[x][y-1]==0)
			secondPosition = y-1;
		else
			if(enemyMap[x][y-1]==-1)
			{
				int i=2;
				while(enemyMap[x][y-i]!=0)
				{
					i++;
				}
				if(enemyMap[x][y-i]==-2)
				{
					if(enemyMap[x+1][y]==0)
						firstPosition=x+1;
					else
						if(enemyMap[x-1][y]==0)
							firstPosition=x-1;
				}
				else
					secondPosition=y+i;
			}
		if(checkIfPossible(firstPosition,secondPosition)== false)
			nextTurn();
	}
	public boolean checkIfPossible(int x, int y)
	{
		if(enemyMap[x][y]==-1 || enemyMap[x][y]==-2)
			return false;
		if(x<0 || x>9 || y<0 || y>9)
			return false;
		return true;
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
	public void printEnemy()
	{
		System.out.println();
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				System.out.print(" "+ enemyMap[i][j] + " ");
			}
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
	public int getX()
	{
		return firstPosition;
	}
	public int getY()
	{
		return secondPosition;
	}
}