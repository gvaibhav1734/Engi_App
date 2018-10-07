package com.example.lenovo.engineer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class MapsFragment extends Fragment implements OnMapReadyCallback {
    private static final String TAG = "MapsFragment";
    private GoogleMap googleMap;
    private Bundle bundle;

    public MapsFragment() {
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
        this.googleMap.getUiSettings().setMapToolbarEnabled(false);
        this.googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style)
        );
        if (bundle != null) {
            String name = bundle.getString("location");
            LatLng latLng ;
            try {
                JSONObject locations = new JSONObject(loadJSONFromAsset());
                JSONObject location = locations.getJSONObject(name);
                latLng = new LatLng(location.getDouble("Lat"),location.getDouble("Lng"));
                setLocation(name, latLng);
            }catch (Exception e){
                Log.e(TAG, "locs.json parsing error "+e.getMessage());
                setLocation("Main Building", new LatLng(13.010595, 74.794298));
            }
        } else {
            // Default location
            setLocation("Main Building", new LatLng(13.010595, 74.794298));
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

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("locs.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Log.e(TAG, "locs.json error");
        }
        return json;
    }
}
