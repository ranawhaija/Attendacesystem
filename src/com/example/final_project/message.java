package com.example.final_project;

import android.content.Context;
import android.widget.Toast;

public class message {
	public void msg(String value, Context contxt)
	{
		Toast.makeText(contxt, value, Toast.LENGTH_SHORT).show();	
	}

		public void msg2(int value, Context contxt)
		{
			Toast.makeText(contxt, value, Toast.LENGTH_SHORT).show();	
		}
}
