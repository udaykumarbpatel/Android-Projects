/*
a. Midterm
b. AsyncFavoritesGet.java
c. Udaykumar Bhupendra Kumar
 */

package com.example.rottentomatoes;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncFavoritesGet extends AsyncTask<String, Void, Response> {
	Result_Favorite result_favorite;

	public AsyncFavoritesGet(Result_Favorite result_favorite) {
		this.result_favorite = result_favorite;
	}

	@Override
	protected Response doInBackground(String... params) {
		String urlString = params[0];
		HttpPost request = new HttpPost(urlString);
		HttpClient client = new DefaultHttpClient();
		List<NameValuePair> argument = new ArrayList<NameValuePair>();
		argument.add(new BasicNameValuePair("uid", Config.getUid()));
		InputStream in = null;
		try {
			UrlEncodedFormEntity formParams = new UrlEncodedFormEntity(argument);
			request.setEntity(formParams);
			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				in = response.getEntity().getContent();
				Response result = ResponseUtil.ResponseXMLPullParser
						.parseResponse(in);
				return result;
			}

		} catch (MalformedURLException e) {
			Log.d("demo", e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("demo", e.getMessage());
			e.printStackTrace();
		} catch (NumberFormatException e) {
			Log.d("demo", e.getMessage());
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			Log.d("demo", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Response result) {
		if (Integer.parseInt(result.error_id) < 0 || result.movie_ids.length() < 2 ) {
			result_favorite.result_toast(result);
		} 
		if (result.movie_ids.length() > 2) {
			result_favorite.result_get_all_fav(result);
		}
	}

	public interface Result_Favorite {
		void result_toast(Response result);

		void result_get_all_fav(Response result);
	}
}