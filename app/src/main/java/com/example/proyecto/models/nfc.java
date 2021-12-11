package com.example.proyecto.models;

public class nfc {
    private String value;
    private String created_at;

    public nfc(){

    }
    public nfc(String value, String created_at) {
        this.value = value;
        this.created_at = created_at;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
