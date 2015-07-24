package com.example.final_project;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Custum extends ArrayAdapter<String>{

	Context mcontext;
	String[] name1;
	int [] imageId;
	public Custum(Context context, String[] name ,int [] imageIdd) {
		super(context, R.layout.listrow,R.id.row_txt,name);
		// TODO Auto-generated constructor stub
		
		this.mcontext=context;
		this.name1=name;
		this.imageId=imageIdd;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row =inflater.inflate(R.layout.listrow,parent,false);
		TextView txt=(TextView) row.findViewById(R.id.row_txt);

	    String fontPath="color/A_Nefel_Adeti.ttf";
	    Typeface type=Typeface.createFromAsset(mcontext.getAssets(), fontPath);
	    txt.setTypeface(type);
	    
		txt.setText(name1[position]);
		ImageView img= (ImageView) row.findViewById(R.id.row_img);
		img.setImageResource(imageId[position]);
		
		
		return row;
	}

	
	

}
