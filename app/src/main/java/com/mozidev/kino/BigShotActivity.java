package com.mozidev.kino;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.Arrays;
import java.util.List;


public class BigShotActivity extends ActionBarActivity {

    private List<Integer> bigShot;
    private SliderLayout slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_big_shot);

        bigShot = Arrays.asList(R.raw.shot_1, R.raw.shot_2,
                R.raw.shot_3, R.raw.shot_4,
                R.raw.shot_5, R.raw.shot_6,
                R.raw.shot_7, R.raw.shot_8,
                R.raw.shot_9, R.raw.shot_10,
                R.raw.shot_11, R.raw.shot_12,
                R.raw.shot_13, R.raw.shot_14,
                R.raw.shot_15, R.raw.shot_16);

         slider = (SliderLayout) findViewById(R.id.slider);

        for (int shot : bigShot) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.image(shot)
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            slider.addSlider(textSliderView);
        }

        int position = getIntent().getIntExtra(Constants.ARG_SHOT_NUMBER, 0);
        slider.setCurrentPosition(position);
        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.stopAutoCycle();
    }


    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().hide();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_big_shot, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        SliderLayout.Transformer transformer;

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.animation_accordion: transformer = SliderLayout.Transformer.Accordion;break;
            case R.id.animation_background2foreground: transformer = SliderLayout.Transformer.Background2Foreground;break;
            case R.id.animation_CubeIn: transformer = SliderLayout.Transformer.CubeIn;break;
            case R.id.animation_default: transformer = SliderLayout.Transformer.Default;break;
            case R.id.animation_depth_page: transformer = SliderLayout.Transformer.DepthPage;break;
            case R.id.animation_Fade: transformer = SliderLayout.Transformer.Fade;break;
            case R.id.animation_flip_horizontal: transformer = SliderLayout.Transformer.FlipHorizontal;break;
            case R.id.animation_flip_page: transformer = SliderLayout.Transformer.FlipPage;break;
            case R.id.animation_foreground2background: transformer = SliderLayout.Transformer.Foreground2Background;break;
            case R.id.animation_rotate_down: transformer = SliderLayout.Transformer.RotateDown;break;
            case R.id.animation_rotate_up: transformer = SliderLayout.Transformer.RotateUp;break;
            case R.id.animation_stack: transformer = SliderLayout.Transformer.Stack;break;
            case R.id.animation_tablet: transformer = SliderLayout.Transformer.Tablet;break;
            case R.id.animation_zoom_in: transformer = SliderLayout.Transformer.ZoomIn;break;
            case R.id.animation_zoom_out: transformer = SliderLayout.Transformer.ZoomOut;break;
            case R.id.animation_zoom_out_slide: transformer = SliderLayout.Transformer.ZoomOutSlide;break;
            default:return false;
        }
        slider.setPresetTransformer(transformer);


        return true;
    }
}
