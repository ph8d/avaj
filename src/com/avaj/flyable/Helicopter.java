package com.avaj.flyable;

import com.avaj.simulator.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

  private WeatherTower weatherTower = null;

  Helicopter(String name, Coordinates coordinates) {
    super(name, coordinates);
  }

  private void _land() {
    this.log("landing. [lat: " + this.coordinates.getLatitude() + ", long: " + this.coordinates.getLongitude() + "]");
    this.weatherTower.unregister(this);
    this.weatherTower = null;
  }

  public String getDescription() {
    return "Helicopter#" + this.name + "(" + this.id + ")";
  }

  public void registerTower(WeatherTower weatherTower) {
    this.weatherTower = weatherTower;
    this.weatherTower.register(this);
  }

  public void updateConditions() {
    if (this.weatherTower == null) {
      throw new RuntimeException(this.getDescription() + " has not registered to weather tower.");
    }
    
    String weather = this.weatherTower.getWeather(this.coordinates);

    int longitude = this.coordinates.getLongitude();
    int latitude = this.coordinates.getLatitude();
    int height = this.coordinates.getHeight();

    switch (weather) {
      case "SUN":
        longitude += 10;
        height += 2;
        this.log("Too hot for me.");
        break;
      case "RAIN":
        longitude += 5;
        this.log("I hate rain.");
        break;
      case "FOG":
        longitude += 1;
        this.log("Fog again?");
        break;
      case "SNOW":
        height -= 12;
        this.log("Too cold.");
        break;
      default:
        throw new RuntimeException("Invalid weather value: " + weather);
    }

    if (height > 100) {
      height = 100;
    }

    this.coordinates = new Coordinates(longitude, latitude, height);
    
    if (height <= 0) {
      this._land();
    }
  }

}