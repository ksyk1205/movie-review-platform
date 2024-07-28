package com.moviereview.application.dto;

public record ReviewCreateRequest (
    Double rating,
    String comment
){
}
