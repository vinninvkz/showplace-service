package ru.vinninvkz.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vinninvkz.homework.dto.ShowplaceDto;
import ru.vinninvkz.homework.service.ShowplaceService;

import java.util.List;


/**
 * Контроллер для управления операциями с достопримечательностями.
 * Обрабатывает HTTP-запросы для создания, получения, обновления и удаления достопримечательностей.
 */
@RestController
@RequestMapping("/api/v1/showplace")
public class ShowplaceController {
    private final ShowplaceService showplaceService;

    /**
     * Конструктор для внедрения зависимости ShowplaceService.
     *
     * @param showplaceService Сервис для управления достопримечательностями.
     */
    @Autowired
    public ShowplaceController(ShowplaceService showplaceService) {
        this.showplaceService = showplaceService;
    }

    /**
     * Обрабатывает POST-запрос для добавления новой достопримечательности.
     *
     * @param showplaceDto DTO объекта достопримечательности.
     * @return ResponseEntity с добавленной достопримечательностью и статусом CREATED (201).
     */
    @PostMapping
    public ResponseEntity<ShowplaceDto> addShowplace(@RequestBody ShowplaceDto showplaceDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(showplaceService.addShowplace(showplaceDto));
    }

    /**
     * Обрабатывает GET-запрос для получения всех достопримечательностей.
     * Может принимать необязательный параметр для фильтрации по типу.
     *
     * @param type Тип достопримечательности (необязательно).
     * @return ResponseEntity с списком всех достопримечательностей и статусом OK (200).
     */
    @GetMapping
    public ResponseEntity<List<ShowplaceDto>> getAllShowplaces(
            @RequestParam(required = false) String type) {
        List<ShowplaceDto> showplaces = showplaceService.getAllShowplaces(type);
        return ResponseEntity.ok(showplaces);
    }

    /**
     * Обрабатывает GET-запрос для получения всех достопримечательностей конкретного города.
     *
     * @param townName Имя города для поиска достопримечательностей.
     * @return ResponseEntity с списком достопримечательностей города и статусом OK (200).
     */
    @GetMapping("/{townName}")
    public ResponseEntity<List<ShowplaceDto>> getShowplacesByTown(@PathVariable String townName) {
        return ResponseEntity.status(HttpStatus.OK).body(showplaceService.getShowplacesByTown(townName));
    }

    /**
     * Обрабатывает PUT-запрос для обновления описания достопримечательности.
     *
     * @param id             Идентификатор достопримечательности.
     * @param newDescription Новое описание для достопримечательности.
     * @return ResponseEntity с обновленной достопримечательностью и статусом OK (200).
     */
    @PutMapping("/{id}/description")
    public ResponseEntity<ShowplaceDto> updateDescription(@PathVariable Long id, @RequestBody String newDescription) {
        return ResponseEntity.status(HttpStatus.OK).body(showplaceService.updateShowplaceDescription(id, newDescription));
    }

    /**
     * Обрабатывает DELETE-запрос для удаления достопримечательности по ее идентификатору.
     *
     * @param id Идентификатор достопримечательности.
     * @return ResponseEntity с пустым телом и статусом OK (200).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowplace(@PathVariable Long id) {
        showplaceService.deleteShowplace(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
