package com.valentino.instagram.activites;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.valentino.instagram.R;
import com.valentino.instagram.data.ApplicationData;
import com.valentino.instagram.data.Instagram;
import com.valentino.instagram.data.JSONParser;
import com.valentino.instagram.adapters.MyGridListAdapter;


import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends Activity {


        ProgressDialog pd;
	    private GridView gvAllImages;



@Override
public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);


        Instagram instagram=new Instagram(pd,this);
        instagram.execute();

        gvAllImages=(GridView)findViewById(R.id.gridView);
        gvAllImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent itent = new Intent(MainActivity.this, FullScreenActivity.class);
                itent.putExtra("url", ApplicationData.standardImageList.get(i));
                MainActivity.this.startActivity(itent);
            }
        });

	}
     public  void setImageGridAdapter() {
        gvAllImages.setAdapter(new MyGridListAdapter(MainActivity.this,ApplicationData.imageThumbList,ApplicationData.userList,ApplicationData.nameImageList));
    }

}
