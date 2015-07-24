package com.example.final_project;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;



public class DBAdapter 
{
	
		///////Basic elements in any Data base.
        public static final String ID = "__ID";
    	private static final String DATABASE_NAME = "Course.db";
        public static final String Course_Details_table_Name = "course_details";
   	    private static final int DATABASE_VERSION = 4020;
   	    static final String TAG = "DBAdapter";
   	    
   	    ////////coloms name for table 1
	    public static final String Course_NAME = "name";
		public static final String Course_Symbole = "Course_Symbol";
	    public static final String Year = "year";
	    public static final String Semester = "semester";
	    public static final String Section = "section";
	    public static final String Start_Date = "start_Date";
	    public static final String End_Date = "end_date";
	    public static final String Days = "days";
	    public static final String _ID = "_id";
	    
	    //////coloms name for table 2
	    public static final String student_table = "Student4";
	    public static final String serail_Number = "SN";
	    public static final String Student_ID = "ID";
	    public static final String Student_Name = "Student_Name";
	    public static final String courseid = "course_ID";
	    
	    ////// colom name for table 3 
	    public static final String studentinfo = "Studentinfo1";
	    public static final String DDate = "datee";
	    public static final String dateFlage = "dateflag";

	    public static final String SID = "sid";
	    public static final String Indexx = "indexx";

	    public static String[] studentinfo_TABLE_COLUMNS = { 
	    	DDate,
	    	SID,
	    	dateFlage,
	    	Indexx
    		
    };
	    
	    
	    public static String[] Course_TABLE_COLUMNS = { 
	    		Course_NAME,
	    		Course_Symbole,
	    		Year,
	    		Semester,  
	    		Section,
	    		Start_Date,
	    		End_Date,
	    		Days ,
	    		_ID
	    };
	    
	    public static String[] Student_TABLE_COLUMNS = { 
	    		serail_Number,
	    		Student_ID,
	    		Student_Name,
	    		courseid
	    };
	 

	  
	    //////Query for create table 1
	    private static final String Course_details_table_create = "create table " +Course_Details_table_Name    + "(" +
	            Course_NAME + " text not null, "
	            + Course_Symbole + " text not null, "
	            + Year + " integer not null, "
	            + Semester + " text not null, "
	            + Section + " integer not null, "
	            + Start_Date + " text not null, "
	            + End_Date + " text not null, "
	            + _ID + " integer primary key autoincrement, "
	            + Days + " text not null);";

	    ////////General variable
	    final Context context;
	    DatabaseHelper DBHelper;
	    SQLiteDatabase db;
    
	    
	    public DBAdapter(Context ctx)
	    {
	        this.context = ctx;
	        DBHelper = new DatabaseHelper(context);
	    }
	
	    private static class DatabaseHelper extends SQLiteOpenHelper
	    {
	        DatabaseHelper(Context context)
	        {
	            super(context, DATABASE_NAME, null, DATABASE_VERSION);
	        }
	
	        @Override
	        public void onCreate(SQLiteDatabase db)
	        {
	         try 
	         {  	 

	        	 db.execSQL(
	            			"create table Studentinfo1 (" +
	            			"datee Text not null , " +
	            			"dateflag integer , " +
	            			"sid text text not null , " +
	            			"indexx integer , " +
	            			"FOREIGN KEY(indexx) REFERENCES course_details(_id)" +
	            			");"
	            			);
	     
	        	 db.execSQL(
	            			"create table Student4 (" +
	            			"SN Text not null , " +
	            			"ID text text not null , " +
	            			"Student_Name text not null , " +
	            			"course_ID integer , " +
	            			"FOREIGN KEY(course_ID) REFERENCES course_details(_id)" +
	            			");"
	            			);
	        	

	        	 
	        	 db.execSQL(Course_details_table_create);


	           
	           }
	          catch (SQLException e)
	          {
	                e.printStackTrace();
	          }
	        }
	
