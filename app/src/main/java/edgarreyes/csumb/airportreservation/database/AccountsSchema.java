package edgarreyes.csumb.airportreservation.database;

public class AccountsSchema {

    public static final class AccountsLogTable {
        public static final String NAME = "ACCLOG_Table";
        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TRANSACTIONTYPE = "transactionType";
            public static final String TYPE = "type";
            public static final String USERNAME = "username";
            public static final String PASSWORD = "password";
            public static final String DATE = "date";
        }
    }
}
