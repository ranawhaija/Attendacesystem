
package com.example.final_project;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;
public class Export {
	
	 private static final String DNAME="Students Information" ;
	 
	
	public void  ExportFun(Context context, final int cid)
	{
		 DBAdapter db = new DBAdapter(context);
	     db.open();
	     List values = db.getsinglename(cid);
	     List ids= db.getStudents__ID(cid);
	     
	     
	     String names[]=(String[]) values.toArray(new String [values.size()]);
	     String IDs[]=(String[]) ids.toArray(new String [ids.size()]);
	     
	     final String FILENAME = db.getCourseNS(cid)+".csv";
	     String nabs;
	     String nabs2;
	     
		File rootPath = new File(Environment.getExternalStorageDirectory(), DNAME);
        if(!rootPath.exists()) {
            rootPath.mkdirs();
        }
        File dataFile = new File(rootPath, FILENAME);
        Toast.makeText(context,  rootPath+"", Toast.LENGTH_SHORT).show();
        
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(context, "Cannot use storage.", Toast.LENGTH_SHORT).show();
            //finish();
            return;
        }
        try {           
            FileOutputStream mOutput = new FileOutputStream(dataFile, false);
            String data = "Name,Id,No. of Absent days without permission,No. of Absent days permission\n";
            mOutput.write(data.getBytes());
            for (int i=0;i<values.size();i++)
            {
            	nabs=Integer.toString(db.getStudentDaysAbsents(cid,IDs[i]));
            	nabs2=Integer.toString(db.getStudentDaysAbsents2(cid, IDs[i]));
            	
            //	Toast.makeText(context, IDs[i]+"", Toast.LENGTH_SHORT).show();
            	//Toast.makeText(context, nabs+""+nabs2, Toast.LENGTH_SHORT).show();
            	//Toast.makeText(context, nabs2+"", Toast.LENGTH_SHORT).show();
            	
            	mOutput.write(names[i].toString().getBytes());
            	mOutput.write(",".getBytes());
            	mOutput.write(IDs[i].toString().getBytes());
            	mOutput.write(",".getBytes());
            	//mOutput.write(nabs);
            	mOutput.write(nabs.getBytes());
            	mOutput.write(",".getBytes());
            	//mOutput.write(nabs2);
            	mOutput.write(nabs2.getBytes());
            	mOutput.write(",".getBytes());
            	mOutput.write("\n".getBytes());
            	
            }
       
            mOutput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        db.close();
	    
	}
}