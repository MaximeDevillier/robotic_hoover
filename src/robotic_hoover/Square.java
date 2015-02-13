package robotic_hoover;

public class Square {
	
	private int dust;
	
	public Square(){
		dust=0;
	}
	
//  <GETTERS AND SETTERS>

	public int getDust() {
		return dust;
	}
	public void setDust(int dust) {
		this.dust = dust;
	}
	
//  </GETTERS AND SETTERS>
	
	public Boolean isDusty(){
		if(this.dust>0) return true;
		return false;
	}

	public void clean(){
		this.dust=0;
	}
	
}
