package com.iansd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Util {

	public static String makeJSONText(String type, String desc) {
		String JSONtext = "{\"type\": \"" + type + "\"," + "\"desc\": \""
				+ desc + "\"}";
		return JSONtext;
	}

	public static void makeHttpRequest(final String urlString,
			final HttpRequestListener httpRequestListener) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				URL url = null;
				try {
					url = new URL(urlString);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
					httpRequestListener.onError(e1.getMessage());
					return;
				}
				URLConnection urlConnection = null;
				try {
					urlConnection = url.openConnection();
				} catch (IOException e1) {
					e1.printStackTrace();
					httpRequestListener.onError(e1.getMessage());
					return;
				}
				BufferedReader in = null;
				try {
					in = new BufferedReader(new InputStreamReader(
							urlConnection.getInputStream()));
				} catch (IOException e) {
					e.printStackTrace();
					httpRequestListener.onError(e.getMessage());
					return;
				}
				String lines = "";
				String inputLine;

				try {
					while ((inputLine = in.readLine()) != null) {
						lines += inputLine;
					}
					httpRequestListener.onSuccess(lines);
				} catch (IOException e) {
					e.printStackTrace();
					httpRequestListener.onError(e.getMessage());
					return;
				}
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
		thread.start();
	}

	public static abstract class HttpRequestListener {
		public abstract void onSuccess(String s);

		public abstract void onError(String error);
	}
}
