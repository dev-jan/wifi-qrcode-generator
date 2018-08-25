package com.devjan.wifiqrcodegenerator;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unittest for {@link AuthenticationMode}
 *
 * @author Jan Bucher
 */
public class AuthenticationModeTest {
  @Test
  public void test_get_existingKey() {
    // arrange
    String key = "WPA";

    // act
    AuthenticationMode mode = AuthenticationMode.get(key);

    // assert
    assertEquals(AuthenticationMode.WPA, mode);
  }

  @Test(expected=IllegalArgumentException.class)
  public void test_get_notExistingKey() {
    // arrange
    String key = "foobar";

    // act
    AuthenticationMode.get(key);
  }

}
