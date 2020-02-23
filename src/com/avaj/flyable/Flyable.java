package com.avaj.flyable;

import com.avaj.simulator.WeatherTower;

public interface Flyable {
  void updateConditions();
  void registerTower(WeatherTower weatherTower);
  String getDescription();
}