package com.github.covidalert.covidtests.repositories;

import com.github.covidalert.covidtests.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movie, Long>
{
}
