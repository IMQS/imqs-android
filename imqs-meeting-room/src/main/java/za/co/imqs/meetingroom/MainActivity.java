package za.co.imqs.meetingroom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static String MESSAGE = "inter_activity_key";
	
	ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView)findViewById(R.id.list_attendees);
		String[] values = getListValues();
        
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
		
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	            @Override
	            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	            // ListView Clicked item index
	            int itemPosition = position;
	            // ListView Clicked item value
	            String itemValue = (String) listView.getItemAtPosition(position);
	            // Show Alert 
	            Toast.makeText(getApplicationContext(), "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG).show();
            }
       });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
			
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
			return rootView;
		}
	}
	
	/**
	 * Added for tutorial - this method name is the value of the button's onClick property
	 */
	public void sendMessage(View view) {
//		Intent intent = new Intent(this, EditMeetingActivity.class);
//      TextView editText = (TextView) findViewById(R.id.header_detail);
//		String msg = editText.getText().toString();
//		intent.putExtra(MESSAGE, msg);
//		startActivity(intent);
	}
	
	private String[] getListValues() {
		return new String[]{
			"Jaco Vosloo",
			"Erick Underhill",
			"Oliete Williams",
			"Thea Bester",
			"Rob Knight",
			"Gerhard van Wyk"
		};
	}
}
