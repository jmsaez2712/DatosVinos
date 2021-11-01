package com.saezgarcia.datosvinos.vinos;

public class Vino {

    //Declaracion de las variables del objeto.
    private int id;
    private String nombre;
    private String bodega;
    private String color;
    private String origen;
    private double graduación;
    private int fecha;

    public Vino(int id, String nombre, String bodega, String color, String origen, double graduación, int fecha) {
        this.id = id;
        this.nombre = nombre;
        this.bodega = bodega;
        this.color = color;
        this.origen = origen;
        this.graduación = graduación;
        this.fecha = fecha;
    }

    //region Getters Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public double getGraduación() {
        return graduación;
    }

    public void setGraduación(double graduación) {
        this.graduación = graduación;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }
    //endregion Getters Setters

    //Método toString del objeto Vino
    @Override
    public String toString() {
        return "Vino{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", bodega='" + bodega + '\'' +
                ", color='" + color + '\'' +
                ", origen='" + origen + '\'' +
                ", graduación=" + graduación +
                ", fecha=" + fecha +
                '}';
    }

    //Método equals para poder saber cuando dos objetos Vino son el mismo
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vino vino = (Vino) o;

        return id == vino.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
