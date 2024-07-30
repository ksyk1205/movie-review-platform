package com.moviereview.application.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record ReviewCreateRequest (
    @Min(0)
    @Max(5)
    @NotEmpty()
    Double rating,
    @NotEmpty()
    String comment
){
}
