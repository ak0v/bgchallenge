package com.bg.restchallange.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class RestRequestHelper {
	
	private static Gson gson = new Gson();
	private static HttpURLConnection connection = null; 
	
	
	public static <T> T getResponse(RestRequest<T> request, Class<T> responseClass) {
		// TODO Auto-generated method stub

		String targetURL = "https://api.iextrading.com/1.0/stock/"+ request.getPath();
	    T parsedResponse = null;
		try {
			// Create connection
			URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(request.getMethodType().toString());
			connection.setUseCaches(false);
			connection.setDoOutput(false);
			connection.setDoInput(true);

			// Send request
			connection.connect();

			// Get response
			InputStream is = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder();
			String line;

			while ((line = reader.readLine()) != null) {
				response.append(line);
				response.append('\n');
			}

			reader.close();
			parsedResponse = gson.fromJson(response.toString(), responseClass);
			return parsedResponse;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return parsedResponse; 
	}
}
 
