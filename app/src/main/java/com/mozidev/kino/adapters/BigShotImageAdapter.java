package com.mozidev.kino.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.Utils;

import java.util.List;

/**
 * Created by y.storchak on 15.01.15.
 */
public class BigShotImageAdapter extends PagerAdapter {

    private List<Integer> shots;
    private Context mContext;


    public BigShotImageAdapter(Context context, List<Integer> shots) {
        this.shots = shots;
        mContext = context;
    }


    @Override
    public int getCount() {
        return shots.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public View instantiateItem(ViewGroup container, int position) {
        // ImageView img = (ImageView)LayoutInflater.from(container.getContext()).inflate(R.layout.item_big_shot, container, false);
        ImageView img = new ImageView(mContext);
        int px = (int) Utils.convertDpToPixel(240, mContext);
        img.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, px, Gravity.CENTER));
        container.addView(img);
        Bitmap bitmap = getBitmap(shots.get(position));
        img.setImageBitmap(bitmap);
        return img;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((BitmapDrawable) ((ImageView) object).getDrawable()).getBitmap().recycle();
        container.removeView((ImageView) object);
    }


    private Bitmap getBitmap(int shot) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(mContext.getResources(), shot, options);
        int with = options.outWidth;
        int height = options.outHeight;
        Log.e("BigShotActivity ", " with -" + with + " height - " + height);
        Bitmap bitmap;
        if (with > Constants.IMAGE_MAX_SIZE || height > Constants.IMAGE_MAX_SIZE) {
            int ratio = (int) Math.ceil((with > height ? with / Constants.IMAGE_MAX_SIZE : height / Constants.IMAGE_MAX_SIZE));
            options.inSampleSize = ratio;
        } else {
            options.inScaled = false;
        }
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), shot, options);
        return bitmap;
    }
}
