package edgarreyes.csumb.airportreservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmReservation extends AppCompatActivity {

    TextView flightsData;
    Button confirmReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_reservation);

        final String flights = getIntent().getStringExtra("Flights");
        flightsData = findViewById(R.id.AvailableFlightTextView);
        confirmReservation = findViewById(R.id.ConfirmReservationButton);

        flightsData.setMovementMethod(new ScrollingMovementMethod());
        flightsData.setText(flights);
    }
}
