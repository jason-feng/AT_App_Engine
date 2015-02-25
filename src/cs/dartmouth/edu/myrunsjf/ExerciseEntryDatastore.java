package cs.dartmouth.edu.myrunsjf;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Transaction;

import java.util.Date;

public class ExerciseEntryDatastore {

	private static final Logger mLogger = Logger
			.getLogger(ExerciseEntryDatastore.class.getName());
	private static final DatastoreService mDatastore = DatastoreServiceFactory
			.getDatastoreService();

	private static Key getParentKey() {
		return KeyFactory.createKey(ExerciseEntry.PARENT_ENTITY_NAME,
				ExerciseEntry.PARENT_KEY_NAME);
	}

	private static void createParentEntity() {
		Entity entity = new Entity(getParentKey());
		mDatastore.put(entity);
	}

	public static boolean add(ExerciseEntry entry) {
		if (getExerciseEntryById(entry.id, null) != null) {
			mLogger.log(Level.INFO, "entry exists");
			return false;
		}
		mLogger.log(Level.INFO, "Adding Entry: " + entry.id);
		Key parentKey = getParentKey();
		try {
			mDatastore.get(parentKey);
		} catch (Exception ex) {
			createParentEntity();
		}

		Entity entity = new Entity(ExerciseEntry.ENTITY_NAME, entry.id,
				parentKey);
		entity.setProperty(ExerciseEntry.ID, entry.id);
		entity.setProperty(ExerciseEntry.INPUT_TYPE, entry.mInputType);
		entity.setProperty(ExerciseEntry.ACTIVITY_TYPE, entry.mActivityType);
		entity.setProperty(ExerciseEntry.DATE, entry.mDateTime);
		entity.setProperty(ExerciseEntry.DURATION, entry.mDuration);
		entity.setProperty(ExerciseEntry.DISTANCE, entry.mDistance);
		entity.setProperty(ExerciseEntry.AVG_PACE, entry.mAvgPace);
		entity.setProperty(ExerciseEntry.CUR_SPEED, entry.mCurSpeed);
		entity.setProperty(ExerciseEntry.AVG_SPEED, entry.mAvgSpeed);
		entity.setProperty(ExerciseEntry.CALORIES, entry.mCalorie);
		entity.setProperty(ExerciseEntry.CLIMB, entry.mClimb);
		entity.setProperty(ExerciseEntry.HEART_RATE, entry.mHeartRate);
		entity.setProperty(ExerciseEntry.COMMENT, entry.mComment);
		
		mDatastore.put(entity);

		return true;
	}

	public static boolean delete(long id) {
		// you can also use name to get key, then use the key to delete the
		// entity from datastore directly
		// because name is also the entity's key

		// query
		mLogger.log(Level.INFO, "Deleting Entry: " + Long.toString(id));
		Filter filter = new FilterPredicate(ExerciseEntry.ID,
				FilterOperator.EQUAL, id);

		Query query = new Query(ExerciseEntry.ENTITY_NAME);
		query.setFilter(filter);

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = mDatastore.prepare(query);

		Entity result = pq.asSingleEntity();
		boolean ret = false;
		if (result != null) {
			// delete
			mDatastore.delete(result.getKey());
			ret = true;
		}

		return ret;
	}
	public static ArrayList<ExerciseEntry> query() {
		ArrayList<ExerciseEntry> resultList = new ArrayList<ExerciseEntry>();
		Query query = new Query(ExerciseEntry.ENTITY_NAME);
		query.setFilter(null);
		query.setAncestor(getParentKey());
		query.addSort(ExerciseEntry.ID, SortDirection.ASCENDING);
		PreparedQuery pq = mDatastore.prepare(query);

		for (Entity entity : pq.asIterable()) {
			ExerciseEntry entry = new ExerciseEntry(
					(long) entity.getProperty(ExerciseEntry.ID),
					(String) entity.getProperty(ExerciseEntry.INPUT_TYPE),
					(String) entity.getProperty(ExerciseEntry.ACTIVITY_TYPE),
					(String) entity.getProperty(ExerciseEntry.DATE),
					(String) entity.getProperty(ExerciseEntry.DURATION),
					(String) entity.getProperty(ExerciseEntry.DISTANCE),
					(double) entity.getProperty(ExerciseEntry.AVG_PACE),
					(double) entity.getProperty(ExerciseEntry.CUR_SPEED),
					(double) entity.getProperty(ExerciseEntry.AVG_SPEED),
					(int) (long) entity.getProperty(ExerciseEntry.CALORIES),
					(double) entity.getProperty(ExerciseEntry.CLIMB),
					(int) (long) entity.getProperty(ExerciseEntry.HEART_RATE),
					(String) entity.getProperty(ExerciseEntry.COMMENT));
			resultList.add(entry);
		}
		return resultList;
	}
	
	public static ArrayList<ExerciseEntry> singleQuery(long id) {
		ArrayList<ExerciseEntry> resultList = new ArrayList<ExerciseEntry>();
		if (id != -1) {
			mLogger.log(Level.INFO, "Query Entry: " + Long.toString(id));
			ExerciseEntry entry = getExerciseEntryById(id, null);
			if (entry != null) {
				resultList.add(entry);
			}
		} else {
			mLogger.log(Level.INFO, "Query Entry Not Successful: " + Long.toString(id));
			Query query = new Query(ExerciseEntry.ENTITY_NAME);
			// get every record from datastore, no filter
			query.setFilter(null);
			// set query's ancestor to get strong consistency
			query.setAncestor(getParentKey());

			PreparedQuery pq = mDatastore.prepare(query);

			for (Entity entity : pq.asIterable()) {
				ExerciseEntry entry = getExerciseEntryFromEntity(entity);
				if (entry != null) {
					resultList.add(entry);
				}
			}
		}
		return resultList;
	}

	public static ExerciseEntry getExerciseEntryById(long id, Transaction txn) {
		mLogger.log(Level.INFO, "Get Exercsise Entry By Id: " + Long.toString(id));
		Entity result = null;
		try {
			result = mDatastore.get(KeyFactory.createKey(getParentKey(),
					ExerciseEntry.ENTITY_NAME, id));
		} catch (Exception ex) {

		}
		return getExerciseEntryFromEntity(result);
	}

	private static ExerciseEntry getExerciseEntryFromEntity(Entity entity) {
		if (entity == null) {
			return null;
		}
		mLogger.log(Level.INFO, "getExerciseEntryFromEntity: " + entity.getProperty(ExerciseEntry.ID));
		return new ExerciseEntry(
				(long) entity.getProperty(ExerciseEntry.ID),
				(String) entity.getProperty(ExerciseEntry.INPUT_TYPE),
				(String) entity.getProperty(ExerciseEntry.ACTIVITY_TYPE),
				(String) entity.getProperty(ExerciseEntry.DATE),
				(String) entity.getProperty(ExerciseEntry.DURATION),
				(String) entity.getProperty(ExerciseEntry.DISTANCE),
				(double) entity.getProperty(ExerciseEntry.AVG_PACE),
				(double) entity.getProperty(ExerciseEntry.CUR_SPEED),
				(double) entity.getProperty(ExerciseEntry.AVG_SPEED),
				(int) (long) entity.getProperty(ExerciseEntry.CALORIES),
				(double) entity.getProperty(ExerciseEntry.CLIMB),
				(int) (long) entity.getProperty(ExerciseEntry.HEART_RATE),
				(String) entity.getProperty(ExerciseEntry.COMMENT));
	}
}
