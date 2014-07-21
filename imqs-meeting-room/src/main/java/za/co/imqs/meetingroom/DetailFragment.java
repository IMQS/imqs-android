package za.co.imqs.meetingroom;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

/**
 * This indicates the details of a meeting
 * @author donovan
 */
public class DetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_detail, container, false);
        result.setBackgroundColor(Color.parseColor("#DFECF5"));
        return result;
    }

	@Override
	public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        Activity parentActivity = this.getActivity();
        final Button btnOpenPopup = (Button) parentActivity.findViewById(R.id.button_edit_detail);
        btnOpenPopup.setOnClickListener(getOpenEditDetailListener(this));
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}

    /**
     * Button action for editing a meeting's details
     */
    public void openEditDetail(View view) {

    }

    private Button.OnClickListener getOpenEditDetailListener(final Fragment parentFragment) {

        return new Button.OnClickListener() {

            public void onClick(View view) {
                Activity parentActivity = parentFragment.getActivity();
                LayoutInflater layoutInflater = (LayoutInflater) parentActivity.getBaseContext().getSystemService(parentActivity.LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.activity_edit_detail, null);
                final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                Button btnDismiss = (Button) popupView.findViewById(R.id.button_save_detail);

                btnDismiss.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        };
    }
}
