package com.github.covidalert.covidtests.repositories;

import com.github.covidalert.covidtests.models.CovidTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CovidTestsRepository extends JpaRepository<CovidTest, Long>
{

    List<CovidTest> findAllByUserId(String userId);

    CovidTest findByIdAndUserId(Long id, String userId);

    @Transactional
    Long deleteByIdAndUserId(Long id, String UserId);

}
