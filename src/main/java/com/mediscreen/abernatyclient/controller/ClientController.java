package com.mediscreen.abernatyclient.controller;

import com.mediscreen.abernatyclient.beans.PatientBean;
import com.mediscreen.abernatyclient.proxies.MicroservicePatientsProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.List;

@Controller
public class ClientController {
    private final MicroservicePatientsProxy patientsProxy;
    public ClientController(MicroservicePatientsProxy patientsProxy){
    this.patientsProxy = patientsProxy;
    }
    @RequestMapping("/")
    public String displayHomePage(Model model){

        List<PatientBean> patients = patientsProxy.listOfPatients();

        model.addAttribute("patients", patients);
        return "HomePage";
    }
    @GetMapping("/patient/update/{id}")
    public String displayUpdateForm(@PathVariable("id")String id, Model model){
        System.out.println("id is" + id);
        PatientBean patient = patientsProxy.retrievePatient(Integer.parseInt(id));
        //model.addAttribute("family", patient.getFamily());
        //model.addAttribute("given", patient.getGiven());
        System.out.println(patient.getDate_of_birth());
        System.out.println(patient.getDate_of_birth() instanceof Date);
        model.addAttribute("patient", patient);
        return "UpdatePatient";
    }

    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable("id") String id, @RequestParam(name = "family", required = true) String family, @RequestParam(name = "given", required = true) String given, @RequestParam(name = "date_of_birth", required = false) Date dateOfBirth, @RequestParam(name = "sex", required = false) String sex, @RequestParam(name = "address", required = false) String address, @RequestParam(name = "phone", required = false) String phone, Model model){
        System.out.println("this is family "+ family);
        System.out.println("this is date :"+ dateOfBirth);
        //j'ai une family et une date  format 1999-12-19

       //Date dateOfBirth = Date.valueOf(stringDateOfBirth);
        PatientBean patient = patientsProxy.updatePatient(id, family, given, dateOfBirth, sex, address, phone);
        //ma date est nulle donc pas instance de Date
        System.out.println("in client controller post : date of birth = "+ patient.getDate_of_birth());
        System.out.println("in client controller post : date instance of Date?" + (patient.getDate_of_birth() instanceof Date));

        //model.addAttribute("patient", patient);
        //model.addAttribute("given", patient.getGiven());

    return "redirect:/" ;
    }

}
