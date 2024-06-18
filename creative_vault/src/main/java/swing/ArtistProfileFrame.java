package swing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.entities.*;

public class ArtistProfileFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public ArtistProfileFrame(ArtistsEntity artist) {
        setTitle("Artist Profile");
        setSize(800, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel(artist.getArtistName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(nameLabel);

        JLabel userLabel = new JLabel("User: " + artist.getUser().getEmail());
        userLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        headerPanel.add(userLabel);

        JLabel portfolioLabel = new JLabel("Portfolio: " + artist.getPortfolio().getPortfolioName());
        portfolioLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        headerPanel.add(portfolioLabel);

        JLabel descriptionLabel = new JLabel("<html>Description: " + artist.getPortfolio().getDescription() + "</html>");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        headerPanel.add(descriptionLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Profile Picture
        if (artist.getProfilePicture() != null) {
            try {
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(artist.getProfilePicture()));
                ImageIcon icon = new ImageIcon(img.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                JLabel imageLabel = new JLabel(icon);
                imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                headerPanel.add(imageLabel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Main content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ratings
        JLabel ratingsLabel = new JLabel("Ratings:");
        ratingsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        contentPanel.add(ratingsLabel);

        List<ArtistRatingsEntity> ratings = artist.getArtistRatings();
        for (ArtistRatingsEntity rating : ratings) {
            JLabel ratingLabel = new JLabel("Rating: " + rating.getScore() + " - " + rating.getArtistComment());
            ratingLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            ratingLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            contentPanel.add(ratingLabel);
        }

        // Artworks
        JLabel artworksLabel = new JLabel("Artworks:");
        artworksLabel.setFont(new Font("Arial", Font.BOLD, 20));
        artworksLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        contentPanel.add(artworksLabel);

        List<ArtWorksEntity> artworks = artist.getPortfolio().getArtWorks();
        for (ArtWorksEntity artwork : artworks) {
            JLabel artworkNameLabel = new JLabel("Artwork Name: " + artwork.getTitle());
            artworkNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            contentPanel.add(artworkNameLabel);

            JLabel artworkDescriptionLabel = new JLabel("<html>Description: " + artwork.getDescription() + "</html>");
            artworkDescriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            artworkDescriptionLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
            contentPanel.add(artworkDescriptionLabel);

            if (artwork.getPicture() != null) {
                try {
                    BufferedImage artworkImg = ImageIO.read(new ByteArrayInputStream(artwork.getPicture()));
                    ImageIcon artworkIcon = new ImageIcon(artworkImg.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                    JLabel artworkImageLabel = new JLabel(artworkIcon);
                    artworkImageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
                    contentPanel.add(artworkImageLabel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    public static void main(String[] args) {
        // Mock data for testing
        UsersEntity user = new UsersEntity();
        user.setEmail("artist@example.com");

        PortfoliosEntity portfolio = new PortfoliosEntity();
        portfolio.setPortfolioName("Art Portfolio");
        portfolio.setDescription("This is the portfolio description");

        ArtistRatingsEntity rating1 = new ArtistRatingsEntity();
        rating1.setScore(5);
        rating1.setArtistComment("Excellent!");

        ArtistRatingsEntity rating2 = new ArtistRatingsEntity();
        rating2.setScore(4);
        rating2.setArtistComment("Very good!");

        ArtWorksEntity artwork1 = new ArtWorksEntity();
        artwork1.setTitle("Artwork 1");
        artwork1.setDescription("Description of artwork 1");

        ArtWorksEntity artwork2 = new ArtWorksEntity();
        artwork2.setTitle("Artwork 2");
        artwork2.setDescription("Description of artwork 2");

        ArtistsEntity artist = new ArtistsEntity();
        artist.setId(1L);
        artist.setArtistName("John Doe");
        artist.setUser(user);
        artist.setPortfolio(portfolio);
        artist.setArtistRatings(List.of(rating1, rating2));
        portfolio.setArtWorks(List.of(artwork1, artwork2));

        SwingUtilities.invokeLater(() -> {
            ArtistProfileFrame frame = new ArtistProfileFrame(artist);
            frame.setVisible(true);
        });
    }
}
