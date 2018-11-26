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
	
	
	public void avance() {// CapteurLumiere lumiere) 
		pilote.setLinearAcceleration(100);
		pilote.setLinearSpeed(200);
		pilote.forward();
	}
	
	public void recule() {
		pilote.setLinearAcceleration(100);
		pilote.setLinearSpeed(200);
		pilote.backward();
		}		
	
	//peut-etre juste faire genre une m�thode qui fait tourner
	public void tourne(double angle) {
		pilote.setAngularAcceleration(100);
		pilote.setAngularSpeed(200);
		pilote.rotate(angle);
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
