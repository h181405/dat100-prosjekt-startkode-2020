package no.hvl.dat100ptc.oppgave1;

import no.hvl.dat100ptc.TODO;

public class GPSPoint {

	// TODO - objektvariable
	
	private int time;
	private double latitude, longitude, elevation;
		
	public GPSPoint(int time, double latitude, double longitude, double elevation) {

		// TODO - konstruktur
		
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
		
	}

	// TODO - get/set metoder
	public int getTime() {
		
		return this.time;
	}

	public void setTime(int time) {
				
		this.time = time;

	}

	public double getLatitude() {
		
		return this.latitude;
		
	}

	public void setLatitude(double latitude) {
		
		this.latitude = latitude;
		
	}

	public double getLongitude() {
		
		return this.longitude;
		
	}

	public void setLongitude(double longitude) {
		
		this.longitude = longitude;
		
	}

	public double getElevation() {
		
		return this.elevation;
		
	}

	public void setElevation(double elevation) {
		
		this.elevation = elevation;
		
	}
	
	public String toString() {
		
		String str;
		
		// TODO - start
		str = "";
		str += this.time;
		str += " (";
		str += this.latitude;
		str += ",";
		str += this.longitude;
		str += ") ";
		str += this.elevation;
		str += "\n";
		
		// TODO - slutt
		return str;
	}
}
