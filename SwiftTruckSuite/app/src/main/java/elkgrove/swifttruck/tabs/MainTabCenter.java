package elkgrove.swifttruck.tabs;

/* Created by evolanddazly on 11/13/16. */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import elkgrove.swifttruck.MainActivity;
import elkgrove.swifttruck.R;
import elkgrove.swifttruck.maps.MapLoader;


public class MainTabCenter extends Fragment implements SearchView.OnQueryTextListener {
    private View rootView;
    private Button mtcMapLocateBtn;
    private android.support.v7.widget.SearchView searchView;

    private final static String TAG = MainActivity.class.getName().toString();


    /* Override for Fragment */

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.main_tab_center, container, false);

        mtcMapLocateBtn = (Button) this.rootView.findViewById(R.id.mapLocateBtn);
        mtcMapLocateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* generate fragment manager ; begin transaction */
                /* container id of replacee , replacer */
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mtc_relative_layout_1, new MapLoader());
                fragmentTransaction.commit();

                // TODO: Switch the button to the top and center the map
                mtcMapLocateBtn.setVisibility(Button.GONE);
            }
        });

        searchView = (android.support.v7.widget.SearchView) rootView.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this); searchView.setIconifiedByDefault(false);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // TODO: listen for valid search
        // TODO: Link marker with another
        // TODO: Adjust markers to re-route using djkstras algorithm

        return rootView;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
