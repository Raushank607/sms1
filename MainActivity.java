package com.example.androidrecivesms;

import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsMessage;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends BroadcastReceiver {
	public static final String SMS_BUNDLE = "pdus";
   
	public void onReceive(Context context, Intent intent) {
		  Bundle intentExtras = intent.getExtras();
	        if (intentExtras != null) {
	            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
	            String smsMessageStr = "";
	            for (int i = 0; i < sms.length; ++i) {
	                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

	                String smsBody = smsMessage.getMessageBody().toString();
	                String address = smsMessage.getOriginatingAddress();

	                smsMessageStr += "SMS From: " + address + "\n";
	                smsMessageStr += smsBody + "\n";
	            }
	            Toast.makeText(context, smsMessageStr, Toast.LENGTH_SHORT).show();

	            //this will update the UI with message
	            SmsActivity inst = SmsActivity.instance();
	            inst.updateList(smsMessageStr);
	        }
	    }
	
		
	}

