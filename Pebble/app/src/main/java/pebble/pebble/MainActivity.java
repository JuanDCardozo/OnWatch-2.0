package pebble.pebble;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {

    @Override
    public int getSelectedItemPosition() {
        return super.getSelectedItemPosition();
    }

    @Override
    public long getSelectedItemId() {
        return super.getSelectedItemId();
    }

    ListView lv;
    Cursor cursor1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        cursor1 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        startManagingCursor(cursor1);
        String[] from={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER};
        int[] to = {R.id.text,R.id.text2};
        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor1,from,to);
        setListAdapter(listAdapter);
        lv =  (ListView) findViewById(R.id.listView);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    public void storeData(){

        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

}
