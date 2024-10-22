package com.cibertec.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.cibertec.arreglos.ArregloCliente;
import com.cibertec.clases.Cliente;
import com.cibertec.util.LeerArchivos;

public class JD_Cliente_Buscar extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtCodigoCliente;
    private JTable tableCliente;
    DefaultTableModel model;
    private ClienteSeleccionadoListener listener;

    public interface ClienteSeleccionadoListener {
        void clienteSeleccionado(String codigo, String nombreCompleto);
    }

    public JD_Cliente_Buscar(ClienteSeleccionadoListener listener) {
        this.listener = listener;
        setTitle("Formulario Búsqueda Cliente");
        setBounds(100, 100, 596, 365);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(20, 36, 63, 13);
        contentPanel.add(lblCodigo);

        txtCodigoCliente = new JTextField();
        txtCodigoCliente.setBounds(93, 33, 278, 19);
        contentPanel.add(txtCodigoCliente);
        txtCodigoCliente.setColumns(10);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscar();
            }
        });
        btnBuscar.setBounds(413, 32, 85, 21);
        contentPanel.add(btnBuscar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 71, 562, 202);
        contentPanel.add(scrollPane);

        tableCliente = new JTable();
        model = new DefaultTableModel();
        tableCliente.setModel(model);
        model.addColumn("Código");
        model.addColumn("Nombre Completo");
        scrollPane.setViewportView(tableCliente);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Aceptar");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = tableCliente.getSelectedRow();
                if (fila != -1) {
                    String codigo = tableCliente.getValueAt(fila, 0).toString();
                    String nombreCompleto = tableCliente.getValueAt(fila, 1).toString();
                    listener.clienteSeleccionado(codigo, nombreCompleto);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un cliente.");
                }
            }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        setLocationRelativeTo(null);
    }

    public void buscar() {
        model.setNumRows(0);
        String codigoCliente = txtCodigoCliente.getText().trim();

        if (codigoCliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un código para buscar.");
            return;
        }

        LeerArchivos objLeer = new LeerArchivos();
        List<Cliente> listaCliente = objLeer.cargarClientes();
        boolean clienteEncontrado = false;

        for (Cliente cliente : listaCliente) {
            if (String.valueOf(cliente.getCodigoCliente()).equals(codigoCliente)) {
                Object[] fila = { cliente.getCodigoCliente(), cliente.getNombres() + " " + cliente.getApellidos() };
                model.addRow(fila);
                clienteEncontrado = true;
                break;
            }
        }

        if (!clienteEncontrado) {
            JOptionPane.showMessageDialog(null, "No se encontró un cliente con el código ingresado.");
        }
    }
}