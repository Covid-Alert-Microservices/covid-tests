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
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/covid-tests")
public class CovidTestsController
{

    @Autowired
    private KafkaTemplate<String, UserPositiveDTO> userPositiveKafkaTemplate;

    @Autowired
    private CovidTestsRepository covidTestsRepository;

    @GetMapping
    public List<CovidTest> getAllCovidTests(Principal principal)
    {
        return covidTestsRepository.findAllByUserId(principal.getName());
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

    @GetMapping("{id}")
    public CovidTest getCovidTest(@PathVariable Long id, Principal principal)
    {
        return covidTestsRepository.findByIdAndUserId(id, principal.getName());
    }

    @DeleteMapping("{id}")
    public void deleteCovidTest(@PathVariable Long id, Principal principal)
    {
        if (covidTestsRepository.deleteByIdAndUserId(id, principal.getName()) < 1)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public CovidTest updateCovidTest(@PathVariable Long id, Principal principal, @Valid @RequestBody UpdateCovidTestDTO covidTestDTO)
    {
        CovidTest existingCovidTest = covidTestsRepository.findByIdAndUserId(id, principal.getName());
        BeanUtils.copyProperties(covidTestDTO, existingCovidTest);

        if (covidTestDTO.getTestResult().equals("POSITIVE"))
        {
            UserPositiveDTO userPositive = new UserPositiveDTO(existingCovidTest.getUserId(), existingCovidTest.getTestDate());
            userPositiveKafkaTemplate.send("user_positive", userPositive);
        }

        return covidTestsRepository.saveAndFlush(existingCovidTest);
    }

}
