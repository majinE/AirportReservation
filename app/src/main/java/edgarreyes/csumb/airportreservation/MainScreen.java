package edgarreyes.csumb.airportreservation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends AppCompatActivity {

    Button createAccount;
    Button reserveSeat;
    Button cancelReservation;
    Button manageSystem;
    String cancelReserveLogin = "cancel";
    String  manageSystemLogin = "system";

    AccountsLog accountsLog;
    private AccountsList accountsList;
    private List<AccountsItem> accounts;
    private List<String> userNames = new ArrayList<>();
    private List<String> userPasswords = new ArrayList<>();

    FlightsLog flightsLog;
    private FlightsList flightsList;
    private List<FlightsItem> flights;
    private List<String> flightNo = new ArrayList<>();
    private List<String> departure = new ArrayList<>();
    private List<String> arrival = new ArrayList<>();
    private List<String> departureTime = new ArrayList<>();
    private List<Integer> flightCap = new ArrayList<>();
    private List<Double> price = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        createAccount = findViewById(R.id.CreateAccountButton);
        reserveSeat = findViewById(R.id.ReserveSeatButton);
        cancelReservation = findViewById(R.id.CancelReservationButton);
        manageSystem = findViewById(R.id.ManageSystemButton);

        accountsLog = AccountsLog.get(this.getApplicationContext());
        accountsList = AccountsList.get(this);
        getAccounts();

        flightsLog = FlightsLog.get(this.getApplicationContext());
        flightsList = FlightsList.get(this);
        getFlights();

        if(accounts.size() == 0) {
            createStarterAccounts();
            createStarterFlights();
        }

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
                Intent reservationScreen = new Intent(MainScreen.this, ReservationScreen.class);
                startActivity(reservationScreen);
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

        manageSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainScreen.this,LoginScreen.class);
                login.putExtra("SessionID", manageSystemLogin);
                startActivity(login);
            }
        });


    }

    private void getAccounts() {
        accountsList.updateList();
        accounts = accountsList.getAccounts();
    }

    private void updateAccountLists(){
        userNames.add("alice5");
        userPasswords.add("csumb100");
        userNames.add("brian77");
        userPasswords.add("123ABC");
        userNames.add("chris21");
        userPasswords.add("CHRIS21");
    }

    private void updateFlightLists() {
        flightNo.add("Otter101");
        departure.add("Monterey");
        arrival.add("Los Angeles");
        departureTime.add("10:00(AM)");
        flightCap.add(10);
        price.add(150.00);
        flightNo.add("Otter102");
        departure.add("Los Angeles");
        arrival.add("Monterey");
        departureTime.add("1:00(PM)");
        flightCap.add(10);
        price.add(150.00);
        flightNo.add("Otter201");
        departure.add("Monterey");
        arrival.add("Seattle");
        departureTime.add("11:00(AM)");
        flightCap.add(5);
        price.add(200.50);
        flightNo.add("Otter205");
        departure.add("Monterey");
        arrival.add("Seattle");
        departureTime.add("3:00(PM)");
        flightCap.add(15);
        price.add(150.00);
        flightNo.add("Otter202");
        departure.add("Seattle");
        arrival.add("Monterey");
        departureTime.add("2:00(PM)");
        flightCap.add(5);
        price.add(200.50);
    }

    private void createStarterAccounts(){
        updateAccountLists();
        AccountsItem admin = new AccountsItem();
        admin.setTransactionType("new admin");
        admin.setType("admin");
        admin.setUsername("admin2");
        admin.setPassword("admin2");
        accountsLog.addLongItem(admin);
        for(int i = 0; i < 3; i++) {
            AccountsItem account = new AccountsItem();
            account.setTransactionType("new account");
            account.setType("user");
            account.setUsername(userNames.get(i));
            account.setPassword(userPasswords.get(i));
            accountsLog.addLongItem(account);
        }
    }

    private void getFlights() {
        flightsList.updateList();
        flights = flightsList.getFlights();
    }

    private void createStarterFlights() {
        updateFlightLists();
        for(int i = 0; i < 5; i++) {
            FlightsItem flight = new FlightsItem();
            flight.setFlightNo(flightNo.get(i));
            flight.setDeparture(departure.get(i));
            flight.setArrival(arrival.get(i));
            flight.setDepartureTime(departureTime.get(i));
            flight.setFlightCap(flightCap.get(i));
            flight.setPrice(price.get(i));

            flightsLog.addLongItem(flight);
        }
    }
}
