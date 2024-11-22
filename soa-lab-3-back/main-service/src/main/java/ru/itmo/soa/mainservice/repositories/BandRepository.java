package ru.itmo.soa.mainservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itmo.soa.mainservice.model.Band;
import ru.itmo.soa.mainservice.model.MusicGenre;

import java.util.List;

@Repository
public interface BandRepository extends JpaRepository<Band, Long>, JpaSpecificationExecutor<Band> {
    void deleteByGenre(MusicGenre genre);

    @Query("SELECT b FROM Band b ORDER BY b.genre ASC, b.name ASC")
    List<Band> findAllOrderByGenreAscNameAsc();
}