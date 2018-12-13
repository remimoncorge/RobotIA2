import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.utility.Delay;
import lejos.hardware.Button;
import lejos.hardware.Power;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.robotics.Color;
import lejos.hardware.*;;

public class RobotIA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Battery b = new Battery();

		if (b.getVoltage() < 4 ) {
			System.out.println(" Chargez la batterie !! ");
			Delay.msDelay(5000);
			System.exit(0);
		}
		
		Pinces p = new Pinces();
		CapteurDistance distance = new CapteurDistance();
		Obstacle o = new Obstacle();
		Roues r = new Roues();
		CapteurToucher toucher = new CapteurToucher();
		CapteurLumiere lumiere = new CapteurLumiere();
		
		System.out.println("Appuyer ENTREE pour commencer");
		while (Button.ESCAPE.isUp()) {
			Button.waitForAnyPress();
			if (Button.ENTER.isDown()) {
				//début partie classique
				o.depart(r, p, lumiere);
				o.recherche(distance.getDistance(), distance, r, p, toucher, lumiere, o);

			}
			Button.waitForAnyPress();
			if (Button.ENTER.isDown()) {
				//pause
				r.stopRoues();
				System.out.println("Temps mort");
				Button.waitForAnyPress();
				
				if (Button.ENTER.isDown()) {
					//reprise partie sans départ brut
					System.out.println("Reprise partie");
					o.recherche(distance.getDistance(), distance, r, p, toucher, lumiere, o);
				}
			}
			Button.waitForAnyPress();
			if (Button.UP.isDown()) {
				System.exit(0);
			}
		}

		}
	
	
	
    }
                

