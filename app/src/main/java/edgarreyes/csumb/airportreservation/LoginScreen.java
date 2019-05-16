package edgarreyes.csumb.airportreservation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LoginScreen extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    EditText username;
    EditText password;
    Button login;

    int attempts = 0;

    private AccountsList accountsList;
    private List<AccountsItem> accounts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = alertBuilder.create();
        final String session = getIntent().getStringExtra("SessionID");

        username = findViewById(R.id.userNameEditText);
        password = findViewById(R.id.passwordEditText);
        login = findViewById(R.id.loginButton);
        accountsList = AccountsList.get(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAccounts();
                boolean signedIn = false;
                boolean validSignIn = false;

                String usernameLogged = username.getText().toString();
                String passwordLogged = password.getText().toString();

                for(AccountsItem account : accounts) {
                    if(account.getUsername().equals(usernameLogged)) {
                        if(account.getPassword().equals(passwordLogged)) {
                            signedIn = true;
                            if (session.equals("cancel") && account.getType().equals("user")) {
                                Intent cancelReservation = new Intent(LoginScreen.this, CancelReservationScreen.class);
                                startActivity(cancelReservation);
                                validSignIn = true;
                            } else if (session.equals("system") && account.getType().equals("admin")) {
                                Intent systemManage = new Intent(LoginScreen.this, ManageSystemLogs.class);
                                startActivity(systemManage);
                                validSignIn = true;
                            }
                        } else{
                            attempts++;
                            alertBuilder.setMessage("Incorrect Username or Password");
                            alertBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(attempts == 2){
                                        Intent unsuccessfulLogin = new Intent(LoginScreen.this, MainScreen.class);
                                        startActivity(unsuccessfulLogin);
                                    }
                                    alertDialog.dismiss();
                                }
                            });
                            alertBuilder.show();
                        }
                    }
                }
                if(!signedIn || !validSignIn) {
                    attempts++;
                    alertBuilder.setMessage("Incorrect Username or Password");
                    alertBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (attempts == 2) {
                                Intent unsuccessfulLogin = new Intent(LoginScreen.this, MainScreen.class);
                                startActivity(unsuccessfulLogin);
                            }
                            alertDialog.dismiss();
                        }
                    });
                    alertBuilder.show();
                }
            }
        });
    }

    private void getAccounts() {
        accountsList.updateList();
        accounts = accountsList.getAccounts();
    }
}
