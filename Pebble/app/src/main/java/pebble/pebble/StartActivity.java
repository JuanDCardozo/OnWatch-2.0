package pebble.pebble;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
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
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    int mode = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


    }

    public void sendSMS(String[] phoneNumbers, String message){
        for(int i=0; i<phoneNumbers.length; i++) {
            try {
                SmsManager.getDefault().sendTextMessage(phoneNumbers[i], null,message, null, null);
            } catch (Exception e) {
                AlertDialog.Builder alertDialogBuilder = new
                        AlertDialog.Builder(this);
                AlertDialog dialog = alertDialogBuilder.create();

                dialog.setMessage(e.getMessage());
                dialog.show();


            }
        }

    }


    public void checkThreat(View v) {
        //Variables Needed
        Button escalateThreat = (Button) findViewById(R.id.escalateThreat);
        Button vv = (Button) v;
        RelativeLayout relativeLayoutB = (RelativeLayout) findViewById(R.id.relativeLayout);
        TextView displayMode = (TextView) findViewById(R.id.modeDisplay);
        TextView descriptionMode = (TextView) findViewById(R.id.modeDescription);

        // CHeck the type of button press and whether to escalate threat or not.
        if(escalateThreat == vv){
            ++mode;
            if (mode > 4) {
                mode = 4;
            }
        }else{
            --mode;
            if (mode < 1) {
                mode = 1;
            }
        }

        switch (mode) {
            case 1:// Safe Mode
                relativeLayoutB.setBackgroundResource(R.color.safeModeCol);
                displayMode.setText(R.string.safeModeTxt);
                descriptionMode.setText(R.string.safeModeDesc);

                break;
            case 2: // Alert Mode
                relativeLayoutB.setBackgroundResource(R.color.alertModeCol);
                displayMode.setText(R.string.alertModeTxt);
                descriptionMode.setText(R.string.alertModeDesc);
                break;
            case 3: // Threat Mode
                relativeLayoutB.setBackgroundResource(R.color.threatModeCol);
                displayMode.setText(R.string.threatModeTxt);
                descriptionMode.setText(R.string.threatModeDesc);
                String[] phone = {"14088565696"};
                String lat = "12345543";
                String lon = "345543";
                String newName = String.format("Hello I am at google.com/%s,%s", lat, lon);
                sendSMS(phone,"Hi, Its" + newName);
                break;
            case 4: //Panic Mode
                relativeLayoutB.setBackgroundResource(R.color.panicModeCol);
                displayMode.setText(R.string.panicModeTxt);
                descriptionMode.setText(R.string.panicModeDesc);

                break;

        }


    }
}


