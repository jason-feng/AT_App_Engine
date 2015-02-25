package cs.dartmouth.edu.myrunsjf;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String JSONString = req.getParameter("data");
		ArrayList<ExerciseEntry> currentData = ExerciseEntryDatastore.query();
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(JSONString);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		for (int j = 0; j < currentData.size(); j++) {
			ExerciseEntry current = currentData.get(j);
			long current_id = current.id;
			boolean contains = false;
			for (int k = 0; k < jsonArray.length(); k++) {
	            try {
					JSONObject jsonObject = (JSONObject) jsonArray.get(k);
					long id = jsonObject.getLong("id");
					if (id == current_id) {
						contains = true;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			if (contains == false) {
				ExerciseEntryDatastore.delete(current_id);
			}
		}
		
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
	            long id = jsonObject.getLong("id");
	            int input_type = jsonObject.getInt("input_type");
	            int activity_type = jsonObject.getInt("activity_type");
	            String dateString = (String)jsonObject.get("date");
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MMM dd yyyy");
	            Date date = dateFormat.parse(dateString);
	            int duration = jsonObject.getInt("duration");
	            double distance = jsonObject.getDouble("distance");
	            double avg_pace = jsonObject.getDouble("avg_pace");
	            double avg_speed = jsonObject.getDouble("avg_speed");
	            double cur_speed = jsonObject.getDouble("cur_speed");
	            int calories = jsonObject.getInt("calories");
	            double climb = jsonObject.getDouble("climb");
	            int heart_rate = jsonObject.getInt("heart_rate");
	            String comment = (String) jsonObject.get("comment");
	            
	            String input = Global.parseInput(input_type);
	            String activity = Global.parseActivity(activity_type);
	            String date_string = Global.parseTime(date);
	            String duration_string = Global.parseDuration(duration);
	            String distance_string = Global.parseDistance(distance);
	            
				ExerciseEntryDatastore.add(new ExerciseEntry(id, input, activity, 
						date_string, duration_string, distance_string, avg_pace, 
						avg_speed, cur_speed, calories, climb, heart_rate, comment));				
			}
			catch (JSONException | ParseException e2) {
				e2.printStackTrace();
			}
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doPost(req, resp);
	}
}
