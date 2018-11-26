import lejos.hardware.port.SensorPort;

public class CapteurDistance {

	private float distance;
	private UltraSonicSensor sensor  = new UltraSonicSensor(SensorPort.S1);

	
	public CapteurDistance() {
	}
	
	// traite deja les valeurs
	public float getDistance() {
		float valeur;
		float[] tableau = new float[10];
		float ecart = 0;
		float moyenne = 0;
		int check = 10;
		for (int i =0; i<10; i++) {
			valeur = sensor.getRange();
			tableau[i]=valeur;
		}
		moyenne = moyenne(tableau);
		
		ecart = ecartType(tableau);
		
		for (int i = 0; i<10; i++) {
			if((tableau[i]>(moyenne + ecart)) ^ tableau[i]<(moyenne - ecart)) {
				tableau[i] = -1;
				check --;
			}
		}
		float [] tableauFinal = new float[check];
		int j =0;
		for(int i = 0; i<tableau.length; i++) {
			if (tableau[i]>0) {
				tableauFinal[j] = tableau[i];
				j++;
			}
		}
		distance = moyenne(tableauFinal);
		return distance;
	}
	
	public float ecartType(float[] tab) {
		float somme = 0;
		int longueur = tab.length;
		float moyenne = 0;
		float[] valeur = new float[longueur];
		moyenne = moyenne(tab);
		for(int i=0; i<longueur; i++)
			valeur[i] = (tab[i] - moyenne)*(tab[i] - moyenne);
		somme = 0;
		for(int i=0; i<longueur; i++)
			somme = somme + valeur[i];
		
		return (float) Math.sqrt((somme/(float)longueur));
		
		
	}
	
	public float moyenne(float[] tab) {
		float somme = 0;
		for(int i = 0; i<tab.length; i++) {
			somme = somme + tab[i];
		}
		return somme/(float)tab.length;
	}
	
	public void enable()
	{
		sensor.enable();
	}
	
	/**
	 * Disable UltraSonic sensor.
	 */
	public void disable()
	{
		sensor.disable();
	}
}
