import java.util.ArrayList;
import java.util.Random;

public class Gameshow {
	
	public int numCorrectControl = 0;
	public int numIncorrectControl = 0;
	public int numIncorrectChange = 0;
	public int numCorrectChange = 0;


	
	Random r = new Random();
	int c,g;
	
	public void runTrialControl(){
		c = -1;
		g = -1;
		c = r.nextInt(3);
		ArrayList<Door> doors = new ArrayList<Door>();
		Door d1 = new Door();
		Door d2 = new Door();
		Door d3 = new Door();
		doors.add(d1);
		doors.add(d2);
		doors.add(d3);
		doors.get(c).prizeDoor = true;
		g = r.nextInt(3);
		if(doors.get(g).prizeDoor == true){
			logCorrectControl();
		}else{
			logIncorrectControl();
		}
	}
	
	public void runTrialChange(){
		c = r.nextInt(3);
		ArrayList<Door> doors = new ArrayList<Door>();
		Door d1 = new Door();
		Door d2 = new Door();
		Door d3 = new Door();
		doors.add(d1);
		doors.add(d2);
		doors.add(d3);
		doors.get(c).prizeDoor = true;
		g = r.nextInt(3);
		doors.get(g).picked = true;
		for(int i = 0; i < 3; i++){
			if(doors.get(i).picked != true && doors.get(i).prizeDoor != true) {
				doors.remove(i);
				break;
			}
		}
		
		if(doors.get(0).picked == true){
			if(doors.get(1).prizeDoor == true){
				logCorrectChange();
			}else{
				logIncorrectChange();
			}
		}else{
			if(doors.get(0).prizeDoor == true){
				logCorrectChange();
			}else{
				logIncorrectChange();
			}
		}
		
	}
	
	private void logIncorrectChange() {
		numIncorrectChange++;
		System.out.println("Change Incorrect");
	}

	private void logCorrectChange() {
		numCorrectChange++;
		System.out.println("Change Correct");
	}

	private void logIncorrectControl(){
		numIncorrectControl++;
		System.out.println("Control Incorrect");
	}

	public void logCorrectControl(){
		numCorrectControl++;
		System.out.println("Control Correct");
	}
	
	public static void main(String[] args){
		Gameshow g = new Gameshow();
		for(int i = 0; i < 10000000; i++){
			g.runTrialControl();
			g.runTrialChange();
		}
		double control = (double) g.numCorrectControl / (g.numCorrectControl + g.numIncorrectControl);
		double change = (double) g.numCorrectChange / (g.numCorrectChange + g.numIncorrectChange);
		
		System.out.println("Control percentage is: " + control);
		System.out.println("Change percentage is: " + change);

	}

}
