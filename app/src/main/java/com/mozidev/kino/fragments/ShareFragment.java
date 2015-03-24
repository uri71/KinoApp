package com.mozidev.kino.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.WebDialog;
import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.photo.VKImageParameters;
import com.vk.sdk.api.photo.VKUploadImage;
import com.vk.sdk.dialogs.VKCaptchaDialog;
import com.vk.sdk.dialogs.VKShareDialog;
import com.vk.sdk.util.VKUtil;

import java.util.Arrays;

/**
 * Created by y.storchak on 21.03.15.
 */
public class ShareFragment extends BaseFragment {

    private UiLifecycleHelper uiHelper;
    private Button btn_share_fb;
    private Button btn_share_vk;
    private Button btn_login_vk;
    private static String vkTokenKey = "VK_ACCESS_TOKEN";

    private static final String TAG = "ShareFragment";
    private LinearLayout btn_container;
    private static final String[] sMyScope = new String[] {
            VKScope.WALL,
            VKScope.NOHTTPS,
    };

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
        VKUIHelper.onCreate(getActivity());
        VKSdk.initialize(sdkListener, getActivity().getString(R.string.vk_app_id), VKAccessToken.tokenFromSharedPreferences(getActivity(), vkTokenKey));


        String[] fingerprint = VKUtil.getCertificateFingerprint(getActivity(), getActivity().getPackageName());
        Log.d("Fingerprint", fingerprint[0]);
        getActivity().setTitle("Реєстрація");
      /*  if (VKSdk.wakeUpSession()) {

            return;
        }*/
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
        btn_share_fb = (Button) view.findViewById(R.id.btn_share_fb);
        btn_share_vk = (Button) view.findViewById(R.id.btn_share_vk);
        btn_login_vk = (Button) view.findViewById(R.id.sign_in_button);
        btn_share_vk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publishVK();
            }
        });
        btn_share_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publishFeedDialog();
            }
        });
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
        int random = (int) (Math.random() * 2);
        String url_ios = getActivity().getPreferences(Context.MODE_PRIVATE).getString(Constants.ARG_URL_IOS, "");
        String url_android = getActivity().getPreferences(Context.MODE_PRIVATE).getString(Constants.ARG_URL_ANDROID, "");

        String appstore = getString(R.string.link_appstore);
        String googleplay = getString(R.string.link_googleplay);
        String description = text[random]
                + (url_ios.isEmpty() ? "" : (appstore + url_ios))
                + (url_android.isEmpty() ? "" : (googleplay + url_android))
                + getString(R.string.link_site);
        return description;
    }


    private class SessionStatusCallback implements Session.StatusCallback {

        @Override
        public void call(Session session, SessionState state, Exception exception) {
            if (state.isOpened()) {
                Log.i(TAG, "Logged in...");
                writeFBUserToServer();
            } else if (state.isClosed()) {
                Log.i(TAG, "Logged out...");
            }
        }
    }



    protected class UserDataStatusCallback implements Session.StatusCallback {

        @Override
        public void call(final Session session, SessionState state, Exception exception) {
            if (exception != null) {
                // Crashlytics.logException(exception);
            }

            if (state.isOpened()) {
                Request.newMeRequest(session, new Request.GraphUserCallback() {
                    @Override
                    public void onCompleted(GraphUser user, Response response) {
                // данные юзера + правильный ответ
                    }
                }).executeAsync();
            }
        }
    }


    private boolean writeFBUserToServer() {
        Session session = Session.getActiveSession();
        UserDataStatusCallback dataStatusCallback = new UserDataStatusCallback();
        if (session != null && !session.isOpened() && !session.isClosed()) {
            session.openForRead(new Session.OpenRequest(this)
                    .setPermissions(Arrays.asList("public_profile", "email"))
                    .setCallback(dataStatusCallback));
        } else {
            Session.openActiveSession(getActivity(), true,
                    Arrays.asList("public_profile", "email"), dataStatusCallback);
        }
        return false;
    }


    private boolean writeVKUserToServer() {
        return false;
    }


    private void publishVK() {
        VKAccessToken token = VKAccessToken.tokenFromSharedPreferences(getActivity(), vkTokenKey);
        if ((token == null) || token.isExpired()) {
            VKSdk.authorize(sMyScope, true, false);
            Toast.makeText(getActivity(), "Требуется авторизация. После нее повторите попытку публикации", Toast.LENGTH_LONG).show();
        } else {
            final Bitmap b = getPhoto();
            new VKShareDialog()
                    .setText(getDescription())
                    .setAttachmentImages(new VKUploadImage[] {
                            new VKUploadImage(b, VKImageParameters.jpgImage(0.5f))
                    })
                    .setShareDialogListener(new VKShareDialog.VKShareDialogListener() {
                        @Override
                        public void onVkShareComplete(int postId) {
                            b.recycle();
                        }


                        @Override
                        public void onVkShareCancel() {
                            b.recycle();
                        }
                    })
                    .show(getFragmentManager(), "VK_SHARE_DIALOG");
        }
    }


    private Bitmap getPhoto() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.share_img);
        return bitmap;
    }


   /* private void makePost(VKAttachments attachments, String message) {
        VKRequest post = VKApi.wall().post(VKParameters.from(VKApiConst.OWNER_ID, "-60479154", VKApiConst.ATTACHMENTS, attachments, VKApiConst.MESSAGE, message));
        post.setModelClass(VKWallPostResult.class);
        post.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                VKWallPostResult result = (VKWallPostResult) response.parsedModel;
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("https://vk.com/wall-60479154_%s", result.post_id)));
                startActivity(i);
            }


            @Override
            public void onError(VKError error) {
                showError(error.apiError != null ? error.apiError : error);
            }
        });
    }*/


    private void showError(VKError error) {
        new AlertDialog.Builder(getActivity())
                .setMessage(error.errorMessage)
                .setPositiveButton("OK", null)
                .show();

        if (error.httpError != null) {
            Log.w("Test", "Error in request or upload", error.httpError);
        }
    }


    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Log.i(TAG, "Logged in...");
            btn_share_fb.setVisibility(View.VISIBLE);

        } else if (state.isClosed()) {
            Log.i(TAG, "Logged out...");
            btn_share_fb.setVisibility(View.GONE);
        }
    }


    private void showLogout() {
        btn_login_vk.setText("Log out with VK");
        btn_share_vk.setVisibility(View.VISIBLE);

        btn_login_vk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VKSdk.logout();
                showLogin();
            }
        });
    }


    private void showLogin() {
        btn_login_vk.setText("Log in with VK");
        btn_share_vk.setVisibility(View.GONE);
        btn_login_vk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VKSdk.authorize(sMyScope, true, false);
            }
        });
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


        VKUIHelper.onResume(getActivity());
        VKSdk.initialize(sdkListener, getActivity().getString(R.string.vk_app_id), VKAccessToken.tokenFromSharedPreferences(getActivity(), vkTokenKey));
        if (VKSdk.isLoggedIn()) {
            showLogin();
        } else {
            showLogout();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
        VKUIHelper.onActivityResult(requestCode, resultCode, data);
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
        VKUIHelper.onDestroy(getActivity());
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }


    private final VKSdkListener sdkListener = new VKSdkListener() {
        @Override
        public void onCaptchaError(VKError captchaError) {
            new VKCaptchaDialog(captchaError).show();
        }


        @Override
        public void onTokenExpired(VKAccessToken expiredToken) {
            Log.e("token", expiredToken.toString());
            VKSdk.authorize(sMyScope, true, false);
        }


        @Override
        public void onAccessDenied(final VKError authorizationError) {
            Log.e("token", authorizationError.toString());
            new AlertDialog.Builder(VKUIHelper.getTopActivity())
                    .setMessage(authorizationError.toString())
                    .show();
        }


        @Override
        public void onReceiveNewToken(VKAccessToken newToken) {
            Log.e("token", newToken.toString());
            newToken.saveTokenToSharedPreferences(getActivity().getApplicationContext(), vkTokenKey);
        }


        @Override
        public void onAcceptUserToken(VKAccessToken token) {
            Log.e("token", token.toString());
            VKSdk.setAccessToken(token, true);
        }
    };
}