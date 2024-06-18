package swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ArtWorksController;
import controller.ArtistsController;
import model.entities.ArtWorksEntity;
import model.entities.ArtistsEntity;
import model.entities.UsersEntity;

public class DefaultMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel container;
	private JPanel contentPanel;
	private JPanel findArtistsPanel;
	private JPanel findArtWorksPanel;
	private JPanel updateProfilePanel;
	private JPanel becomeArtistPanel;
	private DefaultTableModel artistTableModel;
	private DefaultTableModel artTableModel;

	private ArtistsController artistsController = new ArtistsController();
	private ArtWorksController artWorksController = new ArtWorksController();
	private JTextField searchField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefaultMenu frame = new DefaultMenu(new UsersEntity("teste", "teste"));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DefaultMenu(UsersEntity user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 650);
		setLocationRelativeTo(null);
		container = new JPanel();
		container.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(container);
		container.setLayout(null);

		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(49, 64, 95));
		menuPanel.setBounds(0, 0, 280, 613);
		container.add(menuPanel);
		menuPanel.setLayout(null);

		JLabel lblTitle = new JLabel("Creative Vault");
		lblTitle.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 56));
		lblTitle.setForeground(new Color(196, 152, 144));
		lblTitle.setBounds(10, 0, 270, 95);
		menuPanel.add(lblTitle);

		JButton btnFindArtists = new JButton("Find Artists");
		btnFindArtists.setBackground(new Color(240, 240, 240));
		btnFindArtists.addActionListener(new MenuItemActionListener(btnFindArtists.getText()));
		btnFindArtists.setBounds(79, 147, 115, 21);
		menuPanel.add(btnFindArtists);

		JButton btnFindArts = new JButton("Find Art Works");
		btnFindArts.setBackground(new Color(240, 240, 240));
		btnFindArts.addActionListener(new MenuItemActionListener(btnFindArts.getText()));
		btnFindArts.setBounds(79, 193, 115, 21);
		menuPanel.add(btnFindArts);

		JButton btnUpdateProfile = new JButton("Update Profile");
		btnUpdateProfile.setBackground(new Color(240, 240, 240));
		btnUpdateProfile.addActionListener(new MenuItemActionListener(btnUpdateProfile.getText()));
		btnUpdateProfile.setBounds(79, 242, 115, 21);
		menuPanel.add(btnUpdateProfile);

		JButton btnBecomeArtist = new JButton("Become Artist");
		btnBecomeArtist.setBackground(new Color(240, 240, 240));
		btnBecomeArtist.addActionListener(new MenuItemActionListener(btnBecomeArtist.getText()));
		btnBecomeArtist.setBounds(79, 288, 115, 21);
		menuPanel.add(btnBecomeArtist);

		contentPanel = new JPanel();
		contentPanel.setBounds(288, 0, 638, 613);
		container.add(contentPanel);
		contentPanel.setLayout(new CardLayout(0, 0));

		findArtistsPanel = createFindArtistsPanel(user);
		findArtWorksPanel = createFindArtWorksPanel(user);
		updateProfilePanel = createUpdateProfilePanel();
		becomeArtistPanel = createBecomeArtistPanel();

		contentPanel.add(findArtistsPanel, "findArtists");
		contentPanel.add(findArtWorksPanel, "findArtWorks");
		contentPanel.add(updateProfilePanel, "updateProfile");
		contentPanel.add(becomeArtistPanel, "becomeArtist");
	}

	private JPanel createFindArtistsPanel(UsersEntity user) {
		JPanel panel = new JPanel();

		List<ArtistsEntity> artistsList = artistsController.findAllArtists();

		String[] columnNames = { "Name", "Portfolio", "Art Works Number" };

		Object[][] data = new Object[artistsList.size()][3];

		for (int i = 0; i < artistsList.size(); i++) {
			ArtistsEntity artist = artistsList.get(i);
			data[i][0] = artist.getArtistName();
			data[i][1] = artist.getPortfolio().getPortfolioName();
			data[i][2] = artist.getPortfolio().getArtWorks().size();
		}

		// Tabela de artistas
		artistTableModel = new DefaultTableModel(data, columnNames);
		JTable table = new JTable(artistTableModel);
		JScrollPane scrollPane = new JScrollPane(table);

		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					// Quando uma linha é selecionada, mostra o perfil do artista
					String artistName = (String) table.getValueAt(selectedRow, 0);
					showArtistProfile(artistName, user);
				}
			}
		});
		panel.setLayout(new BorderLayout(0, 0));

		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel searchPanel = new JPanel();
		panel.add(searchPanel, BorderLayout.NORTH);
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		searchPanel.setPreferredSize(new Dimension(searchPanel.getWidth(), 50));
		searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(searchField.getWidth(), 40));

		searchPanel.add(searchField);
		searchField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterArtists(artistsList);
			}
		});
		searchPanel.add(btnSearch);

		return panel;
	}

	private void updateArtistTable(List<ArtistsEntity> filteredList) {
		artistTableModel.setRowCount(0);
		for (ArtistsEntity artist : filteredList) {
			artistTableModel.addRow(new Object[] { artist.getArtistName(), artist.getPortfolio().getPortfolioName(),
					artist.getPortfolio().getArtWorks().size() });
		}
	}

	private void filterArtists(List<ArtistsEntity> artistsList) {
		String searchText = searchField.getText().toLowerCase();
		List<ArtistsEntity> filteredList = artistsList.stream()
				.filter(artist -> artist.getArtistName().toLowerCase().contains(searchText))
				.collect(Collectors.toList());
		updateArtistTable(filteredList);
	}

	private void showArtistProfile(String artistName, UsersEntity user) {
		ArtistsEntity artist = artistsController.findArtistByName(artistName);
		if (artist != null) {
			ArtistProfile artistProfile = new ArtistProfile(artist, user);
			artistProfile.setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Artist not found!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private JPanel createFindArtWorksPanel(UsersEntity user) {
		JPanel panel = new JPanel();

		List<ArtWorksEntity> artWorks = artWorksController.findAllArts();

		String[] columnNames = { "Name", "Artist", "Type" };

		Object[][] data = new Object[artWorks.size()][3];

		for (int i = 0; i < artWorks.size(); i++) {
			ArtWorksEntity art = artWorks.get(i);
			data[i][0] = art.getTitle();
			data[i][1] = art.getPortfolio().getArtist().getArtistName();
			data[i][2] = showArtType(art);
		}

		// Tabela de artes
		artTableModel = new DefaultTableModel(data, columnNames);
		JTable table = new JTable(artTableModel);
		JScrollPane scrollPane = new JScrollPane(table);

		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					String artTitle = (String) table.getValueAt(selectedRow, 0);
					showArtDetails(artTitle, user);
				}
			}
		});
		panel.setLayout(new BorderLayout(0, 0));

		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel searchPanel = new JPanel();
		panel.add(searchPanel, BorderLayout.NORTH);
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		searchPanel.setPreferredSize(new Dimension(searchPanel.getWidth(), 50));
		searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(searchField.getWidth(), 40));

		searchPanel.add(searchField);
		searchField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterArts(artWorks);
			}
		});
		searchPanel.add(btnSearch);

		return panel;
	}
	
	private String showArtType(ArtWorksEntity art) {
		switch (art.getArtType().getType()) {
		case "painting":
			return "Painting";
		case "Drawing":
			return "Drawing";
		case "illustration":
			return "Illustration";
		case "sculpture":
			return "Sculpture";
		case "poem":
			return "Poem";
		case "song":
			return "Song";		
		default:
			return "";
		}
	}

	private void updateArtWorksTable(List<ArtWorksEntity> filteredList) {
		artistTableModel.setRowCount(0);
		for (ArtWorksEntity art : filteredList) {
			artistTableModel.addRow(new Object[] { art.getTitle(), art.getPortfolio().getArtist().getArtistName(),
					showArtType(art)});
		}
	}

	private void filterArts(List<ArtWorksEntity> arts) {
		String searchText = searchField.getText().toLowerCase();
		List<ArtWorksEntity> filteredList = arts.stream()
				.filter(art -> art.getTitle().toLowerCase().contains(searchText))
				.collect(Collectors.toList());
		updateArtWorksTable(filteredList);
	}

	private void showArtDetails(String artTitle, UsersEntity user) {
		ArtWorksEntity art = artWorksController.findArtByTitle(artTitle);
		if (art != null) {
			ArtDetailsFrame artDetails = new ArtDetailsFrame(art, user);
			artDetails.setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Artist not found!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private JPanel createUpdateProfilePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JButton updateEmailButton = new JButton("Update Email");
		updateEmailButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Implemente a lógica para atualizar o e-mail
				JOptionPane.showMessageDialog(null, "Update Email clicked");
			}
		});

		JButton updatePasswordButton = new JButton("Update Password");
		updatePasswordButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Implemente a lógica para atualizar a senha
				JOptionPane.showMessageDialog(null, "Update Password clicked");
			}
		});

		JButton deleteProfileButton = new JButton("Delete Profile");
		deleteProfileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Implemente a lógica para excluir o perfil
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your profile?",
						"Delete Profile", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Profile deleted");
				}
			}
		});

		panel.add(updateEmailButton);
		panel.add(updatePasswordButton);
		panel.add(deleteProfileButton);

		return panel;
	}

	private JPanel createBecomeArtistPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Become Artist Panel");
		panel.add(label, BorderLayout.NORTH);

		// Adicione os componentes para se tornar um artista aqui
		// Exemplo: text fields, labels, buttons, etc.

		return panel;
	}

	private class MenuItemActionListener implements ActionListener {
		private String menuItem;

		public MenuItemActionListener(String menuItem) {
			this.menuItem = menuItem;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
			switch (menuItem) {
			case "Find Artists":
				cardLayout.show(contentPanel, "findArtists");
				break;
			case "Find Art Works":
				cardLayout.show(contentPanel, "findArtWorks");
				break;
			case "Update Profile":
				cardLayout.show(contentPanel, "updateProfile");
				break;
			case "Become Artist":
				cardLayout.show(contentPanel, "becomeArtist");
				break;
			}
		}
	}
}
