package com.github.matheusmv.beerstock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BeerStockInsufficientException extends RuntimeException {

    public BeerStockInsufficientException(Long id, int quantityToDecrement) {
        super(String.format("Beers with %d ID for decrement informed have low stock capacity: %s", id, quantityToDecrement));
    }
}
