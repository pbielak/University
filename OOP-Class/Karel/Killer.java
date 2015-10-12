import java.awt.Point;
import java.util.Vector;

import stanford.karel.*;

public class Killer extends Karel{
	private int mapWidth;
	private int mapHeight;
	private FieldTypes[][] 	world;
	
	private Vector<Point> beeperVector;
	private int beeperCounter;
	private int beeperFields;
	
	public void run(){
		init();
		searchForBeepers();
		recalculateMap();
		updateMap();
		
		System.out.println("Liczba beeperów: " + beeperCounter); //Debug + Large Maps
		System.out.println("Liczba obszarów: " + beeperFields); // Debug + Large Maps
		
		sleep(4000);
		cleanUpMap();
		putNBeepers(this.beeperCounter + this.beeperFields);
	}
	
	
	
	/**
	 * Initializes the program variables 
	 */
	private void init()
	{
		this.mapWidth 	= getWidth();
		this.mapHeight 	= getHeight();
		
		this.world = new FieldTypes[this.mapWidth + 1][this.mapHeight + 1];
		
		this.beeperFields = 0;
		this.beeperCounter = 0;
		this.beeperVector = new Vector<Point>();
	}

	/**
	 * Calculates the map width. 
	 * Precondition: facing East 
	 * Postcondition: facing East
	 */
	private int getWidth() {
		int width = 1;

		while (frontIsClear()) {
			width++;
			move();
		}

		turnAround();
		moveNTimes(width - 1);
		turnAround();

		return width;
	}

	/**
	 * Calculates the map height. 
	 * Precondition:  facing East 
	 * Postcondition: facing East
	 */
	private int getHeight() {
		int height = 1;

		turnLeft();

		while (frontIsClear()) {
			height++;
			move();
		}

		turnAround();
		moveNTimes(height - 1);
		turnLeft();

		return height;
	}
	
	
	
	/**
	 * Search for Initial Beepers.
	 * Precondition: facing East, in (1,1)
	 * Postcondition:  facing East, in the bottom right corner
	 */
	private void searchForBeepers() {
		for (int i = 1; i < this.mapWidth; i++) {
			searchInAvenue(i);
			move();
		}
		
		searchInAvenue(this.mapWidth);
	}

	/**
	 * Looks for Initial Beepers in an Avenue.
	 * Precondition: facing East
	 * Postcondition: facing East
	 */
	private void searchInAvenue(int avenue) {
		turnLeft();
		
		for (int street = 1; street < this.mapHeight; street++) {
			checkForInitBeeper(avenue, street);
			move();
		}
		
		checkForInitBeeper(avenue, this.mapHeight);
		turnAround();
		moveNTimes(this.mapHeight - 1);
		turnLeft();
	}
	
	/**
	 * Checks if in (posX, posY) there is an Initial Beeper.
	 */
	private void checkForInitBeeper(int posX, int posY)
	{
		if(beepersPresent()) 
		{
			world[posX][posY] = FieldTypes.I_BEEPER;
			
			this.beeperVector.addElement(new Point(posX,posY));
			this.beeperCounter++;
		}
		else world[posX][posY] = FieldTypes.EMPTY;
	}
	
	
	
	/**
	 * Runs the 'floodfill' algorithm for each Beeper on the map.
	 */
	private void recalculateMap(){
		for(int i = 1; i <= this.mapWidth; i++)
		{
			for(int j = 1; j <= this.mapHeight; j++)
			{
				if(world[i][j] == FieldTypes.I_BEEPER)
				{
					if(!isVisited(i,j))
					{
						this.beeperFields++;
						floodFill(i, j, Directions.NONE);
					}
				}
			}
		}
	}
	
	/**
	 * Checks if the Beeper in (posX, posY) was already visited. 
	 */
	private boolean isVisited(int posX, int posY){	
		if(this.beeperVector.contains(new Point(posX,posY))) return false;
		
		return true;
	}
	
