package com.mozidev.kino.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.KinoApplication;
import com.mozidev.kino.R;
import com.norbsoft.typefacehelper.ActionBarHelper;
import com.norbsoft.typefacehelper.TypefaceHelper;

/**
 * Created by y.storchak on 30.03.15.
 */
public class MessageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        KinoApplication.getInstance(this).initTypeFace();
        setTitle("Повідомлення");
        TextView tv_message = (TextView) findViewById(R.id.tv_message);
        Button btn_continue= (Button) findViewById(R.id.btn_continue);
        String text = getSharedPreferences(Constants.PREFERENCES, MODE_PRIVATE).getString(Constants.ARG_NOTIFICATION, "");
        tv_message.setText(text);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MessageActivity.this, SplashActivity.class));
                MessageActivity.this.finish();
            }
        });
        TypefaceHelper.typeface(this);
        BitmapDrawable background = new BitmapDrawable(BitmapFactory.decodeResource(getResources(), R.drawable.ab_texture));
        background.setTileModeX(android.graphics.Shader.TileMode.REPEAT);
        getSupportActionBar().setBackgroundDrawable(background);
    }

    @Override
    public void setTitle(CharSequence title) {
        ActionBarHelper.setTitle(getSupportActionBar(), TypefaceHelper.typeface(title));
//		super.setTitle(title);
    }
}
