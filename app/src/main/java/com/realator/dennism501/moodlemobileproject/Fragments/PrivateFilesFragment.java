package com.realator.dennism501.moodlemobileproject.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.realator.dennism501.moodlemobileproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrivateFilesFragment extends Fragment {


    public PrivateFilesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_private_files, container, false);

        return view;
    }

}
