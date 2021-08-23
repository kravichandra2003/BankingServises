package com.chandra.demo.controllerAdvisordemo;


public class NoProductsFoundException extends RuntimeException {

  public NoProductsFoundException(String string) {
    super(String.format("Product with Type/Name %s not found", string));

  }

  public NoProductsFoundException(String price, String type) {
    super(String.format("Product with price %s type %s not found", price, type));
  }

  public NoProductsFoundException() {
    super(String.format("Products not found"));
  }


}
