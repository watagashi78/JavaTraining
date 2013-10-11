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
		System.out.println("Music Start -> " + musicTitle + " by " + artist);
	}
}
