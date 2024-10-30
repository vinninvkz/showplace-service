package ru.vinninvkz.homework.repository;

import org.springframework.data.jpa.repository.Query;
import ru.vinninvkz.homework.model.Showplace;
import ru.vinninvkz.homework.model.ShowplaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowplaceRepository extends JpaRepository<Showplace,Long> {
    Optional<List<Showplace>> findByTownName(String townName);
    Optional<List<Showplace>> findByType(ShowplaceType type);

    @Query("SELECT s FROM Showplace s WHERE (:type IS NULL OR s.type = :type) ORDER BY s.name ASC")
    Optional<List<Showplace>> findAllByTypeSortedByName(String type);
}
