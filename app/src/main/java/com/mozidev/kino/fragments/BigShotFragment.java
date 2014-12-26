package com.mozidev.kino.fragments;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.mozidev.kino.Constants;
import com.mozidev.kino.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by y.storchak on 22.12.14.
 */
public class BigShotFragment extends Fragment {

    private List<Integer> bigShot;


    public BigShotFragment() {
        super();
        bigShot = Arrays.asList(R.raw.shot_1, R.raw.shot_2,
                R.raw.shot_3, R.raw.shot_4,
                R.raw.shot_5, R.raw.shot_6,
                R.raw.shot_7, R.raw.shot_8,
                R.raw.shot_9, R.raw.shot_10,
                R.raw.shot_11, R.raw.shot_12,
                R.raw.shot_13, R.raw.shot_14,
                R.raw.shot_15, R.raw.shot_16);
    }


    public static BigShotFragment newInstance(int number) {
        BigShotFragment fragment = new BigShotFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SHOT_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable
                             ViewGroup container,
                             @Nullable
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_big_shot, container, false);
        SliderLayout slider = (SliderLayout) view.findViewById(R.id.slider);
        for(int shot: bigShot){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView.image(shot)
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);

            slider.addSlider(textSliderView);
        }
        slider.setCurrentPosition(getArguments().getInt(Constants.ARG_SHOT_NUMBER));
        slider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.stopAutoCycle();
        slider.setDuration(50000);
        return view;
    }


    @Override
    public void onViewCreated(View view,
                              @Nullable
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onStop() {
        super.onStop();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
