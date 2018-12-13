import lejos.utility.Delay;

public class Obstacle {


	public Obstacle() {
		}
	
	/*public void isPresent(float distanceInitiale, CapteurDistance distance, CapteurToucher toucher, Roues r, Pinces p) {
		float d = 0;
		d = distanceInitiale;
		System.out.println(distanceInitiale);
		Delay.msDelay(2000);
	
			while (distance.getDistance()<distanceInitiale+0.02 && distance.getDistance()>0.25) {
				System.out.println("DistanceInitiale : " + distanceInitiale + "distance : " + distance.getDistance());
				Delay.msDelay(2000);
				distanceInitiale = distance.getDistance();
				r.avance();
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
	}*/
	
	
	public void recherche(float distanceInitiale, CapteurDistance cd, Roues r, Pinces p, CapteurToucher t, CapteurLumiere lumiere, Obstacle o) {
		r.demiTour();
		int i = 0;
		int j = 0;
		while (i==0 && j<20) {
			if(cd.getDistance()<0.55) {
				attrape(t, r, p, cd, lumiere, o, t);
				i=1;
				break;
			}
			else {
				r.tourne(5);
				Delay.msDelay(100);
				j+=1;
			}
				
		}
		//rien attrappé et tourné plus de 20 fois
		if (j==20) {
			r.demiTour();
			r.avance(cd, o, r, p, t, lumiere, 300);
			recherche(cd.getDistance(), cd, r, p , t, lumiere, o);
		}
		
		
	}
	
		
	public void attrape(CapteurToucher toucher, Roues r, Pinces p, CapteurDistance distance, CapteurLumiere lumiere, Obstacle o, CapteurToucher t) {
		p.relachePalet();
		while(toucher.isTouche()==false || distance.getDistance()>0.20) {
			r.avance(distance, o, r, p, t, lumiere);
		}
		p.capturerPalet();
		deposePalet(r, lumiere, toucher, distance, p, o);

	}
	
	public void deposePalet(Roues r, CapteurLumiere lumiere, CapteurToucher toucher, CapteurDistance distance, Pinces p, Obstacle o) {
		r.tourne(-(r.getOrientation()));
		
		while (distance.getDistance()>0.20) {
			r.avance(distance, o, r, p, toucher, lumiere);
		}
		p.relachePalet();
		r.pilote.travel(-200);
		r.demiTour();
		p.capturerPalet();
		recherche(distance.getDistance(), distance, r, p, toucher, lumiere, o);
	}
	
	
	
	public void depart(Roues r, Pinces p, CapteurLumiere lumiere) {
		r.pilote.setLinearAcceleration(150);
		r.pilote.setLinearSpeed(250);
		p.ouvertureInitiale();
		r.pilote.travel(280); 
		p.capturerPalet();
		r.tournePilote(-15);
		r.pilote.travel(300);
		r.tournePilote(15);
		r.pilote.travel(720);
		p.relachePalet();
		r.recule(100);
		p.capturerPalet();
		r.demiTour();
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
