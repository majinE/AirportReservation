package edgarreyes.csumb.airportreservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.concurrent.TimeoutException;

public class CreateAccount extends AppCompatActivity {

    public static final String TAG = "Account_Log";

    TextView newUsernameTextView;
    TextView newPasswordTextView;
    TextView confirmPasswordTextView;
    Switch typeSwitch;
    Button createAccountButton;

    TextView AccountLogTextView;

    AccountsLog accountsLog;
    AccountsItem accountsItem;
    Long rowId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        newUsernameTextView = findViewById(R.id.NewUsernameEditText);
        newPasswordTextView = findViewById(R.id.NewPasswordEditText);
        confirmPasswordTextView = findViewById(R.id.ConfirmPasswordEditText);
        createAccountButton = findViewById(R.id.createAccountButton);
        typeSwitch = findViewById(R.id.adminOrUserSwitch);
//
        AccountLogTextView = findViewById(R.id.AccountLogTextView);
        AccountLogTextView.setMovementMethod(new ScrollingMovementMethod());
//
        accountsLog = AccountsLog.get(this.getApplicationContext());
        accountsItem = new AccountsItem();

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = null;
                if(typeSwitch.isChecked()){
                    type = "Administrator";
                } else if (!typeSwitch.isChecked()) {
                    type = "User";
                }
                accountsItem = getAccountsItemFromDisplay(type);
                rowId = accountsLog.addLongItem(accountsItem);

                AccountLogTextView.setText(accountsLog.getLogString());
            }
        });

    }

    private AccountsItem getAccountsItemFromDisplay(String switchOnOff) {
        String username = newUsernameTextView.getText().toString();
        String password;
        String confirmPassword;
        //TODO: Make sure fields have valid input. Think of using self-created Exception handling java class.
        while (true) {
            try{
                password = newPasswordTextView.getText().toString();
                confirmPassword = confirmPasswordTextView.getText().toString();
                if(password.equals(confirmPassword))
                {
                    break;
                }
            } catch (Exception e){
                toaster("Passwords don't match");
            }
        }
        AccountsItem account = new AccountsItem();

        account.setUsername(username);
        account.setPassword(password);
        account.setType(switchOnOff);

        toaster("Account Created");

        return account;

    }

    private void toaster(String message) {
        Toast t = Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        t.show();
    }
}
