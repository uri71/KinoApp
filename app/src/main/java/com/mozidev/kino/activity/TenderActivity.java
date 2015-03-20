package com.mozidev.kino.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.mozidev.kino.R;
import com.mozidev.kino.fragments.AboutTenderFragment;
import com.mozidev.kino.fragments.TenderFragment;

/**
 * Created by user on 16.03.2015.
 */
public class TenderActivity extends BaseActivity{
    public  boolean allAnswerTrue = true;
    public int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tender);
        getSupportFragmentManager().beginTransaction().add(R.id.container, new AboutTenderFragment(), null).commit();
    }
}
