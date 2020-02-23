package com.avaj.flyable;

import com.avaj.logger.Logger;

public abstract class Aircraft {

  private static long idCounter = 0;

  protected long id;
  protected String name;
  protected Coordinates coordinates;

  protected Aircraft(String name, Coordinates coordinates) {
    this.id = nextId();
    this.name = name;
    this.coordinates = coordinates;
  }

  protected void log(String msg) {
    String aircraftDescription = this.getDescription();
    Logger.log(aircraftDescription + ": " + msg);
  }

  private long nextId() {
    return idCounter++;
  }

  public abstract String getDescription();
}