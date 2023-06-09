package com.mediscreen.abernatyclient.controller;

import com.mediscreen.abernatyclient.beans.PatientBean;
import com.mediscreen.abernatyclient.proxies.MicroservicePatientsProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ClientController {
    private final MicroservicePatientsProxy patientsProxy;
    public ClientController(MicroservicePatientsProxy patientsProxy){
    this.patientsProxy = patientsProxy;
    }
    @RequestMapping("/")
    public String returnHomePage(Model model){
        String test = "hello world";
        List<PatientBean> patients = patientsProxy.listOfPatients();

        model.addAttribute("patients", patients);
        return "HomePage";
    }
}
