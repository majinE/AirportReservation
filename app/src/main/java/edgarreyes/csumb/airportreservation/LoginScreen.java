package edgarreyes.csumb.airportreservation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edgarreyes.csumb.airportreservation.R;

public class LoginScreen extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    EditText username;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        username = findViewById(R.id.userNameEditText);
        password = findViewById(R.id.passwordEditText);
        login = findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameLogged = username.getText().toString();
                String passwordLogged = password.getText().toString();
                Log.i(TAG, "username: " + usernameLogged);
                Log.i(TAG, "password: " + passwordLogged);
            }
        });
    }
}
