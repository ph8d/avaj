package com.avaj.flyable;

import com.avaj.flyable.exceptions.InvalidAircraftTypeException;
import com.avaj.flyable.exceptions.InvalidCoordinatesException;

public class AircraftFactory {

  public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws InvalidAircraftTypeException, InvalidCoordinatesException {

    if (longitude < 0) {
      throw new InvalidCoordinatesException("Longitude value should be positive.");
    } else if (latitude < 0) {
      throw new InvalidCoordinatesException("Latitude value should be positive.");
    } else if (height < 0) {
      throw new InvalidCoordinatesException("Height value should be positive.");
    }

    switch (type.toLowerCase()) {
      case "jetplane":
      case "2e66f7e0ad65edb0632b6a3ff6af27ff":
      case "3d5501667cdc38430a9e6ebfeef9867d":
      case "fe240a94da01cd0b329b4c6859c9034e":
      case "554cd647d6b135f7e36ab1214c5e816a":
        return new JetPlane(name, new Coordinates(longitude, latitude, height));
      case "helicopter":
      case "e29e13ac702a77bcd28a4463a211eba4":
      case "632eec75bd3d46b34f202b9157056f24":
      case "2ab8b43468e8b92b0fc5c81e70e35a2d":
        return new Helicopter(name, new Coordinates(longitude, latitude, height));
      case "baloon":
      case "ee6544c4745402bf582d71bd15fd81c5":
      case "f773eab2fdb11a3af8aef54f43f62616":
      case "994736b4f0aec72f6e5ae580051d012f":
        return new Baloon(name, new Coordinates(longitude, latitude, height));
      default:
        throw new InvalidAircraftTypeException("Aircraft type '" + type + "' does not exist.");
    }

  }

}