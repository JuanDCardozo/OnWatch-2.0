package pebble.pebble;

import android.content.Context;
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

import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;

import java.util.UUID;

public class StartActivity extends AppCompatActivity {
    // Private Global Variables
    private int mode = 1;
    private PebbleKit.PebbleDataReceiver mReceiver;

    private static final int
            KEY_BUTTON_EVENT = 0,
            BUTTON_EVENT_UP = 1,
            BUTTON_EVENT_DOWN = 3,
            BUTTON_EVENT_SELECT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }

    @Override
    protected void onResume() {
        super.onResume();

        mReceiver = new PebbleKit.PebbleDataReceiver(UUID.fromString("4728fd50-df72-432d-8ff3-ecb4f158ec05")) {

            @Override
            public void receiveData(Context context, int transactionId, PebbleDictionary data) {
                //ACK the message
                PebbleKit.sendAckToPebble(context, transactionId);

                //Check the key exists
                if(data.getUnsignedIntegerAsLong(KEY_BUTTON_EVENT) != null) {
                    int button = data.getUnsignedIntegerAsLong(KEY_BUTTON_EVENT).intValue();

                    switch(button) {
                        case BUTTON_EVENT_UP:
                            Button eThreat = (Button) findViewById(R.id.escalateThreat);
                            checkThreat(eThreat);
                            //The UP button was pressed
                            break;
                        case BUTTON_EVENT_DOWN:
                            Button dThreat = (Button) findViewById(R.id.difuseThreat);
                            checkThreat(dThreat);
                            //The DOWN button was pressed
                            break;
                        case BUTTON_EVENT_SELECT:
                            //The SELECT button was pressed
                            break;
                    }
                }
            }

        };

        PebbleKit.registerReceivedDataHandler(this, mReceiver);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mReceiver);
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


