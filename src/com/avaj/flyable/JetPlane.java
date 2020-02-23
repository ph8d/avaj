package com.avaj.flyable;

import com.avaj.simulator.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {

  private WeatherTower weatherTower = null;

  JetPlane(String name, Coordinates coordinates) {
    super(name, coordinates);
  }

  private void _land() {
    this.log("landing. [lat: " + this.coordinates.getLatitude() + ", long: " + this.coordinates.getLongitude() + "]");
    this.weatherTower.unregister(this);
    this.weatherTower = null;
  }

  public String getDescription() {
    return "JetPlane#" + this.name + "(" + this.id + ")";
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
        latitude += 10;
        height += 2;
        this.log("The sun is blinding me, I can't see anything.");
        break;
      case "RAIN":
        latitude += 5;
        this.log("Rain is my favourite type of weather.");
        break;
      case "FOG":
        latitude += 1;
        this.log("Fog? Good thing that I have an autopilot.");
        break;
      case "SNOW":
        height -= 7;
        this.log("Snow is so pretty.");
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