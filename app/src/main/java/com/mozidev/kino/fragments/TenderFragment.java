package com.mozidev.kino.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.mozidev.kino.activity.BaseActivity;
import com.mozidev.kino.activity.MainActivity;
import com.mozidev.kino.activity.ShareActivity;
import com.mozidev.kino.activity.TenderActivity;
import com.mozidev.kino.util.RippleDrawable;

/**
 * Created by user on 16.03.2015.
 */
public class TenderFragment extends Fragment /*implements CompoundButton.OnCheckedChangeListener*/ {

    private String[] question;
    private String answer;


    public TenderFragment newInstance(String[] set) {
        Bundle bundle = new Bundle();
        bundle.putStringArray(Constants.ARG_NUMBER_SET_QUESTIONS, set);
        TenderFragment fragment = new TenderFragment();
        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable
                             ViewGroup container,
                             @Nullable
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tender, container, false);
    }


    @Override
    public void onViewCreated(View view,
                              @Nullable
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        question = ((TenderActivity) getActivity()).getQuestion();
        Button btn_continue = (Button) view.findViewById(R.id.btn_continue);
        RippleDrawable.createRipple(btn_continue, Color.parseColor("#ffffff"));

        TextView tv_question = (TextView) view.findViewById(R.id.tv_question);
        tv_question.setText(question[0]);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_answer);
        RadioButton rb_answer_1 = (RadioButton) view.findViewById(R.id.rb_answer1);
        RadioButton rb_answer_2 = (RadioButton) view.findViewById(R.id.rb_answer2);
        RadioButton rb_answer_3 = (RadioButton) view.findViewById(R.id.rb_answer3);
        RippleDrawable.createRipple(rb_answer_1, Color.parseColor("#ffffff"));
        RippleDrawable.createRipple(rb_answer_2, Color.parseColor("#ffffff"));
        RippleDrawable.createRipple(rb_answer_3, Color.parseColor("#ffffff"));
        rb_answer_1.setText(question[1]);
        rb_answer_2.setText(question[2]);
        rb_answer_3.setText(question[3]);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rb_answer1:
                        answer = "1";
                        break;
                    case R.id.rb_answer2:
                        answer = "2";
                        break;
                    default:
                        answer = "3";
                }
                String trim = question[4].trim();
                boolean b = answer.equals(trim);
                ((TenderActivity) getActivity()).setAllAnswer(b);
                Log.e("ANSWER", "number answer -" + answer + " right answer -" + trim + "right boolean - " + b);
            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer == null || answer.isEmpty()) return;
                if (((TenderActivity) getActivity()).getCounter() == 2) {
                    // записываем результаты конкурса
                    SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                    preferences.edit().putBoolean(Constants.ARG_PREFERENCES_WIN, ((TenderActivity) getActivity()).isAllAnswerTrue()).apply();
                    if (((BaseActivity) getActivity()).isConnected()) {
                        sendIntent();
                    } else {
                        ((BaseActivity) getActivity()).showConnectedDialog();
                    }

                } else {
                    ((TenderActivity) getActivity()).onCounter();
                    ((TenderActivity) getActivity()).setFragment();
                }
            }
        });
    }


    private void sendIntent() {
        Intent intent = new Intent(getActivity(), ShareActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        getActivity().finish();
    }

}
