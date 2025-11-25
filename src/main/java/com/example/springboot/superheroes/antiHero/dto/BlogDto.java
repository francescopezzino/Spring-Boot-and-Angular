package com.example.springboot.superheroes.antiHero.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {
    private String title;
    private String description;
    private String author;
}