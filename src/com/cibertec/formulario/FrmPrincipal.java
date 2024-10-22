package com.cibertec.formulario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;

public class FrmPrincipal extends JFrame {

	private JPanel contentPane;
	private JDesktopPane escritorio;
	IF_Ventas_Generar ventasGenerar = null;
	private JMenu mnAlmacen;
	private JMenuItem mntmListadoGeneral;
	private JMenuItem mntmStockProductos;
	private JMenuItem mntmNewMenuItem_2;
	private JLabel lblNewLabel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
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
	public FrmPrincipal() {
		setTitle("Coffee Belle");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmPrincipal.class.getResource("/img/coffebellepng.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1131, 770);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnClientes = new JMenu("Mantenimiento");
		mnClientes.setFont(new Font("Century Gothic", Font.BOLD, 16));
		menuBar.add(mnClientes);
		
		JMenuItem mniClienteCrud = new JMenuItem("Cliente Crud");
		mniClienteCrud.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mniClienteCrud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JD_Clientes jdClientes= new JD_Clientes();
				jdClientes.setVisible(true);
			}
		});
		mnClientes.add(mniClienteCrud);
		
		JMenuItem mniProductoCrud = new JMenuItem("Producto Crud");
		mniProductoCrud.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnClientes.add(mniProductoCrud);
		mniProductoCrud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JD_Productos jdProductos= new JD_Productos();
				jdProductos.setVisible(true);
			}
		});
		
		JMenu mnVentas = new JMenu("Ventas");
		mnVentas.setFont(new Font("Century Gothic", Font.BOLD, 16));
		menuBar.add(mnVentas);
		
		JMenuItem mniVentasGenerar = new JMenuItem("Generar");
		mniVentasGenerar.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mniVentasGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (ventasGenerar==null || ventasGenerar.isClosed()) {
					ventasGenerar= new IF_Ventas_Generar();
					escritorio.add(ventasGenerar);
					ventasGenerar.setVisible(true);
				}
			}
		});
		mnVentas.add(mniVentasGenerar);
		
		mnAlmacen = new JMenu("Almacen");
		mnAlmacen.setFont(new Font("Century Gothic", Font.BOLD, 16));
		menuBar.add(mnAlmacen);
		JMenuItem mniIngresarStock = new JMenuItem("Ingresar Stock");
		mniIngresarStock.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnAlmacen.add(mniIngresarStock);

		mniIngresarStock.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JD_IngresarStock jdIngresarStock = new JD_IngresarStock();
		        jdIngresarStock.setVisible(true);
		    }
		});
		
		JMenu mnReportes = new JMenu("Reportes");
		mnReportes.setFont(new Font("Century Gothic", Font.BOLD, 16));
		menuBar.add(mnReportes);
		
		mntmListadoGeneral = new JMenuItem("Listado General");
		mntmListadoGeneral.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnReportes.add(mntmListadoGeneral);
		
		mntmStockProductos = new JMenuItem("Stock Productos");
		mntmStockProductos.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnReportes.add(mntmStockProductos);
		
		mntmNewMenuItem_2 = new JMenuItem("Total Acumulado");
		mntmNewMenuItem_2.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnReportes.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		escritorio = new JDesktopPane();
		escritorio.setBackground(Color.WHITE);
		contentPane.add(escritorio, BorderLayout.CENTER);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/logo.png")));
		lblNewLabel.setBounds(607, 121, 557, 628);
		escritorio.add(lblNewLabel);
		setExtendedState(MAXIMIZED_BOTH);
	
	}
}
