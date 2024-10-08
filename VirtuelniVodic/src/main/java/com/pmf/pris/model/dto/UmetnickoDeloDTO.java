package com.pmf.pris.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import model.Umetnickodelo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UmetnickoDeloDTO {
    private String naziv;
    private String opis;
    private double latitude; // Geografska sirina
    private double longitude; // Geografska duzina
    private Date datum;

    public UmetnickoDeloDTO toDto(Umetnickodelo delo){
        return new UmetnickoDeloDTO(delo.getNaziv(), delo.getOpis(), delo.getGeografskaSirina(), delo.getGeografskaDuzina(), delo.getDatum());
    }
}
