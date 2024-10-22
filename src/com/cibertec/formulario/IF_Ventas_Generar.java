package com.cibertec.formulario;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.cibertec.datos.DatosAplicacion;
import com.cibertec.entidad.Cliente;
import com.cibertec.entidad.Producto;
import com.cibertec.entidad.Venta;
import com.cibertec.util.LeerArchivos;
import java.awt.GridLayout;

public class IF_Ventas_Generar extends JInternalFrame {
    private JTextField txtClienteCod;
    private JTextField txtClienteNombre;
    private JTextField txtProductoCodigo;
    private JTextField txtProductoNombre;
    private JTextField txtProductoCantidad;
    private JTable table;
    private JTextField txtIGV;
    private JTextField txtTotalPagar;
    private JTextField txtProductoPrecio;
    DefaultTableModel model;
    int itemContador = 0;
    private JLabel lblNumeroVenta;
    private JTextField txtSubTotal;
    private JTextField txtValProductoCodigo;
    private JTextField txtValProductoStock;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IF_Ventas_Generar frame = new IF_Ventas_Generar();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public IF_Ventas_Generar() {
        setTitle("Formulario de Ventas");
        setIconifiable(true);
        setClosable(true);
        setBounds(100, 100, 836, 686);
        getContentPane().setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(SystemColor.inactiveCaptionBorder);
        panel_1.setForeground(Color.GRAY);
        panel_1.setBounds(10, 60, 680, 218);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lblClienteCodigo = new JLabel("Cliente");
        lblClienteCodigo.setBounds(10, 24, 45, 13);
        panel_1.add(lblClienteCodigo);

        txtClienteCod = new JTextField();
        txtClienteCod.setEnabled(false);
        txtClienteCod.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String codigo = txtClienteCod.getText();
                    buscarCodigoCliente(codigo);
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                int key = e.getKeyChar();
                if (!(key >= 48 && key <= 57)) {
                    e.consume();
                }
            }
        });
        txtClienteCod.setBounds(99, 46, 97, 19);
        panel_1.add(txtClienteCod);
        txtClienteCod.setColumns(10);

        txtClienteNombre = new JTextField();
        txtClienteNombre.setBounds(99, 76, 443, 19);
        panel_1.add(txtClienteNombre);
        txtClienteNombre.setColumns(10);

        JPanel panelProducto = new JPanel();
        panelProducto.setBackground(SystemColor.inactiveCaption);
        panelProducto.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Producto",
                TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 255)));
        panelProducto.setBounds(10, 105, 646, 103);
        panel_1.add(panelProducto);
        panelProducto.setLayout(null);

        JLabel lblProductoCodigo = new JLabel("Codigo");
        lblProductoCodigo.setBounds(10, 30, 45, 13);
        panelProducto.add(lblProductoCodigo);

        txtProductoCodigo = new JTextField();
        txtProductoCodigo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String codigo = txtProductoCodigo.getText();
                    buscarCodigoProducto(codigo);
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                int key = e.getKeyChar();
                if (!(key >= 48 && key <= 57)) {
                    e.consume();
                }
            }
        });
        txtProductoCodigo.setBounds(53, 27, 58, 19);
        panelProducto.add(txtProductoCodigo);
        txtProductoCodigo.setColumns(10);

        txtProductoNombre = new JTextField();
        txtProductoNombre.setBounds(239, 27, 271, 19);
        panelProducto.add(txtProductoNombre);
        txtProductoNombre.setColumns(10);

        JLabel lblCantidad = new JLabel("Cantidad");
        lblCantidad.setBounds(523, 51, 113, 13);
        panelProducto.add(lblCantidad);

        txtProductoCantidad = new JTextField();
        txtProductoCantidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int key = e.getKeyChar();
                if (!(key >= 48 && key <= 57)) {
                    e.consume();
                }
            }
        });
        txtProductoCantidad.setBounds(540, 74, 96, 19);
        panelProducto.add(txtProductoCantidad);
        txtProductoCantidad.setColumns(10);

        JLabel lblProductoPrecio = new JLabel("Precio");
        lblProductoPrecio.setBounds(146, 51, 45, 13);
        panelProducto.add(lblProductoPrecio);

        txtProductoPrecio = new JTextField();
        txtProductoPrecio.setBounds(128, 74, 96, 19);
        panelProducto.add(txtProductoPrecio);
        txtProductoPrecio.setColumns(10);

        JLabel lblProductoNombre = new JLabel("Nombre");
        lblProductoNombre.setBounds(164, 33, 45, 13);
        panelProducto.add(lblProductoNombre);

        JLabel lblNewLabel_4 = new JLabel("Producto Codigo");
        lblNewLabel_4.setBounds(10, 51, 126, 13);
        panelProducto.add(lblNewLabel_4);

        txtValProductoCodigo = new JTextField();
        txtValProductoCodigo.setBounds(10, 74, 96, 19);
        panelProducto.add(txtValProductoCodigo);
        txtValProductoCodigo.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("Producto Stock");
        lblNewLabel_5.setBounds(523, 10, 113, 13);
        panelProducto.add(lblNewLabel_5);

        txtValProductoStock = new JTextField();
        txtValProductoStock.setBounds(540, 27, 96, 19);
        panelProducto.add(txtValProductoStock);
        txtValProductoStock.setColumns(10);

        JButton btnClienteBuscar = new JButton("Buscar");
        btnClienteBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JD_Cliente_Buscar busquedaCliente = new JD_Cliente_Buscar(new JD_Cliente_Buscar.ClienteSeleccionadoListener() {
                    @Override
                    public void clienteSeleccionado(String codigo, String nombreCompleto) {
                        txtClienteCod.setText(codigo);
                        txtClienteNombre.setText(nombreCompleto);
                    }
                });
                busquedaCliente.setVisible(true);
            }
        });
        btnClienteBuscar.setBounds(99, 22, 117, 17);
        panel_1.add(btnClienteBuscar);

        JLabel lblClienteCodigoVal = new JLabel("Codigo");
        lblClienteCodigoVal.setBounds(10, 50, 79, 13);
        panel_1.add(lblClienteCodigoVal);

        JLabel lblClienteNombre = new JLabel("Nombres");
        lblClienteNombre.setBounds(10, 79, 79, 13);
        panel_1.add(lblClienteNombre);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregar();
            }
        });
        btnAgregar.setBackground(SystemColor.activeCaption);
        btnAgregar.setForeground(SystemColor.desktop);
        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnAgregar.setBounds(719, 104, 85, 34);
        getContentPane().add(btnAgregar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 288, 680, 197);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, "", null, null }, },
                new String[] { "Item", "Codigo", "Nombre", "Cantidad", "Precio", "SubTotal" }));
        model = new DefaultTableModel();
        table.setModel(model);
        model.addColumn("Item");
        model.addColumn("Codigo");
        model.addColumn("Nombre");
        model.addColumn("Cantidad");
        model.addColumn("Precio");
        model.addColumn("SubTotal");

        scrollPane.setViewportView(table);

        JButton btnComprar = new JButton("Comprar");
        btnComprar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnComprar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comprar();
            }
        });
        btnComprar.setBounds(719, 148, 85, 34);
        getContentPane().add(btnComprar);

        lblNumeroVenta = new JLabel("New label");
        lblNumeroVenta.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNumeroVenta.setBounds(214, 9, 96, 29);
        getContentPane().add(lblNumeroVenta);
        
        

        JButton btnNuevo = new JButton("Nuevo");
        btnNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nuevo();
            }
        });
        btnNuevo.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnNuevo.setBounds(719, 60, 85, 34);
        getContentPane().add(btnNuevo);

        JLabel lblNewLabel_2 = new JLabel("NUMERO VENTA");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_2.setBounds(20, 10, 184, 28);
        getContentPane().add(lblNewLabel_2);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
                new Color(160, 160, 160)), "Totales", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
        panel.setBounds(432, 495, 258, 122);
        getContentPane().add(panel);
        panel.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel lblSubTotal = new JLabel("Sub Total");
        panel.add(lblSubTotal);

        txtSubTotal = new JTextField();
        panel.add(txtSubTotal);
        txtSubTotal.setColumns(10);

        JLabel lblNewLabel = new JLabel("IGV");
        panel.add(lblNewLabel);

        txtIGV = new JTextField();
        panel.add(txtIGV);
        txtIGV.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Total a Pagar");
        panel.add(lblNewLabel_1);

        txtTotalPagar = new JTextField();
        panel.add(txtTotalPagar);
        txtTotalPagar.setColumns(10);

        cargarNumeroVenta();
        deshabilitar_componentes();
    }
    private static int numeroVenta = 3000;  // Inicializa el número de venta en 3000

    public void agregar() {
        String codigoProducto = txtValProductoCodigo.getText();
        String cantidadStr = txtProductoCantidad.getText();
        String nombreProducto = txtProductoNombre.getText();
        String precioStr = txtProductoPrecio.getText();
        String stockStr = txtValProductoStock.getText();

        if (codigoProducto.length() > 0 && cantidadStr.length() > 0) {
            int cantidad = Integer.parseInt(cantidadStr);
            int stockDisponible = Integer.parseInt(stockStr);

            // Verificar que la cantidad solicitada no exceda el stock disponible
            if (cantidad > stockDisponible) {
                JOptionPane.showMessageDialog(null, "La cantidad solicitada excede el stock disponible. Stock disponible: " + stockDisponible);
                return;  // No continuar si la cantidad es mayor al stock
            }

            itemContador++;
            double precio = Double.parseDouble(precioStr);
            double subTotal = precio * cantidad;

            Object[] fila = { itemContador, codigoProducto, nombreProducto, cantidad, precio, subTotal };
            model.addRow(fila);

            // Limpiar los campos de producto después de agregar
            limpiarCamposProducto();

            // Actualizar los totales después de agregar el producto
            generarTotales();
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese el código del producto y la cantidad.");
        }
    }

    public void limpiarCamposProducto() {
        txtProductoCodigo.setText("");
        txtProductoNombre.setText("");
        txtProductoCantidad.setText("");
        txtProductoPrecio.setText("");
        txtValProductoCodigo.setText("");
        txtValProductoStock.setText("");
    }

    public void comprar() {
        int cantidadFilas = table.getRowCount();
        String codClienteVal = txtClienteCod.getText();

        if (cantidadFilas > 0 && !codClienteVal.equals("")) {
            LeerArchivos objLeer = new LeerArchivos();
            List<Producto> listaProductos = objLeer.cargarProductos();

            for (int fila = 0; fila < cantidadFilas; fila++) {
                String codigoProducto = table.getValueAt(fila, 1).toString();
                int cantidadComprada = Integer.parseInt(table.getValueAt(fila, 3).toString());

                for (Producto producto : listaProductos) {
                    if (producto.getCodigoProducto() == Integer.parseInt(codigoProducto)) {
                        int nuevoStock = producto.getStockActual() - cantidadComprada;
                        if (nuevoStock < 0) {
                            JOptionPane.showMessageDialog(null, "Stock insuficiente para el producto: " + producto.getNombre());
                            return;
                        }
                        // Actualiza solo el stock
                        producto.setStockActual(nuevoStock);
                        break;
                    }
                }
            }

            // Guardar los productos con el stock actualizado (y todos sus datos)
            objLeer.guardarProductos(listaProductos);
            
         // Incrementar el número de venta después de la compra
            incrementarCodigoVenta();

            // Limpiar los campos de la venta y la tabla
            limpiarCamposVenta();
            model.setRowCount(0); // Limpiar la tabla

            JOptionPane.showMessageDialog(null, "Compra realizada con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Valide la información del formulario");
        }
    }

    public void incrementarCodigoVenta() {
        numeroVenta++;  // Incrementa el número de venta
        lblNumeroVenta.setText(String.valueOf(numeroVenta));  // Actualiza el label con el nuevo número
    }

    public void limpiarCamposVenta() {
        txtClienteCod.setText("");
        txtClienteNombre.setText("");
        txtSubTotal.setText("");
        txtIGV.setText("");
        txtTotalPagar.setText("");
    }


    public void actualizarStockProducto(String codigoProducto, int cantidadComprada) {
        try {
            LeerArchivos objLeer = new LeerArchivos();
            List<Producto> listaProductos = objLeer.cargarProductos();

            for (Producto producto : listaProductos) {
                if (producto.getCodigoProducto() == Integer.parseInt(codigoProducto)) {
                    int nuevoStock = producto.getStockActual() - cantidadComprada;
                    if (nuevoStock < 0) {
                        JOptionPane.showMessageDialog(null, "Stock insuficiente para el producto " + producto.getNombre());
                        return;
                    }
                    producto.setStockActual(nuevoStock);
                    break;
                }
            }
            objLeer.guardarProductos(listaProductos);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el stock: " + e.getMessage());
        }
    }

    public void generarTotales() {
        double importeCompra = 0, igv, importePagar;
        int cantidadFila = table.getRowCount();

        for (int fila = 0; fila < cantidadFila; fila++) {
            importeCompra += Double.parseDouble(table.getValueAt(fila, 5).toString()); // Columna "SubTotal"
        }

        igv = importeCompra * 0.18;
        importePagar = importeCompra + igv;
        
        // Actualizar los campos de texto con los totales
        txtSubTotal.setText(String.format("%.2f", importeCompra));
        txtIGV.setText(String.format("%.2f", igv));
        txtTotalPagar.setText(String.format("%.2f", importePagar));
    }


    public void incrementarCodigoVenta(int incremento) {
        DatosAplicacion.VENTA_NUMERO = incremento;
    }

    public void cargarNumeroVenta() {
        lblNumeroVenta.setText(String.valueOf(numeroVenta));  // Actualiza el label con el número de venta actual
    }

    public String getFecha() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(new Date());
    }

    public void grabarArchivo(Venta venta) {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter("c:/log/venta.txt", true);
            pw = new PrintWriter(fw);

            String cadenaGuardar = venta.getCodigoVenta() + ";" + venta.getCodigoCliente() + ";"
                    + venta.getCodigoProducto() + ";" + venta.getCantidad() + ";" + venta.getPrecio() + ";"
                    + venta.getFecha();

            pw.println(cadenaGuardar);

        } catch (Exception ex) {
            System.out.println("grabarArchivo - Error : " + ex.getMessage());
        } finally {
            try {
                if (pw != null) pw.close();
                if (fw != null) fw.close();
            } catch (IOException ex) {
                System.out.println("grabarArchivo - Error : " + ex.getMessage());
            }
        }
    }

    public void nuevo() {
        cargarNumeroVenta();
        itemContador = 0;
        model.setNumRows(0);
        txtSubTotal.setText("");
        txtIGV.setText("");
        txtTotalPagar.setText("");
        txtClienteCod.setText("");
        txtClienteNombre.setText("");
        txtProductoCodigo.setText("");
        txtProductoNombre.setText("");
        txtProductoPrecio.setText("");
        txtProductoCantidad.setText("");

        txtValProductoCodigo.setText("");
        txtValProductoStock.setText("");
        txtClienteCod.requestFocus();
    }

    public void deshabilitar_componentes() {
        txtIGV.setEditable(false);
        txtSubTotal.setEditable(false);
        txtTotalPagar.setEditable(false);

        txtClienteNombre.setEditable(false);
        txtProductoNombre.setEditable(false);
        txtProductoPrecio.setEditable(false);
        txtValProductoCodigo.setEditable(false);
        txtValProductoStock.setEditable(false);
    }

    public void buscarCodigoCliente(String codigo) {
        try {
            LeerArchivos objLeer = new LeerArchivos();
            List<Cliente> lista = objLeer.cargarClientes();
            int contador = 0;
            for (Cliente x : lista) {
                if (x.getCodigoCliente() == Integer.parseInt(codigo)) {
                    txtClienteNombre.setText(x.getApellidos() + " " + x.getNombres());
                    contador++;
                    break;
                }
            }

            if (contador == 0) {
                JOptionPane.showMessageDialog(null, "No se encontró el código de cliente");
                txtClienteCod.setText("");
                txtClienteCod.requestFocus();
                txtClienteNombre.setText("");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
        }
    }

    public void buscarCodigoProducto(String codigo) {
        try {
            LeerArchivos objLeer = new LeerArchivos();
            List<Producto> lista = objLeer.cargarProductos();
            int contador = 0;
            for (Producto x : lista) {
                if (x.getCodigoProducto() == Integer.parseInt(codigo)) {
                    txtProductoNombre.setText(x.getNombre());
                    txtValProductoCodigo.setText(x.getCodigoProducto() + "");
                    txtValProductoStock.setText(x.getStockActual() + "");
                    txtProductoPrecio.setText(x.getPrecio() + "");
                    contador++;
                    break;
                }
            }

            if (contador == 0) {
                JOptionPane.showMessageDialog(null, "No se encontró el código de producto");
                txtProductoCodigo.setText("");
                txtProductoCodigo.requestFocus();
                txtProductoNombre.setText("");
                txtValProductoCodigo.setText("");
                txtValProductoStock.setText("");
                txtProductoPrecio.setText("");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
        }
    }
}
