package pebble.pebble;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }

    public void switchMode(){
        RelativeLayout relativeLayoutB = (RelativeLayout) findViewById(R.id.relativeLayout);
        relativeLayoutB.setBackgroundResource(R.color.alertMode);
    }

}
