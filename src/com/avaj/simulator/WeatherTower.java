package com.avaj.simulator;
import com.avaj.flyable.Coordinates;
import com.avaj.weather.WeatherProvider;

public class WeatherTower extends Tower {

  public String getWeather(Coordinates coordinates) {
    return WeatherProvider.getProvider().getCurrentWeather(coordinates);
  }

  void changeWeather() {
    this.conditionsChanged();
  }

}