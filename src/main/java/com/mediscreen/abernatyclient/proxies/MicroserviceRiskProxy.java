package com.mediscreen.abernatyclient.proxies;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface MicroserviceRiskProxy {

   /* @PostMapping(value = "/risk/add/{patId}")
    riskProxy.calculateRisk(@PathVariable ("patId") String patId, listOfNotes, );
}*/

    @PostMapping(value = "/risk/calculate")
    public String calculateRisk(@RequestParam String sex, @RequestParam Date dateOfBirth, @RequestBody List<String> listOfOnePatientNotesMessages);

}