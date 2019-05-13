package edgarreyes.csumb.airportreservation;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CreateAccount extends AppCompatActivity {

    public static final String TAG = "Account_Log";

    TextView newUsernameTextView;
    TextView newPasswordTextView;
    Button createAccountButton;

    TextView AccountLogTextView;

    AccountsLog accountsLog;
    AccountsItem accountsItem;

    private AccountsList accountsList;
    private List<AccountsItem> accounts;

    int createAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = alertBuilder.create();

        setContentView(R.layout.activity_create_account);

        newUsernameTextView = findViewById(R.id.NewUsernameEditText);
        newPasswordTextView = findViewById(R.id.NewPasswordEditText);
        createAccountButton = findViewById(R.id.createAccountButton);
//
        AccountLogTextView = findViewById(R.id.AccountLogTextView);
        AccountLogTextView.setMovementMethod(new ScrollingMovementMethod());
//
        accountsLog = AccountsLog.get(this.getApplicationContext());
        accountsItem = new AccountsItem();
        accountsList = AccountsList.get(this);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAccounts();

                String username = newUsernameTextView.getText().toString();
                String password = newPasswordTextView.getText().toString();
                boolean accountsExists = false;
                for(AccountsItem account : accounts) {
                    if(account.getUsername().equals(username)) {
                        if(createAttempts < 1) {
                            alertBuilder.setMessage("Username Already Exists");
                            alertBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    alertDialog.dismiss();
                                }
                            });
                            alertBuilder.show();
                        }
                        accountsExists = true;
                        createAttempts++;
                        break;
                    }
                }
                if(!username.isEmpty() && !password.isEmpty() && !accountsExists){
                    if(validation(username) && validation(password)) {
                        accountsItem = getAccountsItemFromDisplay(username, password);
                        accountsLog.addLongItem(accountsItem);
                        AccountLogTextView.setText(accountsLog.getLogString());
                        toaster("Account Successfully Created");
                        Intent accountSuccessful = new Intent(CreateAccount.this, MainScreen.class );
                        startActivity(accountSuccessful);
                    } else if(!validation(username) || !validation(password)) {
                        if(createAttempts < 1) {
                            alertBuilder.setMessage("Invalid Username or Password");
                            alertBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    alertDialog.dismiss();
                                }
                            });
                            alertBuilder.show();
                        }
                        createAttempts++;
                    }
                }
                if(username.isEmpty() || password.isEmpty()) {
                    if(createAttempts < 1) {
                        alertBuilder.setMessage("Invalid Username or Password");
                        alertBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                            }
                        });
                        alertBuilder.show();
                    }
                    createAttempts++;
                }
                if(createAttempts == 2){
                    toaster("Account Creation Unsuccessful");
                    Intent intent = new Intent(CreateAccount.this, MainScreen.class );
                    startActivity(intent);
                }
            }
        });
    }

    private AccountsItem getAccountsItemFromDisplay(String username, String password) {
        AccountsItem account = new AccountsItem();

        account.setType("USER");
        account.setUsername(username);
        account.setPassword(password);

        toaster("Account Created");

        return account;

    }

    private boolean validation(String checkUserOrPass) {
        if(checkUserOrPass.length() < 4) {
            return false;
        }
        int letters = 0;
        int numbers = 0;
        for(int i = 0; i < checkUserOrPass.length(); i++){
            if(Character.isLetter(checkUserOrPass.charAt(i))){
                letters++;
            }
            if(Character.isDigit(checkUserOrPass.charAt(i))) {
                numbers++;
            }
        }
        if(letters >= 3 && numbers >= 1) {
            return true;
        } else {
            return false;
        }
    }

    private void getAccounts() {
        accountsList.updateList();
        accounts = accountsList.getAccounts();
    }

    private void toaster(String message) {
        Toast t = Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        t.show();
    }
}
