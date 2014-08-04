package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.prefs.Preferences;

public class ClockSettings4 implements Cloneable {
	private final static String[] AVAILABLE_FONTS = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	private final static byte[] DEFAULT_FONT_COLOR = {(byte) 0x00, (byte) 0x00, (byte) 0x00};
	private final static byte[] DEFAULT_BACKGROUND_COLOR = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF};

	private Preferences prefs;
	private String font_name;
	private int font_size;
	private Color font_color;
	private Color backGround_color;

	// Default Settings
	public ClockSettings4() {
		prefs = Preferences.userRoot().node("hasegawaSwingclock");
		this.font_name = prefs.get("font_name", "Serif");
		this.font_size = prefs.getInt("font_size", 100);
		// Color情報はByteArrayで保持
		byte[] fc_array = prefs.getByteArray("font_color", DEFAULT_FONT_COLOR);
		byte[] bc_array = prefs.getByteArray("background_color", DEFAULT_BACKGROUND_COLOR);
		// byte(-128...128)から符号なしのintへ変換
		this.font_color = new Color(fc_array[0] & 0xFF, fc_array[1] & 0xFF, fc_array[2] & 0xFF);
		this.backGround_color = new Color(bc_array[0] & 0xFF, bc_array[1] & 0xFF, bc_array[2] & 0xFF);
	}

	public String[] getAvailableFonts() {
		return AVAILABLE_FONTS;
	}

	public Font getFont() {
		return new Font(font_name, Font.PLAIN, font_size);
	}

	public String getFont_name() {
		return font_name;
	}

	public void setFont(String font_name) {
		this.font_name = font_name;
		prefs.put("font_name", font_name);
	}

	public int getFont_size() {
		return font_size;
	}

	public void setFont_size(int font_size) {
		this.font_size = font_size;
		prefs.putInt("font_size", font_size);
	}

	public Color getFont_color() {
		return font_color;
	}

	public void setFont_color(Color font_color) {
		this.font_color = font_color;
		byte[] value = {(byte) font_color.getRed(), (byte) font_color.getGreen(), (byte) font_color.getBlue()};
		prefs.putByteArray("font_color", value);
	}

	public Color getBackGround_color() {
		return backGround_color;
	}

	public void setBackGround_color(Color backGround_color) {
		this.backGround_color = backGround_color;
		byte[] value = {(byte) this.backGround_color.getRed(), (byte) this.backGround_color.getGreen(), (byte) this.backGround_color.getBlue()};
		prefs.putByteArray("background_color", value);
	}

	public Preferences getPreferences() {
		return prefs;
	}

	@Override
    protected ClockSettings4 clone() {
        try {
            return (ClockSettings4) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }
}
