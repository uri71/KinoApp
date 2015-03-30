package com.mozidev.kino.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
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
import com.norbsoft.typefacehelper.TypefaceHelper;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKUsersArray;
import com.vk.sdk.api.photo.VKImageParameters;
import com.vk.sdk.api.photo.VKUploadImage;
import com.vk.sdk.dialogs.VKCaptchaDialog;
import com.vk.sdk.dialogs.VKShareDialog;
import com.vk.sdk.util.VKUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by y.storchak on 20.03.15.
 */
public class ShareActivity extends BaseActivity {

	private UiLifecycleHelper uiHelper;
	//	private Button btn_login_vk;
	private static String vkTokenKey = "VK_ACCESS_TOKEN";

	private static final String TAG = "ShareFragment";
//	private LinearLayout btn_container;
	private static final String[] sMyScope = new String[]{
			VKScope.WALL,
			VKScope.NOHTTPS,
			VKScope.PHOTOS
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
				Session.openActiveSession(this, true, statusCallback);
			}
		}
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_share);

//		LoginButton authButton = (LoginButton) findViewById(R.id.authButton);
//		authButton.setReadPermissions(Arrays.asList("public_profile"));
//		authButton.setFragment(this);
		ImageButton btn_share_fb = (ImageButton) findViewById(R.id.btn_share_fb);
		ImageButton btn_share_vk = (ImageButton) findViewById(R.id.btn_share_vk);
//		btn_login_vk = (Button) findViewById(R.id.sign_in_button);
		btn_share_vk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				publishVK();
			}
		});
		btn_share_fb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				publishFB();
			}
		});
		TypefaceHelper.typeface(this);

		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);
		this.setTitle("Реєстрація");

		VKUIHelper.onCreate(this);
		VKSdk.initialize(sdkListener, this.getString(R.string.vk_app_id));
		if (VKSdk.wakeUpSession()) {
//			VKSdk.authorize(sMyScope);
			return;
		}

		String[] fingerprint = VKUtil.getCertificateFingerprint(this, this.getPackageName());
		Log.d("Fingerprint", fingerprint[0]);

//        try {
//            PackageInfo info = this.getPackageManager().getPackageInfo(
//                    "com.mozidev.kino", PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }
	  /*  if (VKSdk.wakeUpSession()) {
			return;
        }*/
	}


//	@Override
//	public View onCreateView(LayoutInflater inflater,
//							 @Nullable
//							 ViewGroup container,
//							 @Nullable
//							 Bundle savedInstanceState) {
//		return inflater.inflate(R.layout.fragment_share, container, false);
//	}


	private String getShareImageUrl() {
		String url = this.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE)
				.getString(Constants.PREFS_DOWNLOAD_IMAGE_URL, getString(R.string.url_sharing));
		return url;
	}


	// фотка для шаринга в ВК
	private Bitmap getPhoto() {
		String pathName = this.getFilesDir().getPath() + Constants.IMAGE_PATH;
		Bitmap bitmap = BitmapFactory.decodeFile(pathName);
		if (bitmap == null) {
			Log.d(TAG, "bitmap == null");
			bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.poster_2);
		}
		return bitmap;
	}


	private String getDescription() {
		String description = this.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE).getString(Constants.PREFS_DOWNLOAD_DESCRIPTION, "");
		if (description.isEmpty()) {
			Log.d(TAG, "description isEmpty");
			description = getErrorDescription();
		} else {
			Log.d(TAG, description);
		}
		return description;
	}


	// если по каким-то причинам с сервера мы не получили описание для шаринга - используем дефолтное
	private String getErrorDescription() {
		String[] text = getResources().getStringArray(R.array.descriptions);
		int random = (int) (Math.random() * 2);
		String description = text[random] + getString(R.string.link_site);
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
				Log.i(TAG, exception.toString());
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
       /* Session session = Session.getActiveSession();
        UserDataStatusCallback dataStatusCallback = new UserDataStatusCallback();
        if (session != null && !session.isOpened() && !session.isClosed()) {
            session.openForRead(new Session.OpenRequest(this)
                    .setPermissions(Arrays.asList("public_profile", "email"))
                    .setCallback(dataStatusCallback));
        } else {
            Session.openActiveSession(this, true,
                    Arrays.asList("public_profile", "email"), dataStatusCallback);
        }*/

		return false;
	}


	private boolean writeUserToServer(Map<String, Object> params) {

		AQuery aq = new AQuery(this);
		String url = Constants.URL_BASE + Constants.URL_MEMBERS;

		//first_name, last_name, email, phone, is_correct_answer, contacts, fb_id, vk_id, ios_device_token, android_device_token

		aq.ajax(url, params, JSONObject.class, new AjaxCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				Log.d(TAG, "SUCCESS send data:" + status.getCode());
				publishVK();
			}
		});
		return false;
	}


	//получаем данные юзера
	private void getVKUserData() {
		VKRequest request = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS,
				"id,first_name,last_name" +
						"online_mobile,lists,domain,has_mobile,contacts,connections,site,education,"));
