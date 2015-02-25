package cs.dartmouth.edu.myrunsjf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jasonfeng on 1/27/15.
 */
public class ExerciseEntry {

	public static final String PARENT_ENTITY_NAME = "ExerciseEntryParent";
	public static final String PARENT_KEY_NAME = "ExerciseEntryParent";
	public static final String ENTITY_NAME = "ExerciseEntry";
	
    public static final String ID = "id";
	public static final String INPUT_TYPE = "input_type";
	public static final String ACTIVITY_TYPE = "activity_type";
	public static final String DATE = "date";
	public static final String DURATION = "duration";
	public static final String DISTANCE = "distance";
	public static final String AVG_PACE = "avg_pace";
	public static final String CUR_SPEED = "cur_speed";
	public static final String AVG_SPEED = "avg_speed";
	public static final String CALORIES = "calories";
	public static final String CLIMB = "climb";
	public static final String HEART_RATE = "heart_rate";
	public static final String COMMENT = "comment";
	public static final String KEY_NAME = ID;
	
    public long id;			   // DB ID
    public String mInputType;        // Manual, GPS or automatic
    public String mActivityType;     // Running, cycling etc.
    public String mDateTime;    	   // When does this entry happen
    public String mDuration;         // Exercise duration in seconds
    public String mDistance;      // Distance traveled. Either in meters or feet.
    public double mAvgPace;       // Average pace
    public double mCurSpeed;      // Current speed
    public double mAvgSpeed;      // Average speed
    public int mCalorie;          // Calories burnt
    public double mClimb;         // Climb. Either in meters or feet.
    public int mHeartRate;        // Heart rate
    public String mComment;       // Comments

    public ExerciseEntry(long id, String mInputType, String mActivityType, String mDateTime, String mDuration, 
    		String mDistance, double mAvgPace, double mCurSpeed, double mAvgSpeed, int mCalorie, double mClimb,
    		int mHeartRate, String mComment) {
    	this.id = id;
        this.mInputType = mInputType;
        this.mActivityType = mActivityType;
        this.mDateTime = mDateTime;
        this.mDuration = mDuration;
        this.mDistance = mDistance;
        this.mAvgPace = mAvgPace;
        this.mAvgSpeed = mAvgSpeed;
        this.mCalorie = mCalorie;
        this.mClimb = mClimb;
        this.mHeartRate = mHeartRate;
        this.mComment = mComment;
    }
}
