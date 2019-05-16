package edgarreyes.csumb.airportreservation.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edgarreyes.csumb.airportreservation.FlightsItem;
import edgarreyes.csumb.airportreservation.database.FlightsSchema.FlightsLogTable;
public class FlightsLogHelper extends SQLiteOpenHelper {

    public static final String TAG = "Flights_Log";
    public static final String DATABASE_NAME = "flightsLogDatabase.db";
    public static final int VERSION = 1;
    private SQLiteDatabase db;

    public FlightsLogHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + FlightsLogTable.NAME + " (" +
                    "_id integer primary key autoincrement , " +
                    FlightsLogTable.Cols.UUID + "," +
                    FlightsLogTable.Cols.FLIGHTNO + "," +
                    FlightsLogTable.Cols.DEPARTURE + "," +
                    FlightsLogTable.Cols.ARRIVAL + "," +
                    FlightsLogTable.Cols. DEPARTURETIME + "," +
                    FlightsLogTable.Cols.FLIGHTCAP + "," +
                    FlightsLogTable.Cols.PRICE + "," +
                    FlightsLogTable.Cols.DATE +
                    ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public long addLongItem(FlightsItem log) {
        ContentValues cv = getContentValues(log);
        db = this.getWritableDatabase();

        return db.insert(FlightsLogTable.NAME, null, cv);
    }

    public List<FlightsItem> getFlightsItems() {
        FlightsLogCursorWrapper cursor = new FlightsLogCursorWrapper(queryDB(FlightsLogTable.NAME, null, null));

        List<FlightsItem> logs = new ArrayList<>();

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                logs.add(cursor.getFlightsItem());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return logs;
    }

    private ContentValues getContentValues(FlightsItem log) {

        ContentValues cv = new ContentValues();

        cv.put(FlightsLogTable.Cols.UUID, log.getFlightId().toString());
        cv.put(FlightsLogTable.Cols.FLIGHTNO, log.getFlightNo());
        cv.put(FlightsLogTable.Cols.DEPARTURE, log.getDeparture());
        cv.put(FlightsLogTable.Cols.ARRIVAL, log.getArrival());
        cv.put(FlightsLogTable.Cols.DEPARTURETIME, log.getDepartureTime());
        cv.put(FlightsLogTable.Cols.FLIGHTCAP, log.getFlightCap());
        cv.put(FlightsLogTable.Cols.PRICE, log.getPrice());
        cv.put(FlightsLogTable.Cols.DATE, log.getDate().getTime());

        return cv;

    }

    private Cursor queryDB(String dbName, String whereCluse, String[] whereArgs) {
        db = this.getWritableDatabase();

        try {
            return db.query(
                    FlightsLogTable.NAME,
                    null,
                    whereCluse,
                    whereArgs,
                    null,
                    null,
                    null
            );
        } catch (Exception e) {
            Log.d(TAG, "FlightsLogHelper: QueryDB didn't find anything...");
            return null;
        }
    }
}
