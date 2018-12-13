import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Pinces {
	

	private UnregulatedMotor pinces = new UnregulatedMotor(MotorPort.B);
	
	public Pinces() {
		
	}
	
	//genre va falloir calculer le nombre de tours du moteur pour ouvrir/fermer
	//faut aussi voir pour ouvrir un minimum
	public void ouvrir(int puissance, int delay, int duree) {
		//ptetre v�rifier pour valeur n�gatives
		if (delay != 0) {
			Delay.msDelay(delay);
		}		
		pinces.setPower(puissance);
		pinces.forward();
		Delay.msDelay(duree);
		pinces.stop();
//		pinces.close();
	}
	
	public void fermer(int puissance, int delay, int duree) {
		if (delay != 0) {
			Delay.msDelay(delay);
		}
		pinces.setPower(puissance);		
		pinces.backward();
		Delay.msDelay(duree);
		pinces.stop();
	}
	
	public void capturerPalet() {
		fermer(80, 0, 1200);
	}
	
	public void relachePalet() {
		ouvrir(80, 0, 1200);
	}
	
	public void ouvertureInitiale() {
		ouvrir(80,0,1500);
	}
	
	public void fermetureInitiale() {
		fermer(80,0,1500);
	}
	
	public void close() {
		pinces.close();
	}
	
//	public void stop() {
//		
//	}
	
	//verifier quand c'est un palet ou juste quand il fait calin dans le vide
}
