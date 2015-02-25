package cs.dartmouth.edu.myrunsjf;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Global {
    public static final int MANUAL_ENTRY = 4;
    public static final int GPS = 5;
    public static final int AUTOMATIC = 6;
    
    public static final int ERROR = -1;
    public static final int STANDING = 0;
    public static final int WALKING = 1;
    public static final int RUNNING = 2;
    public static final int CYCLING = 3;
    public static final int HIKING = 4;
    public static final int DOWNHILL_SKIING = 5;
    public static final int CROSS_COUNTRY_SKIING = 6;
    public static final int SNOWBOARDING = 7;
    public static final int SKATING = 8;
    public static final int SWIMMING = 9;
    public static final int MOUNTAIN_BIKING = 10;
    public static final int WHEELCHAIR = 11;
    public static final int ELLPITICAL = 12;
    public static final int OTHER = 13;
    // Constants for distance/time conversions
    public static final double MILE_TO_KM = 1.609344;
    // Different format to display the information
    public static final String DATE_FORMAT = "HH:mm:ss MMM dd yyyy";
    public static final String DISTANCE_FORMAT = "#.##";
    public static final String MINUTES_FORMAT = "%d mins";
    public static final String SECONDS_FORMAT = "%d secs";
    
	public static final String GCMAPIKEY = "AIzaSyBvI1snAw_dlYB_AS2AZXuRFh0sHXyLyiE";
	
	public static long deleteID = -1;
	
	public static String parseInput(int input_type) {
		switch (input_type) {
		case MANUAL_ENTRY:
			return "Manual Entry";
		case GPS:
			return "GPS";
		case AUTOMATIC:
			return "Automatic";
			default:
				return null;
		}
	}
	
	public static String parseActivity(int activity_type) {
		 	switch(activity_type) {
		 		case STANDING:
		 			return "Standing";
		 		case WALKING:
		 			return "Walking";
		 		case RUNNING:
		 			return "Running";
		 		case CYCLING:
		 			return "Cycling";
		 		case HIKING:
		 			return "Hiking";
		 		case DOWNHILL_SKIING:
		 			return "Downhill Skiing";
		 		case CROSS_COUNTRY_SKIING:
		 			return "Cross Country Skiing";
		 		case SNOWBOARDING:
		 			return "Snowboarding";
		 		case SKATING:
		 			return "Skating";
		 		case SWIMMING:
		 			return "Swimming";
		 		case MOUNTAIN_BIKING:
		 			return "Mountain Biking";
		 		case WHEELCHAIR:
		 			return "Wheelchair";
		 		case ELLPITICAL:
		 			return "Ellpitical";
		 		case OTHER:
		 			return "Other";
		 		default:
		 			return null;
		 	}
		 		
	    }

	    public static String parseTime(Date date) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	        return dateFormat.format(date);
	    }

	    public static String parseDistance(double distanceInMeters) {
            String unit = "Miles";
            double distInMiles = distanceInMeters / 1000 * MILE_TO_KM;
            DecimalFormat decimalFormat = new DecimalFormat(DISTANCE_FORMAT);
            return decimalFormat.format(distInMiles) + " " + unit;
	        
	    }

	    // Convert duration in seconds to minutes.
	    public static String parseDuration(int durationInSeconds) {
	        StringBuilder duration = new StringBuilder("");
	        int min = durationInSeconds / 60;
	        int sec = durationInSeconds % 60;

	        if (min > 0) {
	            duration.append(min + "mins ");
	        }
	        duration.append(sec + "secs");

	        return duration.toString();
	    }

	    public static String parseSpeed(double speed) {
	        DecimalFormat decimalFormat = new DecimalFormat(DISTANCE_FORMAT);
	        double speedInMPH = speed;
	        String unit = "Miles ";
	        return decimalFormat.format(speedInMPH) + " " + unit;
	    }
}
