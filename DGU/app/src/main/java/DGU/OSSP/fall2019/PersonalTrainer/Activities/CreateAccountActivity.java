package DGU.OSSP.fall2019.PersonalTrainer.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import DGU.OSSP.fall2019.PersonalTrainer.Classes.SQLiteUserManager;
import DGU.OSSP.fall2019.DGU.R;

public class CreateAccountActivity extends AppCompatActivity {

    EditText inFirstName, inLastName, inEmail, inPassword, inHeight, inWeight;
    Button buttonRegister, buttonBack;
    String fname, lname, email, pw;
    int height, weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_layout);

        bindViews();
        setupClickListeners();
    }

    private void bindViews() {
        inFirstName = findViewById(R.id.inputFirstName);
        inLastName = findViewById(R.id.inputLastName);
        inHeight = findViewById(R.id.inputHeight);
        inWeight = findViewById(R.id.inputWeight);
        inEmail = findViewById(R.id.inputEmail);
        inPassword = findViewById(R.id.inputPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonBack = findViewById(R.id.buttonBack);
    }

    private void setupClickListeners() {
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fname = inFirstName.getText().toString();
                lname = inLastName.getText().toString();
                email = inEmail.getText().toString();
                pw = inPassword.getText().toString();
                height = Integer.parseInt(inHeight.getText().toString());
                weight = Integer.parseInt(inWeight.getText().toString());

                tryToRegister(fname, lname, height, weight, email, pw);
                finish(); // go back to LoginActivity
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();   // go back to LoginActivity
            }
        });
    }

    private void tryToRegister(String fname, String lname, int height, int weight,
                                  String email, String pw) {
        SQLiteUserManager myDB = new SQLiteUserManager(this);
        myDB.initUser(email, pw);
        myDB.close();
    }
}
