package com.example.final_project;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Edit_name extends Activity {

	String sname;
	int coureIndex;
	String sn;
	String Dname;
	EditText tnewName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_name);
		
		
		
		TextView tname=(TextView)findViewById(R.id.textView1);
		tnewName =(EditText)findViewById(R.id.editText1);
		Button done=(Button)findViewById(R.id.button1);
		Button back=(Button)findViewById(R.id.button2);
		
		
		 Intent i4=getIntent();
		 coureIndex=i4.getIntExtra("coursrindex", 0);
		 sname=i4.getStringExtra("Sname");
		 sn=i4.getStringExtra("SN");
		 
		 
		 tname.setText(sname);
		 //tnewName.setBackgroundColor(Color.GREEN);
		 tnewName.setText(sname);
		 
			Toast.makeText(getBaseContext(), sn, Toast.LENGTH_SHORT).show();

		 done.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
                  	  openAlert2();


				}
			});
		 
		 
		 back.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
             	Intent i =new Intent(Edit_name.this,InformationOfAbsent.class);
				 i.putExtra("coursrid", coureIndex);

             startActivity(i);


				}
			});
		 
		 
		 
		 
		 
		 
		 
		 
		
	}
	
//////////////////////////

private void openAlert2() { 

AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Edit_name.this);

alertDialogBuilder.setTitle("Edit Student Name"); 
alertDialogBuilder.setMessage(Html.fromHtml("<b>Are you Sure you want To Egit this Student Name?  </b>"));
alertDialogBuilder.setNegativeButton("Confirm ",new DialogInterface.OnClickListener() { 
public void onClick(DialogInterface dialog,int id)

{ 
	Dname=tnewName.getText().toString();
	
	DBAdapter db = new DBAdapter(getBaseContext());
    db.open();
		db.EditStudentName(coureIndex, Dname,sn);					 
      
	db.close();
	Intent i =new Intent(Edit_name.this,InformationOfAbsent.class);
	 i.putExtra("coursrid", coureIndex);

startActivity(i);
 
} 

}); 


alertDialogBuilder.setNeutralButton("Cancel",new DialogInterface.OnClickListener() { 
public void onClick(DialogInterface dialog,int id) {


} 

});   

AlertDialog alertDialog = alertDialogBuilder.create(); 

alertDialog.show(); 
} 



}
