package ru.vinninvkz.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.vinninvkz.homework.dto.ShowplaceDto;
import ru.vinninvkz.homework.model.Attraction;
import ru.vinninvkz.homework.model.Showplace;
import ru.vinninvkz.homework.model.Town;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ShowplaceMapper {

    ShowplaceMapper INSTANCE = Mappers.getMapper(ShowplaceMapper.class);

    @Mapping(source = "town.id", target = "townId")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "attractionList", target = "attractionNames", qualifiedByName = "mapAttractions")
    ShowplaceDto showplaceToShowplaceDTO(Showplace showplace);

    @Mapping(source = "townId", target = "town")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "attractionNames", target = "attractionList", ignore = true)
    Showplace showplaceDtoToShowplace(ShowplaceDto showplaceDTO);

//    @Named("mapAttractions")
//    default List<String> mapAttractions(List<Attraction> attractions) {
//        if (attractions == null) {
//            return null;
//        }
//        return attractions.stream()
//                .map(Attraction::getName)
//                .collect(Collectors.toList());
//    }
//
//    default Town map(Long townId) {
//        if (townId == null) {
//            return null;
//        }
//        Town town = new Town();
//        town.setId(townId);
//        return town;
//    }
}
