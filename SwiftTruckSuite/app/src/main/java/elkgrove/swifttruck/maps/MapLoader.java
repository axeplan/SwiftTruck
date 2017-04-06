package elkgrove.swifttruck.maps;
/* Created by evolanddazly on 12/26/16 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import elkgrove.swifttruck.R;

public class MapLoader extends Fragment implements OnMapReadyCallback
                                                , ActivityCompat.OnRequestPermissionsResultCallback {
    private View rootView;

    /* Override for Fragment */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean permissionGranted = ActivityCompat.checkSelfPermission(getActivity()
                , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if(permissionGranted) {
            //TODO: do stuff after permissions were granted after on create
        } else {
            ActivityCompat.requestPermissions(getActivity()
                    , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.map_activity_transition, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(MapLoader.this);

        return rootView;
    }

    /* Override for OnRequestPermissionsResultCallback */

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 200: {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //TODO: do stuff after permissions were granted after on request
                }
            }
        }
    }

    /* Override for OnMapReadyCallback */

    @Override
    public void onMapReady(GoogleMap map) {
        //TODO: add details from database, so trucks can be displayed based on user selection
        //TODO: Sac State location will switch with current location of user phone
        //TODO: Target Location will switch with current location of truck (read from database)
        LatLng SacState_ParkingLot_5 = new LatLng(38.558779, -121.422269); //USER_CURRENT_LOCATION
        LatLng TARGET_LOCATION = new LatLng(40.558779, -119.422269); //TRUCK_DETAILS_FROM_DB

        if (ActivityCompat.checkSelfPermission(getActivity()
                , Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity()
                , Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(SacState_ParkingLot_5, 13));

        //add user marker
        map.addMarker(new MarkerOptions()
                .title("Sac State") //USER_NAME
                .snippet("Sac State Parking Lot 5") //USER_DESCRIPTION
                .position(SacState_ParkingLot_5)); //USER_POSITION
        //add target marker
        map.addMarker(new MarkerOptions()
                .title("TARGET") //TARGET_NAME
                .snippet("TARGET LOCATION") //TARGET_LOCATION
                .position(TARGET_LOCATION)); //TARGET_LOCATION
        //add blue route

    }
}
