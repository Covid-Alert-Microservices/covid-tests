package com.github.covidalert.covidtests.controllers;

import com.github.covidalert.covidtests.dtos.CreateCovidTestDTO;
import com.github.covidalert.covidtests.dtos.UpdateCovidTestDTO;
import com.github.covidalert.covidtests.dtos.UserPositiveDTO;
import com.github.covidalert.covidtests.models.CovidTest;
import com.github.covidalert.covidtests.repositories.CovidTestsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/covid-test")
public class CovidTestsController
{

    @Autowired
    private KafkaTemplate<String, UserPositiveDTO> userPositiveKafkaTemplate;

    @Autowired
    private CovidTestsRepository covidTestsRepository;

    @GetMapping
    public CovidTest getCovidTest(Principal principal)
    {
        return covidTestsRepository.getById(principal.getName());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CovidTest createCovidTest(Principal principal, @Valid @RequestBody CreateCovidTestDTO covidTestDTO)
    {
        CovidTest covidTest = new CovidTest(
                principal.getName(),
                covidTestDTO.getDisease(),
                covidTestDTO.getTestType(),
                covidTestDTO.getTestResult(),
                covidTestDTO.getTestDate(),
                covidTestDTO.getCertificationAuthorityIdentifier(),
                covidTestDTO.getMemberState(),
                covidTestDTO.getCertificateIssuer()
        );

        if (covidTest.getTestResult().equals("POSITIVE"))
        {
            UserPositiveDTO userPositive = new UserPositiveDTO(covidTest.getUserId(), covidTest.getTestDate());
            userPositiveKafkaTemplate.send("user_positive", userPositive);
        }

        return covidTestsRepository.saveAndFlush(covidTest);
    }

    @DeleteMapping
    public void deleteCovidTest(Principal principal)
    {
        covidTestsRepository.deleteById(principal.getName());
    }

    @PutMapping
    public CovidTest updateCovidTest(Principal principal, @Valid @RequestBody UpdateCovidTestDTO covidTestDTO)
    {
        CovidTest existingCovidTest = covidTestsRepository.getById(principal.getName());
        BeanUtils.copyProperties(covidTestDTO, existingCovidTest);

        if (covidTestDTO.getTestResult().equals("POSITIVE"))
        {
            UserPositiveDTO userPositive = new UserPositiveDTO(existingCovidTest.getUserId(), existingCovidTest.getTestDate());
            userPositiveKafkaTemplate.send("user_positive", userPositive);
        }

        return covidTestsRepository.saveAndFlush(existingCovidTest);
    }

}
