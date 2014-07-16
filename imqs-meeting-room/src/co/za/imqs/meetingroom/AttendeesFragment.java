package co.za.imqs.meetingroom;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This indicates the details of who is in a meeting
 * @author donovan
 */
public class AttendeesFragment extends Fragment {
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_attendees, container, false);
    }

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
}
