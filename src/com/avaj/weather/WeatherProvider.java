package com.avaj.weather;
import com.avaj.flyable.Coordinates;

public class WeatherProvider {
  
  private static final String[] weather = { "RAIN", "FOG", "SUN", "SNOW" };
  private static final WeatherProvider weatherProvider = new WeatherProvider();

  private WeatherProvider() {}

  public static WeatherProvider getProvider() {
    return WeatherProvider.weatherProvider;
  }

  public String getCurrentWeather(Coordinates coordinates) {
    int random = (coordinates.getLongitude() - coordinates.getLatitude() + coordinates.getHeight()) % 16;
    String weather;

    if (random <= 4) {
      weather = WeatherProvider.weather[3];
    } else if (random <= 8) {
      weather = WeatherProvider.weather[2];
    } else if (random <= 12) {
      weather = WeatherProvider.weather[1];
    } else {
      weather = WeatherProvider.weather[0];
    }

    return weather;
  }


} 