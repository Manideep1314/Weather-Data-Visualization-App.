package data;

public class WeatherData {
    private String date;
    private double temperature;
    private double humidity;
    private double precipitation;

    public WeatherData(String date, double temperature, double humidity, double precipitation) {
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.precipitation = precipitation;
    }

    public String getDate() { return date; }
    public double getTemperature() { return temperature; }
    public double getHumidity() { return humidity; }
    public double getPrecipitation() { return precipitation; }
}
