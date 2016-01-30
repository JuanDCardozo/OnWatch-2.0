package pebble.pebble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void storeData(){
        //Store Contact data
        EditText contact1 = (EditText) findViewById(R.id.contact1);
        EditText phone1 = (EditText) findViewById(R.id.phoneNum1);

        EditText contact2 = (EditText) findViewById(R.id.contact2);
        EditText phone2 = (EditText) findViewById(R.id.phoneNum2);

        EditText contact3 = (EditText) findViewById(R.id.contact2);
        EditText phone3 = (EditText) findViewById(R.id.phoneNum2);

        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

}
