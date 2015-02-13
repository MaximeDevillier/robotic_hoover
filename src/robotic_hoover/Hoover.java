package robotic_hoover;

public class Hoover {
	
	private Position position;
	private int dustRemoved;//number of times the hoover removed dust
	
	public Hoover(Position initialPosition)
	{
		position = initialPosition;
		dustRemoved = 0;
	}

	
	//    <GETTERS AND SETTERS>
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getDustRemoved() {
		return dustRemoved;
	}
	public void setDustRemoved(int dust) {
		this.dustRemoved = dust;
	}
	
//  </GETTERS AND SETTERS>
	
	
	public void clean()
	{
		this.dustRemoved += 1;
	}

	public void goNorth(){
		this.position.setY(position.getY() + 1);
	}
	
	public void goSouth(){
		this.position.setY(position.getY() - 1);
	}
	
	public void goEst(){
		this.position.setX(position.getX() + 1);
	}
	
	public void goWest(){
		this.position.setX(position.getX() - 1);
	}
}
