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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Drive Forward\nand Stop\n");
        /*System.out.println("Press any key to start");

        Button.LEDPattern(4);     // flash green led and
        Sound.beepSequenceUp();   // make sound when ready.

        Button.waitForAnyPress();*/

        // create two motor objects to control the motors.
//        UnregulatedMotor roueGauche = new UnregulatedMotor(MotorPort.A);
//        UnregulatedMotor roueDroite = new UnregulatedMotor(MotorPort.C);
//        UnregulatedMotor pinces = new UnregulatedMotor(MotorPort.B);
//
//        // set motors to 50% power.
//        /*roueGauche.setPower(0);
//        roueDroite.setPower(0);
//        
//
//        // wait 2 seconds.
//        Delay.msDelay(2000);
//
//        // stop motors with brakes on. 
//        roueGauche.stop();
//        roueDroite.stop();
//
//        // free up motor resources. 
//        roueGauche.close(); 
//        roueDroite.close();*/
//                
// 
//        //Sound.beepSequence(); // we are done.
//        
//
//        //System.out.println("Color Demo");
//        Lcd.print(2, "Press to start");
//        
//        Button.LEDPattern(4);    // flash green led and
//        Sound.beepSequenceUp();    // make sound when ready.
//
//        Button.waitForAnyPress();
//        Button.LEDPattern(0);
//        
//        // run until escape button pressed.
//        

//
//        Delay.msDelay(1000);
//
//        color.setRedMode();
//        color.setFloodLight(Color.RED);
//        color.setFloodLight(true);
//        
//        while (Button.ESCAPE.isUp())
//        {
//            Lcd.clear(5);
//            Lcd.print(5, "red=%.3f", color.getRed());
//            Delay.msDelay(250);
//        }
//
//        Delay.msDelay(1000);
//
//        color.setRGBMode();
//        color.setFloodLight(Color.WHITE);
//        
//        Color rgb;
//        
//        while (Button.ESCAPE.isUp())
//        {
//            rgb = color.getColor();
//            
//            Lcd.clear(6);
//            Lcd.print(6, "r=%d g=%d b=%d", rgb.getRed(), rgb.getGreen(), rgb.getBlue());
//            Delay.msDelay(250);
//        }
//
//        Delay.msDelay(1000);
//
//        color.setColorIdMode();
//        color.setFloodLight(false);
//        
//        while (Button.ESCAPE.isUp())
//        {
//            Lcd.clear(7);
//            Lcd.print(7, "id=%s", ColorSensor.colorName(color.getColorID()));
//            Delay.msDelay(250);
//        }
//
//        // free up resources.
//        color.close();
//        
//        Sound.beepSequence();    // we are done.
		
		
		Pinces p = new Pinces();
		CapteurDistance distance = new CapteurDistance();
		Obstacle o = new Obstacle();
		Roues r = new Roues();
		Battery b = new Battery();
		CapteurToucher toucher = new CapteurToucher();
		//CapteurLumiere lumiere = new CapteurLumiere();
		
		
		if (b.getVoltage() < 4 ) {
			System.out.println(" Chargez la batterie !! ");
			Delay.msDelay(5000);
			System.exit(0);
			
		}
		
		
		o.depart(r, p);
		p.fermetureInitiale();

			
	
	
	}
    }
                