	        @Override
	        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	        {
	            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
	                    + newVersion + ", which will destroy all old data");
	            db.execSQL("DROP TABLE IF EXISTS contacts");
	            onCreate(db);
	        }
	    }///////////////////////////////////////// end of class DatabaseHelper 
	    
	    
	
	    //---opens the database---
	    public DBAdapter open() throws SQLException 
	    {
	        db = DBHelper.getWritableDatabase();
	        return this;
	    }
	
	    //---closes the database---
	    public void close() 
	    {
	        DBHelper.close();
	    }
	
	    //---insert a course into the database table 1---
	    public long insertContact(String Start ,String end,String dayS,String naME,
	    		String symbole,int year,int sec,String sem)
	    
	   {
	        ContentValues values = new ContentValues();
	        values.put(Start_Date, Start);
	        values.put(End_Date, end);
	        values.put(Days, dayS);
	        values.put(Course_NAME, naME);
	        values.put(Course_Symbole, symbole);
	        values.put(Year, year);
	        values.put(Section, sec);
	        values.put(Semester, sem);
	        return db.insert(Course_Details_table_Name, null, values);
	    }
	    
	    //---insert a student into the database table 2---
	    public long inset_student(String snn3,String id5,String name,int courseIDD)
	  {
	        ContentValues values = new ContentValues();
	        values.put(serail_Number, snn3);
	        values.put(Student_ID, id5);
	        values.put(courseid, courseIDD);
	        values.put(Student_Name, name);      
	        return db.insert(student_table, null, values);
	    }
	    
	    
	    //---insert into the database table 3---
	    public long insert_studentinfo(String iddd,String daate,int indx)
	  {
	    	//0 without
	    	
	        ContentValues values = new ContentValues();
	        values.put(Indexx, indx);
	        values.put(SID, iddd);
	        values.put(dateFlage,0);
	        values.put(DDate, daate);

	        return db.insert(studentinfo, null, values);
	    }
	    //---insert into the database table 3 with---

	    public long insert_studentinfo2(String iddd,String daate,int indx)
		  {
		    	//0 without
		    	
		        ContentValues values = new ContentValues();
		        values.put(Indexx, indx);
		        values.put(SID, iddd);
		        values.put(dateFlage,1);
		        values.put(DDate, daate);

		        return db.insert(studentinfo, null, values);
		    }
	
	
	    //---retrieves all the courses---
	    public List getAllCourses()
	    {
	    	 List courses = new ArrayList();
	    	  Cursor cursor = db.query(Course_Details_table_Name,Course_TABLE_COLUMNS , null, null, null, null, null);
	         cursor.moveToFirst();
	         while (!cursor.isAfterLast()) 
	         {
	        	 String course_name= cursor.getString(1)+"  "+ cursor.getString(cursor.getColumnIndex(Course_NAME))+" Section: "+cursor.getInt(4)+" Number of students is: "+getstudentnumbers(cursor.getInt(cursor.getColumnIndex(_ID)))+" ";
	        	 
	        	 	courses.add(course_name);
	               cursor.moveToNext();
	         } 
	        cursor.close();
	         return courses;
	    } 
	    
	    
	    //---get the Course ID
	    public int getcourseID(int sec,String CourseSymbol) {
	    	int id;
	    	String q = "SELECT * FROM course_details WHERE section = " + sec +" AND Course_Symbol ='"+CourseSymbol+"'";
	    	Cursor mCursor = db.rawQuery(q, null);
	    	mCursor.moveToFirst();
	        id= mCursor.getInt(mCursor.getColumnIndex(_ID));
	    	mCursor.close(); 
	        return id;
	    	
		}
	    
	    //---get the total absentday for specific student without
	    public int getStudentDaysAbsentstotal(int couseindex,String iddd)
	    {// 0 without
	    	int count;
	    	String q = "SELECT * FROM Studentinfo1 WHERE indexx = " + couseindex + " and sid = " +"'"+iddd+"'";
	    	Cursor mCursor = db.rawQuery(q, null);
	    	mCursor.moveToFirst();
	        count=mCursor.getCount();
	       // Toast.makeText(context, count+"", Toast.LENGTH_SHORT).show();
	        if(count>0)
	        return count;
	        else
	        	return 0;
		}
	    
	    //---get the days abendence for specific student without
	    public int getStudentDaysAbsents(int couseindex,String iddd)
	    {// 0 without
	    	int count;
	    	String q = "SELECT * FROM Studentinfo1 WHERE indexx = " + couseindex + " and sid = " +"'"+iddd+"'"+" and dateflag =  0 ";
	    	Cursor mCursor = db.rawQuery(q, null);
	    	mCursor.moveToFirst();
	        count=mCursor.getCount();
	       // Toast.makeText(context, count+"", Toast.LENGTH_SHORT).show();
	        if(count>0)
	        return count;
	        else
	        	return 0;
		}
	    
	    //---get the days abendence for specific student with
	    public int getStudentDaysAbsents2(int couseindex,String iddd)
	    {// 0 without
	    	int count;
	    	String q = "SELECT * FROM Studentinfo1 WHERE indexx = " + couseindex + " and sid = " +"'"+iddd+"'"+" and dateflag =  1 ";
	    	Cursor mCursor = db.rawQuery(q, null);
	    	mCursor.moveToFirst();
	        count=mCursor.getCount();
	        if(count>0)
	        return count;
	        else
	        	return 0;
		}
	    
	  //---  update absent day
	    public void updateAbsentDay(String date,String id,int couseindex)
	    {// 0 without
	    		    	
	    	//a5er 3'yab
	    	String strSQL = "UPDATE Studentinfo1 SET dateflag = 1 WHERE indexx ="+ couseindex + " and datee ="+"'"+date+"'" + "and sid ="+"'"+id+"'" ;

	    	db.execSQL(strSQL);
	    	
	    	
	  
		}
	    
	    
		  //---  update absent day
	    public void updateAbsentDay2(String date,String id,int couseindex)
	    {// 0 without
	    		    	
	    	//a5er 3'yab
	    	String strSQL = "UPDATE Studentinfo1 SET dateflag = 0 WHERE indexx ="+ couseindex + " and datee ="+"'"+date+"'" + "and sid ="+"'"+id+"'" ;

	    	db.execSQL(strSQL);
	    	
	    	
	  
		}
	    
	    //---number of absent student 
	    public int getnumberabsentstudent(int index , String date)
	    {// 0 without

	   	 List dayss = new ArrayList();
	   	 String date1;
	   	String q = "SELECT * FROM Studentinfo1 WHERE indexx = " +index+ " and datee= " + "'"+date+"'" +"and dateflag = 0";
	   	Cursor mCursor = db.rawQuery(q, null);
	   	
	    int cnt = mCursor.getCount();
	    mCursor.close();
	    return cnt;
		}
	    
	    
	    //---get student name by id
	    public String getStudentNameById(String name) {
	        //Toast.makeText(context,name, Toast.LENGTH_SHORT).show();

	    	String  Studentid;
	    	String q = "SELECT * FROM Student4 WHERE Student_Name = " +"'"+ name+"'" ;
	    	Cursor mCursor = db.rawQuery(q, null);

	    	mCursor.moveToFirst();

	    	Studentid= mCursor.getString(1);
	    //    Toast.makeText(context,Studentid, Toast.LENGTH_SHORT).show();

	    	mCursor.close(); 
	    	return Studentid;
	    	
		}
	    
	    //---get student name by id
	    public String getStudentNameById2(String name) {
	     //   Toast.makeText(context,name, Toast.LENGTH_SHORT).show();

	    	String  Studentid;
	    	String q = "SELECT * FROM Student4 WHERE Student_Name = " +"'"+ name+"'" ;
	    	Cursor mCursor = db.rawQuery(q, null);

	    	mCursor.moveToFirst();
	      //  Toast.makeText(context,mCursor.getString(1), Toast.LENGTH_SHORT).show();

	    	Studentid= mCursor.getString(1);
	    	mCursor.close(); 
	    	return Studentid;
	    	
		}
	    
	    //---course name and semester
	    public String getCourseName(int id) {
	    	String  name;
	    	String q = "SELECT * FROM course_details WHERE _id = " +"'"+ id+"'" ;
	    	Cursor mCursor = db.rawQuery(q, null);
	    	mCursor.moveToFirst();
	    	name= mCursor.getString(mCursor.getColumnIndex(Course_NAME));
	    	mCursor.close(); 
	    	return name;
	    	
		}

	    //---course name and semester
	    public String getSemesterName(int id) {
	    	String  name;
	    	String q = "SELECT * FROM course_details WHERE _id = " +"'"+ id+"'" ;
	    	Cursor mCursor = db.rawQuery(q, null);
	    	mCursor.moveToFirst();
	    	name= mCursor.getString(mCursor.getColumnIndex(Semester));
	    	mCursor.close(); 
	    	return name;
	    	
		}
	    
	    
	    //---course days and 
	    public String getcourseday(int id) {
	    	String  days;
	    	String q = "SELECT * FROM course_details WHERE _id = " +"'"+ id+"'" ;
	    	Cursor mCursor = db.rawQuery(q, null);
	    	mCursor.moveToFirst();
	    	days= mCursor.getString(mCursor.getColumnIndex(Days));
	    	mCursor.close(); 
	    	return days;
	    	
		}
	    
	    ///////////////////////////////student name by id
	    public String getStudentName(String id) {
	    	String  name;
	    	String q = "SELECT * FROM Student4 WHERE ID = " +"'"+ id+"'" ;
	    	Cursor mCursor = db.rawQuery(q, null);
	    	mCursor.moveToFirst();
	    	name= mCursor.getString(2);
	    	mCursor.close(); 
	    	return name;
	    	
		}
	    
	    //without
	    public List missingDays(int courseindex,String id)
	    {
	    	 List dayss = new ArrayList();
	    	 String day;
		    String q = "SELECT * FROM studentinfo1 WHERE sid = " +"'"+ id+"'"+"and indexx = "+courseindex ;
	    	Cursor cursor = db.rawQuery(q, null);
	         cursor.moveToFirst();
	         while (!cursor.isAfterLast()) 
	         {
	        	 day= cursor.getString(cursor.getColumnIndex(DDate));
	        	 
	        	 dayss.add(day);
	               cursor.moveToNext();
	         } 
	        cursor.close();
	         return dayss;
	    } 
	    

	    //---retrieves all the absent student---
	    public List getallabsentstudent(int index , String date)
	    {
	 List dayss = new ArrayList();
	 String date1;
	String q = "SELECT * FROM Studentinfo1 WHERE indexx = " +index+ " and datee= " + "'"+date+"'" +"and dateflag = 0";
	Cursor cursor = db.rawQuery(q, null);
	
	         cursor.moveToFirst();
	         while (!cursor.isAfterLast()) 
	         {
	        	  date1= cursor.getString(cursor.getColumnIndex(DDate))+"       "+ cursor.getString(cursor.getColumnIndex(SID));
	        	 
	        	 dayss.add(date1);
	               cursor.moveToNext();
	         } 
	        cursor.close();
	         return dayss;
	    } 
	    
	    
	    //---retrieves all the students---
	    public List getAllStudent1()
	    {
	    	 List students = new ArrayList();
	    	  Cursor cursor = db.query(student_table,Student_TABLE_COLUMNS , null, null, null, null, null);
	    			  	 
	         cursor.moveToFirst();
	         while (!cursor.isAfterLast()) 
	         {
	        	 String student_name= cursor.getString(cursor.getColumnIndex(serail_Number)) + 
	        			 cursor.getInt(cursor.getColumnIndex(Student_ID))+
	        			 cursor.getString(cursor.getColumnIndex(Student_Name));
	        	 
	        	 students.add(student_name);
	        	 		cursor.moveToNext();
	         } 
	        cursor.close();
	        return students;
	    } 
	    //--- get spesific students
	    public List getStudents(long rowId) throws SQLException 
	    { 	 List students = new ArrayList();
	        Cursor mCursor =
	                db.query(true, student_table, Student_TABLE_COLUMNS, courseid + "=" + rowId, null,
	                null, null, null, null);
	        mCursor.moveToFirst();
	         while (!mCursor.isAfterLast()) 
	         {
	        	 String student_name= mCursor.getInt(mCursor.getColumnIndex(serail_Number)) + "    "+
	        			 mCursor.getString(mCursor.getColumnIndex(Student_Name))+"   "+"<br>"+
	        			 mCursor.getInt(mCursor.getColumnIndex(Student_ID))+"</br>"

	        			 	        			 ;
	        	 
	        	 students.add(student_name);
	        	 mCursor.moveToNext();
	        	 
	         }

	         mCursor.close();
		        return students;
	    }
	    
	   

	//---get spesific students ID's
		    public List getStudents__ID(long rowId) throws SQLException 
		    { 	 List students = new ArrayList();
		    String student_id;
		        Cursor mCursor =
		                db.query(true, student_table, Student_TABLE_COLUMNS, courseid + "=" + rowId, null,
		                null, null, null, null);
		        mCursor.moveToFirst();
		         while (!mCursor.isAfterLast()) 
		         {
		        	  student_id=mCursor.getString(mCursor.getColumnIndex(Student_ID));
		        	 
		        	 students.add(student_id);
		        	 mCursor.moveToNext();
		        	 
		         }

		         mCursor.close();
			        return students;
		    }
		    
		    
	//--- edit student name
		    public void EditStudentName(long rowId,String name , String sn) 
		    { 
		    	
		    	String strSQL = "UPDATE Student4 SET Student_Name = "+ "'" + name + "'"+" WHERE course_ID ="+ rowId + " and SN ="+"'"+sn+"'" ;

		    	db.execSQL(strSQL);

		    }

		    
