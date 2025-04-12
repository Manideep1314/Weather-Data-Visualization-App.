package api;

import data.WeatherData;
import java.util.*;
import java.io.*;
import java.net.*;
import org.json.*;

public class APIHandler {
    private static final String API_KEY = "27a7c6af262105a0ce0757b3fd2ac3a6"; // your real key

    public List<WeatherData> fetchWeatherData(String city) {
        List<WeatherData> weatherList = new ArrayList<>();
        try {
            String urlStr = "https://api.openweathermap.org/data/2.5/forecast?q=" + URLEncoder.encode(city, "UTF-8")
                    + "&appid=" + API_KEY + "&units=metric";
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject json = new JSONObject(response.toString());
            JSONArray list = json.getJSONArray("list");

            for (int i = 0; i < list.length(); i += 8) { // every 24h
                JSONObject obj = list.getJSONObject(i);
                JSONObject main = obj.getJSONObject("main");

                String date = obj.getString("dt_txt").split(" ")[0];
                double temp = main.getDouble("temp");
                double humidity = main.getDouble("humidity");
                double precipitation = 0.0; // You can add rain parsing later

                WeatherData data = new WeatherData(date, temp, humidity, precipitation);
                weatherList.add(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return weatherList;
    }
}
