package ru.vinninvkz.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vinninvkz.homework.model.Showplace;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttractionDto {
    private Long id;
    private String name;
    private String description;
    private List<Showplace> showplaceList;
}
