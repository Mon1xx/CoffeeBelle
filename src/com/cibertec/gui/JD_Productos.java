package com.cibertec.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.cibertec.arreglos.ArregloProducto;
import com.cibertec.clases.Producto;
import com.cibertec.util.Mensajes;
import java.awt.Toolkit;

public class JD_Productos extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStockActual;
    private JTextField txtStockMinimo;
    private JTextField txtStockMaximo;
    private JTextArea txtResultado;
    private JComboBox<String> comboBoxTipoProducto;
    private JComboBox<String> comboBoxTamaño;
    ArregloProducto arregloProducto = new ArregloProducto();

    // Lista de productos
    ArrayList<Producto> listaProductos = new ArrayList<>();

    public static void main(String[] args) {
        try {
            JD_Productos dialog = new JD_Productos();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JD_Productos() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(JD_Productos.class.getResource("/img/logowindow.png")));
        setTitle("Productos");
        setBounds(100, 100, 621, 677);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(250, 235, 215));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblCodigo = new JLabel("Código");
        lblCodigo.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblCodigo.setBounds(55, 81, 46, 14);
        contentPanel.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setFont(new Font("Century Gothic", Font.PLAIN, 11));
        txtCodigo.setBounds(140, 78, 140, 20);
        txtCodigo.setEnabled(false);
        contentPanel.add(txtCodigo);
        txtCodigo.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblNombre.setBounds(55, 122, 69, 14);
        contentPanel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Century Gothic", Font.PLAIN, 11));
        txtNombre.setBounds(140, 119, 140, 20);
        contentPanel.add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblPrecio = new JLabel("Precio");
        lblPrecio.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblPrecio.setBounds(55, 231, 46, 14);
        contentPanel.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(134, 225, 96, 20);
        contentPanel.add(txtPrecio);
        txtPrecio.setColumns(10);

        JLabel lblStockActual = new JLabel("Stock Actual");
        lblStockActual.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblStockActual.setBounds(55, 266, 109, 14);
        contentPanel.add(lblStockActual);

        txtStockActual = new JTextField();
        txtStockActual.setBounds(167, 264, 80, 20);
        contentPanel.add(txtStockActual);
        txtStockActual.setColumns(10);

        JLabel lblStockMinimo = new JLabel("Stock Mínimo");
        lblStockMinimo.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblStockMinimo.setBounds(55, 312, 109, 14);
        contentPanel.add(lblStockMinimo);

        txtStockMinimo = new JTextField();
        txtStockMinimo.setBounds(167, 310, 80, 20);
        contentPanel.add(txtStockMinimo);
        txtStockMinimo.setColumns(10);

        JLabel lblStockMaximo = new JLabel("Stock Máximo");
        lblStockMaximo.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblStockMaximo.setBounds(55, 356, 109, 14);
        contentPanel.add(lblStockMaximo);

        txtStockMaximo = new JTextField();
        txtStockMaximo.setBounds(167, 354, 80, 20);
        contentPanel.add(txtStockMaximo);
        txtStockMaximo.setColumns(10);

        JLabel lblTipoProducto = new JLabel("Tipo de Producto");
        lblTipoProducto.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblTipoProducto.setBounds(55, 153, 130, 14);
        contentPanel.add(lblTipoProducto);

        comboBoxTipoProducto = new JComboBox<>();
        comboBoxTipoProducto.setFont(new Font("Century Gothic", Font.PLAIN, 11));
        comboBoxTipoProducto.setModel(new DefaultComboBoxModel<>(
                new String[]{"Bebidas", "Snacks", "Postres", "Café en grano", "Promociones"}));
        comboBoxTipoProducto.setBounds(179, 150, 120, 22);
        contentPanel.add(comboBoxTipoProducto);

        JLabel lblTamaño = new JLabel("Tamaño");
        lblTamaño.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblTamaño.setBounds(55, 191, 86, 14);
        contentPanel.add(lblTamaño);

        comboBoxTamaño = new JComboBox<>();
        comboBoxTamaño.setFont(new Font("Century Gothic", Font.PLAIN, 11));
        comboBoxTamaño.setModel(new DefaultComboBoxModel<>(new String[]{"Pequeño", "Mediano", "Grande"}));
        comboBoxTamaño.setBounds(124, 188, 140, 22);
        contentPanel.add(comboBoxTamaño);

        txtResultado = new JTextArea();
        txtResultado.setBounds(32, 400, 544, 217);
        contentPanel.add(txtResultado);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnIngresar.setBounds(456, 36, 89, 23);
        contentPanel.add(btnIngresar);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnModificar.setBounds(456, 98, 89, 23);
        contentPanel.add(btnModificar);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnConsultar.setBounds(456, 159, 89, 23);
        contentPanel.add(btnConsultar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setForeground(new Color(255, 0, 0));
        btnEliminar.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnEliminar.setBounds(456, 217, 89, 23);
        contentPanel.add(btnEliminar);

        JButton btnListar = new JButton("Listar");
        btnListar.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnListar.setBounds(456, 275, 89, 23);
        contentPanel.add(btnListar);

        JScrollPane scrollPane = new JScrollPane(txtResultado);
        scrollPane.setBounds(32, 400, 544, 217);
        contentPanel.add(scrollPane);

        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	  txtCodigo.setEnabled(false);
                try {
                	
                    if (txtNombre.getText().isEmpty() || txtPrecio.getText().isEmpty()
                            || txtStockActual.getText().isEmpty() || txtStockMinimo.getText().isEmpty()
                            || txtStockMaximo.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                        return;
                    }

                    cargarProductosDesdeArchivo();

                    String tipoProducto = comboBoxTipoProducto.getSelectedItem().toString();
                    String tamañoProducto = comboBoxTamaño.getSelectedItem().toString();
                    int codigo = listaProductos.size() + 2001;

                    // Verificar si ya existe un producto con el mismo nombre
                    for (Producto p : listaProductos) {
                        if (p.getNombre().equals(txtNombre.getText())) {
                            JOptionPane.showMessageDialog(null, "El producto ya está registrado.");
                            return;
                        }
                    }

                    String nombre = txtNombre.getText();
                    double precio = Double.parseDouble(txtPrecio.getText());
                    int stockActual = Integer.parseInt(txtStockActual.getText());
                    int stockMinimo = Integer.parseInt(txtStockMinimo.getText());
                    int stockMaximo = Integer.parseInt(txtStockMaximo.getText());

                    Producto nuevoProducto = new Producto(codigo, nombre, precio, stockActual, stockMinimo,
                            stockMaximo, tipoProducto, tamañoProducto);
                    listaProductos.add(nuevoProducto);
                    grabarArchivo();
                    arregloProducto.agregar(nuevoProducto);
                    JOptionPane.showMessageDialog(null, "Producto ingresado correctamente\nTipo: " + tipoProducto
                            + "\nTamaño: " + tamañoProducto);

                    limpiarCampos();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese valores numéricos válidos en Precio y Stock.");
                }
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (txtCodigo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un código de producto para modificar.");
                    return;
                }

                try {
                    int codigo = Integer.parseInt(txtCodigo.getText());
                    cargarProductosDesdeArchivo();

                    for (Producto p : listaProductos) {
                        if (p.getCodigoProducto() == codigo) {
                            p.setNombre(txtNombre.getText());
                            p.setPrecio(Double.parseDouble(txtPrecio.getText()));
                            p.setStockActual(Integer.parseInt(txtStockActual.getText()));
                            p.setStockMinimo(Integer.parseInt(txtStockMinimo.getText()));
                            p.setStockMaximo(Integer.parseInt(txtStockMaximo.getText()));
                            p.setTipoProducto(comboBoxTipoProducto.getSelectedItem().toString());
                            p.setTamaño(comboBoxTamaño.getSelectedItem().toString());

                            grabarArchivo();
                            JOptionPane.showMessageDialog(null, "Producto modificado correctamente.");
                            limpiarCampos();
                            txtCodigo.setEnabled(false);
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese valores numéricos válidos.");
                }
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtCodigo.setEnabled(true);
                if (txtCodigo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un código de producto para consultar.");
                    return;
                }

                try {
                    int codigo = Integer.parseInt(txtCodigo.getText());
                    cargarProductosDesdeArchivo();

                    for (Producto p : listaProductos) {
                        if (p.getCodigoProducto() == codigo) {
                            txtNombre.setText(p.getNombre());
                            txtPrecio.setText(String.valueOf(p.getPrecio()));
                            txtStockActual.setText(String.valueOf(p.getStockActual()));
                            txtStockMinimo.setText(String.valueOf(p.getStockMinimo()));
                            txtStockMaximo.setText(String.valueOf(p.getStockMaximo()));
                            comboBoxTipoProducto.setSelectedItem(p.getTipoProducto());
                            comboBoxTamaño.setSelectedItem(p.getTamaño());
                            return;
                        }
                        txtCodigo.setEnabled(false);
                    }
                    JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un valor numérico válido.");
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtCodigo.setEnabled(true);
                if (txtCodigo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un código de producto para eliminar.");
                    return;
                }

                try {
                    int codigo = Integer.parseInt(txtCodigo.getText());
                    cargarProductosDesdeArchivo();

                    for (Producto p : listaProductos) {
                        if (p.getCodigoProducto() == codigo) {
                            listaProductos.remove(p);
                            grabarArchivo();
                            JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
                            limpiarCampos();
                            txtCodigo.setEnabled(false);
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un valor numérico válido.");
                }
            }
        });

        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarProductosDesdeArchivo();
                StringBuilder sb = new StringBuilder();
                for (Producto p : listaProductos) {
                    sb.append("Código: ").append(p.getCodigoProducto()).append("\n");
                    sb.append("Nombre: ").append(p.getNombre()).append("\n");
                    sb.append("Precio: ").append(p.getPrecio()).append("\n");
                    sb.append("Stock Actual: ").append(p.getStockActual()).append("\n");
                    sb.append("Stock Mínimo: ").append(p.getStockMinimo()).append("\n");
                    sb.append("Stock Máximo: ").append(p.getStockMaximo()).append("\n");
                    sb.append("Tipo de Producto: ").append(p.getTipoProducto()).append("\n");
                    sb.append("Tamaño: ").append(p.getTamaño()).append("\n");
                    sb.append("---------------------------\n");
                }
                txtResultado.setText(sb.toString());
                txtCodigo.setEnabled(false);
            }
        });
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStockActual.setText("");
        txtStockMinimo.setText("");
        txtStockMaximo.setText("");
        comboBoxTipoProducto.setSelectedIndex(0);
        comboBoxTamaño.setSelectedIndex(0);
    }

    private String obtenerRutaArchivo(String nombreArchivo) {
        String userDir = System.getProperty("user.dir");
        return userDir + File.separator + "log" + File.separator + nombreArchivo;
    }

    private void grabarArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(obtenerRutaArchivo("producto.txt")))) {
            for (Producto p : listaProductos) {
                pw.println(p.getCodigoProducto() + ";" + p.getNombre() + ";" + p.getPrecio() + ";"
                        + p.getStockActual() + ";" + p.getStockMinimo() + ";" + p.getStockMaximo() + ";"
                        + p.getTipoProducto() + ";" + p.getTamaño());
            }
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo: " + ex.getMessage());
        }
    }

    private void cargarProductosDesdeArchivo() {
        listaProductos.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(obtenerRutaArchivo("producto.txt")))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 8) {
                    Producto producto = new Producto(Integer.parseInt(datos[0]), datos[1], Double.parseDouble(datos[2]),
                            Integer.parseInt(datos[3]), Integer.parseInt(datos[4]), Integer.parseInt(datos[5]),
                            datos[6], datos[7]);
                    listaProductos.add(producto);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo: " + ex.getMessage());
        }
    }
}
