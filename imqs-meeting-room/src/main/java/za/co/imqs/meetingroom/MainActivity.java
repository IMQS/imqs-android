package za.co.imqs.meetingroom;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	public static String MESSAGE = "inter_activity_key";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_container);

        View fragmentContainer = findViewById(R.id.container_fragment);
        View detailContainer = fragmentContainer.findViewById(R.id.container_detail);
        View attendeesContainer = fragmentContainer.findViewById(R.id.container_attendees);

        if (detailContainer != null && attendeesContainer != null) {
            if (savedInstanceState != null)
                return;
            addFragments(detailContainer.getId(), new DetailFragment());
            addFragments(attendeesContainer.getId(), new AttendeesFragment());
        }
	}

    private <F extends Fragment> void addFragments(int container, F... fragments) {
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        for (F f : fragments) {
            f.setArguments(new Bundle());
            tx.add(container, f);
        }
        tx.commit();
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

    public void onClickEditDetail() {

    }
	

}
