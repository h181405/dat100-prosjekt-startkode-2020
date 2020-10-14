package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		double ystep;
		
		// TODO - START
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		

		ystep = MAPYSIZE / (Math.abs(maxlat - minlat)); 

		return ystep;

		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {

		// TODO - START
		
		setColor(0,255,0);
		
		int x1,x2, y1, y2;
		double xmin = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		double ymin = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		for(int i = 0; i < gpspoints.length -1; i++) {
			x1 = (int)((gpspoints[i].getLongitude()-xmin)*xstep());
			x2 = (int)((gpspoints[i+1].getLongitude()-xmin)*xstep());
			
			y1 = (int)((gpspoints[i].getLatitude()-ymin)*ystep());
			y2 = (int)((gpspoints[i+1].getLatitude()-ymin)*ystep());
			
			fillCircle(x1,MAPYSIZE-y1,3);
			
			drawLine(x1,MAPYSIZE-y1,x2,MAPYSIZE - y2);
		}
		
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO - START
		
		String str;
		
		str = String.format("%1$-15s:%2$11s", "Total Time",GPSUtils.formatTime(gpscomputer.totalTime()));
		drawString(str, 0, TEXTDISTANCE);
		
		str = String.format("%1$-15s:%2$11.2f km", "Total Distance",gpscomputer.totalDistance());
		drawString(str, 0, TEXTDISTANCE*2);
		
		str = String.format("%1$-15s:%2$11.2f m", "Total Elevation",gpscomputer.totalElevation());
		drawString(str, 0, TEXTDISTANCE*3);
		
		str = String.format("%1$-15s:%2$11.2f km/t", "Max Speed",gpscomputer.maxSpeed());
		drawString(str, 0, TEXTDISTANCE*4);
		
		str = String.format("%1$-15s:%2$11.2f km/t", "Average Speed",gpscomputer.averageSpeed());
		drawString(str, 0, TEXTDISTANCE*5);
		
		str = String.format("%1$-15s:%2$11.2f kcal", "Energy",gpscomputer.totalKcal(80));
		drawString(str, 0, TEXTDISTANCE*6);
		
		
		// TODO - SLUTT;
	}

}
