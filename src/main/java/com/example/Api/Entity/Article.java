package com.example.Api.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @jakarta.persistence.Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long Id;
    private LocalDate fechaPub;

}
