package examen1_programacion;

public enum TipoPesquero {
    PEZ(50.0), CAMARON(100.0), LANGOSTA(150.0);

    public final double price;

    TipoPesquero(double price) {
        this.price = price;
    }

    public double getPrecio() {
        return price;
    }
}
