package com.mozidev.kino.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mozidev.kino.R;
import com.mozidev.kino.adapters.SniperAdapter;
import com.mozidev.kino.model.Sniper;
import com.norbsoft.typefacehelper.TypefaceHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SnipersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SnipersFragment extends Fragment {


    private String mParam1;
    private List<Sniper> snipers;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SnipersFragment.
     */
    public static SnipersFragment newInstance() {
        SnipersFragment fragment = new SnipersFragment();
        return fragment;
    }


    public SnipersFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_snipers, container, false);
    }


    @Override
    public void onViewCreated(View view,
                              @Nullable
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView list = (RecyclerView)view.findViewById(R.id.list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        list.setLayoutManager(manager);
        list.setHasFixedSize(true);
        SniperAdapter adapter = new SniperAdapter(getActivity(), getSnipers());
        list.setAdapter(adapter);

    }


    private List<Sniper> getSnipers() {
        List <Sniper> snipers = new ArrayList<>();
        String[] name = getResources().getStringArray(R.array.snipers_top);
        String[] result = getResources().getStringArray(R.array.sniper_result);
        String[] about = getResources().getStringArray(R.array.sniper_about);
        int[] photo = new int[] {
                R.drawable.snip_1, R.drawable.snip_2, R.drawable.snip_3,
                R.drawable.snip_4, R.drawable.snip_5, R.drawable.snip_6,
                R.drawable.snip_7, R.drawable.snip_8, R.drawable.snip_9,
                R.drawable.snip_10
        };

        for (int i = 0; i < name.length; i++){
            snipers.add (new Sniper(name[i], result[i], about[i], photo[i]));
        }
        return snipers;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            getActivity().getSupportFragmentManager().popBackStack();
        }
        return false;
    }
}
