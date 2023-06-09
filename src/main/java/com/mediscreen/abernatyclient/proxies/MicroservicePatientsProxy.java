package com.mediscreen.abernatyclient.proxies;

import com.mediscreen.abernatyclient.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-patients", url = "localhost:8080")
public interface MicroservicePatientsProxy {
    @GetMapping(value = "/patients")
    List<PatientBean> listOfPatients();


    @GetMapping(value = "/Produits/{id}")
    PatientBean retrievePatient(@PathVariable("id")int id);

}
