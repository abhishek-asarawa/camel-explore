package com.jss.camel.dto;

import java.util.ArrayList;

public class PrescribersDto {
    private ArrayList<PrescriberDto> list = new ArrayList<PrescriberDto>();

    public void addPrescriber(PrescriberDto prescriber) {
        list.add(prescriber);
    }

    public ArrayList<PrescriberDto> getAllPrescribersInJson() {
        return list;
    }
}
