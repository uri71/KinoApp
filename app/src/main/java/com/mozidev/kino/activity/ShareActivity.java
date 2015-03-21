package com.mozidev.kino.activity;

import android.os.Bundle;

import com.mozidev.kino.R;
import com.mozidev.kino.fragments.ShareFragment;


/**
 * Created by y.storchak on 20.03.15.
 */
public class ShareActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ShareFragment()).commit();
    }
}