	/**
	 * Calculate the positions for the Wall-Beepers.
	 * 
	 * Explanation:
	 * 1) If(out of map range OR there is a Wall-Beeper) -> end
	 * 2) If(there is nothing) -> "put" Wall-Beeper and end
	 * 3) If(there is an Initial Beeper) -> mark as "visited" (remove from beeperVector), but if it is already visited end
	 * 4) Depending on "from"-variable call recursively the floodFill method
	 * 	  (for example: if Karel is coming from North he doesn't need to check North)
	 * 5) End
	 */
	private void floodFill(int PosX, int PosY, Directions from){
		if(	(PosX == 0) || (PosX == (this.mapWidth + 1)) || 
			(PosY == 0) || (PosY == (this.mapHeight + 1)) ||
			(this.world[PosX][PosY] == FieldTypes.WALL)
		  ) return;
		
		if(this.world[PosX][PosY] == FieldTypes.EMPTY)
		{
			this.world[PosX][PosY] = FieldTypes.WALL;
			return;
		}
		
		if(this.world[PosX][PosY] == FieldTypes.I_BEEPER)
		{
			if(isVisited(PosX, PosY))
				return;
			else
				this.beeperVector.remove(new Point(PosX, PosY));
		}
		
		if(from == Directions.NONE)
		{
			floodFill(PosX + 1, PosY, Directions.WEST);
			floodFill(PosX - 1, PosY, Directions.EAST);
			floodFill(PosX, PosY + 1, Directions.SOUTH);
			floodFill(PosX, PosY - 1, Directions.NORTH);
			
			return;
		}
		else if(from == Directions.EAST) 
		{
			floodFill(PosX - 1, PosY, Directions.EAST);
			floodFill(PosX, PosY + 1, Directions.SOUTH);
			floodFill(PosX, PosY - 1, Directions.NORTH);
			
			return;
		}
		else if(from == Directions.WEST)
		{
			floodFill(PosX + 1, PosY, Directions.WEST);
			floodFill(PosX, PosY + 1, Directions.SOUTH);
			floodFill(PosX, PosY - 1, Directions.NORTH);
			
			return;
		}
		else if(from == Directions.NORTH)
		{
			floodFill(PosX + 1, PosY, Directions.WEST);
			floodFill(PosX - 1, PosY, Directions.EAST);
			floodFill(PosX, PosY - 1, Directions.NORTH);
			
			return;
		}
		else if(from == Directions.SOUTH)
		{
			floodFill(PosX + 1, PosY, Directions.WEST);
			floodFill(PosX - 1, PosY, Directions.EAST);
			floodFill(PosX, PosY + 1, Directions.SOUTH);
			
			return;
		}
		
		return;
	}
	
	
	
	/**
	 * Updates the map.
	 * Precondition: facing East, in the bottom right corner
	 * Postcondition: facing East, in the bottom right corner
	 */
	private void updateMap() {
		goHome();
		
		for (int i = 1; i < this.mapWidth; i++) {
			updateAvenue(i);
			move();
		}
		
		updateAvenue(this.mapWidth);
	}

	/**
	 * Updates an Avenue.
	 * Precondition: facing East
	 * Postcondition: facing East
	 */
	private void updateAvenue(int avenue) {
		turnLeft();
		
		for (int street = 1; street < this.mapHeight; street++) {
			updatePosition(avenue, street);
			move();
		}
		
		updatePosition(avenue, this.mapHeight);
		turnAround();
		moveNTimes(this.mapHeight - 1);
		turnLeft();
	}
	
	/**
	 * Updates the current (posX, posY) position.
	 */
	private void updatePosition(int posX, int posY){
		if(world[posX][posY] == FieldTypes.I_BEEPER)
		{
			pickBeeper();
		}
		else if(world[posX][posY] == FieldTypes.WALL)
		{
			putBeeper();
		}
	}
	
	
	
	/**
	 * Collects all Beepers on the map.
	 * Precondition: facing East, in the bottom right corner
	 * Postcondition: facing East, in (1,1)
	 */
	private void cleanUpMap() {
		turnLeft();

		for (int i = this.mapWidth - 1; i > 0; i--) {
			clearAvenue();
			turnRight();
			move();
			turnRight();
		}
		
		clearAvenue();
		turnLeft();
	}

	/**
	 * Gets all Beepers in an Avenue.
	 * Precondition: facing North
	 * Postcondition: facing South
	 */
	private void clearAvenue() {
		while(!frontIsBlocked())
		{
			while(beepersPresent()) pickBeeper();
			move();
		}
		
		while(beepersPresent()) pickBeeper();		
		turnAround();
		moveNTimes(mapHeight - 1);
	}
	
	
	
	/**
	 * Goes to (1,1) from the bottom right corner.
	 * Precondition: facing East in the bottom right corner
	 * Postcondition: facing East in (1,1)
	 */
	private void goHome()
	{
		turnAround();
		moveNTimes(this.mapWidth - 1);
		turnAround();
	}
	
	/**
	 * Moves N times. 
	 */
	private void moveNTimes(int N) 
	{
		for(int i = 0; i < N; i++) move();
	}

	/**
	 * Turns Karel around 180 degrees.
	 */
	private void turnAround() {
		turnLeft();
		turnLeft();
	}
	
	/**
	 * Turns Karel right 90 degrees.
	 */
	private void turnRight() {
		turnLeft();
		turnLeft();
		turnLeft();
	}
	
	/**
	 * Karel puts N Beepers in the current position.
	 */
	private void putNBeepers(int N) {
		for(int i = 0; i < N; i++)
			putBeeper();
		
		System.out.println("£¹cznie: " + N); //Debug + Large Maps
		
		moveToShowBeepers();
	}
	
	/**
	 * Karel moves one street / avenue to show how many Beepers he put down.
	 * Precondition: facing East
	 * Postcondition: facing East
	 */
	private void moveToShowBeepers()
	{
		if(this.mapWidth == 1) {
			turnLeft();
			move();
			turnRight();
		}
		else move();
	}

	/**
	 * Pauses the program for 'offset' ms.
	 * */	
	private void sleep(int offset) {
		try{
			Thread.sleep(offset);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
