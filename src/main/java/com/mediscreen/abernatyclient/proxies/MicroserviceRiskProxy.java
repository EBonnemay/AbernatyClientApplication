package com.mediscreen.abernatyclient.proxies;

import com.mediscreen.abernatyclient.beans.RiskFactorDtoBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-risk", url = "localhost:8081")
public interface MicroserviceRiskProxy {

   /* @PostMapping(value = "/risk/add/{patId}")
    riskProxy.calculateRisk(@PathVariable ("patId") String patId, listOfNotes, );
}*/

    @PostMapping(value = "/risk/calculate")
    public String calculateRiskFactors(@RequestBody RiskFactorDtoBean riskFactorDtoBean);
    //public String calculateRiskFactors(@RequestBody RiskFactor riskFactor);

}