package com.searshc.mercy.util;

import java.io.OutputStream;
import java.io.PrintStream;

public class MercyTrace {
	
	public static void switchTraceOn(boolean flag){
		if(flag == Boolean.TRUE){
			PrintStream originalStream = System.out;
			System.setOut(originalStream);
		}else
		{
			PrintStream blankStream  = new PrintStream(new OutputStream(){
			    public void write(int b) {
			        //no output
			    }
			});
			System.setOut(blankStream);
			}
		}
	}
