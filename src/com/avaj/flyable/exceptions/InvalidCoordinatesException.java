package com.avaj.flyable.exceptions;

import com.avaj.simulator.exceptions.InvalidScenarioException;

public class InvalidCoordinatesException extends InvalidScenarioException {

  private static final long serialVersionUID = 2L;

  public InvalidCoordinatesException(String msg) {
    super(msg);
  }

}