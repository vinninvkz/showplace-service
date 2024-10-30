package ru.vinninvkz.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowplaceDto {
    private Long id;
    private String name;
    private LocalDate creationDate;
    private String description;
    private String type;
    private Long townId;
    private List<String> attractionNames;
}





