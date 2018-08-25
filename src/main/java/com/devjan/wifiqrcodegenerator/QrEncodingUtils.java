package com.devjan.wifiqrcodegenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for encoding string into QR code payload.
 *
 * @author Jan Bucher
 */
public class QrEncodingUtils {
  private static final String ESCAPE_CHAR = "\\";

  private QrEncodingUtils() {
    // this utils class only has static methods
  }

  private static final List<String> charsToEscape = new ArrayList<>();
  static {
    charsToEscape.add("\\");
    charsToEscape.add(",");
    charsToEscape.add(";");
    charsToEscape.add("\"");
    charsToEscape.add(":");
  }

  /**
   * Encode input string according to the rules of the wifi QR code payload
   * @param input input sting
   * @return encoded output string
   */
  public static String encodeString(String input) {
    String output = input;

    for (String c : charsToEscape) {
      output = output.replace(c, ESCAPE_CHAR + c);
    }
    return output;
  }

}
