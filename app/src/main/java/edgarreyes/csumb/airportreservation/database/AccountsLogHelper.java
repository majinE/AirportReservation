package edgarreyes.csumb.airportreservation.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edgarreyes.csumb.airportreservation.AccountsItem;
import edgarreyes.csumb.airportreservation.AccountsLog;
import edgarreyes.csumb.airportreservation.database.AccountsSchema.AccountsLogTable;

public class AccountsLogHelper extends SQLiteOpenHelper {
    public static final String TAG = "Account_Log";
    public static final String DATABASE_NAME = "accountLogDatabase.db";
    public static final int VERSION = 1;
    private SQLiteDatabase db;

    public AccountsLogHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + AccountsLogTable.NAME + " (" +
                   "_id integer primary key autoincrement , " +
                   AccountsLogTable.Cols.UUID + "," +
                   AccountsLogTable.Cols.TYPE + "," +
                   AccountsLogTable.Cols.USERNAME + "," +
                   AccountsLogTable.Cols.PASSWORD +
                   ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public long addLongItem(AccountsItem log) {
        ContentValues cv = getContentValues(log);
        db = this.getWritableDatabase();

        return db.insert(AccountsLogTable.NAME, null, cv);
    }

    public List<AccountsItem> getAccountsItems() {
        AccountsLogCursorWrapper cursor = new AccountsLogCursorWrapper(queryDB(AccountsLogTable.NAME, null, null));

        List<AccountsItem> logs = new ArrayList<>();

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                logs.add(cursor.getAccountsItem());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return logs;
    }

    private ContentValues getContentValues(AccountsItem log) {

        ContentValues cv = new ContentValues();

        cv.put(AccountsLogTable.Cols.UUID, log.getAccountsId().toString());
        cv.put(AccountsLogTable.Cols.TYPE, log.getType());
        cv.put(AccountsLogTable.Cols.USERNAME, log.getUsername());
        cv.put(AccountsLogTable.Cols.PASSWORD, log.getPassword());

        return cv;
    }

    private Cursor queryDB(String dbName, String whereClause, String[] whereArgs) {
        db = this.getWritableDatabase();

        try {
            return db.query(
                    AccountsLogTable.NAME,
                    null,
                    whereClause,
                    whereArgs,
                    null,
                    null,
                    null
            );
        } catch(Exception e) {
            Log.d(TAG, "AccountsLogHelper: QueryDB didn't find anything...");
            return null;
        }
    }
}
