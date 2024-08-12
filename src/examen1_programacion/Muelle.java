package examen1_programacion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

public class Muelle extends JFrame {

    private final ArrayList<Barco> barcos;

    public Muelle() {
        barcos = new ArrayList<>();
        crearmein();
    }

    private void crearmein() {
        setTitle("Muelle");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Menu de Muelle", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));

        JButton botonagregarbarco = crearBoton("Agregar Barco");
        botonagregarbarco.addActionListener(e -> agregarBarco());
        JButton botonagregarelemento = crearBoton("Agregar Elemento");
        botonagregarelemento.addActionListener(e -> agregarElemento());
        JButton botonvaciarbarco = crearBoton("Vaciar Barco");
        botonvaciarbarco.addActionListener(e -> vaciarBarco());
        JButton botonbarcodesde = crearBoton("Barcos Desde");
        botonbarcodesde.addActionListener(e -> listarBarcosDesde());
        JButton botonsalir = crearBoton("Salir");
        botonsalir.addActionListener(e -> System.exit(0));
        panel.add(botonagregarbarco);
        panel.add(botonagregarelemento);
        panel.add(botonvaciarbarco);
        panel.add(botonbarcodesde);
        panel.add(botonsalir);

        add(panel, BorderLayout.CENTER);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(130, 50));
        boton.setBackground(Color.WHITE);
        boton.setForeground(Color.BLACK);
        return boton;
    }

    private void agregarBarco() {
        String tipo = JOptionPane.showInputDialog(this, "Tipo de barco (PESQUERO o PASAJERO):");
        String nombre = JOptionPane.showInputDialog(this, "Nombre del barco:");

        if (buscarBarco(nombre).isPresent()) {
            JOptionPane.showMessageDialog(this, "Este nombre de barco ya existe.");
            return;
        }

        if ("PESQUERO".equalsIgnoreCase(tipo)) {
            TipoPesquero tipoPesquero = (TipoPesquero) JOptionPane.showInputDialog(this,
                    "Seleccione el tipo de Pesquero:",
                    "Tipo de Pesquero:",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    TipoPesquero.values(),
                    TipoPesquero.PEZ);
            barcos.add(new BarcoPesquero(nombre, tipoPesquero));
        } else if ("PASAJERO".equalsIgnoreCase(tipo)) {
            int maxPasajeros = Integer.parseInt(JOptionPane.showInputDialog(this, "Capacidad de pasajeros:"));
            double precioBoleto = Double.parseDouble(JOptionPane.showInputDialog(this, "Precio del boleto:"));
            barcos.add(new BarcoPasajero(nombre, maxPasajeros, precioBoleto));
        } else {
            JOptionPane.showMessageDialog(this, "Este tipo de barco es invalido.");
        }
    }

    private void agregarElemento() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del barco:");
        Optional<Barco> barco = buscarBarco(nombre);

        barco.ifPresentOrElse(
                Barco::agregarElemento,
                () -> JOptionPane.showMessageDialog(this, "Este barco no se pudo encontrar.")
        );
    }

    private void vaciarBarco() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del barco:");
        Optional<Barco> barco = buscarBarco(nombre);

        barco.ifPresentOrElse(b -> {
            double totalCobrado = b.vaciarCobrar();
            JOptionPane.showMessageDialog(this, "Total cobrado: $" + totalCobrado);
            if (b instanceof BarcoPasajero) {
                ((BarcoPasajero) b).listarPasajeros();
            }
        }, () -> JOptionPane.showMessageDialog(this, "Este barco no se pudo encontrar."));
    }

    private void listarBarcosDesde() {
        int year = Integer.parseInt(JOptionPane.showInputDialog(this, "Año:"));
        StringBuilder resultado = new StringBuilder("Barcos desde " + year + ":\n");
        listarBarcosRecursivo(0, year, resultado);

        if (resultado.length() == ("Barcos desde " + year + ":\n").length()) {
            resultado.append("No se han encontrado barcos.");
        }

        JOptionPane.showMessageDialog(this, resultado.toString());
    }

    private void listarBarcosRecursivo(int index, int year, StringBuilder mostrar) {
        if (index < barcos.size()) {
            Barco barco = barcos.get(index);
            if (barco.getFechaCirculacion().getYear() >= year) {
                mostrar.append(barco.getNombre()).append(" : ").append(barco.getFechaCirculacion()).append("\n");
            }
            listarBarcosRecursivo(index + 1, year, mostrar);
        }
    }

    private Optional<Barco> buscarBarco(String nombre) {
        return barcos.stream().filter(b -> b.getNombre().equalsIgnoreCase(nombre)).findFirst();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Muelle main = new Muelle();
            main.setVisible(true);
        });
    }
}
