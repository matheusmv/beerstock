package com.github.matheusmv.beerstock.exception;

public class BeerAlreadyRegisteredException extends RuntimeException {

    public BeerAlreadyRegisteredException(String beerName) {
        super(String.format("Beer with name %s already registered in the system.", beerName));
    }
}
