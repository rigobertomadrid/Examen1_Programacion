package examen1_programacion;

import java.time.LocalDate;

public abstract class Barco {

    private final String nombre;
    private final LocalDate fechaCirculacion;

    public Barco(String nombre) {
        this.nombre = nombre;
        this.fechaCirculacion = LocalDate.now();
    }

    public final String getNombre() {
        return nombre;
    }

    public final LocalDate getFechaCirculacion() {
        return fechaCirculacion;
    }

    public String toString() {
        return nombre;
    }

    public abstract void agregarElemento();

    public abstract double vaciarCobrar();

    public abstract double precioElemento();
}
