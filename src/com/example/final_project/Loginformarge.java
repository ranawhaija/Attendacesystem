package com.example.final_project;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;




public class Loginformarge extends Activity {
	
	EditText edtUser;
	EditText edtPassword;
	Button btnlog;
	String UserName, Password;
	static Loginformarge activityA;
	
	ImageView header;
	// TextView txt_Uni_name;
	Animation animFadein;
	ImageView splashLogo;
	TextView vv;
	private Animation animFadeOut;
	public String[][] finv;
	ArrayList<String> ls= new ArrayList<String>();
	ArrayList<String> li= new ArrayList<String>();
	AsyncTask<Void, Void, Void> my;
	ArrayList<String> courselist;
	ArrayList<String> courseName;
	   DBAdapter db = new DBAdapter(this);

	// public static UniParser UNI_PARSER;
	String postParams; //concanitation post with usr pass 
	public String responsdataFromLogin = "";
	public String responsdataFromLogin2 = "";
	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.124 Safari/537.36";
	String url = "https://elearning.yu.edu.jo/yulms/login/index.php"; 
	boolean LoginOrNo;
	 int course_ID;
	//boolean showpass = true;
	private HttpURLConnection conn;
	private ProgressDialog pDialog;
	String post = "";// fixed 
	int count = 0;
	public int mypos;
	public int flag=0;
	boolean[] itemsChecked ;
	public String smytest;
	public String np="";

	
	/*EditText edtUser;
	EditText edtPassword;
	String UserName, Password;
	Button btnlog;
	ImageView header;
	// TextView txt_Uni_name;
	Animation animFadein;
	ImageView splashLogo;
	private Animation animFadeOut;
	// public static UniParser UNI_PARSER;
	String postParams; //concanitation post with usr pass 
	public String responsdataFromLogin = "";
	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.111 Safari/537.36";
	String url = "http://sis.yu.edu.jo/pls/yuapps/wwv_flow.accept";//ÇáÏÎæá Úáì ÍÓÇÈ ÇáØÇáÈ 
	boolean LoginOrNo;
	//boolean showpass = true;
	private HttpURLConnection conn;
	private ProgressDialog pDialog;
	String post = "";// fixed 
	int count = 0;*/


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		//WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_loginformarge);
		
