package com.javatar.genresservice.repository;

import com.javatar.genresservice.model.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenresRepository extends JpaRepository<Genres, Integer> {
}
