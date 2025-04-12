package gui;

import api.APIHandler;
import data.WeatherData;
import graph.GraphManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUIManager {
    private JFrame frame;
    private JTextArea textArea;

    public void initUI() {
        JTextField cityInput = new JTextField("Auburn Hills", 15);

        frame = new JFrame("Weather Data Viewer");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Buttons
        JButton fetchButton = new JButton("Show Weather");
        JButton tempButton = new JButton("Show Temperature Chart");
        JButton humidityButton = new JButton("Show Humidity Chart");
        JButton precipButton = new JButton("Show Precipitation Chart");
        JButton demoButton = new JButton("Demo Precipitation Chart");

        // Text area
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Handlers
        APIHandler handler = new APIHandler();
        GraphManager gm = new GraphManager();

        // Button Actions
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = cityInput.getText();
                List<WeatherData> list = handler.fetchWeatherData(city);
                textArea.setText("");
                for (WeatherData data : list) {
                    textArea.append("Date: " + data.getDate() + "\n");
                    textArea.append("Temp: " + data.getTemperature() + "°C\n");
                    textArea.append("Humidity: " + data.getHumidity() + "%\n");
                    textArea.append("Precipitation: " + data.getPrecipitation() + " mm\n");
                    textArea.append("-----------------------------\n");
                }
            }
        });

        tempButton.addActionListener(e -> gm.showTemperatureChart(handler.fetchWeatherData(cityInput.getText())));
        humidityButton.addActionListener(e -> gm.showHumidityChart(handler.fetchWeatherData(cityInput.getText())));
        precipButton.addActionListener(e -> gm.showPrecipitationChart(handler.fetchWeatherData(cityInput.getText())));
demoButton.addActionListener(e -> {
    List<WeatherData> demoList = new ArrayList<>();
    demoList.add(new WeatherData("2025-04-12", 9.2, 38.0, 1.5));
    demoList.add(new WeatherData("2025-04-13", 8.4, 53.0, 2.3));
    demoList.add(new WeatherData("2025-04-14", 8.0, 72.0, 0.8));
    demoList.add(new WeatherData("2025-04-15", 10.7, 67.0, 1.2));
    demoList.add(new WeatherData("2025-04-16", 5.3, 62.0, 3.0));

    gm.showPrecipitationChart(demoList);
});
        // Layout
        JPanel topPanel = new JPanel();
        topPanel.add(cityInput);
        topPanel.add(fetchButton);
        topPanel.add(tempButton);
        topPanel.add(humidityButton);
        topPanel.add(precipButton);
        topPanel.add(demoButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
