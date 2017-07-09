package com.otoniel.g6pd.g6pddeficiencyapp.mainListing;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.otoniel.g6pd.g6pddeficiencyapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainListingFragment extends Fragment {

    public static MainListingFragment newInstance() {
        return new MainListingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_listing, container, false);
    }
}
