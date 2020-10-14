package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START

		for(int i = 0; i < gpspoints.length - 1 ; i++) {
			
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
			
			
		}

		// TODO - SLUTT
		return distance;
	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START

		for(int i = 0; i < gpspoints.length - 1 ; i++) {
				
			if(gpspoints[i+1].getElevation() > gpspoints[i].getElevation()) {
				elevation += gpspoints[i+1].getElevation()-gpspoints[i].getElevation();
				
			}
				
		}

		// TODO - SLUTT
		return elevation;
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		int time = 0;
		time = gpspoints[gpspoints.length-1].getTime() - gpspoints[0].getTime();
		
		return time;
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		// TODO - START		// OPPGAVE - START
		
		double speed[] = new double[gpspoints.length-1];
		
		double time;
		
		double distance;
		
		for(int i = 0; i < gpspoints.length - 1; i++) {
			
			time = gpspoints[i+1].getTime() - gpspoints[i].getTime();
			distance = GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
			
			speed[i] = distance / time * 3.6;
			
		}

		// TODO - SLUTT
		return speed;

	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		double speed[] = speeds();
		
		// TODO - START
		
		for(int i = 0; i < speed.length;i++) {
			if(speed[i] > maxspeed) {
				maxspeed = speed[i];
			}
		}
		
		// TODO - SLUTT
		return maxspeed;
	}

	public double averageSpeed() {

		double average = 0;
		
		// TODO - START
		
		average = totalDistance() / totalTime() *3.6;
		
		// TODO - SLUTT
		return average;
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		// TODO - START
		
		if(speedmph < 10) {
			met = 4.0;
		} else if (speedmph < 12) {
			met = 6.0;
		} else if (speedmph < 14) {
			met = 8.0;
		} else if (speedmph < 16) {
			met = 10.0;
		} else if (speedmph < 20) {
			met = 12.0;
		} else if (speedmph > 20) {
			met = 16.0;
		}
		
		kcal = met*secs/3600*weight;
		// TODO - SLUTT
		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;
		
		double speed;
		int time;

		// TODO - START
		
		for(int i = 0; i < gpspoints.length - 1; i++) {
			speed = speeds()[i];
			time = gpspoints[i+1].getTime() - gpspoints[i].getTime();
			
			totalkcal += kcal(weight, time, speed);
		}
		
		// TODO - SLUTT
		return totalkcal;
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");
		
		String str;


		// TODO - START
		str = String.format("%1$-15s:%2$11s", "Total Time",GPSUtils.formatTime(totalTime()));
		System.out.println(str);
		
		str = String.format("%1$-15s:%2$11.2f km", "Total Distance",totalDistance());
		System.out.println(str);
		
		str = String.format("%1$-15s:%2$11.2f m", "Total Elevation",totalElevation());
		System.out.println(str);
		
		str = String.format("%1$-15s:%2$11.2f km/t", "Max Speed",maxSpeed());
		System.out.println(str);
		
		str = String.format("%1$-15s:%2$11.2f km/t", "Average Speed",averageSpeed());
		System.out.println(str);
		
		str = String.format("%1$-15s:%2$11.2f kcal", "Energy",totalKcal(WEIGHT));
		System.out.println(str);
		
		System.out.println("==============================================");
		System.out.println();
		System.out.println();
		// TODO - SLUTT
	}

}