		activityA = this;
		
		
		   ImageView Home=(ImageView)findViewById(R.id.imageView2);
		   ImageView Back=(ImageView)findViewById(R.id.imageView3);
		   
		   
		   Home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent I=new Intent(Loginformarge.this,Home.class);
				startActivity(I);
				
			}
		});
		   
		   Back.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent I=new Intent(Loginformarge.this,Loadmoodformarge.class);
					startActivity(I);
					
				}
			});

		 Intent i3=getIntent();
		   course_ID=i3.getIntExtra("coursrid", 0);
		
		///encryption in html file 
		post = "username=";
		edtUser=(EditText)findViewById(R.id.edtUserName);
		edtPassword=(EditText)findViewById(R.id.edtPassword);
		btnlog=(Button) findViewById(R.id.btn_login);
		vv= (TextView) findViewById(R.id.textView1);

	
		//hint to fill in blanck 
		edtUser.setHint("User Name");
		edtPassword.setHint("Password");

		checkLoginOrNo();

		buttonEffect();
		
		///encryption in html file 
	/*			post = "p_flow_id=134&p_flow_step_id=101&p_instance=219397599730603&p_page_submission_id=810552442483465&p_request=LOGIN&p_arg_names=3808843104802386083&p_t01=%D9%8A%D8%B1%D8%AC%D9%89+%D8%A7%D9%84%D8%B9%D9%84%D9%85+%D8%A8%D8%A3%D9%86%D9%87+%D9%84%D9%86+%D9%8A%D8%AA%D9%85+%D8%B4%D8%B7%D8%A8+%D8%A3%D9%8A+%D9%85%D8%B3%D8%A7%D9%82+%D9%84%D9%84%D8%B7%D9%84%D8%A8%D8%A9+%D8%A7%D9%84%D8%B0%D9%8A%D9%86+%D8%A3%D8%B6%D8%A7%D9%81%D9%88%D8%A7+%D9%85%D8%B3%D8%A7%D9%82%D8%A7%D8%AA+%28+%D8%A7%D9%8A+%D8%A8%D8%B9%D8%AF+%D8%AF%D9%81%D8%B9+%D8%B1%D8%B3%D9%88%D9%85+%D8%A7%D9%84%D8%AA%D8%B3%D8%AC%D9%8A%D9%84+%29%D9%88%D9%84%D9%85+%D9%8A%D8%AF%D9%81%D8%B9%D9%88%D8%A7+%D8%A7%D9%84%D8%B1%D8%B3%D9%88%D9%85+%D8%A7%D9%84%D9%85%D9%82%D8%B1%D8%B1%D8%A9+%D8%B9%D9%86+%D8%A7%D9%84%D9%85%D8%B3%D8%A7%D9%82%D8%A7%D8%AA+%D8%A7%D9%84%D9%85%D8%B6%D8%A7%D9%81%D8%A9++%D9%88%D8%B9%D9%84%D9%8A%D9%87+%D8%B3%D9%8A%D8%AA%D8%AD%D9%85%D9%84+%D8%A7%D9%84%D8%B7%D8%A7%D9%84%D8%A8+%D8%B1%D8%B3%D9%88%D9%85+%D8%A7%D9%84%D8%B3%D8%A7%D8%B9%D8%A7%D8%AA+%D8%A7%D9%84%D8%AA%D9%8A+%D8%A3%D8%B6%D8%A7%D9%81%D9%87%D8%A7+%D8%A7%D8%AB%D9%86%D8%A7%D8%A1+%D9%81%D8%AA%D8%B1%D8%A9+%D8%A7%D9%84%D8%A7%D9%86%D8%B3%D8%AD%D8%A7%D8%A8+%D9%88%D8%A7%D9%84%D8%A7%D8%B6%D8%A7%D9%81%D8%A9++%D9%88%D8%A7%D9%84%D8%B9%D9%84%D8%A7%D9%85%D8%A9+%D8%A7%D9%84%D8%AA%D9%8A+%D8%AA%D8%B1%D8%B5%D8%AF+%D9%84%D9%87+%D9%81%D9%8A+%D8%AA%D9%84%D9%83+%D8%A7%D9%84%D9%85%D8%B3%D8%A7%D9%82%D8%A7%D8%AA.%D9%84%D8%B0%D8%A7+%D8%A3%D8%B1%D8%AC%D9%88+%D9%85%D9%86+%D8%AC%D9%85%D9%8A%D8%B9+%D8%A7%D9%84%D8%B7%D9%84%D8%A8%D8%A9+%D8%B9%D8%AF%D9%85+%D8%A5%D8%B6%D8%A7%D9%81%D8%A9+%D8%A3%D9%8A+%D9%85%D8%B3%D8%A7%D9%82+%D9%84%D8%A7+%D9%8A%D8%B1%D8%BA%D8%A8%D9%88%D9%86+%D8%A8%D8%AF%D8%B1%D8%A7%D8%B3%D8%AA%D9%87+%D8%AA%D9%84%D8%A7%D9%81%D9%8A%D8%A7+%D9%84%D8%AD%D8%AF%D9%88%D8%AB+%D8%A8%D8%B9%D8%B6+%D8%A7%D9%84%D8%A5%D8%B4%D9%83%D8%A7%D9%84%D8%A7%D8%AA+%D9%85%D8%B9+%D8%A7%D9%84%D8%B7%D9%84%D8%A8%D8%A9+%D8%8C+%D8%B9%D9%84%D9%85%D8%A7+%D8%A8%D8%A7%D9%86+%D9%87%D8%B0%D8%A7+%D8%A7%D9%84%D8%A7%D8%AC%D8%B1%D8%A7%D8%A1+%D9%85%D8%B7%D8%A8%D9%82+%D9%85%D9%86%D8%B0+%D9%81%D8%B5%D9%88%D9%84+%D8%B3%D8%A7%D8%A8%D9%82%D8%A9+0______%D8%B9%D8%AF%D9%85+%D8%A7%D9%84%D8%B3%D9%85%D8%A7%D8%AD+%D9%84%D9%84%D8%B7%D9%84%D8%A8%D8%A9+%D8%A8%D8%A7%D9%84%D8%AA%D8%B3%D8%AC%D9%8A%D9%84+%D9%84%D8%A7%D9%83%D8%AB%D8%B1+%D9%85%D9%86+%2818%29+%D8%B3%D8%A7%D8%B9%D8%A9+%D9%85%D8%B9%D8%AA%D9%85%D8%AF%D8%A9+%D9%81%D9%8A+%D8%A7%D9%84%D9%81%D8%B5%D9%84+%D8%A7%D9%84%D8%AF%D8%B1%D8%A7%D8%B3%D9%8A+%D8%A7%D9%84%D8%A7%D9%88%D9%84+%D8%A7%D9%84%D8%AB%D8%A7%D9%86%D9%8A%D8%8C+%D9%88%2810%29+%D8%B3%D8%A7%D8%B9%D8%A7%D8%AA+%D9%85%D8%B9%D8%AA%D9%85%D8%AF%D8%A9+%D9%81%D9%8A+%D8%A7%D9%84%D9%81%D8%B5%D9%84+%D8%A7%D9%84%D8%AF%D8%B1%D8%A7%D8%B3%D9%8A+%D8%A7%D9%84%D8%B5%D9%8A%D9%81%D9%8A+%D8%AE%D9%84%D8%A7%D9%84+%D9%81%D8%AA%D8%B1%D8%A9+%D8%A7%D9%84%D8%AA%D8%B3%D8%AC%D9%8A%D9%84+%D8%A7%D9%84%D8%AA%D9%8A+%D8%AA%D8%B3%D8%A8%D9%82+%D9%81%D8%AA%D8%B1%D8%A9+%D8%A7%D9%84%D8%B3%D8%AD%D8%A8+%D9%88%D8%A7%D9%84%D8%A7%D8%B6%D8%A7%D9%81%D8%A9.+%D9%88%D8%A7%D9%84%D8%B3%D9%85%D8%A7%D8%AD+%D9%84%D9%84%D8%B7%D9%84%D8%A8%D8%A9+%D8%A7%D9%84%D8%B0%D9%8A%D9%86+%D8%AA%D9%86%D8%B7%D8%A8%D9%82+%D8%B9%D9%84%D9%8A%D9%87%D9%85+%D8%A7%D9%84%D8%AA%D8%B9%D9%84%D9%8A%D9%85%D8%A7%D8%AA+%D8%A8%D8%A7%D9%84%D8%AA%D8%B3%D8%AC%D9%8A%D9%84+%D9%84%D9%80+%2821%29+%D8%B3%D8%A7%D8%B9%D8%A9+%D9%85%D8%B9%D8%AA%D9%85%D8%AF%D8%A9+%D9%81%D9%8A+%D8%A7%D9%84%D9%81%D8%B5%D9%84+%D8%A7%D9%84%D8%AF%D8%B1%D8%A7%D8%B3%D9%8A+%D8%A7%D9%84%D8%A7%D9%88%D9%84+%D9%88%D8%A7%D9%84%D8%AB%D8%A7%D9%86%D9%8A%D8%8C+%D9%88%2812%29+%D8%B3%D8%A7%D8%B9%D8%A9+%D9%85%D8%B9%D8%AA%D9%85%D8%AF%D8%A9+%D9%81%D9%8A+%D8%A7%D9%84%D9%81%D8%B5%D9%84+%D8%A7%D9%84%D8%B5%D9%8A%D9%81%D9%8A+%D8%AE%D9%84%D8%A7%D9%84+%D9%81%D8%AA%D8%B1%D8%A9+%D8%A7%D9%84%D8%B3%D8%AD%D8%A8+%D9%88%D8%A7%D9%84%D8%A7%D8%B6%D8%A7%D9%81%D8%A9+%D9%81%D9%82%D8%B7.______%D8%B9%D9%84%D9%89+%D8%AC%D9%85%D9%8A%D8%B9+%D8%A7%D9%84%D8%B7%D9%84%D8%A8%D8%A9+%D8%A7%D9%84%D8%B0%D9%83%D9%88%D8%B1+%D9%85%D9%86+%D9%85%D9%88%D8%A7%D9%84%D9%8A%D8%AF+%281974-1995%29+%D9%85%D8%B1%D8%A7%D8%AC%D8%B9%D8%A9+%D9%85%D9%83%D8%AA%D8%A8+%D8%AE%D8%AF%D9%85%D8%A9+%D8%A7%D9%84%D8%B9%D9%84%D9%85+%D8%A8%D8%BA%D8%B6+%D8%A7%D9%84%D9%86%D8%B8%D8%B1+%D8%B9%D9%86+%D8%AC%D9%86%D8%B3%D9%8A%D8%AA%D9%87%D9%85+%D9%84%D8%BA%D8%A7%D9%8A%D8%A7%D8%AA+%D8%A7%D9%84%D8%AA%D8%B3%D8%AC%D9%8A%D9%84+%D9%88%D8%A7%D9%84%D8%AA%D8%A3%D8%AC%D9%8A%D9%84+%D8%A7%D9%84%D8%AF%D8%B1%D8%A7%D8%B3%D9%8A+%D9%84%D8%A8%D9%8A%D8%A7%D9%86+%D9%85%D9%88%D9%82%D9%81%D9%87%D9%85+%D9%85%D9%86+%D8%AE%D8%AF%D9%85%D8%A9+%D8%A7%D9%84%D8%B9%D9%84%D9%85+%D9%85%D8%B5%D8%B7%D8%AD%D9%8A%D8%A8%D9%86+%D9%85%D8%B9%D9%87%D9%85+%D8%A7%D9%84%D9%88%D8%AB%D8%A7%D8%A6%D9%82+%D8%A7%D9%84%D8%B1%D8%B3%D9%85%D9%8A%D8%A9+%D9%88%D8%B1%D8%B3%D9%88%D9%85+%D9%85%D8%A7%D9%84%D9%8A%D8%A9+%D8%A8%D9%82%D9%8A%D9%85%D8%A9+%D8%AF%D9%8A%D9%86%D8%A7%D8%B1+%D8%A3%D8%B1%D8%AF%D9%86%D9%8A______%D8%B9%D9%84%D9%89+%D8%AC%D9%85%D9%8A%D8%B9+%D8%A7%D9%84%D8%B7%D9%84%D8%A8%D8%A9+%D9%85%D8%B1%D8%A7%D8%AC%D8%B9%D8%A9+%D9%85%D9%83%D8%AA%D8%A8+%D8%AE%D8%AF%D9%85%D8%A9+%D8%A7%D9%84%D8%B9%D9%84%D9%85+%D9%88%D8%B0%D9%84%D9%83+%D9%84%D8%A7%D9%83%D9%85%D8%A7%D9%84+%D8%A7%D8%AC%D8%B1%D8%A7%D8%A1%D8%A7%D8%AA+%D8%A7%D9%84%D8%AA%D8%B3%D8%AC%D9%8A%D9%84+%D8%AE%D9%84%D8%A7%D9%84+%D9%81%D8%AA%D8%B1%D8%A9+%D8%A7%D9%84%D8%AF%D9%88%D8%A7%D9%85+%D8%A7%D9%84%D9%85%D8%AD%D8%AF%D8%AF++%D9%8A%D9%88%D9%85%D9%8A%D8%A7%D9%8B+%D9%85%D9%86+%D8%A7%D9%84%D8%B3%D8%A7%D8%B9%D8%A9+00%3A8+%D8%B5%D8%A8%D8%A7%D8%AD%D8%A7%D9%8B+%D9%88%D8%AD%D8%AA%D9%89+%D8%A7%D9%84%D8%B3%D8%A7%D8%B9%D8%A9+00%3A2+%D8%A8%D8%B9%D8%AF+%D8%A7%D9%84%D8%B8%D9%87%D8%B1______%D8%A7%D8%B9%D8%B2%D8%A7%D8%A6%D9%8A+%D8%A7%D9%84%D8%B7%D9%84%D8%A8%D8%A9+%D8%A7%D8%B1%D8%AC%D9%88+%D8%A7%D9%84%D8%B9%D9%84%D9%85+%D8%A8%D8%A7%D9%86%D9%87+%D9%84%D9%86+%D9%8A%D8%AA%D9%85+%D8%A7%D9%84%D9%86%D8%B8%D8%B1+%D9%81%D9%8A+%D8%A7%D9%84%D8%BA%D8%A7%D8%A1+%D8%A7%D9%84%D8%B9%D9%84%D8%A7%D9%85%D8%A7%D8%AA+%D8%A7%D9%84%D9%86%D9%87%D8%A7%D8%A6%D9%8A%D8%A9+%D8%A7%D9%84%D8%AA%D9%8A+%D8%AD%D8%B5%D9%84+%D8%B9%D9%84%D9%8A%D9%87%D8%A7+%D8%A7%D9%84%D8%B7%D8%A7%D9%84%D8%A8+%D9%81%D9%8A+%D8%A7%D9%8A+%D9%81%D8%B5%D9%84+%D8%AF%D8%B1%D8%A7%D8%B3%D9%8A+%D8%8C+%D9%88%D9%84%D8%A7+%D9%8A%D8%B9%D8%AA%D9%8A%D8%B1+%D9%85%D8%A4%D8%AC%D9%84%D8%A7+%D9%84%D8%AF%D8%B1%D8%A7%D8%B3%D8%AA%D9%87+%D9%81%D9%8A+%D8%B0%D9%84%D9%83+%D8%A7%D9%84%D9%81%D8%B5%D9%84+%D8%8C+%D9%88%D8%B0%D9%84%D9%83+%D8%A7%D8%B9%D8%AA%D8%A8%D8%A7%D8%B1%D8%A7+%D9%85%D9%86+%D8%A8%D8%AF%D8%A7%D9%8A%D8%A9+%D8%A7%D9%84%D9%81%D8%B5%D9%84+%D8%A7%D9%84%D8%A3%D9%88%D9%84+2014%2F2015%D8%8C+%D8%A3%D9%8A+%D8%A3%D9%86%D9%87+%D9%84%D9%86+%D9%8A%D8%AA%D9%85+%D8%A7%D9%84%D9%86%D8%B8%D8%B1+%D9%81%D9%8A+%D8%AA%D8%A3%D8%AC%D9%8A%D9%84+%D8%AF%D8%B1%D8%A7%D8%B3%D8%A9+%D8%A7%D9%8A+%D8%B7%D8%A7%D9%84%D8%A8+%D8%AD%D8%B5%D9%84+%D8%B9%D9%84%D9%89+%D8%B9%D9%84%D8%A7%D9%85%D8%A9+%2835%25%29+%D9%81%D9%8A+%D8%AC%D9%85%D9%8A%D8%B9+%D8%A7%D9%84%D9%85%D8%B3%D8%A7%D9%82%D8%A7%D8%AA+%D8%A7%D9%84%D9%85%D8%B3%D8%AC%D9%84+%D9%84%D9%87%D8%A7+%D9%81%D9%8A+%D8%B0%D9%84%D9%83+%D8%A7%D9%84%D9%81%D8%B5%D9%84+.%0D%0A%0D%0A______%D9%8A%D8%B1%D8%AC%D9%89+%D8%A7%D9%84%D8%B9%D9%84%D9%85+%D8%A8%D8%A3%D9%86%D9%87+%D8%AA%D9%85+%D8%B7%D8%B1%D8%AD+%D9%85%D8%B3%D8%A7%D9%82+%28%D8%B7%D9%80+100%29+%D8%A7%D9%84%D8%A5%D8%B3%D8%B9%D8%A7%D9%81%D8%A7%D8%AA+%D8%A7%D9%84%D8%A3%D9%88%D9%84%D9%8A%D8%A9+%D9%83%D9%85%D8%AA%D8%B7%D9%84%D8%A8+%D8%AC%D8%A7%D9%85%D8%B9%D8%A9+%D8%A7%D8%AE%D8%AA%D9%8A%D8%A7%D8%B1%D9%8A+%D9%85%D9%86+%D8%A7%D9%84%D9%85%D8%AC%D9%85%D9%88%D8%B9%D8%A9+%D8%A7%D9%84%D8%AB%D8%A7%D9%84%D8%AB%D8%A9+%D9%84%D8%BA%D9%8A%D8%B1+%D8%B7%D9%84%D8%A8%D8%A9+%D8%A7%D9%84%D8%B7%D8%A8%D8%8C+%D8%A7%D8%B9%D8%AA%D8%A8%D8%A7%D8%B1%D8%A7%D9%8B+%D9%85%D9%86+%D8%A8%D8%AF%D8%A7%D9%8A%D8%A9+%D8%A7%D9%84%D9%81%D8%B5%D9%84+%D8%A7%D9%84%D8%A3%D9%88%D9%84+2014%2F2015%0D%0A______%D9%8A%D8%B1%D8%AC%D9%89+%D8%A7%D9%84%D8%B9%D9%84%D9%85+%D8%A8%D8%A3%D9%86+%D8%A2%D8%AE%D8%B1+%D9%85%D9%88%D8%B9%D8%AF+%D9%84%D9%84%D8%A7%D9%86%D8%B3%D8%AD%D8%A7%D8%A8+%D9%81%D9%82%D8%B7+%D9%87%D9%88+%D9%86%D9%87%D8%A7%D9%8A%D8%A9+%D8%AF%D9%88%D8%A7%D9%85+%D9%8A%D9%88%D9%85+%D8%A7%D9%84%D8%AE%D9%85%D9%8A%D8%B3+25%2F12%2F2014&p_arg_names=1235407356725963&p_t02=http%3A%2F%2Fwww.yu.edu.jo&p_arg_names=174514341787584706&p_t03=";
				edtUser=(EditText)findViewById(R.id.edtUserName);
				edtPassword=(EditText)findViewById(R.id.edtPassword);
				btnlog=(Button) findViewById(R.id.btn_login);

				//hint to fill in blanck 
				edtUser.setHint("User Name");
				edtPassword.setHint("Password");

				buttonEffect();*/
			
	}
	
	///////////////////////////////////////////////////
	
	private void buttonEffect() {
		btnlog.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				
				case MotionEvent.ACTION_DOWN:
					//clicked
					//btnlog.setBackgroundResource(R.drawable.edit_onclick);
					break;
				case MotionEvent.ACTION_UP:
					//back to origin shape 
					//btnlog.setBackgroundResource(R.drawable.login_shape);

					UserName = edtUser.getText().toString();
					Password = edtPassword.getText().toString();
					if ((!edtUser.getText().toString().isEmpty() && !edtUser
							.getText().equals(null))) {
						if (!edtPassword.getText().equals("")
								&& !edtPassword.getText().toString().isEmpty()) {
							postParams = post + edtUser.getText().toString()
									+ "&password="
									+ edtPassword.getText().toString()+"&Log in";
									
							if (isOnline()) {
//have network 
								my= new LoginAsync().execute();
							} else {

								Toast.makeText(getApplicationContext(),
										"Check Your Connection",
										Toast.LENGTH_LONG).show();

							}
						} else {
							if (edtUser.getText().length() == 0) {
								edtUser.setError("Field cannot be left blank.");
							}
							if (edtPassword.getText().length() == 0) {
								edtPassword
										.setError("Field cannot be left blank.");
							}
						}
					} else {

						if (edtUser.getText().length() == 0) {
							edtUser.setError("Field cannot be left blank.");
						}
						if (edtPassword.getText().length() == 0) {
							edtPassword.setError("Field cannot be left blank.");
						}

					}

					break;
				}

				return false;
			}
		});

		edtPassword.setOnFocusChangeListener(new OnFocusChangeListener() {
//hint 
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				edtPassword.setHint("");
				edtUser.setHint("User Name");
			}
		});

		edtUser.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				edtUser.setHint("");
				edtPassword.setHint("Password");
			}
		});
	}



	private void checkLoginOrNo() {
		//


	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		} else {
			return false;
		}
	}


	public class LoginAsync extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(Loginformarge.this,
					ProgressDialog.THEME_HOLO_LIGHT);

			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance

			if (isOnline()) {
				//check 
				try {
					PostLogin(postParams);
					
					//inspect elemnts 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			conn.disconnect();
			
			
			//ÇÙåÑ ÇáÌÏæá
			if (responsdataFromLogin.contains(getString(R.string.login_key))) {
				
				//Intent i = new Intent(getApplicationContext(), HomePage2.class);
				//startActivity(i);
				
				Toast.makeText(getApplicationContext(), "successful", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getApplicationContext(), "Unsuccessful", Toast.LENGTH_LONG).show();
			}

		}

		public void PostLogin(String postParameter) throws IOException {
// html responce 
			responsdataFromLogin = new String();
			

//save data remember me 
			CookieHandler.setDefault(new CookieManager());

			URL obj = new URL(url);
			//url link of the login page 
			conn = (HttpURLConnection) obj.openConnection();
			// Acts like a browser

			//these are needed for the connection fixed not changed 
			conn.setRequestMethod("POST");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			conn.setDoOutput(true); // ÈÏí ÇØáÈ ÏÇÊÇ ãÔ ÈÓ ÇÎÏ pass user
			conn.setDoInput(true);

			//ÈÇáäÓÈå Çáí 
			if (conn!=null)
			{
				DataOutputStream wr = new DataOutputStream(conn.getOutputStream());// 
				wr.writeBytes(postParameter);
				wr.flush();

				wr.close();
	//ÈÏí ãäß 
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				responsdataFromLogin = response.toString();
				System.out.println(responsdataFromLogin);
	//ÇáÑÞã ÇáãÊÛíÑ
				GetTheIDforUrl(responsdataFromLogin);

			}
						

		}

		 
		/**
		 * @param responsdataFromLogin
		 * @return
		 */
		private String GetTheIDforUrl(String responsdataFromLogin) {
			// TODO Auto-generated method stub
			
			//***************************************************************
			/*File sdcard = Environment.getExternalStorageDirectory();

			//Get the text file
			File file = new File(sdcard,"drhome.html");

			//Read text from file
			StringBuilder text = new StringBuilder();

			try {
			    BufferedReader br = new BufferedReader(new FileReader(file));
			    String line;

			    while ((line = br.readLine()) != null) {
			        text.append(line);
			         }
			    br.close();
			}
			catch (IOException e) {
			    //You'll need to add proper error handling here
			}*/
			
			//***************************************************************
			//Document doc = Jsoup.parse(responsdataFromLogin);
			//Elements ele = doc.getAllElements();
			courselist= new ArrayList<String>();
			courseName= new ArrayList<String>();
			
			//--------------------------------
				String totalIDs = null;
				Pattern pattern0= Pattern.compile("navtreeexpansions4 = (.*?)//]]>");
			    Matcher matcher0= pattern0.matcher(responsdataFromLogin);
			    
			    while (matcher0.find()) {
				      //  System.out.println(matcher.group(1));
				        try {
				        	
				        	//courselist.add(matcher0.group(1));
				        	System.out.println("############################\n");
				        	System.out.println(matcher0.group(1));
				        	totalIDs=matcher0.group(1);
				        	System.out.println("############################\n");
				        	//courselist.add(GetSchedualPage("https://elearning.yu.edu.jo/yulms/course/view.php?id="+matcher.group(1)));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				       
				 	    }
			    
			//-----------------------------------
			
		   Document doc1 = Jsoup.parse(responsdataFromLogin);
			Element ele1 ;
			Pattern pattern = Pattern.compile("\"key\":\"(.*?)\",\"type");
		    Matcher matcher = pattern.matcher(totalIDs);
		  
			   while (matcher.find()) {
		        System.out.println(matcher.group(1));
		        try {
		        	courselist.add(matcher.group(1));
		        	
		        	ele1 = doc1.select(/*".course_list >+*/ "#course-"+matcher.group(1)+"> .course_title > h2.title > a[title]").first();
		        	courseName.add(ele1.attr("title").toString());
		        	System.out.println(ele1.attr("title").toString());
		        	
					//courselist.add(GetSchedualPage("https://elearning.yu.edu.jo/yulms/course/view.php?id="+matcher.group(1)));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       
		 	    }
			   Bundle bundle=new Bundle();
			   bundle.putStringArrayList("cn", courseName);
			     //set Fragmentclass Arguments
					   
		        FragmentManager manager = getFragmentManager();

		        SimpleListViewActivity dialog = new SimpleListViewActivity();
				 dialog.setArguments(bundle);
		        dialog.show(manager, "dialog");
		        
		        while (true)
		        {
		        	if (flag==1)
		        	{
				        
				        System.out.println(mypos);
				        
				        try {
				        	System.out.println("********* COURSES ****************");
				        	String Temp = courselist.get(mypos).toString();
				        	System.out.println(courselist.get(mypos).toString());
				        	System.out.println(Temp);
				        	System.out.println("********* COURSES ****************");
				        	
				        	//GetSchedualPage("https://elearning.yu.edu.jo/yulms/course/view.php?id="+courselist.get(mypos));
				        	//String FULLURL = "https://elearning.yu.edu.jo/yulms/course/view.php?id="+courselist.get(mypos);
				        	String FULLURL = "https://elearning.yu.edu.jo/yulms/user/index.php?id="+courselist.get(mypos).toString()+"&perpage=200";
				        	System.out.println("********* FULLURL ****************");
				        	System.out.println(FULLURL);
				        	System.out.println("********* FULLURL ****************");
				        	
				        	GetSchedualPage(FULLURL);
				       // GetSchedualPage("https://elearning.yu.edu.jo/yulms/course/view.php?id="+courselist.get(mypos));
				        //GetSchedualPage("https://elearning.yu.edu.jo/yulms/report/participation/index.php?id="+courselist.get(mypos));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        break;
		        	}
		        }

					
			return null;	
		}


		public String GetSchedualPage(String URLTOW) throws Exception {
			// TODO Auto-generated method stub
			
		/*	File sdcard = Environment.getExternalStorageDirectory();

			//Get the text file
			File file = new File(sdcard,"networklab1p.html");

			//Read text from file
			StringBuilder text = new StringBuilder();

			try {
			    BufferedReader br = new BufferedReader(new FileReader(file));
			    String line;

			    while ((line = br.readLine()) != null) {
			        text.append(line);
			         }
			    br.close();
			}
			catch (IOException e) {
			    //You'll need to add proper error handling here
			}*/
			
			//***************************************************************
			
			String url_sch = URLTOW;
			//smytest=getResources().getString(R.string.html_myfile);

			URL obj2 = new URL(url_sch);
			conn = (HttpURLConnection) obj2.openConnection();

			// default is GET
			conn.setRequestMethod("GET");

			conn.setUseCaches(false);

			// act like a browser
			conn.setRequestProperty("User-Agent", USER_AGENT);
			if (conn!=null)
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream())); //gives html
				
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					
					response.append(inputLine);
					//System.out.println(inputLine);
				}

				responsdataFromLogin = response.toString();
			    
				//System.out.println(smytest);

				getFormParams(responsdataFromLogin);
			    return responsdataFromLogin;
			}
			return null;

		}
		}
		
		public <T> String getFormParams(String html) throws Exception {
			System.out.println("********inside the function*******");

			System.out.println("********before try*******");
		
			//new login 
			Document doc = Jsoup.parse(html);
			Elements ele = doc.select("#participants > tbody >tr"); //in html

			for (Element sub : ele)
			{ 
				String name=sub.select("td:nth-child(3)").text();
				String ID =sub.select("td:nth-child(4)").text();
				ls.add(name);
				//System.out.println(name+" "+ID);
				//System.out.println("********endend*******\n");
				
				li.add(ID);
			}
			ls.removeAll(Collections.singleton(""));
			li.removeAll(Collections.singleton(""));
			ls.remove(0);
			li.remove(0);
			finv= new String[ls.size()][li.size()];
			for(int k=0;k<ls.size();k++)
			{
				finv[k][0]=li.get(k);
				finv[k][1]=ls.get(k);

			}
			
	        Arrays.sort(finv, new Comparator<String[]>() {
	            @Override
	            public int compare(final String[] entry1, final String[] entry2) {
	                final String time1 = entry1[0];
	                final String time2 = entry2[0];
	                return time1.compareTo(time2);
	            }
	        });
	        ArrayList<String> nameSorted= new ArrayList<String>();
	        ArrayList<String> IDSorted= new ArrayList<String>();
	        ArrayList<String> SNSorted= new ArrayList<String>();
	        int i=0;
	        String ids;
	        String ns;
	        for(String[] s :finv)
	        {
	        	ns=s[1];
	        	ids=s[0];
	        System.out.println(s[0]+" "+s[1]);
	       // System.out.println(ids+" "+ns);
	        nameSorted.add("\""+ns+"\"");
	        IDSorted.add("\""+ids+"\"");
	        SNSorted.add("\""+Integer.toString(i)+"\"");
	        i++;
	        }
	        
	        System.out.println(nameSorted.get(1)+" "+IDSorted.get(1)+" "+SNSorted.get(1));
	        
	    	db.open();
			db.merg(course_ID,nameSorted,IDSorted,SNSorted);
				db.close();
				
				 Intent intent1 = new Intent(Loginformarge.this,Mange_course.class);
                    
	       
	      
	        // 4. start the activity
	        startActivity(intent1);
	                
			return "";

		}
		
		public static Loginformarge getInstance(){
			   return   activityA;
			 }

	
	///////////////////////////////////////////////////
	
	
	
