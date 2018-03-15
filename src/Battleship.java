import java.util.Scanner;
public class Battleship {
	
	public static void main(String[] args) {
		
		Ships Arena1;
		Ships Arena2;
		Arena1 = new Ships();
		Arena2= new Ships();
		Arena1=game(Arena1);
		System.out.println(" dsadas ");
		Arena2=game(Arena2);
		System.out.println(" dsadas ");
		play(Arena1,Arena2);

	}
	public static Ships game(Ships Arena)
	{
		Scanner reader1 = new Scanner(System.in);
		int positionX,positionY,direction;
		do {
			System.out.println("say the position of the bigship in x , y coordinates between 1 and 10. Also write the direction between 1 and 4. 1 for right, 2 for down, 3 for left, 4 for up");
			positionX = reader1.nextInt();
			positionY = reader1.nextInt();
			direction = reader1.nextInt();
		}while((positionX<1 || positionX>10) || (positionY<1 || positionY>10) || (direction<1 || direction>4) || Arena.checkPosition(5,positionX-1,positionY-1,direction)==false);
		Arena.changeMap(5,(positionX-1), (positionY-1), direction);
		do {
			System.out.println("say the position of the Mediumship in x , y coordinates between 1 and 10. Also write the direction between 1 and 4. 1 for right, 2 for down, 3 for left, 4 for up");
			positionX = reader1.nextInt();
			positionY = reader1.nextInt();
			direction = reader1.nextInt();
		}while((positionX<1 || positionX>10) || (positionY<1 || positionY>10) || (direction<1 || direction>4) || Arena.checkPosition(3,positionX-1,positionY-1,direction)==false);
		Arena.changeMap(3,positionX-1, positionY-1, direction);
		do {
			System.out.println("say the position of the smallship in x , y coordinates between 1 and 10. Also write the direction between 1 and 4. 1 for right, 2 for down, 3 for left, 4 for up");
			positionX = reader1.nextInt();
			positionY = reader1.nextInt();
			direction = reader1.nextInt();
		}while((positionX<1 || positionX>10) || (positionY<1 || positionY>10) || (direction<1 || direction>4) || Arena.checkPosition(2,positionX-1,positionY-1,direction)==false);
		Arena.changeMap(2,positionX-1, positionY-1, direction);
		/*while(Arena1.anyoneThere())
		{
			System.out.println("Private. Let's win this. Missile position.");
			positionX = reader.nextInt();
			positionY = reader.nextInt();
			while(Arena1.checkHit(positionX-1, positionY-1) && Arena1.anyoneThere())
			{
				Arena1.print();
				System.out.println("They are trying to run. shoot another one.");
				positionX = reader.nextInt();
				positionY = reader.nextInt();
				
			}
			Arena1.print();
			
		}*/
		//System.out.println("You won.");
		//reader1.close();
		return Arena;
	}
	public static void play(Ships Arena1, Ships Arena2)
	{
		Scanner reader = new Scanner(System.in);
		int positionX,positionY;
		while(Arena1.anyoneThere() && Arena2.anyoneThere())
		{
			
			System.out.println("Player 1 turn. Say the position of the missile.");
			Arena2.print();
			positionX = reader.nextInt();
			positionY = reader.nextInt();
			while(Arena2.checkHit(positionX-1, positionY-1) && Arena2.anyoneThere())
			{
				
				System.out.println("They are trying to run. shoot another one.");
				Arena2.print();
				positionX = reader.nextInt();
				positionY = reader.nextInt();
				
			}
			if(Arena2.anyoneThere()) 
			{
				System.out.println("We have a chance to counter attack. Let's do this. Player 2 turn.");
				Arena1.print();
				positionX = reader.nextInt();
				positionY = reader.nextInt();
				while(Arena1.checkHit(positionX-1, positionY-1) && Arena1.anyoneThere())
				{
				
					System.out.println("They are trying to run. shoot another one.");
					Arena1.print();
					positionX = reader.nextInt();
					positionY = reader.nextInt();
				
				}
			}
		}
		if(Arena1.anyoneThere())
			System.out.println(" Player 1 won.");
		else
			System.out.println(" Player 2 won. ");
		reader.close();
	}
	

}
