package za.co.imqs.meetingroom;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * This indicates the details of a meeting
 * @author donovan
 */
public class DetailFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_detail, container, true);
        result.setBackgroundColor(Color.parseColor("#DFECF5"));
        result = initiateButtons(result);
        return result;
    }

    public View initiateButtons(View view) {
        final Button buttonEditDetail = (Button) view.findViewById(R.id.button_edit_detail);
        buttonEditDetail.setOnClickListener(this);
        return view;
    }

	@Override
	public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
	}

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_edit_detail: onClickButtonEditDetail(view);
        }
    }

    /**
     * Button action for editing a meeting's details
     */
    public View onClickButtonEditDetail(View view) {

        Activity parentActivity = (Activity)this.getActivity();

        FragmentTransaction transaction = parentActivity.getFragmentManager().beginTransaction();
        Fragment newFragment = new EditDetailFragment();
        transaction.hide(this);
        // transaction.replace(R.id.detail, newFragment, "EditDetailsTag");
        transaction.addToBackStack(null);
        transaction.commit();


        return view;
    }
}
