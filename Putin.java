import jrobots.utils.*;

import java.awt.Color;

import jrobots.simulation.simulationObjects.JRobot2011;


public class Putin extends JRobot2011 {
	private static final long serialVersionUID = 1L;
	boolean a = true;
	double count = 0;
	protected void init() {
		setNameColor(Color.MAGENTA);
		setBodyColor(Color.MAGENTA);
		setTurretColor(Color.MAGENTA);
	}
	
	Angle winkel = RIGHT;
	int i = 0;
	Scan scanner = new Scan(null,null,0,null,0);

	protected void actions() {
		if(getProximityScanDroppable() != null) {
			
			setAutopilot(getProximityScanDroppable().getAngle().add(DOWN), getMaxForwardVelocity());
		}
		scanner = getLastScan();
		if(!scanner.isTargetLocated()) {
			setScanDirection(winkel);
			setScanAperture(DOWN.mult(0.5));
			System.out.println(winkel);
		if(i == 36) {
			winkel = RIGHT;
			i = 0;
		}
		Angle wert = DOWN.mult(0.111111111);
		winkel = winkel.add(wert);
		i++;
		}
		
		if(scanner.isTargetLocated() && isScanFromNow()) {
			
			setScanAperture(DOWN.mult(0.05));
			Angle a = scanner.scanDirection;
			Vector v = scanner.estimatedTargetPosition();
			Angle x = v.getAngle();
			if( getEnergy() > 0.1 && count < 2) {
				setLaunchProjectileCommand(a);
			}
			if(getRocketPosition() != null) {
				count = 0;
			}
			while(getHealth() > 0.5 && getEnergy() > 1) {
				setRocketHeading(getOrientation());
			}
			
			setAutopilot(a.getOpposite().add(LEFT).mult(0.8), getMaxForwardVelocity()); 
			
		}
		setScanDirection(winkel);
		//scan end
		
		/* 
			if(scanner.isTargetLocated()) {
			setScanAperture(DOWN.mult(0.2));
			scanner2 = getLastScan();
			if(scanner2.isTargetLocated()) {
			setScanAperture(DOWN.mult(0.05));
			Angle x = scanner.scanDirection;
			setLaunchProjectileCommand(x);
			}
		 */
	}
}