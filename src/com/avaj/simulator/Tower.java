package com.avaj.simulator;
import com.avaj.flyable.Flyable;
import com.avaj.logger.Logger;

import java.util.concurrent.CopyOnWriteArrayList;

public class Tower {

  private CopyOnWriteArrayList<Flyable> observers = new CopyOnWriteArrayList<Flyable>();

  protected void _log(String msg) {
    Logger.log("Tower says: " + msg);
  }

  public void register(Flyable flyable) {
    observers.add(flyable);
    this._log(flyable.getDescription() + " registered to weather tower.");
  }

  public void unregister(Flyable flyable) {
    observers.remove(flyable);
    this._log(flyable.getDescription() + " unregistered from weather tower.");
  }

  protected void conditionsChanged() {
    for(int i = 0; i < observers.size(); i++) {
      observers.get(i).updateConditions();
    }
  }

}