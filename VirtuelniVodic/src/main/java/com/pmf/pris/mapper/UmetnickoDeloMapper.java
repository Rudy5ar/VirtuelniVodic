package com.pmf.pris.mapper;

import com.pmf.pris.model.dto.UmetnickoDeloDTO;
import model.Umetnickodelo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UmetnickoDeloMapper {

    // Convert Umetnickodelo entity to UmetnickoDeloDTO
    public UmetnickoDeloDTO toDto(Umetnickodelo delo) {
        if (delo == null) {
            return null;
        }
        return new UmetnickoDeloDTO(
                delo.getNaziv(),
                delo.getOpis(),
                delo.getGeografskaSirina(),
                delo.getGeografskaDuzina()
        );
    }

    // Convert list of Umetnickodelo entities to list of UmetnickoDeloDTOs
    public List<UmetnickoDeloDTO> toDtoList(List<Umetnickodelo> dela) {
        if (dela == null || dela.isEmpty()) {
            return null;
        }
        return dela.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Convert UmetnickoDeloDTO to Umetnickodelo entity (if needed)
    public Umetnickodelo toEntity(UmetnickoDeloDTO dto) {
        if (dto == null) {
            return null;
        }
        Umetnickodelo delo = new Umetnickodelo();
        delo.setNaziv(dto.getNaziv());
        delo.setOpis(dto.getOpis());
        delo.setGeografskaSirina(dto.getLatitude());
        delo.setGeografskaDuzina(dto.getLongitude());
        return delo;
    }

    // Convert list of UmetnickoDeloDTOs to list of Umetnickodelo entities (if needed)
    public List<Umetnickodelo> toEntityList(List<UmetnickoDeloDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return null;
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
