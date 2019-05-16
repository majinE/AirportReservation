package edgarreyes.csumb.airportreservation.database;

public class FlightsSchema {

    public static final class FlightsLogTable {
        public static final String NAME = "FLIGHTSLOG_Table";
        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String FLIGHTNO = "flightsNo";
            public static final String DEPARTURE = "departure";
            public static final String ARRIVAL = "arrival";
            public static final String DEPARTURETIME = "departureTime";
            public static final String FLIGHTCAP = "flightCap";
            public static final String PRICE = "price";
            public static final String DATE = "date";
        }
    }
}
