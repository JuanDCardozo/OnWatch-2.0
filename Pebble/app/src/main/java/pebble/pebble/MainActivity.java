package pebble.pebble;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7, ed8, ed9, ed10;
    private EditText iEditText;
    private Button addButton;
    private RelativeLayout rLayout;
    TextView textView = new TextView(this);

    Button b1;
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
    public static final String Opened = "openKey";
    public int opencheck = 0;

    // public final Intent intent = new Intent(this, StartActivity.class);
    public static final String Phone = "phoneKey";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp = getSharedPreferences(myPREFERENCES, Activity.MODE_PRIVATE);


        opencheck = sp.getInt(Opened, 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*New layout stuff*/
        iEditText = (EditText) findViewById(R.id.textView2); //Edit text box
        addButton = (Button) findViewById(R.id.button2);     //Add button
        rLayout = (RelativeLayout) findViewById(R.id.rLayout); //Relative Layout

        addButton.setOnClickListener(onClick());

        /*---------------*/
        /*
        ed1 = (EditText)findViewById(R.id.editText1);
        ed2 = (EditText)findViewById(R.id.editText2);
        ed3 = (EditText)findViewById(R.id.editText3);
        ed4 = (EditText)findViewById(R.id.editText4);
        ed5 = (EditText)findViewById(R.id.editText5);
        ed6 = (EditText)findViewById(R.id.editText6);
        ed7 = (EditText)findViewById(R.id.editText7);
        ed8 = (EditText)findViewById(R.id.editText8);
        ed9 = (EditText)findViewById(R.id.editText9);
        ed10 = (EditText)findViewById(R.id.editText10);
        if(opencheck==0) {
            ed1.setHint("Name of Contact 1");
            ed2.setHint("Phone # of Contact 1");
            ed3.setHint("Name of Contact 2");
            ed4.setHint("Phone # of Contact 2");
            ed5.setHint("Name of Contact 3");
            ed6.setHint("Phone # of Contact 3");
            ed7.setHint("Name of Contact 4");
            ed8.setHint("Phone # of Contact 4");
            ed9.setHint("Name of Contact 5");
            ed10.setHint("Phone # of Contact 5");
        }
        else{
            sp = getSharedPreferences(myPREFERENCES, Activity.MODE_PRIVATE);
            String returnName1 = sp.getString(Name1, "Name of Contact 1");
            String returnPhone1 = sp.getString(Phone1, "Phone # of Contact 1");
            String returnName2 = sp.getString(Name2, "Name of Contact 2");
            String returnPhone2 = sp.getString(Phone2, "Phone # of Contact 2");
            String returnName3 = sp.getString(Name3, "Name of Contact 3");
            String returnPhone3 = sp.getString(Phone3, "Phone # of Contact 3");
            String returnName4 = sp.getString(Name4, "Name of Contact 4");
            String returnPhone4 = sp.getString(Phone4, "Phone # of Contact 4");
            String returnName5 = sp.getString(Name5, "Name of Contact 5");
            String returnPhone5 = sp.getString(Phone5, "Phone # of Contact 5");

            if (returnName1.compareTo("")!=0) {
                ed1.setHint(returnName1);}
            if (returnPhone1.compareTo("")!=0) {
                ed2.setHint(returnPhone1);}
            if (returnName2.compareTo("")!=0) {
                ed3.setHint(returnName2);}
            if (returnPhone2.compareTo("")!=0) {
                ed4.setHint(returnPhone2);}
            if (returnName3.compareTo("")!=0) {
                ed5.setHint(returnName3);}
            if (returnPhone3.compareTo("")!=0) {
                ed6.setHint(returnPhone3);}
            if (returnName4.compareTo("")!=0) {
                ed7.setHint(returnName4);}
            if (returnPhone4.compareTo("")!=0) {
                ed8.setHint(returnPhone4);}
            if (returnName5.compareTo("")!=0) {
                ed9.setHint(returnName5);}
            if (returnPhone5.compareTo("")!=0) {
                ed10.setHint(returnPhone5);}


        }*/

        sharedPreferences = getSharedPreferences(myPREFERENCES, Context.MODE_PRIVATE);

    }

    // What happens when the add button is clicked
    private OnClickListener onClick() {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                rLayout.addView(createText(iEditText.getText().toString()));
            }
        };
    }

    private TextView createText(String text) {
        final LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        final TextView textview = new TextView(this);
        textview.setLayoutParams(lparams);
        textview.setText(text);
        return textview;
    }

