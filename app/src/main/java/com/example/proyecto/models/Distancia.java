package com.example.proyecto.models;

public class Distancia {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Distancia() {
    }

    public int GetIntValue(){
        float f=Float.parseFloat(value);
        int i= (int) f;
        return i;
    }
}