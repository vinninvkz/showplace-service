package ru.vinninvkz.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vinninvkz.homework.aspect.EntityNotFoundException;
import ru.vinninvkz.homework.aspect.ShowplaceNotFoundException;
import ru.vinninvkz.homework.aspect.TownNotFoundException;
import ru.vinninvkz.homework.dto.ShowplaceDto;
import ru.vinninvkz.homework.mapper.ShowplaceMapper;
import ru.vinninvkz.homework.model.Showplace;
import ru.vinninvkz.homework.model.Town;
import ru.vinninvkz.homework.repository.ShowplaceRepository;
import ru.vinninvkz.homework.repository.TownRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class ShowplaceService {
    private final ShowplaceRepository showplaceRepository;
    private final TownRepository townRepository;

    @Autowired
    public ShowplaceService(ShowplaceRepository showplaceRepository, TownRepository townRepository) {
        this.showplaceRepository = showplaceRepository;
        this.townRepository = townRepository;
    }

    public ShowplaceDto addShowplace(ShowplaceDto showplaceDto) {
        Town town = townRepository.findById(showplaceDto.getTownId()).orElseThrow(() -> new TownNotFoundException("Town not found"));
        Showplace showplace = ShowplaceMapper.INSTANCE.showplaceDtoToShowplace(showplaceDto);
        Showplace savedShowplace = showplaceRepository.save(showplace);
        return ShowplaceMapper.INSTANCE.showplaceToShowplaceDTO(savedShowplace);
    }

    public List<ShowplaceDto> getAllShowplaces(String type) {
        List<Showplace> showplaces = showplaceRepository.findAllByTypeSortedByName(type).orElseThrow();
        return showplaces.stream()
                .map(ShowplaceMapper.INSTANCE::showplaceToShowplaceDTO)
                .collect(Collectors.toList());
    }

    public List<ShowplaceDto> getShowplacesByTown(String townName) {
        Town town = townRepository.findByName(townName).orElseThrow(() -> new TownNotFoundException("Town : " + townName + " not found"));
        List<Showplace> showplaces = showplaceRepository.findByTownName(townName).orElseThrow(() -> new ShowplaceNotFoundException("Showplaces  not found"));
        if (showplaces.isEmpty()) {
            throw new EntityNotFoundException("No showplaces found for town: " + townName);
        }
        return showplaces.stream()
                .map(ShowplaceMapper.INSTANCE::showplaceToShowplaceDTO)
                .collect(Collectors.toList());
    }

    public ShowplaceDto updateShowplaceDescription(Long id, String newDescription) {
        Showplace showplace = showplaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Showplace not found"));
        showplace.setDescription(newDescription);
        Showplace updatedShowplace = showplaceRepository.save(showplace);
        return ShowplaceMapper.INSTANCE.showplaceToShowplaceDTO(updatedShowplace);
    }

    public void deleteShowplace(Long id) {
        Showplace showplace = showplaceRepository.findById(id)
                .orElseThrow(() -> new ShowplaceNotFoundException("Showplace not found"));
        showplaceRepository.delete(showplace);
    }
}
