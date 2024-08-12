package examen1_programacion;

public final class BarcoPesquero extends Barco {

    private int pecesCapturados;
    private final TipoPesquero tipoPesquero;

    public BarcoPesquero(String nombre, TipoPesquero tipoPesquero) {
        super(nombre);
        this.pecesCapturados = 0;
        this.tipoPesquero = tipoPesquero;
    }

    public void agregarElemento() {
        pecesCapturados++;
    }

    public double vaciarCobrar() {
        double total = pecesCapturados * tipoPesquero.getPrecio();
        pecesCapturados = 0;
        return total;
    }

    public double precioElemento() {
        return tipoPesquero.getPrecio();
    }

    public String toString() {
        return super.toString() + " (Pesquero: " + tipoPesquero + ", Peces capturados: " + pecesCapturados + ")";
    }
}
