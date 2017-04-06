package elkgrove.swifttruck.tabs;
/* Created by evolanddazly on 1/25/17 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import elkgrove.swifttruck.R;

public class mtbBusinessAccountLoader extends Fragment {
    private View rootView;
    private ToggleButton tglBroadCaseBtn;

    /* Override for Fragment */

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.mtb_account_transition, container, false);

        this.tglBroadCaseBtn = (ToggleButton) this.rootView.findViewById(R.id.broadcast_btn);
        this.tglBroadCaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: fix enabled and disabled status
                /* toggle button will be on by default */
                /* if user clicks on it then toggle it */
                /* update online and offline status ; broadcasting will be affected */
                boolean isOnline = rootView.findViewById(R.id.broadcast_btn).isEnabled();
                System.out.println("TOGGLE BUTTON IS: " + isOnline);
                // TODO : register broadcast with database
            }
        });

        // TODO: create online loader , toggle online button to go green

        // TODO: destroying online loader , toggle online button to go red

        return rootView;
    }
}
