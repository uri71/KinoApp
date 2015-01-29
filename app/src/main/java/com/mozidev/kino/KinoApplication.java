package com.mozidev.kino;

import android.app.Application;
import android.content.Context;

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
    private Context mContext;
    private List<Integer> mSmallShot;


    public KinoApplication(Context context) {
        mContext = context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mTeams = createTeamList();
        mShots = createShotList();
        mPhoto = createPhotoList();


    }


    public static KinoApplication getInstance(Context context) {
        if (instance == null) {
            instance = new KinoApplication(context);
        }
        return instance;
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
        List<String> captions = Arrays.asList(mContext.getResources().getStringArray(R.array.photo_caption));
        int[] photo = new int[] {
                R.raw.photo_1, R.raw.photo_2, R.raw.photo_3, R.raw.photo_4, R.raw.photo_5, R.raw.photo_6, R.raw.photo_7, R.raw.photo_9, R.raw.usa_1, R.raw.usa_2, R.raw.usa_3, R.raw.usa_4, R.raw.usa_5, R.raw.usa_6, R.raw.usa_7, R.raw.photo_12};
        List<Photo> list = new ArrayList<>();
        for (int i = 0; i < photo.length; i++) {
            if (captions.get(i) != null) {
                list.add(new Photo(photo[i], captions.get(i)));
            } else {
                list.add(new Photo(photo[i], " "));
            }
        }
        return list;
    }


    private List<Photo> createShotList() {
        int[] shot = new int[] {
                R.raw.poster_1, R.raw.poster_2,
                R.raw.poster_3, R.raw.shot_1, R.raw.shot_2,
                R.raw.shot_3, R.raw.shot_4,
                R.raw.shot_5, R.raw.shot_6,
                R.raw.shot_7, R.raw.shot_8,
                R.raw.shot_9, R.raw.shot_10,
                R.raw.shot_11, R.raw.shot_12,
                R.raw.shot_13, R.raw.shot_14,
                R.raw.shot_15, R.raw.shot_16, R.raw.shot_17, R.raw.shot_18};
        List<String> captions = new ArrayList<>(shot.length);
        captions.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.shot_caption)));

        List<Photo> list = new ArrayList<>();
        for (int i = 0; i < shot.length; i++) {
            if (captions.size() > i && captions.get(i) != null) {
                list.add(new Photo(shot[i], captions.get(i)));
            } else {
                list.add(new Photo(shot[i], " "));
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


    private List<Integer> createSmallShotList() {

        List<Integer> smallShot = Arrays.asList(R.drawable.small_shot_1, R.drawable.small_shot_2,
                R.drawable.small_shot_3, R.drawable.small_shot_4, R.drawable.small_shot_5,
                R.drawable.small_shot_6, R.drawable.small_shot_7, R.drawable.small_shot_8,
                R.drawable.small_shot_9, R.drawable.small_shot_10, R.drawable.small_shot_11,
                R.drawable.small_shot_12, R.drawable.small_shot_13, R.drawable.small_shot_14,
                R.drawable.small_shot_15, R.drawable.small_shot_16/*, R.drawable.small_shot_17_jpg,
                R.drawable.small_shot_18_jpg*/);
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


}
