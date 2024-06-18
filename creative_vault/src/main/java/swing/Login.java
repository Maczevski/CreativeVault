package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.UsersController;
import model.entities.UsersEntity;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel container;
	private GradientPanel leftPanel;
	private JPanel rightPanel = new JPanel();
	private JLabel lblTitle;
	private JTextField txtEmail;
	private JPasswordField passwordField;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private UsersController userController = new UsersController();
	private JLabel lblImage;
	private ImageIcon icon = new ImageIcon("C:/Users/Emill/Downloads/art.png/"); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
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
	public Login() {
		setTitle("Creative Vault");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 650);
		container = new JPanel();
		container.setBackground(new Color(255, 255, 255));
		container.setBorder(new EmptyBorder(5, 5, 5, 5));

		container.setLayout(new BorderLayout());
		setContentPane(container);

		leftPanel = new GradientPanel();
		leftPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		rightPanel.setBackground(new Color(255, 255, 255));
        rightPanel.setLayout(null); 

        container.add(leftPanel, BorderLayout.WEST);
        container.add(rightPanel, BorderLayout.CENTER);

        // Adjust the sizes of the panels
        leftPanel.setPreferredSize(new java.awt.Dimension(getWidth() / 2, getHeight()));
        leftPanel.setLayout(null);
        
        lblTitle = new JLabel("<html><p style=\"text-align: center\">Creative<br> Vault</p></html>");
        lblTitle.setForeground(new Color(196, 152, 144));
        lblTitle.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 90));
        lblTitle.setBounds(103, 155, 239, 255);
        lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
        Image scaledImage = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon titleIcon = new ImageIcon(scaledImage);
        //lblTitle.setIcon(titleIcon);
        lblTitle.setIconTextGap(10);
        lblTitle.setHorizontalTextPosition(JLabel.CENTER);
        lblTitle.setVerticalTextPosition(JLabel.BOTTOM);
        leftPanel.add(lblTitle);
        
        lblImage = new JLabel("");
        lblImage.setBounds(170, 426, 45, 13);
        leftPanel.add(lblImage);
        rightPanel.setPreferredSize(new java.awt.Dimension(getWidth() / 2, getHeight()));
        
        JLabel loginTxt = new JLabel("LOGIN");
        loginTxt.setFont(new Font("Constantia", Font.PLAIN, 32));
        loginTxt.setBounds(170, 56, 107, 65);
        rightPanel.add(loginTxt);
        
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Constantia", Font.PLAIN, 18));
        txtEmail.setBounds(125, 219, 210, 35);
        rightPanel.add(txtEmail);
        txtEmail.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(125, 338, 210, 35);
        rightPanel.add(passwordField);
        
        lblEmail = new JLabel("Email");
        lblEmail.setBounds(125, 199, 67, 13);
        rightPanel.add(lblEmail);
        
        lblPassword = new JLabel("Password");
        lblPassword.setBounds(125, 315, 67, 13);
        rightPanel.add(lblPassword);
        
        JButton btnLogin = new JButton("LOGIN");
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String email;
    			String password;
    			
    			email = txtEmail.getText();
    			password = new String(passwordField.getPassword());
    			
    			UsersEntity userLogin = userController.verifyEmail(email);
    			if (userLogin != null) {
    			
    				if (userController.verifyPassword(userLogin, password)) {
    					JOptionPane.showMessageDialog(null, "You're logged in!");
    					
    					
    					//showUserMenu(userLogin);
    				} else {
    					JOptionPane.showMessageDialog(null, "Invalid password. Try again");
    				}

    			} else {
    				JOptionPane.showMessageDialog(null, "Email not found. Try again");

    			}
        	}
        });
        btnLogin.setBounds(170, 423, 107, 35);
        rightPanel.add(btnLogin);
        
        JButton btnNewButton = new JButton("SIGN UP");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String email;
    			String password;
    			
    			email = txtEmail.getText();
    			password = new String(passwordField.getPassword());
    			
    			UsersEntity userInData = userController.verifyEmail(email);
    			
    			if (userInData == null) {
    				System.out.println("\nPassword: ");
    				
    				UsersEntity user = userController.registerUser(new UsersEntity(email, password));		
    				
    				if (user != null) {
    					System.out.println("User Registered");
    					System.out.println("===============================================================\n");
    					//showUserMenu(user);
    				}else {
    					JOptionPane.showMessageDialog(null, "ERROR");
    				}
    			} else {
    				JOptionPane.showMessageDialog(null, "Email already exists. Try again or go to login");
    			}
        		
        		
        		
        	}
        });
        btnNewButton.setBounds(170, 497, 107, 35);
        rightPanel.add(btnNewButton);

	}
	
public void showUserMenu(UsersEntity user) {
		
		if (userController.hasArtistRole(user)) {
			ArtistMenu artistMenu = new ArtistMenu();
			artistMenu.setVisible(true);
    		dispose();
		} else {
			DefaultMenu defaultMenu = new  DefaultMenu(user);
    		defaultMenu.setVisible(true);
    		dispose();
		}
	}

	private static class GradientPanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			int width = getWidth();
			int height = getHeight();

			// Cria um degradê do topo (azul) para a base (branco)
			GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(49, 64, 95), 0, height,
					new Color(196, 152, 144));
			g2d.setPaint(gradientPaint);
			g2d.fillRect(0, 0, width, height);
		}
	}
}
