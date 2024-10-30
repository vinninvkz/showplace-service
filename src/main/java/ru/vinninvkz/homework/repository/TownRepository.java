package ru.vinninvkz.homework.repository;

import ru.vinninvkz.homework.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town,Long> {
    Optional<Town> findByName(String townName);
}
