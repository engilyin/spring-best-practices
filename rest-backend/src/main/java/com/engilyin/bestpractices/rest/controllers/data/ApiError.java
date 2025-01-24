package com.engilyin.bestpractices.rest.controllers.data;

public record ApiError(String code, String message, ErrorDetail... details) {}
