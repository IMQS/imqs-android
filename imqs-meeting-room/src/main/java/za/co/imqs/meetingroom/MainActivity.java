package za.co.imqs.meetingroom;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	public static String MESSAGE = "inter_activity_key";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        FrameLayout mainView = (FrameLayout) findViewById(R.id.activity_main);
        if (mainView != null) {
            if (savedInstanceState != null)
                return;

            View f = (View)findViewById(R.id.fragment_attendees);

            AttendeesFragment a = new AttendeesFragment();
            a.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(R.id.activity_main, a).commit();
            //addFragments(new AttendeesFragment());
        }
	}

    private <F extends Fragment> void addFragments(F... fragments) {
        addFragments(R.id.activity_main, fragments);
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
