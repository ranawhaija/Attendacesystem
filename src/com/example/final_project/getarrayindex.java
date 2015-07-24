package com.example.final_project;

import java.lang.reflect.Array;

public class getarrayindex {
	
	public final int getArrayIndex(String[] myArray, String myObject) {
	    int ArraySize = Array.getLength(myArray);// get the size of the array
	    for (int i = 0; i < ArraySize; i++) {
	        if (myArray[i].equals(myObject)) {
	            return (i);
	        }
	    }
	    return (-1);// didn't find what I was looking for
	}

}
