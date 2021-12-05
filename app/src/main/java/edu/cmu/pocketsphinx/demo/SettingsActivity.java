package edu.cmu.pocketsphinx.demo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    EditText ip, port, zeroParam, firstParam, secondParam, http;
    private String strIp, strPort, strZeroParam, strFirstParam, strSecondParam, strHttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        // declare variables
        ip = findViewById(R.id.editIP);
        port = findViewById(R.id.editPort);
        zeroParam = findViewById(R.id.editZeroParam);
        firstParam = findViewById(R.id.editFirstParam);
        secondParam = findViewById(R.id.editSecondParam);
        http = findViewById(R.id.editHttp);


        // load data from PrefConfig
        strIp = PrefConfig.loadIpPref(getApplicationContext());
        strPort = PrefConfig.loadPORTPref(getApplicationContext());
        strZeroParam = PrefConfig.loadZeroParam(getApplicationContext());
        strFirstParam = PrefConfig.loadFirstParam(getApplicationContext());
        strSecondParam = PrefConfig.loadSecondParam(getApplicationContext());
        strHttp = PrefConfig.loadHttpPref(getApplicationContext());

        ip.setText(strIp);
        port.setText(strPort);
        zeroParam.setText(strZeroParam);
        firstParam.setText(strFirstParam);
        secondParam.setText(strSecondParam);
        http.setText(strHttp);

    }

    private void saveData() {
        // get user data and save into mobile phone
        strIp = ip.getText().toString();
        strPort = port.getText().toString();
        strZeroParam = zeroParam.getText().toString();
        strFirstParam = firstParam.getText().toString();
        strSecondParam = secondParam.getText().toString();
        strHttp = http.getText().toString();
        PrefConfig.saveIpPref(getApplicationContext(), strIp);
        PrefConfig.savePORTPref(getApplicationContext(), strPort);
        PrefConfig.saveZeroParam(getApplicationContext(), strZeroParam);
        PrefConfig.saveFirstParam(getApplicationContext(), strFirstParam);
        PrefConfig.saveSecondParam(getApplicationContext(), strSecondParam);
        PrefConfig.saveHttpPref(getApplicationContext(), strHttp);
        // show text for successfully create a new user
        Toast.makeText(SettingsActivity.this, R.string.success, Toast.LENGTH_SHORT).show();

        System.out.println(strHttp + "://" + strIp + strPort + strZeroParam + strFirstParam + strSecondParam);//if you write strZeroParam,you must add "/"
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.action_save) {// if place doesn't exist any characters
            if (ip.getText().toString().length() <= 0) {
                ip.setError(getResources().getString(R.string.fit));
            } else {
                saveData();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
