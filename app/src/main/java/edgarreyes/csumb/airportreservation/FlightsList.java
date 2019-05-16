package edgarreyes.csumb.airportreservation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import edgarreyes.csumb.airportreservation.database.FlightsLogHelper;

public class FlightsList {
    private static FlightsList sList;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private FlightsLogHelper mFlightsHelper;
    private List<FlightsItem> mFlights;

    public static FlightsList get(Context context) {
        if(sList == null) {
            sList = new FlightsList(context);
        }
        return sList;
    }
    private FlightsList(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new FlightsLogHelper(mContext).getWritableDatabase();
        mFlightsHelper = new FlightsLogHelper(mContext);
        mFlights = getFlights();
    }

    public List<FlightsItem> getFlights() {
        return mFlightsHelper.getFlightsItems();
    }

    public void updateList() {
        mFlights = getFlights();
    }
}
