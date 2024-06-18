package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import controller.UsersController;
import model.entities.ArtWorksEntity;
import model.entities.ArtistRatingsEntity;
import model.entities.ArtistsEntity;
import model.entities.UsersEntity;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ArtistProfile extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel container;
	private UsersController userController;
 static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArtistProfile frame = new ArtistProfile(null, null);
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
	public ArtistProfile(ArtistsEntity artist, UsersEntity user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(950, 650);
		setLocationRelativeTo(null);

		container = new JPanel(new BorderLayout());
		container.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(container);

		JPanel headerPanel = new JPanel();
		headerPanel.setPreferredSize(new Dimension(headerPanel.getWidth(), 260));
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		container.add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 0));

		JPanel infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(650, infoPanel.getHeight()));
		infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		headerPanel.add(infoPanel, BorderLayout.EAST);
		infoPanel.setLayout(null);

		JLabel portfolioLabel = new JLabel(artist.getPortfolio().getPortfolioName());
		portfolioLabel.setBounds(10, 10, 110, 43);
		infoPanel.add(portfolioLabel);
		portfolioLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		portfolioLabel.setFont(new Font("Constantia", Font.PLAIN, 18));

		JLabel descriptionLabel = new JLabel("<html>" + artist.getPortfolio().getDescription() + "</html>");
		descriptionLabel.setBounds(10, 53, 630, 36);
		infoPanel.add(descriptionLabel);
		descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		descriptionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JButton btnNewButton = new JButton("MENU");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showUserMenu(user);
			}
		});
		btnNewButton.setBounds(10, 194, 86, 36);
		infoPanel.add(btnNewButton);

		JPanel picturePanel = new JPanel();
		picturePanel.setBackground(new Color(192, 192, 192));
		picturePanel.setMaximumSize(new Dimension(200, picturePanel.getHeight()));
		headerPanel.add(picturePanel, BorderLayout.CENTER);
		picturePanel.setLayout(new BorderLayout(0, 0));

		if (artist.getProfilePicture() != null) {
			try {
				BufferedImage artworkImg = ImageIO.read(new ByteArrayInputStream(artist.getProfilePicture()));
				ImageIcon artworkIcon = new ImageIcon(artworkImg.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
				JLabel artworkImageLabel = new JLabel(artworkIcon);
				artworkImageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				picturePanel.add(artworkImageLabel, BorderLayout.CENTER);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			JLabel artworkImageLabel = new JLabel("No picture");
			picturePanel.add(artworkImageLabel, BorderLayout.CENTER);
		}

		JLabel nameLabel = new JLabel(artist.getArtistName());
		picturePanel.add(nameLabel, BorderLayout.SOUTH);
		nameLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("Constantia", Font.BOLD, 24));

		// Main content
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JScrollPane scrollPane = new JScrollPane(contentPanel);
		container.add(scrollPane, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		// Artworks Panel
		JPanel artWorksPanel = new JPanel();
		contentPanel.add(artWorksPanel);
		artWorksPanel.setLayout(new BorderLayout(0, 0));

		JPanel ArtWorksPanel = new JPanel();
		artWorksPanel.add(ArtWorksPanel, BorderLayout.NORTH);

		JLabel artworksLabel = new JLabel("Artworks");
		ArtWorksPanel.add(artworksLabel);
		artworksLabel.setFont(new Font("Arial", Font.BOLD, 20));
		artworksLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		JPanel artsContainerPanel = new JPanel();
		artWorksPanel.add(artsContainerPanel, BorderLayout.CENTER);
		artsContainerPanel.setLayout(new GridLayout(0, 3, 10, 10));

		List<ArtWorksEntity> artworks = artist.getPortfolio().getArtWorks();

		for (ArtWorksEntity artwork : artworks) {
			JPanel artPanel = new JPanel(new BorderLayout(0, 0));
			artPanel.setPreferredSize(new Dimension(artPanel.getWidth(), 180));
			artPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			JLabel artworkNameLabel = new JLabel("Artwork Name: " + artwork.getTitle());
			artworkNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
			artPanel.add(artworkNameLabel, BorderLayout.SOUTH);

			if (artwork.getPicture() != null) {
				try {
					BufferedImage artworkImg = ImageIO.read(new ByteArrayInputStream(artwork.getPicture()));
					ImageIcon artworkIcon = new ImageIcon(artworkImg.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
					JLabel artworkImageLabel = new JLabel(artworkIcon);
					artworkImageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
					artPanel.add(artworkImageLabel, BorderLayout.CENTER);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				JLabel artworkImageLabel = new JLabel("No picture");
				artPanel.add(artworkImageLabel, BorderLayout.CENTER);
			}
			artsContainerPanel.add(artPanel);
		}
		artWorksPanel.add(artsContainerPanel);

		// Ratings Panel
		JPanel ratingsPanel = new JPanel();
		ratingsPanel.setLayout(new BoxLayout(ratingsPanel, BoxLayout.Y_AXIS));
		contentPanel.add(ratingsPanel, BorderLayout.SOUTH);

		JLabel ratingsLabel = new JLabel("Ratings:");
		ratingsLabel.setFont(new Font("Arial", Font.BOLD, 20));
		ratingsPanel.add(ratingsLabel);

		List<ArtistRatingsEntity> ratings = artist.getArtistRatings();
		for (ArtistRatingsEntity rating : ratings) {
			JLabel ratingLabel = new JLabel("Rating: " + rating.getScore() + " - " + rating.getArtistComment());
			ratingLabel.setFont(new Font("Arial", Font.PLAIN, 16));
			ratingLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
			ratingsPanel.add(ratingLabel);
		}
	}

	public void showUserMenu(UsersEntity user) {

		if (userController.hasArtistRole(user)) {
			ArtistMenu artistMenu = new ArtistMenu();
			artistMenu.setVisible(true);
			dispose();
		} else {
			DefaultMenu defaultMenu = new DefaultMenu(user);
			defaultMenu.setVisible(true);
			dispose();
		}
	}

}
