package com.mediscreen.abernatyclient.proxies;

import com.mediscreen.abernatyclient.beans.PatientBean;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-patients", url = "localhost:8080")
public interface MicroservicePatientsProxy {
    //@Operation(value="récupère la liste des patients")
    @GetMapping(value = "/patients")
    List<PatientBean> listOfPatients();


    @GetMapping(value = "/patient/{id}")
    PatientBean retrievePatient(@PathVariable("id") int id);

    @GetMapping(value = "/patient/{family}/{given}")
    PatientBean retrievePatient(@PathVariable("family") String family, @PathVariable("given") String given);

    /*@PostMapping(value = "/patient/update/{id}")
    PatientBean updatePatient(@PathVariable("id") String id, @RequestParam(name = "family", required = true) String family, @RequestParam(name = "given", required = true) String given, @RequestParam(name = "date_of_birth", required = false) Date dateOfBirth, @RequestParam(name = "sex", required = false) String sex, @RequestParam(name = "address", required = false) String address, @RequestParam(name = "phone", required = false) String phone);
    */
    //object PatientBean is converted with @RequestBody in a format that can the sent over the network as part of an http request or response.
    // the structure formatis json. key value pairs
    //from json to a java object PatientBean
    @PostMapping(value = "/patient/update/{id}")
    public String updatePatient(@PathVariable("id") String id, PatientBean patient);

    @PostMapping(value = "/patient/add")
    public String addPatient( PatientBean patient);
    @GetMapping(value = "/patient/delete/{id}")
    public String deletePatient(@PathVariable("id")String id);
}