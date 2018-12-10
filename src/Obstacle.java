import lejos.utility.Delay;

public class Obstacle {


	public Obstacle() {
		}
	
	public void isPresent(float distanceInitiale, CapteurDistance distance, CapteurToucher toucher, Roues r, Pinces p) {
//		float d = 0;
//		d = distanceInitiale;
		while (distance.getDistance()<distanceInitiale+0.05 && distance.getDistance()>0.25) {
			distanceInitiale = distance.getDistance();
			r.avance();
		}
		r.tourne(15);
		Delay.msDelay(500);
		if(distance.getDistance()<=distanceInitiale) 
			this.attrape(toucher, r, p);
		else
			r.tourne(-30);
			Delay.msDelay(500);
			if(distance.getDistance()<=distanceInitiale) 
	            this.attrape(toucher, r, p);	
			else {
				r.tourne(15);
				this.attrape(toucher, r, p);
				}
		
	}
	
	public void recherche(float distanceInitiale, CapteurDistance cd, Roues r, Pinces p, CapteurToucher t) {
		float nearest = 0.40f;
		int angleOfNearest = 0;
		int angle = 10;				
		while (angle < 180) {
			//maybe remove distanceInitiale
			if ((cd.getDistance() < nearest) && (cd.getDistance() > distanceInitiale + 0.05) ) {
				nearest = cd.getDistance();
				angleOfNearest = angle;
			}
			r.tourne(angle);
			angle+=10;
		}
		//if nearest distance different => something is found
		if (nearest != 0.40f) {
			//turn back to nearest obstacle found
			r.tourne(-(180 - angleOfNearest));
			//this.attrape(t, r, p);
			isPresent(distanceInitiale, cd, t, r, p);
		} else { //nearest distance is the same => nothing is found
			//maybe proceed differently to move forward
			r.avance();
			recherche(distanceInitiale, cd, r, p, t);
		}
	}
	
		
	public void attrape(CapteurToucher toucher, Roues r, Pinces p) {
		p.relachePalet();
		
		while(toucher.isTouche()==false)
			r.avance();
		
		p.capturerPalet();
	}
	
	public void deposePalet(Roues r, CapteurLumiere lumiere, CapteurToucher toucher, CapteurDistance distance, Pinces p) {
		int o = r.getOrientation();
		
		if (o < 0) 
			r.tourne(-o);
		
		else if(o > 0) 
			r.tourne(o);
		
		while (lumiere.determinerCouleur().equals("white") == false) {
			r.avance();
		}
		p.relachePalet();
		r.pilote.travel(-20);
		while (lumiere.determinerCouleur().equals("grey") == true)
			r.recule();
		
		
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
