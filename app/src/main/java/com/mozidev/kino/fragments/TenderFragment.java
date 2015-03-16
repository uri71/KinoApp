package com.mozidev.kino.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;

/**
 * Created by user on 16.03.2015.
 */
public class TenderFragment extends Fragment {

    public TenderFragment newInstance(String[] set)
    {
        Bundle bundle = new Bundle();
        bundle.putStringArray(Constants.ARG_NUMBER_SET_QUESTIONS, set);
        TenderFragment fragment = new TenderFragment();
        fragment.setArguments(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tender, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn_continue = (Button) view.findViewById(R.id.btn_continue);
        TextView tv_question = (TextView) view.findViewById(R.id.tv_question);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_answer);
        RadioButton rb_answer_1 = (RadioButton) view.findViewById(R.id.rb_answer1);
        RadioButton rb_answer_2 = (RadioButton) view.findViewById(R.id.rb_answer2);
        RadioButton rb_answer_3 = (RadioButton) view.findViewById(R.id.rb_answer3);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_answer1:
                        break;
                    case R.id.rb_answer2:
                        break;
                    default:
                }
            }
        });
    }
}
