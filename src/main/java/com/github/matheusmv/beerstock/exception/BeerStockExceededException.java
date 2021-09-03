package com.github.matheusmv.beerstock.exception;

public class BeerStockExceededException extends RuntimeException {

    public BeerStockExceededException(Long id, int quantityToIncrement) {
        super(String.format("Beers with %s ID to increment informed exceeds the max stock capacity: %s", id, quantityToIncrement));
    }
}
