package com.mozidev.kino.fragments;

import android.support.v4.app.Fragment;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONObject;

/**
 * Created by y.storchak on 21.03.15.
 */
public class BaseFragment extends Fragment {
    private   JSONObject jsonObject;
    public JSONObject getResponseAPI(String url){
        AQuery aq = new AQuery(getActivity());
        aq.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {
                if (json != null) {
                    jsonObject = json;
                } else {
                    jsonObject = null;
                }
            }
        });
        return jsonObject;
    }
}
