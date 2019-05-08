package edgarreyes.csumb.airportreservation.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import edgarreyes.csumb.airportreservation.database.AccountsSchema.AccountsLogTable;
import edgarreyes.csumb.airportreservation.AccountsItem;

public class AccountsLogCursorWrapper extends CursorWrapper {
    public AccountsLogCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public AccountsItem getAccountsItem(){
        String uuidString = (getString(getColumnIndex(AccountsLogTable.Cols.UUID)));
        String typeString = (getString(getColumnIndex(AccountsLogTable.Cols.TYPE)));
        String usernameString = (getString(getColumnIndex(AccountsLogTable.Cols.USERNAME)));
        String passwordString = (getString(getColumnIndex(AccountsLogTable.Cols.PASSWORD)));

        AccountsItem log = new AccountsItem(UUID.fromString(uuidString));

        log.setType(typeString);
        log.setUsername(usernameString);
        log.setPassword(passwordString);

        return log;
    }
}
