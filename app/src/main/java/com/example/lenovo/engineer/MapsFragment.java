package com.example.lenovo.engineer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

public class MapsFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap googleMap;
    private HashMap<Integer, LatLng> locations;
    private Bundle bundle;
    public static int MAIN_BUILDING = 0;
    public static int LIBRARY = 1;
    public static int PAVILION = 2;
    public static int SJA = 3;
    public static int SAC = 4;
    public static int GIRLS_HOSTEL = 5;
    public static int CCC = 6;
    public static int NEW_SPORTS_BLOCK = 7;
    public static int ATB = 8;
    public static int LHC_A = 9;
    public static int LHC_B = 10;
    public static int NTB = 11;
    public static int LHC_C = 12;
    public static int MEGA_TOWER_1 = 13;
    public static int MEGA_TOWER_2 = 14;
    public static int MEGA_TOWER_3 = 15;
    public static int AMUL = 16;
    public static int NESCAFE = 17;
    public static int BASKETBALL_COURT = 18;

    public MapsFragment() {
        locations = new HashMap<>();
        locations.put(MAIN_BUILDING, new LatLng(13.010902, 74.794349));
        locations.put(LIBRARY, new LatLng(13.010051, 74.794182));
        locations.put(PAVILION, new LatLng(13.011066, 74.794651));
        locations.put(SJA, new LatLng(13.008865, 74.795673));
        locations.put(SAC, new LatLng(13.011260, 74.796247));
        locations.put(GIRLS_HOSTEL, new LatLng(13.013163, 74.794516));
        locations.put(CCC, new LatLng(13.009447, 74.795794));
        locations.put(NEW_SPORTS_BLOCK, new LatLng(13.009817, 74.798217));
        locations.put(ATB, new LatLng(13.009515, 74.793991));
        locations.put(LHC_A, new LatLng(13.009525, 74.793921));
        locations.put(NTB, new LatLng(13.009458, 74.794565));
        locations.put(LHC_B, new LatLng(13.009458, 74.794565));
        locations.put(LHC_C, new LatLng(13.010581, 74.792296));
        locations.put(MEGA_TOWER_1, new LatLng(13.007785, 74.794839));
        locations.put(MEGA_TOWER_2, new LatLng(13.007001, 74.795322));
        locations.put(MEGA_TOWER_3, new LatLng(13.006400, 74.794426));
        locations.put(AMUL, new LatLng(13.009112, 74.796794));
        locations.put(NESCAFE, new LatLng(13.007542, 74.796850));
        locations.put(BASKETBALL_COURT, new LatLng(13.010806, 74.796776));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.bundle = bundle;
        }
        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        this.googleMap = googleMap;
        this.googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style)
        );

        if (bundle != null) {
            int location = bundle.getInt("location");
            String name = bundle.getString("name");
            setLocation(name, locations.get(location));
        } else {
            // Default location
            setLocation("Main Building", locations.get(MAIN_BUILDING));
        }
    }

    /**
     * Puts the desired location(LatLng) in focus
     *
     * @param name - The name displayed when marker is clicked.
     * @param latLng   - LatLng object representing the particular location
     */
    private void setLocation(String name, LatLng latLng) {
        googleMap.addMarker(new MarkerOptions().position(latLng)
                .title(name));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
    }
}
