package com.cibertec.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.cibertec.arreglos.ArregloVentas;
import com.cibertec.clases.Venta;

import javax.swing.DefaultComboBoxModel;

public class IF_Reportes_reporte extends JInternalFrame {
	private JTextArea txtS;
	private JComboBox cboTipoReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IF_Reportes_reporte frame = new IF_Reportes_reporte();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IF_Reportes_reporte() {
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 805, 512);
		getContentPane().setLayout(null);
		
		JLabel lblTipoReporte = new JLabel("Tipo Reporte");
		lblTipoReporte.setBounds(37, 23, 114, 13);
		getContentPane().add(lblTipoReporte);
		
		cboTipoReporte = new JComboBox();
		cboTipoReporte.setModel(new DefaultComboBoxModel(new String[] {"Listado general de ventas"}));
		cboTipoReporte.setBounds(204, 19, 236, 21);
		getContentPane().add(cboTipoReporte);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
			}
		});
		btnConsultar.setBounds(485, 19, 85, 21);
		getContentPane().add(btnConsultar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 84, 699, 353);
		getContentPane().add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);

	}
	
	public void consultar() {
		limpiar();
		ArrayList<Venta> lista = ArregloVentas.obtenerTodo();
		
		int indice = cboTipoReporte.getSelectedIndex();
		switch(indice) {
			case 0: listadoGeneral(lista);break;
		}
	}
	
	public void listadoGeneral(ArrayList<Venta> lista) {
		
		for(Venta fila: lista) {
			mensaje("Numero de Venta : " + fila.getCodigoVenta());
			mensaje("Cliente Codigo : " + fila.getCodigoCliente());
			mensaje("Fecha de Compra : " + fila.getFecha());
			mensaje("Detalle de Productos ****************");
			mensaje("*************************************");
			mensaje("Cod\t Cantidad\t Precio\t");
			mensaje(fila.getCodigoProducto()+"\t"+fila.getCantidad()+"\t"+fila.getPrecio());
			mensaje("*********************************************************");
		}
		
	}
	
	public void limpiar() {
		txtS.setText("");
	}
	public void mensaje(String mensaje) {
		txtS.append(mensaje+"\n");
	}
	
}
























