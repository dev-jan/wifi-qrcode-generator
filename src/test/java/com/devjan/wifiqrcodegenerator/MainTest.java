package com.devjan.wifiqrcodegenerator;

import org.junit.Test;

/**
 * JUnit test for the class {@link Main}
 *
 * @author Jan Bucher
 */
public class MainTest {
  @Test
  public void test_main() {
    // Arrange
    String[] args = {"--ssid=test", "example.png"};

    // Act
    Main.main(args);

    // Assert
    // -- nothing to assert at the moment...
  }

}
