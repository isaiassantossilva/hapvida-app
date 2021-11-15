package com.hapvida.hapvidaapp.application.valueobject;

public class ID {
    private String id;

    public ID(String id){
        if(id == null)
            throw new IllegalArgumentException("Id can not be null");
        if(id.isBlank())
            throw new IllegalArgumentException("Id can not be blank");
        this.id = id;
    }


    public String getValue(){
        return id;
    }
}
