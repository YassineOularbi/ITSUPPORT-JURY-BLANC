package com.itsupport.exception;

public class TechnicianNotFoundException extends RuntimeException{
    public TechnicianNotFoundException(){
        super("Technician not found !");
    }
}
