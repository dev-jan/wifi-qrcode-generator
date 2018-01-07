package com.devjan.wifiqrcodegenerator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unittest for the class {@link QrEncodingUtils}
 *
 * @author Jan Bucher
 */
public class QrEncodingUtilsTest {
  @Test
  public void test_encodeString_abcd() {
    assertEncodingOutput("abcd", "abcd");
  }

  @Test
  public void test_encodeString_specialChars() {
    assertEncodingOutput("a\\\\b\\;c\\:d\\\"e", "a\\b;c:d\"e");
  }

  @Test
  public void test_encodeString_emptyString() {
    assertEncodingOutput("", "");
  }

  private void assertEncodingOutput(String expectedOutput, String input) {
    // Act
    String output = QrEncodingUtils.encodeString(input);

    // Assert
    assertEquals(expectedOutput, output);
  }

}
