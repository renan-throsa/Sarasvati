package playlist;

import button.Helper;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PlaylistItem extends JPanel {
	
	private String title, artist, path;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	private int pos;
	
	final public static int CREATE = 0;
	final public static int PLAYLIST = 1;
	int type;
	
	public PlaylistItem(String title, String artist, String path, int type, int pos) {
		this(title, artist, path, type);
		
		pos = pos;
	}
	
	public PlaylistItem(String title, String artist, String path, int type) {
		
		this.title = title;
		this.artist = artist;
		this.path = path;
		this.type = type;
		
		setBackground(Helper.loadColorfromJSON("list_background"));
		
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Helper.loadColorfromJSON("list_background_hover"));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(Helper.loadColorfromJSON("list_background"));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(Helper.loadColorfromJSON("list_background_onclick"));
				
				if (e.getX() > getWidth() - 40 && e.getX() < getWidth() - 20 && e.getY() > 10 && e.getY() < getHeight() - 10){
					delete();
					Helper.songHolder.redraw();
				} else {
					if (type == CREATE){
						CreatePlaylist createPlaylist = new CreatePlaylist(Helper.saraswat);
					} else if (type == PLAYLIST){
						Helper.currentPlaylistIndex = pos;
						Helper.currentSongList = Helper.playlists.get(pos).songs;
						Helper.songHolder.setInPlaylist(true);
						
						Helper.songHolder.redraw();
					}
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				setBackground(Helper.loadColorfromJSON("list_background_hover"));
			}
		};
		
		addMouseListener(mouseAdapter);
	}
	
	public void delete(){
		Helper.playlists.remove(pos);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = Helper.getSmoothedGraphics(g);
		
		BufferedImage img;
		Color iconColor = Helper.loadColorfromJSON("list_icon");
		
		if (type == CREATE){
			img = (BufferedImage) Helper.loadResourceImage("/plus.png");
		} else if (type == PLAYLIST){
			img = (BufferedImage) Helper.loadResourceImage("/playlist.png");
		} else {
			img = (BufferedImage) Helper.loadResourceImage("/music.png");
		}
		g2.drawImage(Helper.changeImageColor(img, iconColor), 20, 10, 20, 20, null);
		
		if (type != CREATE){
			g2.drawImage(Helper.changeImageColor((BufferedImage) Helper.loadResourceImage("/trash.png"), iconColor), getWidth() - 40, 10, 20, 20, null);
		}
		
		g2.setColor(Helper.loadColorfromJSON("list_font"));
		g2.setFont(Helper.lato_normal.deriveFont(14f));
		g2.drawString(title, 50, 22);
		
		g2.setFont(Helper.lato_light.deriveFont(10f));
		g2.drawString(artist, 50, 32);
	}
}
