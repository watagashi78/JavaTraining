package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class ClockSettings3 implements Cloneable {
	private final static String[] AVAILABLE_FONTS = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	private String font_name;
	private int font_size;
	private Color font_color;
	private Color backGround_color;

	// Default Settings
	public ClockSettings3() {
		this.font_name = "Serif";
		this.font_size = 100;
		this.font_color = Color.BLACK;
		this.backGround_color = Color.WHITE;
	}

	// Specific Settings
	public ClockSettings3(String name, int size, Color fc, Color bgc) {
		this.font_name = name;
		this.font_size = size;
		this.font_color = fc;
		this.backGround_color = bgc;
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
	}

	public int getFont_size() {
		return font_size;
	}

	public void setFont_size(int font_size) {
		this.font_size = font_size;
	}

	public Color getFont_color() {
		return font_color;
	}

	public void setFont_color(Color font_color) {
		this.font_color = font_color;
	}

	public Color getBackGround_color() {
		return backGround_color;
	}

	public void setBackGround_color(Color backGround_color) {
		this.backGround_color = backGround_color;
	}

	@Override
    protected ClockSettings3 clone() {
        try {
            return (ClockSettings3) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }
}