//		VKRequest request = VKApi.users().get();

//  не знаю USER_IDS - тот ли это id что и в предыдущем запросе
//                  VKRequest request = VKApi.users().get(VKParameters.from(VKApiConst.USER_IDS, "1,2"));
//        request.secure = false;
//        request.useSystemLanguage = false;
//        request.setRequestListener(mRequestListener);
		request.executeWithListener(mRequestListener);
	}


	private void publishFB() {
		Session session = Session.getActiveSession();
		if (session == null || !session.isOpened()) {
//			pendingPublishReauthorization = true;
			Session.openActiveSession(this, true, new Session.StatusCallback() {
				@Override
				public void call(Session session, SessionState state, Exception exception) {
					if (state.isOpened()) {
						Request.newMeRequest(session, new Request.GraphUserCallback() {
							@Override
							public void onCompleted(GraphUser user, Response response) {
								writeUserToServer(getFBMap(user));
							}
						}).executeAsync();
						publishFB();
					}
				}
			});
			return;
		}

		String name = "Акція «Дивись рідне!»\n";
		String image_url = getShareImageUrl();
		String description = getDescription();

		Bundle params = new Bundle();
		params.putString("name", name);
		//params.putString("caption", "Build great social apps and get more installs.");
		params.putString("description", description);
		//params.putString("link", "https://developers.facebook.com/android");
		params.putString("picture", image_url);

		WebDialog feedDialog = (
				new WebDialog.FeedDialogBuilder(this,
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
								Toast.makeText(ShareActivity.this,
										"Posted story, id: " + postId,
										Toast.LENGTH_SHORT).show();
							} else {
								// User clicked the Cancel button
								Toast.makeText(getApplicationContext(),
										"Publish cancelled",
										Toast.LENGTH_SHORT).show();
							}
						} else if (error instanceof FacebookOperationCanceledException) {
							// User clicked the "x" button
							Toast.makeText(getApplicationContext(),
									"Publish cancelled",
									Toast.LENGTH_SHORT).show();
						} else {
							// Generic, ex: network error
							Toast.makeText(getApplicationContext(),
									"Error posting story",
									Toast.LENGTH_SHORT).show();
						}
					}

				})
				.build();
		feedDialog.show();
	}


	private void publishVK() {
		VKAccessToken token = VKAccessToken.tokenFromSharedPreferences(this, vkTokenKey);
		if ((token == null) || token.isExpired()) {
			VKSdk.authorize(sMyScope, true, false);
//            Toast.makeText(this, "Требуется авторизация. После нее повторите попытку публикации", Toast.LENGTH_LONG).show();
		} else {
			final Bitmap b = getPhoto();
			new VKShareDialog()
					.setText(getDescription())
					.setAttachmentImages(new VKUploadImage[]{
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
					.show(getSupportFragmentManager(), "VK_SHARE_DIALOG");
		}
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
		new AlertDialog.Builder(this)
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
//			btn_share_fb.setVisibility(View.VISIBLE);

		} else if (state.isClosed()) {
			Log.i(TAG, "Logged out...");
//			btn_share_fb.setVisibility(View.GONE);
		}
	}


	private void showLogout() {
//		btn_login_vk.setText("Log out with VK");
//		btn_share_vk.setVisibility(View.VISIBLE);

//		btn_login_vk.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				VKSdk.logout();
//				showLogin();
//			}
//		});
	}


	private void showLogin() {
//		btn_login_vk.setText("Log in with VK");
//		btn_share_vk.setVisibility(View.GONE);
//		btn_login_vk.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				VKSdk.authorize(sMyScope, true, false);
//			}
//		});
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

		VKUIHelper.onResume(this);
//        VKSdk.initialize(sdkListener, this.getString(R.string.vk_app_id), VKAccessToken.tokenFromSharedPreferences(this, vkTokenKey));
		if (VKSdk.isLoggedIn()) {
			showLogin();
		} else {
			showLogout();
		}
	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
		VKUIHelper.onActivityResult(this, requestCode, resultCode, data);
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
		VKUIHelper.onDestroy(this);
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
			VKSdk.authorize(sMyScope);
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
			newToken.saveTokenToSharedPreferences(getApplicationContext(), vkTokenKey);
			//получаем данные по пользователю ВК для отправки на сервер
			getVKUserData();
		}


		@Override
		public void onAcceptUserToken(VKAccessToken token) {
			Log.e("token", token.toString());
			//VKSdk.setAccessToken(token, true);
			getVKUserData();
		}
	};


	VKRequest.VKRequestListener mRequestListener = new VKRequest.VKRequestListener() {
		@Override
		public void onComplete(VKResponse response) {
			// отправляем данные пользователя ВК на сервер
			Map<String, Object> params = getVKMap(response);

			writeUserToServer(params);
		}


		@Override
		public void onError(VKError error) {
			Log.d(TAG, error.toString());
		}


		@Override
		public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded,
							   long bytesTotal) {
			// you can show progress of the request if you want
		}


		@Override
		public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
			Log.d(TAG, String.format("Attempt %d/%d failed\n", attemptNumber, totalAttempts));
		}
	};

	private Map<String, Object> getFBMap(GraphUser user) {
		boolean isWin = ShareActivity.this.getPreferences(Context.MODE_PRIVATE).getBoolean(Constants.ARG_PREFERENCES_WIN, false);
		Map<String, Object> params = new HashMap<String, Object>();
		String android_token = ShareActivity.this.getSharedPreferences(MainActivity.class.getSimpleName(),
				Context.MODE_PRIVATE).getString(Constants.PROPERTY_REG_ID, "");

		String name = user.getFirstName() + " " + user.getLastName();
		params.put("first_name", user.getFirstName());
		params.put("last_name", user.getLastName());
		params.put("name", name);
		params.put("is_correct_answer", isWin);
		params.put("fb_id", user.getId());
		params.put("vk_id", "");
		params.put("android_device_token", android_token);
		return params;
	}


	// создаем VK - Map для отправки на сервер
	private Map<String, Object> getVKMap(VKResponse response) {
		boolean isWin = this.getPreferences(Context.MODE_PRIVATE).getBoolean(Constants.ARG_PREFERENCES_WIN, false);
		Map<String, Object> params = new HashMap<String, Object>();
		String android_token = this.getSharedPreferences(MainActivity.class.getSimpleName(),
				Context.MODE_PRIVATE).getString(Constants.PROPERTY_REG_ID, "");
		VKUsersArray data = new VKUsersArray();
		try {
			data.parse(response.json);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		VKApiUserFull user = data.get(0);
		String name = user.first_name + " " + user.last_name;
		params.put("first_name", user.first_name);
		params.put("last_name", user.last_name);
		params.put("name", name);
		params.put("is_correct_answer", isWin);
//			params.put("contacts", user.);
		params.put("fb_id", user.facebook);
		params.put("vk_id", user.id);
		params.put("android_device_token", android_token);

		return params;
	}
}
