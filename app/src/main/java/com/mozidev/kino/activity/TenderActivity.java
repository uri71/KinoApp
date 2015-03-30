package com.mozidev.kino.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;

import com.mozidev.kino.Constants;
import com.mozidev.kino.KinoApplication;
import com.mozidev.kino.R;
import com.mozidev.kino.fragments.AboutTenderFragment;
import com.mozidev.kino.fragments.TenderFragment;
import com.norbsoft.typefacehelper.TypefaceHelper;

import java.util.List;

/**
 * Created by user on 16.03.2015.
 */
public class TenderActivity extends BaseActivity{
    private   boolean allAnswerTrue = true;
    private int counter = 0;
    public List<String[]> questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tender);
        questions = KinoApplication.getInstance(this).getListQuestion();
        setTitle(getString(R.string.tender_title));
       // if(getSharedPreferences(Constants.PREFERENCES, MODE_PRIVATE).getBoolean(Constants.PREFERENCES_FINISH, false))showFinishTenderDialog();
        setFragment();
    }

   /* private Dialog showFinishTenderDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.dialog_finish_tender_message));
        Dialog dialog = builder.show();
        TypefaceHelper.typeface(dialog.getWindow().getDecorView());
        return dialog;
    }*/


    public void setFragment() {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                R.anim.slide_in_left, R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.container, new TenderFragment())
                .commit();
    }


    public void onCounter(){
        counter += 1;
    }


    public int getCounter(){
        return counter;
    }


    public String[] getQuestion(){
        return questions.get(counter);
    }


    public boolean isAllAnswerTrue() {
        return allAnswerTrue;
    }


    public void setAllAnswer(boolean answer) {
        this.allAnswerTrue = answer && allAnswerTrue;
    }


    @Override
    public void onBackPressed() {
       showEscapeDialog();
    }


    private Dialog showEscapeDialog() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("Ви дійсно хочете припинити участь у конкурсі?");
        builder.setNegativeButton("Ні", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }); builder.setPositiveButton("Так", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                TenderActivity.this.finish();
            }
        });
        builder.show();
        return null;
    }
}
