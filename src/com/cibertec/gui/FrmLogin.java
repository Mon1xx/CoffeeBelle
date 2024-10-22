package com.cibertec.gui;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class FrmLogin extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField passwordField;
    private final String usuario1 = "admin";
    private final String contraseña1 = "12345";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmLogin frame = new FrmLogin();
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
    public FrmLogin() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(FrmLogin.class.getResource("/img/logo.png")));
        setTitle("Coffe Belle");
        setType(Type.UTILITY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 595, 489);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Century Gothic", Font.BOLD, 13));
        lblUsuario.setBounds(165, 214, 86, 14);
        contentPane.add(lblUsuario);
        
        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setFont(new Font("Century Gothic", Font.BOLD, 13));
        lblContraseña.setBounds(165, 245, 86, 14);
        contentPane.add(lblContraseña);
        
        txtUsuario = new JTextField();
        txtUsuario.setBounds(283, 211, 127, 20);
        contentPane.add(txtUsuario);
        txtUsuario.setColumns(10);
        
        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setFont(new Font("Century Gothic", Font.BOLD, 13));
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Comparar las credenciales
                String usuario = txtUsuario.getText();
                String contraseña = new String(passwordField.getPassword());

                if (usuario.equals(usuario1) && contraseña.equals(contraseña1)) {
                    JOptionPane.showMessageDialog(null, "¡Haz ingresado con éxito!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    // Mostrar el menú principal
                   FrmPrincipal frmPrincipal = new FrmPrincipal();
                   frmPrincipal.show();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                    txtUsuario.setText(""); 
                    passwordField.setText(""); 
                }
            }
        });
        btnIngresar.setBounds(246, 273, 89, 23);
        contentPane.add(btnIngresar);
        
        JLabel lblBienvenido = new JLabel("Bienvenido");
        lblBienvenido.setFont(new Font("Century Gothic", Font.BOLD, 24));
        lblBienvenido.setBounds(228, 61, 134, 23);
        contentPane.add(lblBienvenido);
        
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(FrmLogin.class.getResource("/img/logo.png")));
        lblLogo.setBounds(34, 0, 505, 466);
        contentPane.add(lblLogo);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(283, 244, 127, 20);
        contentPane.add(passwordField);
        setLocationRelativeTo(null);
    }
}
