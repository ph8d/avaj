package com.avaj.flyable;

import com.avaj.simulator.WeatherTower;

public class Baloon extends Aircraft implements Flyable {

  private WeatherTower weatherTower = null;

  Baloon(String name, Coordinates coordinates) {
    super(name, coordinates);
  }

  private void _land() {
    this.log("landing. [lat: " + this.coordinates.getLatitude() + ", long: " + this.coordinates.getLatitude() + "]");
    this.weatherTower.unregister(this);
    this.weatherTower = null;
  }

  public String getDescription() {
    return "Baloon#" + this.name + "(" + this.id + ")";
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
        longitude += 2;
        height += 4;
        this.log("Finally some good weather.");
        break;
      case "RAIN":
        height -= 5;
        this.log("Don't get me wrong, generally I like rain, but not when I'm flying on a balloon!!!");
        break;
      case "FOG":
        height -= 3;
        this.log("I can't see anything in this fog.");
        break;
      case "SNOW":
        height -= 15;
        this.log("I can't feel my face, Im freezing!");
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