package ru.vinninvkz.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vinninvkz.homework.aspect.TownNotFoundException;
import ru.vinninvkz.homework.dto.TownDto;
import ru.vinninvkz.homework.mapper.TownMapper;
import ru.vinninvkz.homework.model.Town;
import ru.vinninvkz.homework.repository.TownRepository;

@Service
public class TownService {


    private TownRepository townRepository;

    @Autowired
    public TownService(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    public TownDto addTown(TownDto townDto) {
        Town town = TownMapper.INSTANCE.townDtoToTown(townDto);
        Town savedTown = townRepository.save(town);
        return TownMapper.INSTANCE.townToTownDto(savedTown);
    }

    public TownDto updateTown(Long id, long population, boolean hasMetro) {
        Town town = townRepository.findById(id)
                .orElseThrow(() -> new TownNotFoundException("Town not found"));
        town.setPopulation(population);
        town.setHasMetro(hasMetro);
        Town updatedTown = townRepository.save(town);
        return TownMapper.INSTANCE.townToTownDto(updatedTown);
    }


}
