import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.*;
import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.MoveListener;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.MoveProvider;
import lejos.hardware.Button;
import lejos.hardware.motor.*;

public class Roues implements MoveListener{
	
	private float derniereDistance;
	private int orientation;
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
	
	
	public void avance() {// CapteurLumiere lumiere) 
		chassis.setAcceleration(100, 10);
		chassis.setLinearSpeed(200);
		chassis.travel(200);
	}
	
	public void recule() {
		chassis.setAcceleration(100, 10);
		chassis.setLinearSpeed(200);
		chassis.travel(-200);
		}		
	
	//peut-etre juste faire genre une m�thode qui fait tourner
	public void tourne(double angle) {
		chassis.setAcceleration(100, 150);
		chassis.setAngularSpeed(250);
		chassis.rotate(angle);
		orientation+=angle;
	}
	
	//on verra plus tard pour la direction
	public void demiTour() {
		tourne(92);
		
	}
	
	public void retour() {
		MoveListener listener;
		
	}
	
	public void suitLigne(CapteurLumiere lumiere, CapteurDistance distance) {
		while (distance.getDistance()>25) {
			while (lumiere.determinerCouleur().equals("grey")==false) {
				this.avance();
			}
			if (lumiere.determinerCouleur().equals("grey")==true) {
				pilote.rotate(10);
				if (lumiere.determinerCouleur().equals("grey")==false)
					this.suitLigne(lumiere, distance);
				else {
					pilote.rotate(-20);
				    if(lumiere.determinerCouleur().equals("grey")==false)
					    this.suitLigne(lumiere, distance);
				}
			}
		}
			
	
	}

	@Override
	public void moveStarted(Move event, MoveProvider mp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveStopped(Move event, MoveProvider mp) {
		// TODO Auto-generated method stub
		
	}

	
	
}
