package robotic_hoover;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static int XSize = -1, YSize = -1; //size of the room as defined in the input file
	static Position hooverInitialPosition; // hoover initial position as defined in the input file
	static List<Position> dustPositions = new ArrayList<Position>(); // List of patches of dirt positions
	static String instructions=""; // hoover driving instructions

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*steps
		 * 
		 * Read the input file and initialize global variables
		 * create and initialize our room/hoover
		 * place dust
		 * move hoover and count revomed dust
		 * write the solution in the output file
		 * 		  
		 */
		
		readInput();
		
		//is the input file valid or not ?
		Boolean inputFileOK = checkInputFile();
		if(!inputFileOK) return;
		
		
		Room room = new Room(XSize, YSize);
		Hoover hoover = new Hoover(hooverInitialPosition);
		room.placeDustInRoom(dustPositions);
		
		for(int i=0;i<instructions.trim().length();i++)
		{
			char currentChar = instructions.charAt(i);
			int hooverCurrentX = hoover.getPosition().getX();
			int hooverCurrentY = hoover.getPosition().getY();
			
			//we move the hoover if it is possible
			if(currentChar == 'N'){
				if(hooverCurrentY < room.getY() - 1)
					hoover.goNorth();
			}
			
			else if(currentChar == 'S'){
				if(hooverCurrentY > 0)
					hoover.goSouth();
			}
			
			else if(currentChar == 'E'){
				if(hooverCurrentX < room.getX() - 1)
					hoover.goEst();
			}
			
			else if(currentChar == 'W'){
				if(hooverCurrentX > 0)
					hoover.goWest();
			}
			
			
			//clean if there's dust
			if(room.getSquareCollection()[hooverCurrentX][hooverCurrentY].isDusty())
			{
				hoover.clean();
				room.getSquareCollection()[hooverCurrentX][hooverCurrentY].clean();
			}
				
		}

		writeOutput(hoover.getPosition(),hoover.getDustRemoved());
		System.out.println("The solution is available in the file named output.txt");
	}
	
	/**
	 * checks the input file (only checks the values, we assume the file format is OK)
	 * @return true if the file is valid, false otherwise
	 */
	static Boolean checkInputFile(){

		if(XSize<1 || YSize <1)
		{
			System.out.println("The room size is not valid. Double check the input file");
			return false;
		}
		
		if(hooverInitialPosition.getX()<0 
				|| hooverInitialPosition.getX() >= XSize
				|| hooverInitialPosition.getY() < 0
				|| hooverInitialPosition.getY() >= YSize)
		{
			System.out.println("Hoover initial position is not valid. Double check the input file");
			return false;
		}
		
		for(int i=0; i<dustPositions.size(); i++)
		{
			if(dustPositions.get(i).getX() < 0
				|| dustPositions.get(i).getX() >= XSize
				|| dustPositions.get(i).getY() < 0
				|| dustPositions.get(i).getY() >= YSize)
				{
					System.out.println("One or several patches of dirt positions are not valid. Double Check the input file");
					return false;
				}
		}
			
		return true;
	}

	/**
	 * reads input.txt and initializes global variables
	 */
	static void readInput(){
		int count = 1;
		
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt")))
		{
 
			String sCurrentLine;
 
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				
				if(count == 1){
					//room size
					Position roomSize;
					roomSize = splitLine(sCurrentLine);
					XSize = roomSize.getX();
					YSize = roomSize.getY();
					
				}
				else if(count == 2)
				{
					//hoover position
					hooverInitialPosition = splitLine(sCurrentLine);
					
				}
				
				else if(Character.isLetter(sCurrentLine.charAt(0)))
				{
					//instructions
					instructions = sCurrentLine;
				}
				else{
					//dustPositions
					Position dustPosition = splitLine(sCurrentLine);
					dustPositions.add(dustPosition);					
				}
					
				count++;
				
			}
			} catch (IOException e) {
				System.out.println("A problem occured while reading the input file (input.txt)");
				e.printStackTrace();
		} 
	}
	
	/**
	 * Writes the solution in output.txt
	 */
	static void writeOutput(Position hooverPosition,int dust){
		
		try {
	        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));	          
        	out.write(hooverPosition.getX() + " " + hooverPosition.getY());
            out.newLine();
            out.write(dust + "");
            out.close();
	        } catch (IOException e) {}
	}
	
	/**
	 * Splits line in order to get X and Y coordinates
	 */
	public static Position splitLine(String line){
		Position coordinates=new Position();
		String[] splittedText = line.split("\\s+");
		coordinates.setX(Integer.parseInt(splittedText[0]));
		coordinates.setY(Integer.parseInt(splittedText[1]));
		return coordinates;
	}
}
