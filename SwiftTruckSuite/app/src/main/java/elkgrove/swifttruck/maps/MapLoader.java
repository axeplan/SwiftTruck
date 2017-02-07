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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 200: {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //TODO: do stuff after permissions were granted after on request
                }
            }
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

    @Override
    public void onMapReady(GoogleMap map) {
        //TODO: add details from database, so trucks can be displayed based on user selection
        LatLng SacState_ParkingLot_5 = new LatLng(38.558779, -121.422269); //TRUCK_DETAILS_FROM_DB

        if (ActivityCompat.checkSelfPermission(getActivity()
                , Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity()
                , Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(SacState_ParkingLot_5, 13));
        map.addMarker(new MarkerOptions()
                .title("Sac State") //TRUCK_NAME
                .snippet("Sac State Parking Lot 5") //TRUCK_DESCRIPTION
                .position(SacState_ParkingLot_5)); //TRUCK_POSITION
    }
}
