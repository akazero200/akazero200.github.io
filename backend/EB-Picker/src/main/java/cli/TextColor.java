package cli;

/**
 * This Enum lists all possible ANSI colors.
 * Using the method textInColor of a certain Enum
 * wraps the given String with chars so that the output is displayed in the selected color.
 *
 * NOTE: ANSI COLORS ARE NOT SUPPORTED BY EVERY TERMINAL
 *
 */
public enum TextColor {
	NO_COLOR("\u001B[0m"),
	RED("\u001B[31m"),
	GREEN("\u001B[32m"),
	YELLOW("\u001B[33m"),
	BLUE("\u001B[34m"),
	PURPLE("\u001B[35m"),
	CYAN("\u001B[36m"),
	BRIGHT_RED("\u001B[91m"),
	BRIGHT_GREEN("\u001B[92m"),
	BRIGHT_YELLOW("\u001B[93m"),
	BRIGHT_BLUE("\u001B[94m"),
	BRIGHT_PURPLE("\u001B[95m"),
	BRIGHT_CYAN("\u001B[96m");

	private final String colorCode;
	private final static String reset = "\u001B[0m";

	TextColor(String colorCode) {
		this.colorCode = colorCode;
	}

	/**
	 * Change the text color.
	 * @return Return the given string formatted so that the text is colored, when printed.
	 */
	public String textInColor(String text) {
		return colorCode + text + reset;
	}
}
