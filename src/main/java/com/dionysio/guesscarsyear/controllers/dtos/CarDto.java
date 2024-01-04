package com.dionysio.guesscarsyear.controllers.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CarDto(@NotBlank(message = "brand must not be blank") String brand,
                     @NotBlank(message = "model must not be blank") String model,
                     @Min(value = 1850, message = "year should be greater than or equal to 1850")
                     @Max(value = 2024, message = "year should be lesser than or equal to 2024")
                     int year,
                     @NotBlank(message = "img must not be blank") String img) {

}
