package com.example.final_project;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class SimpleListViewActivity extends DialogFragment implements
OnItemClickListener {




   // String[] listitems = { "item01", "item02", "item03", "item04" };

    ListView mylist;
    ArrayList<String> listitems;
	//MainPage obj = new MainPage();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	   listitems=getArguments().getStringArrayList("cn");
		

	        View view = inflater.inflate(R.layout.activity_simple_list_view, null, false);
	        mylist = (ListView) view.findViewById(R.id.list);

	        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
	        return view;
	    }

	    @Override
	    public void onActivityCreated(Bundle savedInstanceState) {

	        super.onActivityCreated(savedInstanceState);

	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
	                android.R.layout.simple_list_item_1, listitems);

	        mylist.setAdapter(adapter);

	        mylist.setOnItemClickListener(this);

	    }

	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position,
	            long id) {

	    	Login.getInstance().mypos=position;
	    	Login.getInstance().flag=1;
	        dismiss();
	      //  Toast.makeText(getActivity(), listitems[position], Toast.LENGTH_SHORT)
	        //        .show();
	    }
		
		
		/*
		ArrayList<String> planetList = null;
		
		  ArrayList<String> planetList2 = null;
		Bundle extras = getIntent().getExtras();
	    if (extras != null) {
	    planetList = extras.getStringArrayList("cn"); //this worked
	    planetList2 = extras.getStringArrayList("cl"); //this worked
	    }
	    mainListView = (ListView) findViewById( R.id.mainListView );
	    listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, planetList);  
	    mainListView.setAdapter( listAdapter ); 
	    mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				listAdapter.getItem(position);
				System.out.println(listAdapter.getItemId(position));
				p=position;
				
			}
		});
	*/		  
	
	}
