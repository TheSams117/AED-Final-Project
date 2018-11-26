/**
 * 
 */
package com.triadamcola.triadamroutes;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author ASUS
 *
 */
public class Routes {
	
	private static final String API_KEY= "AIzaSyAFv-dr1ML_bELibByV1IepG2F_aWmsJ7Y";

	

	/**
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ApiException 
	 * 
	 */
	public Routes() throws ApiException, InterruptedException, IOException {
		
	}
	
	
	public Response getDistance(String origin, String destinations) throws IOException {
	    OkHttpClient client = new OkHttpClient();
	    String url="https://maps.googleapis.com/maps/api/distancematrix/json?origins="+origin+"&destinations="+destinations+"&key="+ API_KEY;
	    Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
		return response;
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ApiException 
	 */
	public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		new Routes();
	}

}
