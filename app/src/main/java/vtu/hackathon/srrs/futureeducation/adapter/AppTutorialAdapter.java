package vtu.hackathon.srrs.futureeducation.adapter;

import android.app.Activity;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import vtu.hackathon.srrs.futureeducation.R;

/**
 * Created by Sathish Mun on 04-07-2015.
 */
public class AppTutorialAdapter extends PagerAdapter {


    private Activity activity;
    private TypedArray appTutorialScreens;


    // initialize the adapter
    public AppTutorialAdapter(Activity activity, TypedArray array) {
        this.activity = activity;
        appTutorialScreens = array;
    }


    // count of the no of screens
    @Override
    public int getCount() {
        return appTutorialScreens.length();
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_images_app_tutorial, container, false);


        ImageView imgViewAppTutorial = (ImageView) view
                .findViewById(R.id.img_app_tutorial);

        //TODO set Image Drawable
        imgViewAppTutorial.setImageDrawable(appTutorialScreens.getDrawable(position));



//        final VideoView videoAppTut = (VideoView) view.findViewById(R.id.video_tutorial);
//
//        // set the path of the resource
//        videoAppTut.setVideoPath("android.resource://" + "in.labdesk.storewalksellerapp" + "/" + appTutorialScreens[position]);
//        //Video Loop
//        videoAppTut.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            public void onCompletion(MediaPlayer mp) {
//                videoAppTut.start(); //need to make transition seamless.
//            }
//        });
//        videoAppTut.requestFocus();
//        videoAppTut.start();


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}