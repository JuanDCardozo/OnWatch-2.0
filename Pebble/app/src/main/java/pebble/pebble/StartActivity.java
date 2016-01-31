package pebble.pebble;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.telephony.SmsManager;
import android.widget.EditText;
import android.app.Activity;
import android.app.AlertDialog;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }

    public void switchMode(View view){
        String[] phoneNumbers= { "17073035048","14085068918","14084205595","14088565696" };
        for(int i=0; i<4; i++) {
            try {
                SmsManager.getDefault().sendTextMessage(phoneNumbers[i], null, "Garrett Stoll has " +
                        "indicated that hes is in a dangerous. His most recent location is: http://maps.google.com/maps?q=37.000560,-271.127123 Sent from Guard App", null, null);
            } catch (Exception e) {
                AlertDialog.Builder alertDialogBuilder = new
                        AlertDialog.Builder(this);
                AlertDialog dialog = alertDialogBuilder.create();


                dialog.setMessage(e.getMessage());


                dialog.show();


            }
        }

    }

    }


