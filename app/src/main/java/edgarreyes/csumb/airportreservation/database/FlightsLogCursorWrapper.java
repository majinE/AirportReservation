package edgarreyes.csumb.airportreservation.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import edgarreyes.csumb.airportreservation.FlightsItem;
import edgarreyes.csumb.airportreservation.database.FlightsSchema.FlightsLogTable;

public class FlightsLogCursorWrapper extends CursorWrapper {
    public FlightsLogCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public FlightsItem getFlightsItem() {
        String uuidString = (getString(getColumnIndex(FlightsLogTable.Cols.UUID)));
        String flightno = (getString(getColumnIndex(FlightsLogTable.Cols.FLIGHTNO)));
        String departure = (getString(getColumnIndex(FlightsLogTable.Cols.DEPARTURE)));
        String arrival = (getString(getColumnIndex(FlightsLogTable.Cols.ARRIVAL)));
        String departureTime = (getString(getColumnIndex(FlightsLogTable.Cols.DEPARTURETIME)));
        int flightcap = (getInt(getColumnIndex(FlightsLogTable.Cols.FLIGHTCAP)));
        double price = (getDouble(getColumnIndex(FlightsLogTable.Cols.PRICE)));
        Date date = new Date(getInt(getColumnIndex(FlightsLogTable.Cols.DATE)));

        FlightsItem log = new FlightsItem(UUID.fromString(uuidString));
        log.setFlightNo(flightno);
        log.setDeparture(departure);
        log.setArrival(arrival);
        log.setDepartureTime(departureTime);
        log.setFlightCap(flightcap);
        log.setPrice(price);
        log.setDate(date);

        return log;
    }
}
