package ex01_14;

public class Music {
	private String artist;
	private String musicTitle;

	public Music() {
		this.artist = "Empty";
		this.musicTitle = "Empty";
	}

	public Music(String name, String title) {
		this.artist = name;
		this.musicTitle = title;
	}

	public void startMusic() {
		if (artist.equals("Empty")) {
			System.out.println("Please put Music into this WalkMan.");
		} else {
			System.out.println("Music Start -> " + musicTitle + " by " + artist);
		}
	}
}
