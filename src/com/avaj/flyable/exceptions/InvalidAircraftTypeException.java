package com.avaj.flyable.exceptions;

import com.avaj.simulator.exceptions.InvalidScenarioException;

public class InvalidAircraftTypeException extends InvalidScenarioException {

  private static final long serialVersionUID = 1L;

  public InvalidAircraftTypeException(String msg) {
    super(msg);
  }

}