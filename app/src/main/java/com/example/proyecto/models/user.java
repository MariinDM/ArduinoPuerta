package com.example.proyecto.models;

public class user {
    private int id;
    private String name;
    private String lastname;
    private int permiso;
    private String email;
    private String LlaveIngreso;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLlaveIngreso() {
        return LlaveIngreso;
    }

    public void setLlaveIngreso(String llaveIngreso) {
        LlaveIngreso = llaveIngreso;
    }

    public  user(){
    }

    public user(String name, String lastname, String email, String LlaveIngreso) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.LlaveIngreso = LlaveIngreso;
    }

    public String nombreCompleto(){
        return getName()+" "+getLastname();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getPermiso() {
        return permiso;
    }

    public void setPermiso(int permiso) {
        this.permiso = permiso;
    }

    public String getPermisos() {
        if (permiso==1){
            return "Denegado";
        }
        return "Permitido";
    }

    public int getId() {
        return id;
    }

    public void setId(int  id) {
        this.id = id;
    }
}
