package edgarreyes.csumb.airportreservation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import edgarreyes.csumb.airportreservation.database.AccountsLogHelper;

public class AccountsList {
    private static AccountsList sList;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private AccountsLogHelper mAccountsHelper;
    private List<AccountsItem> mAccounts;

    public static AccountsList get(Context context) {
        if(sList == null){
            sList = new AccountsList(context);
        }
        return sList;
    }

    private AccountsList(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new AccountsLogHelper(mContext).getWritableDatabase();
        mAccountsHelper = new AccountsLogHelper(mContext);
        mAccounts = getAccounts();
    }

    public List<AccountsItem> getAccounts() {
        return mAccountsHelper.getAccountsItems();
    }

    public void updateList() {
        mAccounts = getAccounts();
    }
}
