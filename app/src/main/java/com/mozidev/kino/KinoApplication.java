package com.mozidev.kino;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import com.mozidev.kino.model.NewsItem;
import com.mozidev.kino.model.Photo;
import com.mozidev.kino.model.Team;
import com.norbsoft.typefacehelper.TypefaceCollection;
import com.norbsoft.typefacehelper.TypefaceHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by y.storchak on 14.01.15.
 */
public class KinoApplication extends Application {

    private static KinoApplication instance;
    private List<Team> mTeams;
    private List<Photo> mPhoto;
    private List<Photo> mShots;
    private List<Photo> mShortShots;
    private Context mContext;
    private List<Integer> mSmallShot;
    private List<Integer> photo;
    private List<Integer> shot;
    private List<Integer> shortShot;
    private TypefaceCollection mTypefaceCollection;


    public KinoApplication(Context context) {
        mContext = context;
        attachBaseContext(context);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mTeams = createTeamList();
        mShots = createShotList();
        mShortShots = createShotList();
        mPhoto = createPhotoList();
        photo = fillPhotoList();
        shot = fillShotList();
        shortShot = fillShortShotList();
        initTypeFace();
    }


    public void initTypeFace() {
        mTypefaceCollection = new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(),
                        "fonts/B52.ttf"))
                .create();
        TypefaceHelper.init(mTypefaceCollection);
    }


    public static KinoApplication getInstance(Context context) {
        if (instance == null) {
            instance = new KinoApplication(context);
        }
        return instance;
    }


    private List<Integer> fillPhotoList() {
        photo = Arrays.asList();
        return photo;
    }


    private List<Integer> fillShotList() {
        shot = Arrays.asList();
        return shot;
    }


    private List<Integer> fillShortShotList() {
        shortShot = Arrays.asList(
        );
        return shortShot;
    }


    private List<Team> createTeamList() {
        List<Team> team = new ArrayList();

        List<String> images = Arrays.asList(mContext.getResources().getStringArray(R.array.thumb_actors_photo_url));
        List<String> names = Arrays.asList(mContext.getResources().getStringArray(R.array.team_names));
        List<String> lines = Arrays.asList(mContext.getResources().getStringArray(R.array.team_lines));
        List<String> about = Arrays.asList(mContext.getResources().getStringArray(R.array.team_about));
        List<Integer> films = Arrays.asList(R.array.line_1, R.array.line_2, R.array.line_3, R.array.line_4, R.array.line_5, R.array.line_6, R.array.line_7, R.array.line_8, R.array.line_9, R.array.line_10, R.array.line_11, R.array.line_12, R.array.line_13, R.array.line_14);

        for (int i = 0; i < names.size(); i++) {
            String[] film = mContext.getResources().getStringArray(films.get(i));
            team.add(new Team(names.get(i), lines.get(i), about.get(i), images.get(i), film));
        }

        return team;

    }


    public List<Team> getTeamsList() {
        if (mTeams == null) {
            mTeams = createTeamList();
        }

        return mTeams;
    }


    private List<Photo> createPhotoList() {
        if (photo == null) {
            photo = fillPhotoList();
        }
        List<String> captions = Arrays.asList(mContext.getResources().getStringArray(R.array.photo_caption));

        List<Photo> list = new ArrayList<>();
        for (int i = 0; i < photo.size(); i++) {
            if (captions.get(i) != null) {
                list.add(new Photo(photo.get(i), captions.get(i)));
            } else {
                list.add(new Photo(photo.get(i), " "));
            }
        }
        return list;
    }


    private List<Photo> createShotList() {
        if (shot == null) {
            shot = fillShotList();
        }
        List<String> captions = new ArrayList<>(shot.size());
        captions.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.shot_caption)));

        List<Photo> list = new ArrayList<>();
        for (int i = 0; i < shot.size(); i++) {
            if (captions.size() > i && captions.get(i) != null) {
                list.add(new Photo(shot.get(i), captions.get(i)));
            } else {
                list.add(new Photo(shot.get(i), " "));
            }
        }
        return list;
    }


    private List<Photo> createShortShotList() {
        if (shortShot == null) {
            shortShot = fillShortShotList();
        }
        List<String> captions = new ArrayList<>(shot.size());
        captions.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.shot_caption)));

        List<Photo> list = new ArrayList<>();
        for (int i = 0; i < shortShot.size(); i++) {
            if (captions.size() > i && captions.get(i) != null) {
                list.add(new Photo(shortShot.get(i), captions.get(i)));
            } else {
                list.add(new Photo(shortShot.get(i), " "));
            }
        }
        return list;
    }


    public List<Photo> getShotList() {
        if (mShots == null) {
            mShots = createShotList();
        }
        return mShots;
    }


    public List<String[]> getListQuestion() {
        // int[] allQuestion = mContext.getResources().getIntArray(R.array.questions_set);
        int[] allQuestion = new int[] {
                R.array.question_1, R.array.question_2, R.array.question_3,
                R.array.question_4, R.array.question_5, R.array.question_6,
                R.array.question_7, R.array.question_8, R.array.question_9,
                R.array.question_10, R.array.question_11, R.array.question_12,
                R.array.question_13, R.array.question_14, R.array.question_15,
                R.array.question_16, R.array.question_17, R.array.question_18,
                R.array.question_19, R.array.question_20, R.array.question_21,
                R.array.question_22, R.array.question_23, R.array.question_24,
                R.array.question_25, R.array.question_26, R.array.question_27,
                R.array.question_28, R.array.question_29, R.array.question_30
        };

        List<String[]> set = new ArrayList();
        while (set.size() < 3) {
            int number = (int) (Math.random() * 30);
            String[] newSet = mContext.getResources().getStringArray(allQuestion[number]);
            if (!set.contains(newSet)) {
                set.add(newSet);
            }
        }
        return set;
    }


    public List<Photo> getShortShotList() {
        if (mShortShots == null) {
            mShortShots = createShortShotList();
        }
        return mShortShots;

    }


    private List<Integer> createSmallShotList() {

        List<Integer> smallShot = Arrays.asList();
        return smallShot;
    }


    public List<Integer> getSmallShotList() {
        if (mSmallShot == null) {
            mSmallShot = createSmallShotList();
        }
        return mSmallShot;
    }


    public List<Photo> getHistoryPhotoList() {
        if (mPhoto == null) {
            mPhoto = createPhotoList();
        }
        return mPhoto;
    }


    private List<Integer> getNewsPhotoList() {
        List<Integer> list = Arrays.asList(
        );
        return list;
    }


    private String[] getNews() {
        return mContext.getResources().getStringArray(R.array.news_title);
    }


    public List<NewsItem> getNewsItem() {

        List<NewsItem> list = new ArrayList();

        return list;
    }
}
