package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import java.util.Locale;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		// TODO - START

		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}

		// TODO - SLUT
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		
		double latitudes[] = new double[gpspoints.length];
		
		for(int i = 0; i < gpspoints.length;i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		
		// TODO - SLUTT
		return latitudes;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START
		
		double longitudes[] = new double[gpspoints.length];
		
		for(int i = 0; i < gpspoints.length;i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		
		// TODO - SLUTT
		return longitudes;

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		// TODO - START

		double lat1, lat2, dlat, dlon, a, c;
		
		latitude1 = gpspoint1.getLatitude();
		latitude2 = gpspoint2.getLatitude();
		longitude1 = gpspoint1.getLongitude();
		longitude2 = gpspoint2.getLongitude();
		
		dlon = toRadians(longitude2 - longitude1);
		dlat = toRadians(latitude2 - latitude1);
		
		lat1 = toRadians(latitude1);
		lat2 = toRadians(latitude2);
		
		a = pow((sin(dlat/2)),2)+cos(lat1)*cos(lat2)*pow((sin(dlon/2)),2);
		c = 2*atan2(sqrt(a),sqrt(1-a));
		d = R*c;
		
		// TODO - SLUTT
		return d;
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START
		secs = gpspoint2.getTime() - gpspoint1.getTime();
		
		speed = distance(gpspoint1,gpspoint2) / secs * 3.6;

		// TODO - SLUTT
		return speed;
	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START
		int hr, min, sec;
		
		hr = secs/3600;
		min = (secs % 3600) / 60;
		sec = secs % 60;
		
		timestr = String.format("  %02d:%02d:%02d", hr,min,sec);
		
		// TODO - SLUTT
		
		return timestr;

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START

		str = String.format(Locale.ROOT,"%10.2f", d);

		// TODO - SLUTT
		return str;
	}
}
