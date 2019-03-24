/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salario_futbolistas;

/**
 *
 * @author USER
 */
public class json {
    String nombre;
    String nivel;
    int goles;
    int sueldo;
    int bono;
    int sueldo_completo;
    String equipo;

    public json(String nombre, String nivel, int goles, int sueldo, int bono, int sueldo_completo, String equipo) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.goles = goles;
        this.sueldo = sueldo;
        this.bono = bono;
        this.sueldo_completo = sueldo_completo;
        this.equipo = equipo;
    }

    json() {
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public int getBono() {
        return bono;
    }

    public void setBono(int bono) {
        this.bono = bono;
    }

    public int getSueldo_completo() {
        return sueldo_completo;
    }

    public void setSueldo_completo(int sueldo_completo) {
        this.sueldo_completo = sueldo_completo;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "json{" + "nombre=" + nombre + ", nivel=" + nivel + ", goles=" + goles + ", sueldo=" + sueldo + ", bono=" + bono + ", sueldo_completo=" + sueldo_completo + ", equipo=" + equipo + '}';
    }
    
}
