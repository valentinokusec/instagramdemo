package com.valentino.instagram.adapters;

import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.valentino.instagram.R;
import com.valentino.instagram.imageview.DownloadImageTask;

public class MyGridListAdapter extends BaseAdapter {


	private ArrayList<String> imageThumbList;
	private ArrayList<String> userList;
    private ArrayList<String> nameImageList;
	private LayoutInflater inflater;

	private TextView textView;
	private TextView textView2;
	private ImageView ivPhoto;
    Context cx;
	public MyGridListAdapter(Context context, ArrayList<String> imageThumbList, ArrayList<String> userList, ArrayList<String> nameImageList) {
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.imageThumbList = imageThumbList;
		this.userList=userList;
        this.nameImageList=nameImageList;
        cx=context;
        Toast.makeText(cx, "2", Toast.LENGTH_SHORT).show();

    }

	@Override
	public int getCount() {
		return imageThumbList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = inflater.inflate(R.layout.media_list_inflater, null);

        ivPhoto = (ImageView) view.findViewById(R.id.ivImage);
		textView = (TextView) view.findViewById(R.id.textView);
		textView2 = (TextView) view.findViewById(R.id.textView2);

        DownloadImageTask downloadimage=new DownloadImageTask(ivPhoto);
        downloadimage.execute(imageThumbList.get(position));

		textView.setText(userList.get(position));
		textView2.setText(nameImageList.get(position));
		return view;
	}

}

