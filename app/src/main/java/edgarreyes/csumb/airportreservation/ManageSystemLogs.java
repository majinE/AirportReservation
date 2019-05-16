package edgarreyes.csumb.airportreservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ManageSystemLogs extends AppCompatActivity {

    TextView accountsData;
    TextView flightsData;
    AccountsLog accountsLog;
    FlightsLog flightsLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system_logs);
        accountsData = findViewById(R.id.AccountsTextView);
        flightsData = findViewById(R.id.FlightsTextView);

        accountsLog = AccountsLog.get(this.getApplicationContext());
        accountsData.setMovementMethod(new ScrollingMovementMethod());
        accountsData.setText(accountsLog.getLogString());

        flightsLog = FlightsLog.get(this.getApplicationContext());
        flightsData.setMovementMethod(new ScrollingMovementMethod());
        flightsData.setText(flightsLog.getLogString());
    }
}
