package com.valentino.instagram.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import com.valentino.instagram.R;
import com.valentino.instagram.imageview.DownloadImageTask;
import com.valentino.instagram.imageview.TouchImageView;

public class FullScreenActivity extends Activity {

    TouchImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        image = (TouchImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        DownloadImageTask downloadimage=new DownloadImageTask(image);
        downloadimage.execute(url);


    }
}
