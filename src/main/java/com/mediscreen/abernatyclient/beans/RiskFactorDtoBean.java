package com.mediscreen.abernatyclient.beans;

import java.time.LocalDate;
import java.util.List;

public class RiskFactorDtoBean {
    private String sex;
    private LocalDate dob;

    private List<String> listOfOnePatientsMessages;

    public RiskFactorDtoBean(String sex, LocalDate dob, List<String> listOfOnePatientsMessages) {
        this.sex = sex;
        this.dob = dob;
        this.listOfOnePatientsMessages = listOfOnePatientsMessages;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public List<String> getListOfOnePatientsMessages() {
        return listOfOnePatientsMessages;
    }

    public void setListOfOnePatientsMessages(List<String> listOfOnePatientsMessages) {
        this.listOfOnePatientsMessages = listOfOnePatientsMessages;
    }
}
