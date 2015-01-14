package com.mozidev.kino;

import android.app.Application;
import android.content.Context;

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
    private static Context mContext;


    public KinoApplication() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mTeams = createTeamList();
    }


    public static KinoApplication getInstance(Context context) {
        if(instance == null) {
            instance = new KinoApplication();
            mContext = context;
        }
        return instance;
    }


    private List<Team> createTeamList() {
        List<Team> team = new ArrayList();

        List<Integer> images = Arrays.asList(R.drawable.team_1, R.drawable.team_2, R.drawable.team_3, R.drawable.team_3, R.drawable.team_3, R.drawable.team_6);
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
        if(mTeams == null)mTeams = createTeamList();
        return mTeams;
    }
}
