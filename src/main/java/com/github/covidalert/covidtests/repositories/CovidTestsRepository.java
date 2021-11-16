package com.github.covidalert.covidtests.repositories;

import com.github.covidalert.covidtests.models.CovidTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CovidTestsRepository extends JpaRepository<CovidTest, String>
{
}
