package com.github.matheusmv.beerstock.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class StandardError {
    private final Instant timestamp;
    private final Integer status;
    private final String error;
    private final String message;
    private final String path;
}
