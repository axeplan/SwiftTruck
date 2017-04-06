package elkgrove.swifttruck.tabs;

/* Created by evolanddazly on 11/13/16. */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import elkgrove.swifttruck.R;

public class MainTabUser extends Fragment {
    private View rootView;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;

    /* Override for Fragment */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.main_tab_user, container, false);

        btn_1 = (Button) this.rootView.findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // btn 1 action
            }
        });

        btn_2 = (Button) this.rootView.findViewById(R.id.btn_2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // btn 1 action
            }
        });

        btn_3 = (Button) this.rootView.findViewById(R.id.btn_3);
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // btn 1 action
            }
        });

        return rootView;
    }
}
