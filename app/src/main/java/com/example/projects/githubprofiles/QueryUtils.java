package com.example.projects.githubprofiles;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    public QueryUtils() {
    }

    private static String LOG_TAG= QueryUtils.class.getSimpleName();

    public static List<Details> fetchData(String requestUrl){
        URL url = createUrl(requestUrl);
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {

        }
        List<Details> e = extractFeatureFromJson(jsonResponse);
        return e;
    }
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);

        } catch (IOException e) {
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();

            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    private static List<Details> extractFeatureFromJson(String eJSON) {
        if (TextUtils.isEmpty(eJSON)) {
            return null;
        }
        List<Details> event= new ArrayList<>();
        try {
            JSONArray base = new JSONArray(eJSON);

            for(int i=0;i<base.length(); i++){
                JSONObject a = base.getJSONObject(i);
                String login = a.getString("login");
                String id = a.getString("id");
                String avatarUrl = a.getString("avatar_url");
                String url = a.getString("html_url");
                Details d = new Details(login,avatarUrl,url,id);
                event.add(d);
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the data", e);
        }
        return event;

    }
}
