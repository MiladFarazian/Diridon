import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * Diridon 1.0
 * By: Milad Farazian
 */

public class Diridon{
	//Recommends music based on your mood
	JFrame frame = new JFrame();
	JLabel feelLabel = new JLabel("How are you feeling? ");
	String [] feelings = {"Happy", "Sad", "Relaxed", 
			"In Love", "Angry", "Energetic"};
	JComboBox feelBox = new JComboBox(feelings);
	ImageIcon logo = new ImageIcon("src/diridon.png");
	ImageIcon icon = new ImageIcon("src/diridon_icon.png");
	ImageIcon background = new ImageIcon("src/diridonGradient.png");
	JLabel logoLabel = new JLabel();
	Color diridonBlue = new Color(10, 0, 130);
	int countx = 0;
	int county = 0;
	int feelBoxCount = 0;
	int frameWidth = 2000;
	int frameHeight = 1000;
	GridBagConstraints layoutC;
	JButton button;
	URI uri;
	JLabel sinceLabel;
	JList songList;
	JScrollPane songScroll;
	
	Diridon(){
		//frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setSize(frameWidth, frameHeight);
		Label copyrightL = new Label("\u00a9");
		frame.setTitle("Diridon" + copyrightL.getText() + " - Bringing Music & Mood Together");
		layoutC = new GridBagConstraints();
		
		Color paleBlue = new Color(230, 250, 255);
		frame.setContentPane(new JLabel(background));
		
		initialize();
		frame.setVisible(true);
	}
	
