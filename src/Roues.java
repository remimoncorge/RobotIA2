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
	private int orientation = 0;
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
	
	public void setOrientation(double orientation) {
		this.orientation = (int) orientation;
	}
	
	
	public void avance(CapteurDistance distance, Obstacle o, Roues r, Pinces p, CapteurToucher t, CapteurLumiere lumiere) {// CapteurLumiere lumiere) 
		chassis.setAcceleration(100, 30);
		chassis.setLinearSpeed(200);
		chassis.travel(50);

		}
	
	
	public void avance(CapteurDistance distance, Obstacle o, Roues r, Pinces p, CapteurToucher t, CapteurLumiere lumiere, float d) {// CapteurLumiere lumiere) 
		chassis.setAcceleration(100, 30);
		chassis.setLinearSpeed(200);
		while (distance.getDistance()>0.15)
			chassis.travel(d);
		if (distance.getDistance()<0.18) {
			r.recule(400);
			r.demiTour();
			o.recherche(distance.getDistance(), distance, r, p, t, lumiere, o);
	}
	}

	
	public void recule() {
		chassis.setAcceleration(100, 10);
		chassis.setLinearSpeed(200);
		chassis.travel(-50);
		}		
	
	public void recule(float d) {
		chassis.setAcceleration(100, 10);
		chassis.setLinearSpeed(200);
		chassis.travel(-d);
		}
	
	//peut-etre juste faire genre une m�thode qui fait tourner
	public void tourne(double angle) {
		chassis.setAcceleration(100, 150);
		chassis.setAngularSpeed(250);
		chassis.rotate(angle);
		setOrientation(orientation+angle);
	}
	
	public void tournePilote(double angle) {
		pilote.setAngularAcceleration(150);
		pilote.setAngularSpeed(250);
		pilote.rotate(angle);
		setOrientation(orientation+angle);
	}
	//on verra plus tard pour la direction
	public void demiTour() {
		tourne(92);
		
	}
	
	
	
	public void stopRoues() {
		chassis.setAcceleration(0, 0);  
		chassis.setLinearSpeed(1);
		chassis.travel(0);
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
