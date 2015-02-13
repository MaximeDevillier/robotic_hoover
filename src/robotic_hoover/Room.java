package robotic_hoover;

import java.util.List;

public class Room {
	
	private int x;
	private int y;
	private Square[][] squareCollection;

	//CONSTRUCTOR
	public Room(int x, int y){
		this.x=x;
		this.y=y;
		squareCollection = new Square[x][y];
		for(int i = 0 ; i < this.x ; i++)
		{
			for(int j = 0; j < this.y; j++)
			{
				squareCollection[i][j] = new Square();
			}
		}
	}
	
//  <GETTERS AND SETTERS>
	
	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	public Square[][] getSquareCollection() {
		return squareCollection;
	}



	public void setSquareCollection(Square[][] squareCollection) {
		this.squareCollection = squareCollection;
	}

//  </GETTERS AND SETTERS>

	void placeDustInRoom(List<Position> dustPositions)
	{
		for(int i=0; i<dustPositions.size(); i++)
		{
			Position p = dustPositions.get(i);
			this.squareCollection[p.getX()][p.getY()].setDust(1);
		}
	}

}
