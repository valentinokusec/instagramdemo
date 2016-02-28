package com.valentino.instagram.data;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import com.valentino.instagram.activites.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

import static com.valentino.instagram.data.ApplicationData.ACCESS_TOKEN;

/**
 * Created by Tino on 28.2.2016..
 */



public class Instagram extends AsyncTask<Void, Void, JSONObject> {

    private ProgressDialog pd;

    private ArrayList<String> imageThumbList = new ArrayList<String>();
    private ArrayList<String> standardImageList = new ArrayList<String>();
    private ArrayList<String> userList = new ArrayList<String>();
    private ArrayList<String> nameImageList = new ArrayList<String>();


    private static final String 	TAG_DATA = "data";
    private static final String TAG_IMAGES = "images";
    private static final String TAG_THUMBNAIL = "thumbnail";
    private static final String TAG_URL = "url";
    private static final String 	TAG_STANDARD = "standard_resolution";
    private static final String 	TAG_USER = "user";
    private static final String 	TAG_USERNAME = "username";
    private static final String 	TAG_CAPTION = "caption";
    private static final String 	TAG_TEXT = "text";

    private MainActivity cx;

    public Instagram(ProgressDialog pd, MainActivity cx) {
        this.pd = pd;
        this.cx = cx;
        this.pd = ProgressDialog.show(cx, "", "Getting data from instagram...");
    }

@Override
    protected JSONObject doInBackground(Void... params) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = jsonParser
                .getJSONFromUrlByGet("https://api.instagram.com/v1/media/search?" +
                        "lat=" + ApplicationData.LAT +
                        "&lng=" + ApplicationData.LONG +
                        "&access_token=" + ApplicationData.ACCESS_TOKEN);
        return jsonObject;
    }

    protected void onPostExecute(JSONObject result) {
        JSONArray data = null;
        try {
            data = result.getJSONArray(TAG_DATA);

            for (int data_i = 0; data_i < data.length(); data_i++) {
                JSONObject data_obj = data.getJSONObject(data_i);

                JSONObject images_obj = null;

                images_obj = data_obj
                        .getJSONObject(TAG_IMAGES);

                JSONObject user_obj = data_obj
                        .getJSONObject(TAG_USER);


                JSONObject thumbnail_obj = images_obj
                        .getJSONObject(TAG_THUMBNAIL);
                JSONObject standard_resolution = images_obj
                        .getJSONObject(TAG_STANDARD);

                String str_name = "";
                try {
                    JSONObject caption_obj = data_obj
                            .getJSONObject(TAG_CAPTION);

                    str_name = caption_obj.getString(TAG_TEXT);
                } catch (Exception exception) {

                }
                String str_username = user_obj.getString(TAG_USERNAME);
                String str_url = thumbnail_obj.getString(TAG_URL);
                String standard_res_url = standard_resolution.getString(TAG_URL);
                ApplicationData.imageThumbList.add(str_url);
                ApplicationData.userList.add(str_username);
                ApplicationData.standardImageList.add(standard_res_url);
                ApplicationData.nameImageList.add(str_name);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cx.setImageGridAdapter();
        pd.dismiss();
    }
}