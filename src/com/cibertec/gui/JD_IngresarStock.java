package com.cibertec.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.cibertec.clases.Producto;

import java.io.*;
import java.util.ArrayList;

public class JD_IngresarStock extends JDialog {

    private JTextField txtCodigoProducto;
    private JTextField txtCantidad;
    private JTextField txtStockActual;
    private JTextField txtStockMaximo;
    private JTextField txtStockMinimo;
    private ArrayList<Producto> listaProductos = new ArrayList<>();
    private Producto productoEncontrado = null; // Guardamos el producto encontrado para evitar búsqueda redundante

    public JD_IngresarStock() {
        setTitle("Ingresar Stock");
        setBounds(100, 100, 400, 400);
        getContentPane().setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(250, 235, 215));
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JLabel lblCodigoProducto = new JLabel("Código Producto:");
        lblCodigoProducto.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblCodigoProducto.setBounds(50, 30, 150, 25);
        contentPanel.add(lblCodigoProducto);

        txtCodigoProducto = new JTextField();
        txtCodigoProducto.setBounds(200, 30, 120, 25);
        contentPanel.add(txtCodigoProducto);
        txtCodigoProducto.setColumns(10);

        JButton btnBuscar = new JButton("Buscar Producto");
        btnBuscar.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnBuscar.setBounds(150, 70, 150, 30);
        contentPanel.add(btnBuscar);

        JLabel lblStockActual = new JLabel("Stock Actual:");
        lblStockActual.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblStockActual.setBounds(50, 120, 150, 25);
        contentPanel.add(lblStockActual);

        txtStockActual = new JTextField();
        txtStockActual.setBounds(200, 120, 120, 25);
        txtStockActual.setEditable(false);  // Campo bloqueado
        contentPanel.add(txtStockActual);
        txtStockActual.setColumns(10);

        JLabel lblStockMinimo = new JLabel("Stock Mínimo:");
        lblStockMinimo.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblStockMinimo.setBounds(50, 160, 150, 25);
        contentPanel.add(lblStockMinimo);

        txtStockMinimo = new JTextField();
        txtStockMinimo.setBounds(200, 160, 120, 25);
        txtStockMinimo.setEditable(false);  // Campo bloqueado
        contentPanel.add(txtStockMinimo);
        txtStockMinimo.setColumns(10);

        JLabel lblStockMaximo = new JLabel("Stock Máximo:");
        lblStockMaximo.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblStockMaximo.setBounds(50, 200, 150, 25);
        contentPanel.add(lblStockMaximo);

        txtStockMaximo = new JTextField();
        txtStockMaximo.setBounds(200, 200, 120, 25);
        txtStockMaximo.setEditable(false);  // Campo bloqueado
        contentPanel.add(txtStockMaximo);
        txtStockMaximo.setColumns(10);

        JLabel lblCantidad = new JLabel("Cantidad a Ingresar:");
        lblCantidad.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblCantidad.setBounds(50, 240, 150, 25);
        contentPanel.add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(200, 240, 120, 25);
        contentPanel.add(txtCantidad);
        txtCantidad.setColumns(10);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnIngresar.setBounds(150, 290, 100, 30);
        contentPanel.add(btnIngresar);

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ingresarStock();
            }
        });
    }

    private void buscarProducto() {
        try {
            int codigo = Integer.parseInt(txtCodigoProducto.getText());
            cargarProductosDesdeArchivo();

            for (Producto producto : listaProductos) {
                if (producto.getCodigoProducto() == codigo) {
                    productoEncontrado = producto;  // Guardamos el producto encontrado
                    txtStockActual.setText(String.valueOf(producto.getStockActual()));
                    txtStockMaximo.setText(String.valueOf(producto.getStockMaximo()));
                    txtStockMinimo.setText(String.valueOf(producto.getStockMinimo()));
                    JOptionPane.showMessageDialog(null, "Producto encontrado.");
                    return;
                }
            }

            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un código numérico válido.");
       
        }
    }

    private void ingresarStock() {
        if (productoEncontrado == null) {
            JOptionPane.showMessageDialog(null, "Primero debe buscar el producto.");
            return;
        }

        try {
            int cantidad = Integer.parseInt(txtCantidad.getText());
            int nuevoStock = productoEncontrado.getStockActual() + cantidad;

            if (nuevoStock > productoEncontrado.getStockMaximo()) {
                JOptionPane.showMessageDialog(null, "El nuevo stock supera el stock máximo permitido." );
                return;
            } txtCantidad.setText("");
            
            
            productoEncontrado.setStockActual(nuevoStock);
            grabarArchivo();
            JOptionPane.showMessageDialog(null, "Stock actualizado correctamente.");
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese una cantidad válida.");
            
        }
        txtCantidad.setText("");
    }

    private void limpiarCampos() {
        txtCodigoProducto.setText("");
        txtStockActual.setText("");
        txtStockMaximo.setText("");
        txtStockMinimo.setText("");
        txtCantidad.setText("");
        productoEncontrado = null;  // Reiniciamos el producto encontrado
    }

    private void cargarProductosDesdeArchivo() {
        listaProductos.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(obtenerRutaArchivo("producto.txt")))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 8) {
                    Producto producto = new Producto(
                        Integer.parseInt(datos[0]),
                        datos[1],
                        Double.parseDouble(datos[2]),
                        Integer.parseInt(datos[3]),
                        Integer.parseInt(datos[4]),
                        Integer.parseInt(datos[5]),
                        datos[6],
                        datos[7]
                    );
                    listaProductos.add(producto);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo: " + ex.getMessage());
        }
    }

    private void grabarArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(obtenerRutaArchivo("producto.txt")))) {
            for (Producto producto : listaProductos) {
                pw.println(producto.getCodigoProducto() + ";" + producto.getNombre() + ";"
                    + producto.getPrecio() + ";" + producto.getStockActual() + ";"
                    + producto.getStockMinimo() + ";" + producto.getStockMaximo() + ";"
                    + producto.getTipoProducto() + ";" + producto.getTamaño());
            }
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo: " + ex.getMessage());
        }
    }

    private String obtenerRutaArchivo(String nombreArchivo) {
        String userDir = System.getProperty("user.dir");
        return userDir + File.separator + "log" + File.separator + nombreArchivo;
    }
}
