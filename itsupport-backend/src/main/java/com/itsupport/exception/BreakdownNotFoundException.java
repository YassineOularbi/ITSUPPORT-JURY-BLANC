package com.itsupport.exception;

public class BreakdownNotFoundException extends RuntimeException{
    public BreakdownNotFoundException(){
        super("Breakdown not found !");
    }
}
