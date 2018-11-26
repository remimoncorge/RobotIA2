import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3TouchSensor;

public class CapteurToucher extends EV3TouchSensor{
	

	
	public CapteurToucher() {
		super(LocalEV3.get().getPort("S4"));
	}
	
	public boolean isTouche() {
        float[] sample = new float[1];
        
        fetchSample(sample, 0);
        if(sample[0]==0)
        	return false;
        else
        	return true;
       	}
	

	
}
