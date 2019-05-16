package edgarreyes.csumb.airportreservation.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import edgarreyes.csumb.airportreservation.AccountsLog;
import edgarreyes.csumb.airportreservation.FlightsItem;
import edgarreyes.csumb.airportreservation.database.AccountsSchema.AccountsLogTable;
import edgarreyes.csumb.airportreservation.AccountsItem;

public class   AccountsLogCursorWrapper extends CursorWrapper {
    public AccountsLogCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public AccountsItem getAccountsItem(){
        String uuidString = (getString(getColumnIndex(AccountsLogTable.Cols.UUID)));
        String transactionTypeString = (getString(getColumnIndex(AccountsLogTable.Cols.TRANSACTIONTYPE)));
        String typeString = (getString(getColumnIndex(AccountsLogTable.Cols.TYPE)));
        String usernameString = (getString(getColumnIndex(AccountsLogTable.Cols.USERNAME)));
        String passwordString = (getString(getColumnIndex(AccountsLogTable.Cols.PASSWORD)));
        Date date = new Date(getInt(getColumnIndex(AccountsLogTable.Cols.DATE)));

        AccountsItem log = new AccountsItem(UUID.fromString(uuidString));

        log.setType(typeString);
        log.setTransactionType(transactionTypeString);
        log.setUsername(usernameString);
        log.setPassword(passwordString);
        log.setDate(date);

        return log;
    }
}
