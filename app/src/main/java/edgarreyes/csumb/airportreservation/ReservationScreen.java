package edgarreyes.csumb.airportreservation;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class ReservationScreen extends AppCompatActivity {
    EditText departure;
    EditText arrival;
    EditText ticketsAmount;
    Button makeReservation;

    private FlightsList flightsList;
    private List<FlightsItem> flights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_screen);

        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = alertBuilder.create();

        departure = findViewById(R.id.DepartureEditText);
        arrival = findViewById(R.id.ArrivalEditText);
        ticketsAmount = findViewById(R.id.AmountEditText);
        makeReservation = findViewById(R.id.makeReservationButton);

        flightsList = FlightsList.get(this);

        makeReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFlights();
                StringBuilder sb = new StringBuilder();

                String getDeparture = departure.getText().toString();
                String getArrival = arrival.getText().toString();
                Integer getTicketAmount = Integer.parseInt(ticketsAmount.getText().toString());
                boolean foundFlights = false;

                for(FlightsItem i : flights) {
                    if(i.getDeparture().equals(getDeparture) && i.getArrival().equals(getArrival)){
                        if(i.getFlightCap() >= getTicketAmount) {
                            foundFlights = true;
                            sb.append("FlightNo: ").append(i.getFlightNo()).append("\n");
                            sb.append("Flight Departure: ").append(i.getDeparture()).append("\n");
                            sb.append("Flight Arrival: ").append(i.getArrival()).append("\n");
                            sb.append("Available Amount of Tickets: ").append(i.getFlightCap()).append("\n");
                            sb.append("Price: $").append(i.getPrice()).append("\n");
                        }
                        sb.append("\n");
                    }
                }
                if(foundFlights){
                    Intent confirmFlights = new Intent(ReservationScreen.this, ConfirmReservation.class);
                    confirmFlights.putExtra("Flights", sb.toString());
                    startActivity(confirmFlights);
                } else if(!foundFlights) {
                    alertBuilder.setMessage("No Flights were found.. Going back to selection menu.");
                    alertBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent unavailableFlights = new Intent(ReservationScreen.this, MainScreen.class);
                            startActivity(unavailableFlights);
                        }
                    });
                    alertBuilder.show();
                }
            }
        });
    }

    private void getFlights() {
        flightsList.updateList();
        flights = flightsList.getFlights();
    }
}
