package com.mediscreen.abernatyclient.proxies;

import com.mediscreen.abernatyclient.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "microservice-practitioners", url = "localhost:8082")
public interface MicroservicePractitionersProxy {

    /**retrieve all notes for all patients__not used__}*/
    @GetMapping(value = "/note/all")
    List<NoteBean> listOfNotes();

    /**get list of notes on one patient__*/
    @GetMapping(value = "/note/patient/{patId}")
    public List displayOnePatientsNotesPage(@PathVariable String patId);

   /**get a note by its id*/
   @GetMapping(value = "/note/{id}")
   public NoteBean findNoteById(@PathVariable("id") String id);

    /**add a note by patient id__*/
    @PostMapping(value = "/note/add/{patId}")
    public void addNote(@PathVariable("patId")String patId, @RequestBody String contentNote);

    /**delete a note__*/
    @GetMapping(value = "/note/delete/{noteId}")
    public void deleteNote(@PathVariable("noteId")String id);

    /**update a note__*/
    @PostMapping(value = "/note/update/{id}")
    public void updateNote(@PathVariable("id")String id, @RequestBody NoteBean noteBean);

}
