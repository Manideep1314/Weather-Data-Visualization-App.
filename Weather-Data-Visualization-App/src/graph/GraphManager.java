package graph;

import data.WeatherData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;

public class GraphManager {

    public void showTemperatureChart(List<WeatherData> weatherList) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (WeatherData data : weatherList) {
            dataset.addValue(data.getTemperature(), "Temperature", data.getDate());
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Temperature Over Time",
                "Date",
                "Temperature (°C)",
                dataset
        );

        displayChart(chart, "Temperature Chart");
    }

    public void showHumidityChart(List<WeatherData> weatherList) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (WeatherData data : weatherList) {
            dataset.addValue(data.getHumidity(), "Humidity", data.getDate());
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Humidity Over Time",
                "Date",
                "Humidity (%)",
                dataset
        );

        displayChart(chart, "Humidity Chart");
    }

    public void showPrecipitationChart(List<WeatherData> weatherList) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (WeatherData data : weatherList) {
            dataset.addValue(data.getPrecipitation(), "Precipitation", data.getDate());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Precipitation Over Time",
                "Date",
                "Precipitation (mm)",
                dataset
        );

        displayChart(chart, "Precipitation Chart");
    }

    private void displayChart(JFreeChart chart, String title) {
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(chartPanel);
        frame.setVisible(true);
    }
}
