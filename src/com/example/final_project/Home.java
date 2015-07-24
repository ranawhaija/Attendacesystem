package com.example.final_project;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		
		ActionBar actionbar =getActionBar();
	
		actionbar.hide();
		int nabs;
//DBAdapter db = new DBAdapter(this);
	
	//db.open();
	//nabs=db.getStudentDaysAbsents(2,"2009873064");
    //	Toast.makeText(this, nabs+"", Toast.LENGTH_SHORT).show();

//db.del3();
	//db.del2();
	//db.del();
  //  db.close();
	
    //   long iddd=db.insert_studentinfo("2011980064", "485435", 7);
        //db.close();

		//db.open();
    //  long iddd=db.insert_studentinfo("2011980064", "485435", 7);

        
		Button mangebtn;
		Button mangeTakeAbsence;
		
		mangebtn=(Button)findViewById(R.id.button1);
		mangeTakeAbsence=(Button)findViewById(R.id.button2);

		
		mangebtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  Intent i2 =new Intent(Home.this,Mange_course.class);
					startActivity(i2);
			}
		});
		
		

		mangeTakeAbsence.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  Intent i =new Intent(Home.this,TakeAbsenceee.class);
					startActivity(i);
			}
		});
		
		
	}
	
	
	

}
