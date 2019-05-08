package edgarreyes.csumb.airportreservation;

import android.content.Context;

import java.util.List;

import edgarreyes.csumb.airportreservation.database.AccountsLogHelper;


public class AccountsLog {
    private static AccountsLog mAccountsLog;
    private Context mContext;
    private AccountsLogHelper mAccountsLogHelper;

    public static AccountsLog get(Context context) {
        if(mAccountsLog == null){
            mAccountsLog = new AccountsLog(context);
        }
        return mAccountsLog;
    }

    private AccountsLog(Context context){
        mContext = context.getApplicationContext();
        mAccountsLogHelper = new AccountsLogHelper(mContext);
    }

    public long addLongItem(AccountsItem log) {
        return mAccountsLogHelper.addLongItem(log);
    }

    public String getLogString(){
        List<AccountsItem> logs = mAccountsLogHelper.getAccountsItems();

        StringBuilder sb = new StringBuilder();
        sb.append("Account Log\n");
        for(AccountsItem log : logs) {
            sb.append(log.toString());
        }

        return sb.toString();
    }
}
