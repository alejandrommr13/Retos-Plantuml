import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Clase principal
public class tarea {

    public static void main(String[] args) {
        new ICentro();
    }
}

// Clase ICentro
class ICentro extends JFrame {
    private JTextField txtDescripcion, txtCantidad, txtCostoUnitario, txtFecha, txtFactura, txtResponsable;
    private ArrayList<String> registros = new ArrayList<>();

    public ICentro() {
setTitle("Registro y Control de Equipos en Centro de Investigacion");
setLayout(new GridLayout(8, 2));

add(new JLabel("Descripcion:"));
txtDescripcion = new JTextField();
add(txtDescripcion);

add(new JLabel("Cantidad:"));
txtCantidad = new JTextField();
add(txtCantidad);

add(new JLabel("Costo Unitario (Bs.):"));
txtCostoUnitario = new JTextField();
add(txtCostoUnitario);

add(new JLabel("Fecha de Adquisicion (dd/mm/aaaa):"));
txtFecha = new JTextField();
add(txtFecha);

add(new JLabel("Nro. de Factura:"));
txtFactura = new JTextField();
add(txtFactura);

add(new JLabel("C.I. del Responsable del equipo:"));
txtResponsable = new JTextField();
add(txtResponsable);

JButton btnRegistrar = new JButton("Registrar data");
btnRegistrar.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
registrarEquipo();
}
});
add(btnRegistrar);

        JButton btnReporte = new JButton("Generar Reporte");
        btnReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new IReporte(registros);
            }
        });
add(btnReporte);

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
add(btnSalir);

setSize(500, 400);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
    }

    private void registrarEquipo() {
String descripcion = txtDescripcion.getText();
String cantidad = txtCantidad.getText();
String costoUnitario = txtCostoUnitario.getText();
String fecha = txtFecha.getText();
String factura = txtFactura.getText();
String responsable = txtResponsable.getText();

String registro = descripcion + "#" + cantidad + "#" + costoUnitario + "#" + fecha + "#" + factura + "#" + responsable;
registros.add(registro);
JOptionPane.showMessageDialog(this, "Equipo registrado correctamente.");

txtDescripcion.setText("");
txtCantidad.setText("");
txtCostoUnitario.setText("");
txtFecha.setText("");
txtFactura.setText("");
txtResponsable.setText("");
}
}

// Clase IReporte
class IReporte extends JFrame {
private JRadioButton rbIndividual, rbGeneral;
private JTextField txtCIResponsable;
private JTextArea txtReporte;
private ArrayList<String> registros;

    public IReporte(ArrayList<String> registros) {
        this.registros = registros;

    setTitle("Reporte del Inventario del Centro de Investigacion");
    setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridLayout(2, 2));

    rbIndividual = new JRadioButton("Individual");
    rbGeneral = new JRadioButton("General");
    ButtonGroup grupo = new ButtonGroup();
       grupo.add(rbIndividual);
     grupo.add(rbGeneral);

    panelSuperior.add(new JLabel("Tipo de reporte:"));
       panelSuperior.add(rbIndividual);
     panelSuperior.add(rbGeneral);
    panelSuperior.add(new JLabel("C.I. del Responsable:"));

    txtCIResponsable = new JTextField();
 panelSuperior.add(txtCIResponsable);

JButton btnTotalizar = new JButton("Totalizar");
 btnTotalizar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    generarReporte();
}
});
panelSuperior.add(btnTotalizar);

add(panelSuperior, BorderLayout.NORTH);

txtReporte = new JTextArea();
        add(new JScrollPane(txtReporte), BorderLayout.CENTER);

        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
}
});
add(btnContinuar, BorderLayout.SOUTH);

setSize(600, 400);
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
setVisible(true);
 }

    private void generarReporte() {
        txtReporte.setText("");

    if (rbIndividual.isSelected()) {
            String ci = txtCIResponsable.getText();
            int totalEquipos = 0;
            double totalMonto = 0;

for (String registro : registros) {
String[] partes = registro.split("#");
if (partes[5].equals(ci)) {
totalEquipos += Integer.parseInt(partes[1]);
totalMonto += Integer.parseInt(partes[1]) * Double.parseDouble(partes[2]);
}
}

txtReporte.append("C.I. Responsable: " + ci + "\n");
txtReporte.append("Cantidad de equipos: " + totalEquipos + "\n");
txtReporte.append("Monto total (Bs.): " + totalMonto + "\n");
} else if (rbGeneral.isSelected()) {
            txtReporte.append("Reporte General:\n");

for (String registro : registros) {
String[] partes = registro.split("#");
txtReporte.append("C.I.: " + partes[5] + ", Equipos: " + partes[1] + ", Monto: " + (Integer.parseInt(partes[1]) * Double.parseDouble(partes[2])) + "\n");
}
        } else {
JOptionPane.showMessageDialog(this, "Seleccione un tipo de reporte.");
        }
    }
}
