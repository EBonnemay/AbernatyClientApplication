package com.mediscreen.abernatyclient.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "microservice-risk", url = "localhost:8081")
public interface MicroserviceRiskProxy {

   /* @PostMapping(value = "/risk/add/{patId}")
    riskProxy.calculateRisk(@PathVariable ("patId") String patId, listOfNotes, );
}*/

    @PostMapping(value = "/risk/calculate")
    public String calculateRiskFactors(@RequestParam String sex, @RequestParam LocalDate dateOfBirth, @RequestParam List<String> listOfOnePatientMessages);
    //public String calculateRiskFactors(@RequestBody RiskFactor riskFactor);

}