//	private void buttonEffect() {
//		btnlog.setOnTouchListener(new OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View arg0, MotionEvent event) {
//				// TODO Auto-generated method stub
//				switch (event.getAction()) {
//				
//				case MotionEvent.ACTION_DOWN:
//					//clicked
//
//					break;
//				case MotionEvent.ACTION_UP:
//					//back to origin shape 
//
//					UserName = edtUser.getText().toString();
//					Password = edtPassword.getText().toString();
//					if ((!edtUser.getText().toString().isEmpty() && !edtUser
//							.getText().equals(null))) {
//						if (!edtPassword.getText().equals("")
//								&& !edtPassword.getText().toString().isEmpty()) {
//							postParams = post + edtUser.getText().toString()
//									+ "&p_arg_names=174514439646584711&p_t04="
//									+ edtPassword.getText().toString()
//									+ "&p_md5_checksum=";
//							if (isOnline()) {
////have network 
//								new LoginAsync().execute();
//							} else {
//
//								Toast.makeText(getApplicationContext(),
//										"Check Your Connection",
//										Toast.LENGTH_LONG).show();
//
//							}
//						} else {
//							if (edtUser.getText().length() == 0) {
//								edtUser.setError("Field cannot be left blank.");
//							}
//							if (edtPassword.getText().length() == 0) {
//								edtPassword
//										.setError("Field cannot be left blank.");
//							}
//						}
//					} else {
//
//						if (edtUser.getText().length() == 0) {
//							edtUser.setError("Field cannot be left blank.");
//						}
//						if (edtPassword.getText().length() == 0) {
//							edtPassword.setError("Field cannot be left blank.");
//						}
//
//					}
//
//					break;
//				}
//
//				return false;
//			}
//		});
//
//		edtPassword.setOnFocusChangeListener(new OnFocusChangeListener() {
////hint 
//			@Override
//			public void onFocusChange(View arg0, boolean arg1) {
//				// TODO Auto-generated method stub
//				edtPassword.setHint("");
//				edtUser.setHint("User Name");
//			}
//		});
//
//		edtUser.setOnFocusChangeListener(new OnFocusChangeListener() {
//
//			@Override
//			public void onFocusChange(View arg0, boolean arg1) {
//				// TODO Auto-generated method stub
//				edtUser.setHint("");
//				edtPassword.setHint("Password");
//			}
//		});
//	}
//
//	
//	private void checkLoginOrNo() {
//		//
//
//		if (true) {
//			//do nthng
//
//		} else {
//			//go to the table 
//
//
//			this.finish();
//		}
//
//	}
//
//	public boolean isOnline() {
//		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo netInfo = cm.getActiveNetworkInfo();
//		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	private class LoginAsync extends AsyncTask<Void, Void, Void> {
//
//		@Override
//		protected void onPreExecute() {
//			super.onPreExecute();
//			// Showing progress dialog
//			pDialog = new ProgressDialog(Login.this,
//					ProgressDialog.THEME_HOLO_LIGHT);
//
//			pDialog.setMessage("Please wait...");
//			pDialog.setCancelable(false);
//			pDialog.show();
//
//		}
//
//		@Override
//		protected Void doInBackground(Void... arg0) {
//			// Creating service handler class instance
//
//			if (isOnline()) {
//				//check 
//				try {
//					PostLogin(postParams);
//					//inspect elemnts 
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(Void result) {
//			super.onPostExecute(result);
//			// Dismiss the progress dialog
//			if (pDialog.isShowing())
//				pDialog.dismiss();
//			/**
//			 * Updating parsed JSON data into ListView
//			 * */
//			conn.disconnect();
//			//Toast.makeText(getApplicationContext(), "successful", Toast.LENGTH_LONG).show();
//			//ÇÙåÑ ÇáÌÏæá
//			if (responsdataFromLogin.contains("المواد المسجلة")) {
//				System.out.println("success");
//				
//			} else {
//				System.out.println("unsuccessful");
//			}
//
//		}
//
//		public void PostLogin(String postParameter) throws IOException {
//// html responce 
//			responsdataFromLogin = new String();
//
////save data remember me 
//			CookieHandler.setDefault(new CookieManager());
//
//			URL obj = new URL(url);
//			//url link of the login page 
//			conn = (HttpURLConnection) obj.openConnection();
//			// Acts like a browser
//
//			//these are needed for the connection fixed not changed 
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("User-Agent", USER_AGENT);
//			conn.setRequestProperty("Content-Type",
//					"application/x-www-form-urlencoded");
//
//			conn.setDoOutput(true); // ÈÏí ÇØáÈ ÏÇÊÇ ãÔ ÈÓ ÇÎÏ pass user
//			conn.setDoInput(true);
//			
//			if (conn !=null)
//			{
//				DataOutputStream wr = new DataOutputStream(conn.getOutputStream());// 
//				wr.writeBytes(postParameter);
//				wr.flush();
//
//				wr.close();
//	//ÈÏí ãäß 
//				BufferedReader in = new BufferedReader(new InputStreamReader(
//						conn.getInputStream()));
//				String inputLine;
//				StringBuffer response = new StringBuffer();
//
//				while ((inputLine = in.readLine()) != null) {
//					response.append(inputLine);
//				}
//				in.close();
//				responsdataFromLogin = response.toString();
//	//ÇáÑÞã ÇáãÊÛíÑ
//				GetTheIDforUrl(responsdataFromLogin);
//				System.out.println(responsdataFromLogin);
//
//			}
//
//			//ÈÇáäÓÈå Çáí 
//			
//		}
//
//		//link for jadwal 
//		private String GetTheIDforUrl(String responsdataFromLogin) {
//			// TODO Auto-generated method stub
//			Document doc = Jsoup.parse(responsdataFromLogin);
//			Elements ele = doc.getAllElements();
//			String GetUrl = ele.select("#pInstance").attr("value"); //#pInstance id for the number which changed
//			System.out.println("HERE  " + GetUrl);
////login and get the link after it ÇáÌÏæá
//			try {
//				GetSchedualPage("http://sis.yu.edu.jo/pls/yuapps/f?p=134:1:"+ GetUrl + "::NO:::");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return GetUrl;
//
//		}
//
//		private void GetSchedualPage(String URLTOW) throws Exception {
//			// TODO Auto-generated method stub
//			String url_sch = URLTOW;
//
//			URL obj2 = new URL(url_sch);
//			conn = (HttpURLConnection) obj2.openConnection();
//
//			// default is GET
//			conn.setRequestMethod("GET");
//
//			conn.setUseCaches(false);
//
//			// act like a browser
//			conn.setRequestProperty("User-Agent", USER_AGENT);
//			if (conn!= null)
//			{
//				BufferedReader in = new BufferedReader(new InputStreamReader(
//						conn.getInputStream())); //gives html
//				String inputLine;
//				StringBuffer response = new StringBuffer();
//
//				while ((inputLine = in.readLine()) != null) {
//					response.append(inputLine);
//					System.out.println(inputLine);
//				}
//				in.close();
//
//				responsdataFromLogin = response.toString();
//				getFormParams(responsdataFromLogin);
//				// System.out.println(response);
//
//				System.out.println(responsdataFromLogin);
//
//			}
//			
//		}
//
//		public String getFormParams(String html) throws Exception {
//
//			
//			//new login 
//			Document doc = Jsoup.parse(html);
//
//			Elements ele = doc
//					.select("#report_std_classes > tbody > tr:nth-child(3) > td > table > tbody")
//					.first().children();  //in html
//			for (Element sub : ele) {
//				String ClassID = sub.select("td:nth-child(2)").text(); // col 2 start from 1 
//				String ClassHours = sub.select("td:nth-child(5)").text();
//				String ClassNumber = sub.select("td:nth-child(4)").text();
//				String ClassName = sub.select("td:nth-child(3)").text();
//				String TimeTo = sub.select("td:nth-child(7)").text();
//				String TimeFrom = sub.select("td:nth-child(6)").text();
//				String sat = sub.select("td:nth-child(9)").text();
//				String sun = sub.select("td:nth-child(10)").text();
//				String mon = sub.select("td:nth-child(11)").text();
//				String tue = sub.select("td:nth-child(12)").text();
//				String wed = sub.select("td:nth-child(13)").text();
//				String thur = sub.select("td:nth-child(14)").text();
//				String daysNumber = setDayNumber(sat, sun, mon, tue, wed, thur);
//				String ClassRoom = sub.select("td:nth-child(16)").text();
//				String Teacher = sub.select("td:nth-child(17)").text();
//				// Google form id
//
//			}
//
//			return "";
//
//		}
//	}
//
//	private static String setDayNumber(String sat, String sun, String mon,
//			String tue, String wed, String thur) {
//		String daysNumber = "";
//		if (sun.contains("X")) {
//			daysNumber = daysNumber + 1;
//		}
//		if (mon.contains("X")) {
//			daysNumber = daysNumber + 2;
//		}
//		if (tue.contains("X")) {
//			daysNumber = daysNumber + 3;
//		}
//		if (wed.contains("X")) {
//			daysNumber = daysNumber + 4;
//		}
//		if (thur.contains("X")) {
//			daysNumber = daysNumber + 5;
//		}
//		if (sat.contains("X")) {
//			daysNumber = daysNumber + 6;
//		}
//		return daysNumber;
//	}
//
//

}
