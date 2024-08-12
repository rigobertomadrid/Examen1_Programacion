package examen1_programacion;

import java.util.Scanner;

public final class BarcoPasajero extends Barco {

    private final String[] pasajeros;
    private final double precioBoleto;
    private int contadorPasajeros;

    public BarcoPasajero(String nombre, int maxPasajeros, double precioBoleto) {
        super(nombre);
        this.pasajeros = new String[maxPasajeros];
        this.precioBoleto = precioBoleto;
        this.contadorPasajeros = 0;
    }

    @Override
    public void agregarElemento() {
        if (contadorPasajeros < pasajeros.length) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nombre del pasajero: ");
            pasajeros[contadorPasajeros] = scanner.nextLine();
            contadorPasajeros++;
        } else {
            System.out.println("Ya no hay espacio para mas pasajeros.");
        }
    }

    @Override
    public double vaciarCobrar() {
        double total = contadorPasajeros * precioBoleto;
        contadorPasajeros = 0;
        return total;
    }

    @Override
    public double precioElemento() {
        return precioBoleto;
    }

    @Override
    public String toString() {
        return super.toString() + " (Pasajero, Cantidad de los pasajeros que han comprado boleto: " + contadorPasajeros + ")";
    }

    public void listarPasajeros() {
        listarPasajerosRecursivo(0);
    }

    private void listarPasajerosRecursivo(int index) {
        if (index < contadorPasajeros) {
            System.out.println(pasajeros[index]);
            listarPasajerosRecursivo(index + 1);
        }
    }
}
