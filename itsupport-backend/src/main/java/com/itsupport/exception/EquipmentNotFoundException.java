package com.itsupport.exception;

public class EquipmentNotFoundException extends RuntimeException{
    public EquipmentNotFoundException(){
        super("Equipment not found !");
    }
}
