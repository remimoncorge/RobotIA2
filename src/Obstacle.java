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
			this.attrape(toucher, r, p);
		}
			else
		
				r.tourne(-30);
				Delay.msDelay(500);
				
				if(distance.getDistance()<r.getDerniereDistance()) {
					
		            this.attrape(toucher, r, p);	
					
				}
		
					else {
						r.tourne(15);
						Delay.msDelay(500);
						this.attrape(toucher, r, p);
						}
		
	}
	
		
	public void attrape(CapteurToucher toucher, Roues r, Pinces p) {
		p.relachePalet();
		
		r.pilote.travel(10);
		
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
	
	
	
	public void depart(Roues r, Pinces p) {
		p.ouvertureInitiale();
		r.pilote.travel(280); 
		p.capturerPalet();
		r.tourne(15);
		r.pilote.travel(300);
		r.tourne(-15);
		r.pilote.travel(700);
		p.relachePalet();
		/*r.pilote.travel(-100);
		r.demiTour();
		r.pilote.travel(100);
		p.capturerPalet();
		r.demiTour();
		r.pilote.travel(200);*/
	}
	
	
	

}
