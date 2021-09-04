package com.github.matheusmv.beerstock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BeerAlreadyRegisteredException extends RuntimeException {

    public BeerAlreadyRegisteredException(String beerName) {
        super(String.format("Beer with name %s already registered in the system.", beerName));
    }
}
