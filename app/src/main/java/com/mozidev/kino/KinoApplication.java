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

        List<String> images = Arrays.asList(mContext.getResources().getStringArray(R.array.actors_photo_url));
        List<String> names = Arrays.asList(mContext.getResources().getStringArray(R.array.team_names));
        List<String> lines = Arrays.asList(mContext.getResources().getStringArray(R.array.team_lines));
        List<String> urls = Arrays.asList(mContext.getResources().getStringArray(R.array.team_links));
        List<String> about = Arrays.asList(mContext.getResources().getStringArray(R.array.team_about));
        int[] filmografy = mContext.getResources().getIntArray(R.array.arrays_filmografy);
        List films = Arrays.asList(filmografy);
        List<String> rating = Arrays.asList(mContext.getResources().getStringArray(R.array.team_rating));

        for (int i = 0; i < names.size(); i++) {
            String[] film = mContext.getResources().getStringArray((int)films.get(i));
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
