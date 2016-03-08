package pebble.pebble;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.CountDownTimer;
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
import android.widget.Toast;
import android.util.Log;
import android.location.LocationManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;
import com.google.android.gms.wearable.Wearable;

import java.util.UUID;

public class StartActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener{

    // Private Global Variables
    private int mode = 1;
    private PebbleKit.PebbleDataReceiver mReceiver;
    public static final String myPREFERENCES = "MyPrefs";
    public static final String Name1 = "name1Key";
    public static final String Phone1 = "phone1Key";
    public static final String Name2 = "name2Key";
    public static final String Phone2 = "phone2Key";
    public static final String Name3 = "name3Key";
    public static final String Phone3 = "phone3Key";
    public static final String Name4 = "name4Key";
    public static final String Phone4 = "phone4Key";
    public static final String Name5 = "name5Key";
    public static final String Phone5 = "phone5Key";
    public String[] phoneNumbers;
    String TAG = "Tag:";
    public static double latitude, longitude;
    private static final int
            KEY_BUTTON_EVENT = 0,
            BUTTON_EVENT_UP = 1,
            BUTTON_EVENT_DOWN = 3,
            BUTTON_EVENT_SELECT = 2;

    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Bundle extras = getIntent().getExtras();

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(Wearable.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


        String[] phoneN = extras.getStringArray("PHONES");
        phoneNumbers = phoneN;


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
                if (data.getUnsignedIntegerAsLong(KEY_BUTTON_EVENT) != null) {
                    int button = data.getUnsignedIntegerAsLong(KEY_BUTTON_EVENT).intValue();

                    switch (button) {
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

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }



    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mReceiver);
    }


    public void sendSMS(String[] phoneNumbers, String message) {
        for (int i = 0; i < phoneNumbers.length; i++) {
            if (phoneNumbers[i] != null) {
                try {
                    SmsManager.getDefault().sendTextMessage(phoneNumbers[i], null, message, null, null);
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

    /*new CountDownTimer(5000, 1000) {

        public void onTick(long millisUntilFinished) {
            displayMode.setText("seconds remaining: " + millisUntilFinished / 1000);
        }

    public void onFinish() {
        displayMode.setText("done!");
    }
}.start();*/

    public void checkThreat(View v) {
        //Variables Needed
        Button escalateThreat = (Button) findViewById(R.id.escalateThreat);
        Button vv = (Button) v;
        RelativeLayout relativeLayoutB = (RelativeLayout) findViewById(R.id.relativeLayout);
        final TextView displayMode = (TextView) findViewById(R.id.modeDisplay);
        TextView descriptionMode = (TextView) findViewById(R.id.modeDescription);

        // CHeck the type of button press and whether to escalate threat or not.
        if (escalateThreat == vv) {
            ++mode;
            if (mode > 4) {
                mode = 4;
            }
        } else {
            mode=1;
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
                /*SharedPreferences sp = context.getSharedPreferences(myPREFERENCES, Context.MODE_PRIVATE);

                String returnPhone1 = sp.getString(Phone1, "DEFAULT");
                String returnPhone2 = sp.getString(Phone2, "DEFAULT");
                String returnPhone3 = sp.getString(Phone3, "DEFAULT");
                String returnPhone4 = sp.getString(Phone4, "DEFAULT");
                String returnPhone5 = sp.getString(Phone5, "DEFAULT");
                phoneNumbers = new String[] {returnPhone1, returnPhone2, returnPhone3, returnPhone3, returnPhone4, returnPhone5};*/
                relativeLayoutB.setBackgroundResource(R.color.alertModeCol);
                displayMode.setText(R.string.alertModeTxt);
                descriptionMode.setText(R.string.alertModeDesc);
                String[] twoContacts = {phoneNumbers[0], phoneNumbers[1]};
                sendSMS(twoContacts, getResources().getString(R.string.Message1));
                String location = latalong();
                sendSMS(twoContacts, location);

                break;
            case 3: // Threat Mode

                relativeLayoutB.setBackgroundResource(R.color.threatModeCol);
                displayMode.setText(R.string.threatModeTxt);
                descriptionMode.setText(R.string.threatModeDesc);

                sendSMS(phoneNumbers, getResources().getString(R.string.Message2));
                sendSMS(phoneNumbers, getResources().getString(R.string.Message2_1));
                String location1 = latalong();
                sendSMS(phoneNumbers, location1);
                //sendSMS(phoneNumbers, getResources().getString(R.string.Message2));

                break;
            case 4: //Panic Mode
                relativeLayoutB.setBackgroundResource(R.color.panicModeCol);
                displayMode.setText(R.string.panicModeTxt);
                descriptionMode.setText(R.string.panicModeDesc);
                sendSMS(phoneNumbers, getResources().getString(R.string.Message3));
                sendSMS(phoneNumbers, getResources().getString(R.string.Message3_1));
                String location2 = latalong();
                sendSMS(phoneNumbers, location2);
                // Text 911 when fully implemented

                break;

        }


    }


    public String latalong() {
 /*   LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);*/
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation!=null) {
            longitude = mLastLocation.getLongitude();
            latitude = mLastLocation.getLatitude();
        }
        String lat = Double.toString(latitude);
        String log = Double.toString(longitude);

        String coord = String.format("Their last known location: https://maps.google.com/maps?q=%s,%s", lat, log);

        return coord;
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}

