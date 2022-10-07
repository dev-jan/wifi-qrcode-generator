package com.devjan.wifiqrcodegenerator;

import java.util.Arrays;
import java.util.List;

/**
 * Utility class for encoding string into QR code payload.
 *
 * @author Jan Bucher
 */
public class QrEncodingUtils {
  private static final String ESCAPE_CHAR = "\\";
  private static final List<String> CHARS_TO_ESCAPE = Arrays.asList(ESCAPE_CHAR, ",", ";", "\"", ":");

  private QrEncodingUtils() {
    // this utils class only has static methods
  }

  /**
   * Encode input string according to the rules of the wifi QR code payload
   * @param input input sting
   * @return encoded output string
   */
  public static String encodeString(String input) {
    if (input == null) {
      return null;
    }
    String output = input;

    for (String c : CHARS_TO_ESCAPE) {
      output = output.replace(c, ESCAPE_CHAR + c);
    }
    return output;
  }

}
