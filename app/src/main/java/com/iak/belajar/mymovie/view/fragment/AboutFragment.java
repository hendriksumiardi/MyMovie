package com.iak.belajar.mymovie.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iak.belajar.mymovie.R;
import com.iak.belajar.mymovie.utility.AppConstant;
import com.iak.belajar.mymovie.utility.CommonFunction;


/**
 * Created by alodokter-it on 10/05/17 -- AboutFragment.
 */

public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ImageView aboutAndroidKejar = (ImageView) view.findViewById(R.id.about_androidkejar);
        ImageView aboutGoogleDev = (ImageView) view.findViewById(R.id.about_googledev);

        CommonFunction.setImage(getContext(), AppConstant.ANDROID_KEJAR_IMAGE_URL, aboutAndroidKejar);
        CommonFunction.setImage(getContext(), AppConstant.GOOGLE_DEV_IMAGE_URL, aboutGoogleDev);
    }
}