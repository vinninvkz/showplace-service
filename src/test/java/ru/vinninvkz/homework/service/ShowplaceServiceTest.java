package ru.vinninvkz.homework.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.vinninvkz.homework.aspect.TownNotFoundException;
import ru.vinninvkz.homework.dto.ShowplaceDto;
import ru.vinninvkz.homework.model.Showplace;
import ru.vinninvkz.homework.model.Town;
import ru.vinninvkz.homework.repository.ShowplaceRepository;
import ru.vinninvkz.homework.repository.TownRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ShowplaceServiceTest {

    @Mock
    private ShowplaceRepository showplaceRepository;

    @Mock
    private TownRepository townRepository;

    @InjectMocks
    private ShowplaceService showplaceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddShowplace_TownNotFound() {
        ShowplaceDto dto = new ShowplaceDto();
        dto.setTownId(1L);

        when(townRepository.findById(anyLong())).thenReturn(Optional.empty());

        TownNotFoundException exception = assertThrows(TownNotFoundException.class, () -> {
            showplaceService.addShowplace(dto);
        });

        assertEquals("Town not found", exception.getMessage());
    }

    @Test
    void testAddShowplace_Success() {
        Town town = new Town();
        town.setId(1);
        Showplace showplace = new Showplace();
        showplace.setTown(town);
        ShowplaceDto dto = new ShowplaceDto();
        dto.setTownId(1L);

        when(townRepository.findById(anyLong())).thenReturn(Optional.of(town));
        when(showplaceRepository.save(any(Showplace.class))).thenReturn(showplace);

        ShowplaceDto result = showplaceService.addShowplace(dto);
        assertNotNull(result);
    }

    @Test
    void testGetAllShowplaces_Success() {
        Showplace showplace = new Showplace();
        when(showplaceRepository.findAllByTypeSortedByName(anyString())).thenReturn(Optional.of(List.of(showplace)));

        List<ShowplaceDto> result = showplaceService.getAllShowplaces("type");
        assertFalse(result.isEmpty());
    }

    @Test
    void testDeleteShowplace_ShowplaceNotFound() {
        when(showplaceRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            showplaceService.deleteShowplace(1L);
        });

        assertEquals("Showplace not found", exception.getMessage());
    }

    @Test
    void testDeleteShowplace_Success() {
        Showplace showplace = new Showplace();
        when(showplaceRepository.findById(anyLong())).thenReturn(Optional.of(showplace));

        showplaceService.deleteShowplace(1L);
        verify(showplaceRepository, times(1)).delete(showplace);
    }
}