	public void createList(String [] songs, String [] artists, String feeling, int countx, int county, int feelBoxx) {
		if(feelBoxx >= 1) {
			frame.remove(sinceLabel);
			frame.remove(songScroll);
		}
		
		String [] songDetail = new String[songs.length + 1];
		for(int i = 0; i < songs.length; i++) {
			songDetail[i] = "  "+ songs[i] + " by " + artists[i] + "  ";
		}
		songList = new JList(songDetail);
		songList.setVisibleRowCount(7);
		sinceLabel = new JLabel("Since You're Feeling " + feeling + "...");
		sinceLabel.setFont(new Font("Italic", Font.ITALIC, 20));
		if(!feeling.equals("Relaxed")) {
			sinceLabel.setForeground(diridonBlue);
		}
		songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		layoutC.gridx = countx + 1;
		layoutC.gridy = county;
		layoutC.insets = new Insets(100,200*countx-50*(countx + 1),10,10);
		frame.add(sinceLabel, layoutC);
		layoutC.gridx = countx + 1;
		layoutC.gridy = county + 1;
		layoutC.insets = new Insets(0,100*countx-20*(countx -1),10,10);
		songScroll = new JScrollPane(songList);
		songList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent l) {
				int index = l.getFirstIndex();
				if(feeling.equals("Happy")) {
					String [] happy = Song.getHappyURL();
					try {
						uri = new URI(happy[index]);
						open(uri);
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}else if(feeling.equals("Sad")) {
					String [] sad = Song.getSadURL();
					try {
						uri = new URI(sad[index]);
						open(uri);
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}else if(feeling.equals("Relaxed")) {
					String [] relaxed = Song.getRelaxedURL();
					try {
						uri = new URI(relaxed[index]);
						open(uri);
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}else if(feeling.equals("In Love")) {
					String [] inLove = Song.getInLoveURL();
					try {
						uri = new URI(inLove[index]);
						open(uri);
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}else if(feeling.equals("Angry")) {
					String [] angry = Song.getAngryURL();
					try {
						uri = new URI(angry[index]);
						open(uri);
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}else if(feeling.equals("Energetic")) {
					String [] energetic = Song.getEnergeticURL();
					try {
						uri = new URI(energetic[index]);
						open(uri);
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
			}			
		});
		
		songScroll.getHorizontalScrollBar();
		
		frame.add(songScroll, layoutC);
		frame.setVisible(true);
	}
	
	class MyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(feelBox.getSelectedItem().equals("Happy")) {
				background = new ImageIcon("src/happyGradient.png");
				frame.setLayout(new BorderLayout());
		        frame.setContentPane(new JLabel(background));
		        frame.setLayout(new GridBagLayout());
		        initialize();
				createList(Song.getHappySongList(),Song.getHappyArtistList(), feelings[0], countx, county, feelBoxCount);
				frame.setVisible(true);
			}else if(feelBox.getSelectedItem().equals("Sad")) {
				background = new ImageIcon("src/sadGradient.png");
				frame.setLayout(new BorderLayout());
		        frame.setContentPane(new JLabel(background));
		        frame.setLayout(new GridBagLayout());
		        initialize();
		        createList(Song.getSadSongList(),Song.getSadArtistList(), feelings[1], countx, county, feelBoxCount);
				frame.setVisible(true);
			}else if(feelBox.getSelectedItem().equals("Relaxed")) {
				background = new ImageIcon("src/relaxGradient.png");
				frame.setLayout(new BorderLayout());
		        frame.setContentPane(new JLabel(background));
		        frame.setLayout(new GridBagLayout());
		        initialize();
				createList(Song.getRelaxedSongList(),Song.getRelaxedArtistList(), feelings[2], countx, county, feelBoxCount);
				frame.setVisible(true);
			}else if(feelBox.getSelectedItem().equals("In Love")) {
				background = new ImageIcon("src/inLove.png");
				frame.setLayout(new BorderLayout());
		        frame.setContentPane(new JLabel(background));
		        frame.setLayout(new GridBagLayout());
		        initialize();
				createList(Song.getInLoveSongList(),Song.getInLoveArtistList(), feelings[3], countx, county, feelBoxCount);
				frame.setVisible(true);
			}else if(feelBox.getSelectedItem().equals("Angry")) {
				background = new ImageIcon("src/angry.png");
				frame.setLayout(new BorderLayout());
		        frame.setContentPane(new JLabel(background));
		        frame.setLayout(new GridBagLayout());
		        initialize();
				createList(Song.getAngrySongList(),Song.getAngryArtistList(), feelings[4], countx, county, feelBoxCount);
				frame.setVisible(true);
			}else if(feelBox.getSelectedItem().equals("Energetic")) {
				background = new ImageIcon("src/energetic.png");
				frame.setLayout(new BorderLayout());
		        frame.setContentPane(new JLabel(background));
		        frame.setLayout(new GridBagLayout());
		        initialize();
				createList(Song.getEnergeticSongList(),Song.getEnergeticArtistList(), feelings[5], countx, county, feelBoxCount);
				frame.setVisible(true);
			}
			//feelBoxCount++;
			
			//Frame Adjustments As New List(s) Appear
			//frameWidth++;
			//frame.setSize(frameWidth, frameHeight);
		}
	}
	
	private void open(URI uri) throws URISyntaxException {
		 if (Desktop.isDesktopSupported()) {
			 try {
				 Desktop.getDesktop().browse(uri);
			 } catch (IOException e) { /* TODO: error handling */ }
		 } else {  }
	}
	
	public void initialize() {
		frame.setLayout(new GridBagLayout());
		
		logoLabel.setIcon(logo);
		frame.setIconImage(icon.getImage());
		
		layoutC.gridx = 0;
		layoutC.gridy = 0;
		layoutC.insets = new Insets(10,10,10,10);
		frame.add(logoLabel, layoutC);
		
		layoutC.gridy = 1;
		feelLabel.setFont(new Font("Italic", Font.ITALIC, 20));
		feelLabel.setForeground(diridonBlue);
		frame.add(feelLabel, layoutC);
		
		feelBox.setForeground(diridonBlue);
		JButton button = new JButton("Listen");
		button.addActionListener(new MyListener());
		button.setForeground(diridonBlue);
		button.setBackground(Color.WHITE);
		
		layoutC.gridy = 2;
		layoutC.insets = new Insets(10,10,10,150);
		frame.add(feelBox, layoutC);
		layoutC.insets = new Insets(10,10,10,-150);
		frame.add(button, layoutC);
		
		layoutC.gridx = 1;
		layoutC.gridy = 0;
		layoutC.insets = new Insets(1000,1000,10,10);
	}
}
