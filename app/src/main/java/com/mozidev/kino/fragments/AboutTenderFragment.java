package com.mozidev.kino.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.mozidev.kino.activity.MainActivity;
import com.mozidev.kino.activity.TenderActivity;
import com.mozidev.kino.util.RippleDrawable;
import com.norbsoft.typefacehelper.TypefaceHelper;

/**
 * Created by user on 16.03.2015.
 */
public class AboutTenderFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable
                             ViewGroup container,
                             @Nullable
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_tender, container, false);
    }


    @Override
    public void onViewCreated(View view,
                              @Nullable
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        Button btn_start = (Button) view.findViewById(R.id.btn_start);
        TextView text_about = (TextView) view.findViewById(R.id.tv_about);
        boolean finish = getActivity().getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE).getBoolean(Constants.PREFERENCES_FINISH, false);
        if(finish){
            text_about.setText(getActivity().getString(R.string.dialog_finish_tender_message));
            text_about.setTextSize(26);
        }
        //btn_cancel.setOnClickListener(this);
        btn_start.setOnClickListener(this);
       /* TextView tv_about = (TextView) view.findViewById(R.id.tv_about);
        tv_about.setText(Html.fromHtml(getString(R.string.about_tender)));*/
        //RippleDrawable.createRipple(btn_cancel, Color.parseColor("#ffffff"));
        RippleDrawable.createRipple(btn_start, Color.parseColor("#ffffff"));
        TypefaceHelper.typeface(view);
    }


    public static AboutTenderFragment newInstance(int number) {
        AboutTenderFragment fragment = new AboutTenderFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                Intent intent = new Intent(getActivity(), TenderActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
           /* case R.id.btn_cancel:
                ((MainActivity) getActivity()).setFragment(AboutFilmFragment.newInstance(1));
                break;*/
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(Constants.ARG_SECTION_NUMBER));

    }
}
