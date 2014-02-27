package com.example.restapiservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener  {
	
	public static String language_hin = "hindi";
	public static String language_tam = "tamil";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.my_button).setOnClickListener(this); 
	}

	
	@Override
	public void onClick(View arg0) {
		
		Button b = (Button) findViewById(R.id.my_button);
		b.setClickable(false);
		System.out.println("Getting response");
		new GetRestAPIResponse().execute(); 
	} 

	
	private class GetRestAPIResponse extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {

			try {

				System.out.println("Calling API");
				HttpClient client = new DefaultHttpClient();

				String getURL = "http://transliteration.reverie.co.in/api/processString?lang=hindi&type=5&inString=hello";
				
				HttpGet get = new HttpGet(getURL);
				get.setHeader("REV-API-KEY",
						"!REVTESTINGAPI!sdfg");
				HttpResponse responseGet = client.execute(get);
				HttpEntity resEntityGet = responseGet.getEntity();
				String getResponse = EntityUtils.toString(resEntityGet);
				System.out.println(getResponse);
				
				// Convert String to json object
				JSONObject json = new JSONObject(getResponse);

				// get name from object
				String name_response = json.getString("response");
				System.out.println(name_response);
				
				return getResponse;

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}
		
	}
}


