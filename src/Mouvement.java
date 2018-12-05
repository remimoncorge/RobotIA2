import java.util.ArrayList;
import java.util.List;

import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.MoveListener;
import lejos.robotics.navigation.MoveProvider;

public class Mouvement implements MoveListener{

	@Override
	public void moveStarted(Move event, MoveProvider mp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveStopped(Move event, MoveProvider mp) {
		// TODO Auto-generated method stub
		
	}
	
	public void ajoutMouvement(Move event) {
		
		String moveType = event.getMoveType().toString();
		float distance = event.getDistanceTraveled();
		float angle = event.getAngleTurned();
		float travelspeed = event.getTravelSpeed();
		float rotateSpeed = event.getRotateSpeed();
		boolean isMoving = event.isMoving();
		
	    List<Object> listeMouvement = new ArrayList<Object>();
	    
	    Move m1 = new Move(distance, angle, isMoving);
	    listeMouvement.add(m1);
	    
	   
	}

}
