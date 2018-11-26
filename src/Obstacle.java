import lejos.utility.Delay;

public class Obstacle {
	
	private boolean attrape;
	private boolean depose;
	private boolean present;

	public Obstacle() {
		attrape = false;
		depose = false;
		present = false;
		}
	
	public void isPresent(float distanceInitiale, CapteurDistance distance, CapteurToucher toucher, Roues r, Pinces p) {
		float d = 0;
		d = distanceInitiale;
		
		r.tourne(15);
		Delay.msDelay(500);
		if(distance.getDistance()<r.getDerniereDistance()) {
			this.attrape(toucher, r, p, distance);
		}
			else
		
				r.tourne(-30);
				Delay.msDelay(500);
				
				if(distance.getDistance()<r.getDerniereDistance()) {
					
		            this.attrape(toucher, r, p, distance);	
					
				}
		
					else {
						r.tourne(15);
						Delay.msDelay(500);
						this.attrape(toucher, r, p, distance);
						}
		
	}
	
		
	public void attrape(CapteurToucher toucher, Roues r, Pinces p, CapteurDistance distance) {
		
		r.avance();
		
		p.capturerPalet();
		
	}
	
	public void deposePalet(Roues r, CapteurLumiere lumiere, CapteurToucher toucher, CapteurDistance distance) {
		int o = r.getOrientation();
		
		if (o < 0) 
			r.tourne(-o);
		
		else if(o > 0) 
			r.tourne(o);
		
		while (lumiere.determinerCouleur().equals("white") == false) {
			r.avance();
		}
		
		
	}
	
	
	
	/*public void depart(Roues r, Pinces p) {
		p.ouvertureInitiale();
		r.avance(80, 1800); 
		p.capturerPalet();
		r.tourUnCranDroite();
		r.avance(100, 2800);
		r.tourUnCranGauche();
		r.avance(100, 300);
		Delay.msDelay(1000);
		p.relachePalet();
		
	}*/
	
	
	

}