/*
    public void storeData(View view){
        sharedPreferences = getSharedPreferences(myPREFERENCES, Context.MODE_PRIVATE);
        String n1str  = ed1.getText().toString();
        String p1str  = ed2.getText().toString();
        String n2str  = ed3.getText().toString();
        String p2str  = ed4.getText().toString();
        String n3str  = ed5.getText().toString();
        String p3str  = ed6.getText().toString();
        String n4str  = ed7.getText().toString();
        String p4str  = ed8.getText().toString();
        String n5str  = ed9.getText().toString();
        String p5str  = ed10.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(n1str.compareTo("")!=0){
            editor.putString(Name1, n1str);}

        if(p1str.compareTo("")!=0){
            editor.putString(Phone1, p1str);}

        if(n2str.compareTo("")!=0){
            editor.putString(Name2, n2str);}

        if(p2str.compareTo("")!=0){
            editor.putString(Phone2, p2str);}

        if(n3str.compareTo("")!=0){
            editor.putString(Name3, n3str);}

        if(p3str.compareTo("")!=0){
            editor.putString(Phone3, p3str);}

        if(n4str.compareTo("")!=0){
            editor.putString(Name4, n4str);}

        if(p4str.compareTo("")!=0){
            editor.putString(Phone4, p4str);}

        if(n5str.compareTo("")!=0){
            editor.putString(Name5, n5str);}

        if(p5str.compareTo("")!=0){
            editor.putString(Phone5, p5str);}

        editor.putInt(Opened, 1);

        editor.commit();
        // Toast.makeText(MainActivity.this, n, Toast.LENGTH_LONG).show();
        SharedPreferences sp = getSharedPreferences(myPREFERENCES, Activity.MODE_PRIVATE);
        String returnName1 = sp.getString(Name1, "Name of Contact 1");
        String returnPhone1 = sp.getString(Phone1, "Phone # of Contact 1");
        String returnName2 = sp.getString(Name2, "Name of Contact 2");
        String returnPhone2 = sp.getString(Phone2, "Phone # of Contact 2");
        String returnName3 = sp.getString(Name3, "Name of Contact 3");
        String returnPhone3 = sp.getString(Phone3, "Phone # of Contact 3");
        String returnName4 = sp.getString(Name4, "Name of Contact 4");
        String returnPhone4 = sp.getString(Phone4, "Phone # of Contact 4");
        String returnName5 = sp.getString(Name5, "Name of Contact 5");
        String returnPhone5 = sp.getString(Phone5, "Phone # of Contact 5");


        if (returnName1.compareTo("")!=0) {
            ed1.setHint(returnName1);}
        if (returnPhone1.compareTo("")!=0) {
            ed2.setHint(returnPhone1);}
        if (returnName2.compareTo("")!=0) {
            ed3.setHint(returnName2);}
        if (returnPhone2.compareTo("")!=0) {
            ed4.setHint(returnPhone2);}
        if (returnName3.compareTo("")!=0) {
            ed5.setHint(returnName3);}
        if (returnPhone3.compareTo("")!=0) {
            ed6.setHint(returnPhone3);}
        if (returnName4.compareTo("")!=0) {
            ed7.setHint(returnName4);}
        if (returnPhone4.compareTo("")!=0) {
            ed8.setHint(returnPhone4);}
        if (returnName5.compareTo("")!=0) {
            ed9.setHint(returnName5);}
        if (returnPhone5.compareTo("")!=0) {
            ed10.setHint(returnPhone5);}

        String[] numArray = {returnPhone1, returnPhone2, returnPhone3, returnPhone4, returnPhone5};




        Intent i = new Intent(this, StartActivity.class);
        i.putExtra("PHONES", numArray);
        startActivity(i);
    }
    */



}
