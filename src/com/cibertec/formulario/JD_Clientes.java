package com.cibertec.formulario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.cibertec.entidad.Cliente;
import com.cibertec.util.Mensajes;
import java.awt.Toolkit;

public class JD_Clientes extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextArea txtResultado;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtDni;
    // Lista de clientes
    List<Cliente> listaClientes = new ArrayList<>();

    public static void main(String[] args) {
        try {
            JD_Clientes dialog = new JD_Clientes();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JD_Clientes() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(JD_Clientes.class.getResource("/img/logowindow.png")));
        setTitle("Clientes");
        setBounds(100, 100, 624, 617);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(250, 235, 215));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblCodigo = new JLabel("Código");
        lblCodigo.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblCodigo.setBounds(55, 73, 46, 14);
        contentPanel.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setFont(new Font("Century Gothic", Font.PLAIN, 11));
        txtCodigo.setBounds(124, 70, 140, 20);
        txtCodigo.setEnabled(false);
        contentPanel.add(txtCodigo);
        txtCodigo.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblNombre.setBounds(55, 110, 69, 14);
        contentPanel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Century Gothic", Font.PLAIN, 11));
        txtNombre.setBounds(138, 107, 140, 20);
        contentPanel.add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblApellidos = new JLabel("Apellidos");
        lblApellidos.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblApellidos.setBounds(55, 152, 69, 14);
        contentPanel.add(lblApellidos);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(138, 150, 140, 20);
        contentPanel.add(txtApellidos);
        txtApellidos.setColumns(10);

        JLabel lbldireccion = new JLabel("Direccion");
        lbldireccion.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lbldireccion.setBounds(55, 190, 69, 14);
        contentPanel.add(lbldireccion);

        txtDireccion = new JTextField();
        txtDireccion.setColumns(10);
        txtDireccion.setBounds(138, 188, 130, 20);
        contentPanel.add(txtDireccion);

        txtTelefono = new JTextField();
        txtTelefono.setColumns(10);
        txtTelefono.setBounds(138, 229, 94, 20);
        contentPanel.add(txtTelefono);

        txtDni = new JTextField();
        txtDni.setColumns(10);
        txtDni.setBounds(124, 264, 89, 20);
        contentPanel.add(txtDni);

        JLabel lbltelefono = new JLabel("Telefono");
        lbltelefono.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lbltelefono.setBounds(55, 231, 69, 14);
        contentPanel.add(lbltelefono);

        JLabel lblDni = new JLabel("DNI");
        lblDni.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblDni.setBounds(55, 266, 69, 14);
        contentPanel.add(lblDni);

        // Cargar clientes desde el archivo
        cargarClientesDesdeArchivo();

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnIngresar.setBounds(469, 56, 89, 23);
        contentPanel.add(btnIngresar);
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ingreso de cliente
                int codigo = listaClientes.size() + 1001; // Código correlativo
                String nombre = txtNombre.getText();
                String apellidos = txtApellidos.getText();
                String direccion = txtDireccion.getText();
                String telefono = txtTelefono.getText();
                String dni = txtDni.getText();

                if (nombre.isEmpty() || apellidos.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || dni.isEmpty()) {
                    Mensajes.mostrarMensaje("Complete todos los campos");
                    return;
                }

                // Verificar si el cliente ya existe por DNI
                for (Cliente c : listaClientes) {
                    if (c.getDni().equals(dni)) {
                        JOptionPane.showMessageDialog(null, "El cliente ya está registrado.");
                        return;
                    }
                }

                Cliente nuevoCliente = new Cliente(codigo, nombre, apellidos, direccion, telefono, dni);
                listaClientes.add(nuevoCliente);
                grabarArchivo();
                JOptionPane.showMessageDialog(null, "Cliente ingresado correctamente");
                limpiarCampos();
            }
        });

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnModificar.setBounds(469, 106, 89, 23);
        contentPanel.add(btnModificar);
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                try {
                    int codigo = Integer.parseInt(txtCodigo.getText());
                    for (Cliente c : listaClientes) {
                        if (c.getCodigoCliente() == codigo) {
                            c.setNombres(txtNombre.getText());
                            c.setApellidos(txtApellidos.getText());
                            c.setDireccion(txtDireccion.getText());
                            c.setTelefono(txtTelefono.getText());
                            c.setDni(txtDni.getText());
                            grabarArchivo();
                            JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
                            limpiarCampos();
                            txtCodigo.setEnabled(false);
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Código no válido.");
                }
            }
        });

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnEliminar.setBounds(469, 159, 89, 23);
        contentPanel.add(btnEliminar);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	txtCodigo.setEnabled(true);
            	 if (txtCodigo.getText().isEmpty()) {
                     JOptionPane.showMessageDialog(null, "Por favor, ingrese un código de cliente para eliminar.");
                     return;
                 }
                try {
                    int codigo = Integer.parseInt(txtCodigo.getText());
                    listaClientes.removeIf(c -> c.getCodigoCliente() == codigo);
                    grabarArchivo();
                    JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");
                    limpiarCampos();
                    txtCodigo.setEnabled(true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Código no válido.");
                }
            }
        });

        JButton btnListar = new JButton("Listar");
        btnListar.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnListar.setBounds(469, 208, 89, 23);
        contentPanel.add(btnListar);
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarClientes();
            }
        });

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnConsultar.setBounds(469, 262, 89, 23);
        contentPanel.add(btnConsultar);
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 txtCodigo.setEnabled(true);
                if (txtCodigo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un código de cliente para consultar.");
                    return;
                }

                try {
                    int codigo = Integer.parseInt(txtCodigo.getText());
                    for (Cliente c : listaClientes) {
                        if (c.getCodigoCliente() == codigo) {
                            txtNombre.setText(c.getNombres());
                            txtApellidos.setText(c.getApellidos());
                            txtDireccion.setText(c.getDireccion());
                            txtTelefono.setText(c.getTelefono());
                            txtDni.setText(c.getDni());
                            return;
                            
                        }
                        txtCodigo.setEnabled(false);
                    }
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Código no válido.");
                }
            }
        });

        txtResultado = new JTextArea();
        txtResultado.setBounds(32, 335, 544, 217);
        contentPanel.add(txtResultado);
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtDni.setText("");
    }

    private void cargarClientesDesdeArchivo() {
        listaClientes.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(obtenerRutaArchivo("cliente.txt")))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 6) {
                    Cliente cliente = new Cliente(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4], datos[5]);
                    listaClientes.add(cliente);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo: " + ex.getMessage());
        }
    }

    private void grabarArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(obtenerRutaArchivo("cliente.txt")))) {
            for (Cliente cliente : listaClientes) {
                pw.println(cliente.getCodigoCliente() + ";" + cliente.getNombres() + ";" + cliente.getApellidos() + ";"
                        + cliente.getDireccion() + ";" + cliente.getTelefono() + ";" + cliente.getDni());
            }
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo: " + ex.getMessage());
        }
    }

    private void listarClientes() {
        cargarClientesDesdeArchivo();
        StringBuilder sb = new StringBuilder();
        for (Cliente c : listaClientes) {
            sb.append("Código: ").append(c.getCodigoCliente()).append("\n");
            sb.append("Nombre: ").append(c.getNombres()).append("\n");
            sb.append("Apellidos: ").append(c.getApellidos()).append("\n");
            sb.append("Dirección: ").append(c.getDireccion()).append("\n");
            sb.append("Teléfono: ").append(c.getTelefono()).append("\n");
            sb.append("DNI: ").append(c.getDni()).append("\n");
            sb.append("---------------------------\n");
        }
        txtResultado.setText(sb.toString());
        txtCodigo.setEnabled(false);
    }

    private String obtenerRutaArchivo(String nombreArchivo) {
        String userDir = System.getProperty("user.dir");
        return userDir + File.separator + "log" + File.separator + nombreArchivo;
    }
}
