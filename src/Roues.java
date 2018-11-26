import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.*;
import lejos.robotics.navigation.MovePilot;
import lejos.hardware.Button;
import lejos.hardware.motor.*;

public class Roues {
	
	private float derniereDistance;
	private int orientation; // restrict to 1 and 0
	private boolean active;
	EV3MediumRegulatedMotor roueG = new EV3MediumRegulatedMotor(MotorPort.A);
	EV3MediumRegulatedMotor roueD = new EV3MediumRegulatedMotor(MotorPort.C);
	Wheel leftWheel = WheeledChassis.modelWheel(roueG, 56).offset(60).gearRatio(2);
	Wheel rightWheel = WheeledChassis.modelWheel(roueD, 56).offset(-60).gearRatio(2);
	Chassis chassis = new WheeledChassis( new Wheel[]{leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
	MovePilot pilote = new MovePilot(chassis);
    
	public Roues() {
		orientation = 0;
//		setSens(1);
//		setActive(false);
	}
	
	public int getOrientation() {
		return orientation;
	}
	
	public float getDerniereDistance() {
	    return derniereDistance;
	}
	
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	
	
	public void avance(CapteurToucher toucher, CapteurDistance distance) {// CapteurLumiere lumiere) 
		
		float distanceInitiale = distance.getDistance();
		float distanceActuelle = 0;
		
		while (distanceActuelle<=distanceInitiale && toucher.isTouche()==false && Button.ENTER.isUp()){/*^ lumiere.determinerCouleur().equals("white") == false) */
			
			distanceActuelle=distanceInitiale;
			distanceInitiale = distance.getDistance();
			pilote.forward();
		}
		derniereDistance = distanceActuelle;
	}
	
	public void recule() {
		//tant qu'on dit de reculer
		chassis.setLinearSpeed(200);
		chassis.setLinearAcceleration(100);
		chassis.travel(-500000);
		chassis.waitComplete();
		}		
	
	//peut-etre juste faire genre une m�thode qui fait tourner
	public void tourne(int angle) {
		chassis.setAngularAcceleration(100);
		chassis.setAngularSpeed(200);
		chassis.rotate(angle);
		chassis.waitComplete();
		orientation+=angle;
	}
	
	//on verra plus tard pour la direction
	public void demiTour() {
		pilote.setAngularAcceleration(100);
		pilote.setAngularSpeed(200);
		pilote.rotate(92);
		orientation += 92;
	}
	
	
}