//*****************************************************************************
		public int getstudentnumbers(int rowId) {
			Cursor mCursor =
	                db.query(true, student_table, Student_TABLE_COLUMNS, courseid + "=" + rowId, null,
	                null, null, null, null);
		    int cnt = mCursor.getCount();
		    mCursor.close();
		    return cnt;
		}
	    

		 //--- delete particualat student form table 2and 3
		public void deleteStudent1(int courseIndex, String id)
	    {
	   	 db.execSQL("DELETE FROM studentinfo1 where indexx ="+courseIndex + " and sid ="+"'"+id+"'" );
	   	 db.execSQL("DELETE FROM Student4 where course_ID = "+courseIndex + " and ID ="+"'"+id+"'" );

	    }
		
		 //--- delete marge student form table 2and 3
		public void deleteStudent4(int courseIndex, String id)
	    {
	   	 db.execSQL("DELETE FROM Student4 where course_ID = "+courseIndex + " and ID ="+"'"+id+"'" );

	    }
		
		
	    //delete all record in table 1
	    public void del()
	    {
	   	 db.execSQL("DELETE FROM course_details");
	    }
	    
	    //delete all record in table 2
	    public void del2()
	    {
	   	 db.execSQL("DELETE FROM Student4");
	    }
	    
	    //delete all record in table 3
	    public void del3()
	    {
	   	 db.execSQL("DELETE FROM Studentinfo1");
	    }
	    
	    
	  //---deletes a particular course---
	    public boolean deletecourse(int rowId) 
	    {
	        return db.delete(Course_Details_table_Name, _ID + "=" + rowId, null) > 0;
	    }
	    
	  //---deletes a particular student---
	    public boolean deleteStudent(int rowId) 
	    {
	        return db.delete(student_table, courseid + "=" + rowId, null) > 0;
	    }
	    
	    //---deletes a particular from table3---
	    public boolean deletestudentinformation(int rowId) 
	    {
	        return db.delete(studentinfo, Indexx + "=" + rowId, null) > 0;
	    }
	    
	    public void deleteParticularDate(String Day,int index,String id) 
	    {
		   	 db.execSQL("DELETE FROM Studentinfo1 where indexx = "+index+ " and sid = "+ "'" + id+ "'" +" and datee = "+ "'" + Day + "'" );
	    }

	    
	    
	public int lastRowInserted()
	{
		
	    Cursor cursor = db.query(Course_Details_table_Name, Course_TABLE_COLUMNS,null, null, null, null, null);
	    cursor.moveToLast();
	    int CoursID=cursor.getInt(cursor.getColumnIndex(_ID));
	    return CoursID;
	}   
	    
	
    //---get student id by SN and course index
    public String getStudentID(String sn,int index) {
    	String  Studentid;
    	String q = "SELECT * FROM Student4 WHERE course_ID = " +index+ " and SN= " + "'"+sn+"'" ;
    	Cursor mCursor = db.rawQuery(q, null);
    	mCursor.moveToFirst();
    	Studentid= mCursor.getString(1);
    	mCursor.close(); 
    	return Studentid;
    	
    }

    //---
	public List dateOfAbsent(String id, int CID )
	{
	   	List date = new ArrayList();
    	String q = "SELECT * FROM Studentinfo1 WHERE indexx = " +CID+ " and sid= " + "'"+id+"'" ;
    	Cursor mCursor = db.rawQuery(q, null);   	
    	mCursor.moveToFirst();
    	while (!mCursor.isAfterLast()) 
        {
       	String DATE=mCursor.getString(0);      	 
       	date.add(DATE);
       	mCursor.moveToNext();    	 
        }
        mCursor.close();
	    return date;
   }
	//--- get name
    public List getsinglename(long rowId) throws SQLException 
    { 	 List studentsn = new ArrayList();
        Cursor mCursor =
                db.query(true, student_table, Student_TABLE_COLUMNS, courseid + "=" + rowId, null,
                null, null, null, null);
        mCursor.moveToFirst();
         while (!mCursor.isAfterLast()) 
         {
        	 String student_name= mCursor.getString(mCursor.getColumnIndex(Student_Name));
        	 
        	 studentsn.add(student_name);
        	 mCursor.moveToNext();
        	 
         }

         mCursor.close();
	        return studentsn;
    }
    
	//----
    public String getCourseNS(int id) {
    	String  name,sec;
    	String q = "SELECT * FROM course_details WHERE _id = " +"'"+ id+"'" ;
    	Cursor mCursor = db.rawQuery(q, null);
    	mCursor.moveToFirst();
    	name= mCursor.getString(mCursor.getColumnIndex(Course_NAME));
    	sec= mCursor.getString(mCursor.getColumnIndex(Section));
    	mCursor.close(); 
    	return name+sec;
    	
	}
    
    //--- wether absent with permission or without permissin
    public boolean wether (int courseID,String Date,String SID)
    {
    	int wetheer;
    	String q = "SELECT * FROM Studentinfo1 WHERE indexx = " +courseID+ " and datee= " + "'"+Date+"'" +" and sid = " + "'"+SID+"'" ;
    	Cursor mCursor = db.rawQuery(q, null);
    	mCursor.moveToFirst();
    	
    	wetheer=mCursor.getInt(mCursor.getColumnIndex(dateFlage));
    	if(wetheer==1) 
    		return true;
    	else return false;
    	
    }
    
    //--- merge
	public void merg(int courseID2, ArrayList<String> fName,ArrayList<String> fID,
			ArrayList<String> fSN) {
		// TODO Auto-generated method stub
		String ID;
		String SN;
		String name;
		int count;
		
    	deleteStudent(courseID2);

		  for(int rows=1;rows<fSN.size();rows++)
	        {
			  
			  ID=fID.get(rows);
			  ID=ID.substring(1, ID.length()-1);
	        	
	        	name=fName.get(rows);
	        	name=name.substring(1, name.length()-1);
	        	
	        	SN=fSN.get(rows);
	        	SN=SN.substring(1, SN.length()-1);
	        
	        	inset_student(SN,ID, name, courseID2);
	  
	        }//for
				
	}
	
	
	//*****************************************************************************


	    
}//end of class DBAdapter
	
