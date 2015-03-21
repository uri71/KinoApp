package com.mozidev.kino.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;
import com.facebook.widget.WebDialog;
import com.mozidev.kino.Constants;
import com.mozidev.kino.R;

import java.util.Arrays;

/**
 * Created by y.storchak on 21.03.15.
 */
public class ShareFragment extends BaseFragment {

    private UiLifecycleHelper uiHelper;

    private static final String TAG = "ShareFragment";
    private LinearLayout btn_container;

    private Session.StatusCallback statusCallback =
            new SessionStatusCallback();

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };


    private void onClickLogin() {
        Session session = Session.getActiveSession();
        if (session != null) {
            if (!session.isOpened() && !session.isClosed()) {
                session.openForRead(new Session.OpenRequest(this).setPermissions(Arrays.asList("public_profile")).setCallback(statusCallback));
            } else {
                Session.openActiveSession(getActivity(), this, true, statusCallback);
            }
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
        getActivity().setTitle("Реєстрація");
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable
                             ViewGroup container,
                             @Nullable
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_share, container, false);
    }


    @Override
    public void onViewCreated(View view,
                              @Nullable
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
        authButton.setReadPermissions(Arrays.asList("public_profile"));
        authButton.setFragment(this);
    }


    private void publishFeedDialog() {
        Bundle params = new Bundle();
        String description = getDescription();
        params.putString("name", "Акція «Дивись рідне!»\n");
        //params.putString("caption", "Build great social apps and get more installs.");
        params.putString("description", description);
        //params.putString("link", "https://developers.facebook.com/android");
        params.putString("picture", getString(R.string.url_sharing));

        WebDialog feedDialog = (
                new WebDialog.FeedDialogBuilder(getActivity(),
                        Session.getActiveSession(),
                        params))
                .setOnCompleteListener(new WebDialog.OnCompleteListener() {

                    @Override
                    public void onComplete(Bundle values,
                                           FacebookException error) {
                        if (error == null) {
                            // When the story is posted, echo the success
                            // and the post Id.
                            final String postId = values.getString("post_id");
                            if (postId != null) {
                                Toast.makeText(getActivity(),
                                        "Posted story, id: " + postId,
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // User clicked the Cancel button
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Publish cancelled",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else if (error instanceof FacebookOperationCanceledException) {
                            // User clicked the "x" button
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Publish cancelled",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Generic, ex: network error
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Error posting story",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                })
                .build();
        feedDialog.show();
    }


    private String getDescription() {
        String[] text = getResources().getStringArray(R.array.descriptions);
        int random = (int)(Math.random()*3);
        String url_ios = getActivity().getPreferences(Context.MODE_PRIVATE).getString(Constants.ARG_URL_IOS, "");
        String url_android = getActivity().getPreferences(Context.MODE_PRIVATE).getString(Constants.ARG_URL_ANDROID, "");

        String appstore = getString(R.string.link_appstore);
        String googleplay = getString(R.string.link_googleplay);
        String description = text[random]
                + (url_ios.isEmpty()?"":(appstore + url_ios))
                + (url_android.isEmpty()?"":(googleplay + url_android))
                + getString(R.string.link_site);
        return description;
    }


    private class SessionStatusCallback implements Session.StatusCallback {

        @Override
        public void call(Session session, SessionState state, Exception exception) {
            if (state.isOpened()) {
                Log.i(TAG, "Logged in...");
                publishFeedDialog();
            } else if (state.isClosed()) {
                Log.i(TAG, "Logged out...");
            }
        }
    }


    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Log.i(TAG, "Logged in...");
            publishFeedDialog();
        } else if (state.isClosed()) {
            Log.i(TAG, "Logged out...");
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        Session session = Session.getActiveSession();
        if (session != null &&
                (session.isOpened() || session.isClosed())) {
            onSessionStateChange(session, session.getState(), null);
        }
        uiHelper.onResume();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

}
