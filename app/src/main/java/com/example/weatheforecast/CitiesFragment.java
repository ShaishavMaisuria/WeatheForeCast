package com.example.weatheforecast;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CitiesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "Citiesfragment" ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CitiesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CitiesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CitiesFragment newInstance(String param1, String param2) {
        CitiesFragment fragment = new CitiesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
ListView listView;
    ArrayList<Data.City> city = new ArrayList<>();
    ArrayAdapter<Data.City> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Cities");
        View view= inflater.inflate(R.layout.fragment_cities, container, false);
        city=Data.cities;
        Log.d(TAG,"cities"+city.get(0).toString());
        listView=view.findViewById(R.id.citiesListView);
        adapter= new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,android.R.id.text1,city);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG," Clicked On city and country"+city.get(position));

                String cityCountry= city.get(position).toString();

                mListener.citiesToCurrent(cityCountry);

            }
        });

        return view;
    }

    CitiesFragmentListiner mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CitiesFragmentListiner) {
            mListener = (CitiesFragmentListiner) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement loginListener");
        }

    }

    interface CitiesFragmentListiner {
        void citiesToCurrent(String cityCountry);
  }


}