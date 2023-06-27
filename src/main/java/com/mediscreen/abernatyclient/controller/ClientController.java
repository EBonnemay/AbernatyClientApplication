package com.mediscreen.abernatyclient.controller;

import com.mediscreen.abernatyclient.beans.NoteBean;
import com.mediscreen.abernatyclient.beans.PatientBean;
import com.mediscreen.abernatyclient.proxies.MicroservicePatientsProxy;
import com.mediscreen.abernatyclient.proxies.MicroservicePractitionersProxy;
import com.mediscreen.abernatyclient.proxies.MicroserviceRiskProxy;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {
    private final MicroservicePatientsProxy patientsProxy;
    private final MicroservicePractitionersProxy practitionersProxy;
    private final MicroserviceRiskProxy riskProxy;
    public ClientController(MicroservicePatientsProxy patientsProxy, MicroservicePractitionersProxy practitionersProxy, MicroserviceRiskProxy riskProxy){
        this.patientsProxy = patientsProxy;
        this.practitionersProxy = practitionersProxy;
        this.riskProxy = riskProxy;
    }
    /**display list of patients on HomePage page*/
    @RequestMapping("/")
    public String displayHomePage(Model model){
        List<PatientBean> patients = patientsProxy.listOfPatients();
        model.addAttribute("patients", patients);
        return "HomePage";
    }
    /**display 'update' form on UpdatePatient page*/
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
    /**display the 'add patient' form on AddPatient page*/
    @GetMapping("/patient/add")
    public String displayAddForm(){
        return "AddPatient";
    }




    /**update a patient and return HomePage*/
   @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable("id") String id, @Valid @ModelAttribute ("patient") PatientBean patient, BindingResult bindingResult){
        System.out.println("this is family "+ patient.getFamily());
        //System.out.println("this is date :"+ dateOfBirth);
        //j'ai une family et une date  format 1999-12-19
        if(bindingResult.hasErrors()){
            return "error-page";
        }
        patientsProxy.updatePatient(id, patient);
        System.out.println("in client controller post : date of birth = "+ patient.getDate_of_birth());
        System.out.println("in client controller post : date instance of Date?" + (patient.getDate_of_birth() instanceof Date));
        return "redirect:/" ;
    }
    /**add a patient and return HomePage*/
    @PostMapping("/patient/add")
    public String addPatient(@Valid @ModelAttribute ("patient")PatientBean patient, BindingResult bindingResult){
        System.out.println("this is family "+ patient.getFamily());

        if(bindingResult.hasErrors()){
            return "error-page";
        }
        patientsProxy.addPatient(patient);

        System.out.println("in client controller post : date of birth = "+ patient.getDate_of_birth());
        System.out.println("in client controller post : date instance of Date?" + (patient.getDate_of_birth() instanceof Date));

        return "redirect:/";
    }
    /**delete a patient and return HomePage*/
   @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable ("id")String id){
        System.out.println("patient to delete's id is "+ id);
        patientsProxy.deletePatient(id);
        return "redirect:/";
    }

    /**display the list of notes on one patient on NotesPage page__*/
   @GetMapping("/note/all/{patId}")
    public String displayNotesPage(@PathVariable("patId")String patId, Model model){
        //PatientBean patient = patientsProxy.retrievePatient(Integer.parseInt(id));
        List<NoteBean> listOfNotes = practitionersProxy.displayOnePatientsNotesPage(patId);

        PatientBean patient = patientsProxy.retrievePatient(Integer.parseInt(patId));
        List<String>listOfOnePatientNotesMessages = new ArrayList<>();
        for (NoteBean note : listOfNotes){
            listOfOnePatientNotesMessages.add(note.getContentNote());
        }
        String risk = riskProxy.calculateRisk(patient.getSex(), patient.getDate_of_birth(), listOfOnePatientNotesMessages);
        model.addAttribute("listOfNotes", listOfNotes);
        model.addAttribute("patient", patient);
        model.addAttribute("risk", risk);

        return "NotesPage";

    }

    /**display 'add note' form on AddNote page_calling patientsProxy, not practitioners' proxy__*/
   @GetMapping("/note/add/{id}")
   public String displayAddNoteForm(@PathVariable("id")String id, Model model){
        PatientBean patient = patientsProxy.retrievePatient(Integer.parseInt(id));
        model.addAttribute("patient", patient);
        return "AddNote";
    }

    /**display 'update note' form on UpdateNote page__ calling patient and pratitioners' proxy__*/
   @GetMapping("/note/update/{id}")
    public String displayUpdateNoteForm(@PathVariable("id")String id, Model model){
       System.out.println("just entered client controller");
        NoteBean note = practitionersProxy.findNoteById(id);
        System.out.println("id in clientcontroller is "+ id);
        String patId = note.getPatId();
        System.out.println("patId in client controller is "+ patId);
        int intPatId = Integer.parseInt(patId);
        PatientBean patient = patientsProxy.retrievePatient(intPatId);
        model.addAttribute("note", note);
        model.addAttribute("patient", patient);
        return "UpdateNote";
    }

    /**add a note and return notesPage with patient's id as param__*/
    @PostMapping("/note/add/{patId}")
    public String addNote(@PathVariable("patId")String patId, @RequestParam ("noteContent")String noteContent){
        System.out.println("method post add " + noteContent);//ok
        System.out.println("inside client controller - post add note");//ok
        practitionersProxy.addNote(patId, noteContent);
        return String.format("redirect:/note/all/%s",patId);
    }
    /**delete a note and return notesPage__*/
   @GetMapping("/note/delete/{id}")
    public String deleteNote(@PathVariable("id")String id){
       NoteBean note = practitionersProxy.findNoteById(id);
       String patId = note.getPatId();
       practitionersProxy.deleteNote(id);


       System.out.println("voilà patId "+ patId);
        return String.format("redirect:/note/all/%s",patId);
    }
    /**update a note and return notesPage__*/
    @PostMapping("/note/update/{id}")
    public String updateNote(@PathVariable("id")String id, @RequestParam("noteContent") String noteContent){
        System.out.println("mathod post update "+noteContent);

        practitionersProxy.updateNote(id,noteContent);
        NoteBean note = practitionersProxy.findNoteById(id);
        String patId = note.getPatId();
        System.out.println(String.format("redirect:/note/%s",patId));
        return String.format("redirect:/note/all/%s",patId);
    }
    /**get risk level*/
    /*@PostMapping("/risk/calculate/{patId}")
    public String calculateRisk(@PathVariable("patId")String patId, @RequestBody PatientBean patient, Model model){
        int age = riskProxy.getAge(patient.getDate_of_birth());
        String sex = patient.getSex();
        List<String>riskFactors = riskProxy.getListOfRiskFactors(String patId);
        RiskBean risk = riskProxy.calculateRisk(age, sex, riskFactors);
        model.addAttribute("risk", risk);
        return String.format("redirect:/note/all/%", patId);
    }*/

}
