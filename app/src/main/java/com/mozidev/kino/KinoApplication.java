package com.mozidev.kino;

import android.app.Application;
import android.content.Context;

import com.mozidev.kino.model.NewsItem;
import com.mozidev.kino.model.Photo;
import com.mozidev.kino.model.Team;

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


    public KinoApplication(Context context) {
        mContext = context;
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

    }


    public static KinoApplication getInstance(Context context) {
        if (instance == null) {
            instance = new KinoApplication(context);
        }
        return instance;
    }


    private List<Integer> fillPhotoList() {
        photo = Arrays.asList(R.raw.photo_1, R.raw.photo_2, R.raw.photo_3,
                R.raw.photo_4, R.raw.photo_5, R.raw.photo_6,
                R.raw.photo_7, R.raw.photo_9, R.raw.usa_1,
                R.raw.usa_2, R.raw.usa_3, R.raw.usa_4,
                R.raw.usa_5, R.raw.usa_6, R.raw.usa_7, R.raw.usa_8, R.raw.photo_12, R.raw.photo_13);
        return photo;
    }


    private List<Integer> fillShotList() {
        shot = Arrays.asList(R.raw.poster_1, R.raw.poster_2,
                R.raw.poster_3, R.raw.shot_1, R.raw.shot_2,
                R.raw.shot_3, R.raw.shot_4,
                R.raw.shot_5, R.raw.shot_6,
                R.raw.shot_7, R.raw.shot_8,
                R.raw.shot_9, R.raw.shot_10,
                R.raw.shot_11, R.raw.shot_12,
                R.raw.shot_13, R.raw.shot_14,
                R.raw.shot_15, R.raw.shot_16,
                R.raw.shot_17, R.raw.shot_18);
        return shot;
    }

    private List<Integer> fillShortShotList() {
        shortShot = Arrays.asList(R.raw.poster_1, R.raw.poster_2,
                R.raw.poster_3, R.raw.shot_1, R.raw.shot_2,
                R.raw.shot_3, R.raw.shot_4,
                R.raw.shot_5, R.raw.shot_6,
                R.raw.shot_7, R.raw.shot_8,
                R.raw.shot_9, R.raw.shot_10,
                R.raw.shot_11, R.raw.shot_12,
                R.raw.shot_13, R.raw.shot_14,
                R.raw.shot_15, R.raw.shot_16);
        return shortShot;
    }


    private List<Team> createTeamList() {
        List<Team> team = new ArrayList();

        List<Integer> images = Arrays.asList(R.drawable.team_1, R.drawable.team_2, R.drawable.team_3, R.drawable.team_7, R.drawable.team_5, R.drawable.team_6);
        List<String> names = Arrays.asList(mContext.getResources().getStringArray(R.array.team_names));
        List<String> lines = Arrays.asList(mContext.getResources().getStringArray(R.array.team_lines));
        List<String> urls = Arrays.asList(mContext.getResources().getStringArray(R.array.team_links));
        List<String> about = Arrays.asList(mContext.getResources().getStringArray(R.array.team_about));
        List<Integer> films = Arrays.asList(R.array.line_1, R.array.line_2, R.array.line_3, R.array.line_4, R.array.line_5, R.array.line_6);
        List<String> rating = Arrays.asList(mContext.getResources().getStringArray(R.array.team_rating));

        for (int i = 0; i < names.size(); i++) {
            String[] film = mContext.getResources().getStringArray(films.get(i));
            team.add(new Team(names.get(i), lines.get(i), urls.get(i), about.get(i), rating.get(i), images.get(i), film));
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
        if (shot == null) shot = fillShotList();
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
        if (shortShot == null)
            shortShot = fillShortShotList();
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

    public List<Photo> getShortShotList() {
        if (mShortShots == null) {
            mShortShots = createShortShotList();
        }
        return mShortShots;

    }


    private List<Integer> createSmallShotList() {

        List<Integer> smallShot = Arrays.asList(R.drawable.small_shot_1, R.drawable.small_shot_2,
                R.drawable.small_shot_3, R.drawable.small_shot_4, R.drawable.small_shot_5,
                R.drawable.small_shot_6, R.drawable.small_shot_7, R.drawable.small_shot_8,
                R.drawable.small_shot_9, R.drawable.small_shot_10, R.drawable.small_shot_11,
                R.drawable.small_shot_12, R.drawable.small_shot_13, R.drawable.small_shot_14,
                R.drawable.small_shot_15, R.drawable.small_shot_16, R.drawable.small_shot_17,
                R.drawable.small_shot_18);
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
                R.raw.photo_1, R.raw.photo_2, R.raw.photo_3,
                R.raw.photo_4, R.raw.photo_5, R.raw.photo_6,
                R.raw.photo_7, R.raw.photo_9, R.raw.usa_1,
                R.raw.usa_2, R.raw.usa_3, R.raw.usa_4,
                R.raw.usa_5, R.raw.usa_6, R.raw.usa_7,
                R.raw.photo_12, R.raw.poster_1, R.raw.poster_2,
                R.raw.poster_3, R.raw.shot_1, R.raw.shot_2,
                R.raw.shot_3, R.raw.shot_4, R.raw.shot_5,
                R.raw.shot_6, R.raw.shot_7, R.raw.shot_8,
                R.raw.shot_9, R.raw.shot_10, R.raw.shot_11,
                R.raw.shot_12, R.raw.shot_13, R.raw.shot_14,
                R.raw.shot_15, R.raw.shot_16);
        return list;
    }


    private String[] getNews() {
        return mContext.getResources().getStringArray(R.array.news_title);
    }


    public List<NewsItem> getNewsItem() {
        String[] title = getNews();
        List<Integer> images = getNewsPhotoList();
        List<NewsItem> list = new ArrayList();
        for (int i = 0; i < title.length; i++) {
            int img1 = i * 3;
            int img2 = i * 3 + 1;
            int img3 = i * 3 + 2;
            list.add(new NewsItem(images.get(img1), images.get(img2), images.get(img3), title[i]));
        }
        return list;
    }
}
