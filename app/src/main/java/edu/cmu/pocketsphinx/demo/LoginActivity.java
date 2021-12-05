package edu.cmu.pocketsphinx.demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.hanks.passcodeview.PasscodeView;

public class LoginActivity extends AppCompatActivity {

    PasscodeView passcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        passcodeView = findViewById(R.id.passcodeView);

        // to set length of password as here
        // we have set the length as 5 digits
        passcodeView.setPasscodeLength(5)
                // to set pinCode or passcode
                .setLocalPasscode("12345")

                // to set listener to it to check whether
                // passwords has matched or failed
                .setListener(new PasscodeView.PasscodeViewListener() {
                    @Override
                    public void onFail() {
                        // to show message when Password is incorrect
                        Toast.makeText(LoginActivity.this, "Ýalňyş!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String number) {
                        // here is used so that when password
                        // is correct user will be
                        // directly navigated to next activity
                        Intent intent = new Intent(LoginActivity.this, PocketSphinxActivity.class);
                        startActivity(intent);
                    }
                });
    }
}