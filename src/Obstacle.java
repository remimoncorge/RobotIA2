import lejos.utility.Delay;

public class Obstacle {


	public Obstacle() {
		}
	
	public void isPresent(float distanceInitiale, CapteurDistance distance, CapteurToucher toucher, Roues r, Pinces p) {
		float d = 0;
		d = distanceInitiale;
		System.out.println(distanceInitiale);
		Delay.msDelay(2000);
	
			while (distance.getDistance()<distanceInitiale+0.02 && distance.getDistance()>0.25) {
				System.out.println("DistanceInitiale : " + distanceInitiale + "distance : " + distance.getDistance());
				Delay.msDelay(2000);
				distanceInitiale = distance.getDistance();
				r.avance(distance);
			}
				
			r.tourne(15);
			Delay.msDelay(1000);
			int i = 0;
			if(distance.getDistance()<=distanceInitiale) {
				this.attrape(toucher, r, p, distance);
				i=1;
			}
			
			else if (i==0) {
				r.tourne(-30);
			    Delay.msDelay(1000);
			    if(distance.getDistance()<=distanceInitiale) {
		            this.attrape(toucher, r, p, distance);	
		            i=1;
			    }
			    else if(i==0){
				    r.tourne(15);
				    this.attrape(toucher, r, p, distance);
				}
			}
			Delay.msDelay(10000);
	}
	
		
	public void attrape(CapteurToucher toucher, Roues r, Pinces p, CapteurDistance distance) {
		p.relachePalet();
		
		while(toucher.isTouche()==false)
			r.avance(distance);
		p.capturerPalet();
	}
	
	public void deposePalet(Roues r, CapteurLumiere lumiere, CapteurToucher toucher, CapteurDistance distance, Pinces p) {
		int o = r.getOrientation()%90;
		
		if (o < 0) 
			r.tourne(-o);
		
		else if(o > 0) 
			r.tourne(o);
		
		while (lumiere.determinerCouleur().equals("white") == false) {
			r.avance(distance);
		}
		p.relachePalet();
		r.pilote.travel(-20);
		while (lumiere.determinerCouleur().equals("grey") == true)
			r.recule();
		r.demiTour();
	}
	
	
	
	public void depart(Roues r, Pinces p, CapteurLumiere lumiere) {
		p.ouvertureInitiale();
		r.pilote.travel(280); 
		p.capturerPalet();
		r.tourne(15);
		r.pilote.travel(300);
		r.tourne(-15);
		r.pilote.travel(700);
		p.relachePalet();
		r.pilote.travel(-100);
		p.capturerPalet();
		//while(lumiere.determinerCouleur().equals("grey")==true)
		//	r.recule();
		/*r.pilote.travel(-100);
		r.demiTour();
		r.pilote.travel(100);
		p.capturerPalet();
		r.demiTour();
		r.pilote.travel(200);*/
	}
	
	
	

}
