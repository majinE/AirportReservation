package edgarreyes.csumb.airportreservation;

import android.content.Context;

import java.util.List;

import edgarreyes.csumb.airportreservation.database.FlightsLogHelper;

public class FlightsLog {
    private static FlightsLog mFlightsLog;
    private Context mContext;
    private FlightsLogHelper mFlightsLogHelper;

    public static FlightsLog get(Context context) {
        if(mFlightsLog == null){
            mFlightsLog = new FlightsLog(context);
        }
        return mFlightsLog;
    }

    private FlightsLog(Context context) {
        mContext = context.getApplicationContext();
        mFlightsLogHelper = new FlightsLogHelper(mContext);
    }

    public long addLongItem(FlightsItem log) {
        return mFlightsLogHelper.addLongItem(log);
    }

    public String getLogString() {
        List<FlightsItem> logs = mFlightsLogHelper.getFlightsItems();
        StringBuilder sb = new StringBuilder();
        sb.append("Flight Log\n");
        for(FlightsItem log: logs) {
            sb.append(log.toString());
        }

        return sb.toString();
    }
}
