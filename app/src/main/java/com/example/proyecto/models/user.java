package com.example.proyecto.models;

public class user {
    private String name;
    private String lastname;
    private String permiso;

    public  user(){
    }
    public user(String name, String lastname, String permiso) {
        this.name = name;
        this.lastname = lastname;
        this.permiso = permiso;
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

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public String getPermisos() {
        if (!permiso.equals("1")){
            return "Denegado";
        }
        return "Permitido";
    }
}
