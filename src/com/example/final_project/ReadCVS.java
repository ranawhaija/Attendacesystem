package com.example.final_project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


import android.content.Context;


public class ReadCVS {
	ArrayList<String> FSN = new ArrayList<String>();
	ArrayList<String> FID = new ArrayList<String>();
	ArrayList<String> FName = new ArrayList<String>();
	
	 
	  public void run(String fp,Context c)  {
		  
		String csvFile = fp;
		BufferedReader br = null;
		String line ="";
		String cvsSplitBy = ",";
		ArrayList<String> test = new ArrayList<String>();
		message m1 = null;
		try {
			
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
	 
			        // use comma as separator
				String[] mylist = line.split(cvsSplitBy);
				
				m1 =new message();
			
				for (int i=1;i<mylist.length;i++)
					{
						test.add(mylist[i]);
					}
				
				
				FSN.add(test.get(0));
				FID.add(test.get(1));
				FName.add(test.get(2));
				
				
				test.clear();
					
				}
			// test for reading csv file
			//for (int j=1;j<FSN.size();j++)
			//m1.msg(FSN.get(j)+"\t"+FID.get(j)+"\t"+FName.get(j),c );
			
			//m1.msg(FSN.get(58)+"\t"+FID.get(58)+"\t"+FName.get(58),c );
			
			
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
	  }
	  
	  ArrayList<String> retSN()
	  {
		  return FSN;
	  }
	  
	  ArrayList<String> retFID()
	  {
		  return FID;
	  }
	  
	  ArrayList<String> retFName()
	  {
		  return FName;
	  }
	  
	 
	}
