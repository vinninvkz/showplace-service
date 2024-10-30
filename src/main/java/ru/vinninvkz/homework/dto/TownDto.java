package ru.vinninvkz.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TownDto {
    private Long id;
    private String name;
    private long population;
    private boolean hasMetro;
    private List<Long> showplaceIds;

}
