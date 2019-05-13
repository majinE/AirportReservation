package edgarreyes.csumb.airportreservation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {

    TextView mainMessage;
    Button createAccount;
    Button reserveSeat;
    Button cancelReservation;
    Button manageSystem;
    final String reserveSeatLogin = "reserve";
    final String cancelReserveLogin = "cancel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        createAccount = findViewById(R.id.CreateAccountButton);
        reserveSeat = findViewById(R.id.ReserveSeatButton);
        cancelReservation = findViewById(R.id.CancelReservationButton);
        manageSystem = findViewById(R.id.ManageSystemButton);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createAcc = new Intent(MainScreen.this, CreateAccount.class);
                startActivity(createAcc);
            }
        });

        reserveSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainScreen.this, LoginScreen.class);
                login.putExtra("SessionID", reserveSeatLogin);
                startActivity(login);
            }
        });

        cancelReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainScreen.this, LoginScreen.class);
                login.putExtra("SessionID", cancelReserveLogin);
                startActivity(login);
            }
        });


    }
}
