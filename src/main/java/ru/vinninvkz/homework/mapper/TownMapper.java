package ru.vinninvkz.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.vinninvkz.homework.dto.TownDto;
import ru.vinninvkz.homework.model.Showplace;
import ru.vinninvkz.homework.model.Town;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface TownMapper {

    TownMapper INSTANCE = Mappers.getMapper(TownMapper.class);

    @Mapping(source = "showplaceList", target = "showplaceIds", qualifiedByName = "mapShowplaceIds")
    TownDto townToTownDto(Town town);

    @Mapping(source = "showplaceIds", target = "showplaceList", ignore = true)
    Town townDtoToTown(TownDto townDTO);

//    @Named("mapShowplaceIds")
//    default List<Long> mapShowplaceIds(List<Showplace> showplaces) {
//        if (showplaces == null) {
//            return null;
//        }
//        return showplaces.stream()
//                .map(Showplace::getId)
//                .collect(Collectors.toList());
//    }
}

