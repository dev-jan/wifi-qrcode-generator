package com.devjan.wifiqrcodegenerator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unittest for the class {@link WifiQrCodeGenerator}
 *
 * @author Jan Bucher
 */
public class WifiQrCodeGeneratorTest {
  @Test
  public void test_getPayloadString_wpa() {
    // Arrange
    WifiQrCodeGenerator generator = new WifiQrCodeGenerator();
    generator.withAuthenticationMode(AuthenticationMode.WPA)
             .withSsid("someSuperNetwork")
             .withPassword("asdfasdf");
    
    // Act
    String qrPayload = generator.getPaylodString();

    // Assert
    assertEquals("WIFI:T:WPA;S:someSuperNetwork;P:asdfasdf;;", qrPayload);
  }

  @Test
  public void test_getPayloadString_wep() {
    // Arrange
    WifiQrCodeGenerator generator = new WifiQrCodeGenerator();
    generator.withAuthenticationMode(AuthenticationMode.WEP)
             .withSsid("unsecureNetwork")
             .withPassword("asdf");

    // Act
    String qrPayload = generator.getPaylodString();

    // Assert
    assertEquals("WIFI:T:WEP;S:unsecureNetwork;P:asdf;;", qrPayload);
  }

  @Test
  public void test_getPayloadString_unsecure() {
    // Arrange
    WifiQrCodeGenerator generator = new WifiQrCodeGenerator();
    generator.withAuthenticationMode(AuthenticationMode.NOPASS)
             .withSsid("openNetwork");

    // Act
    String qrPayload = generator.getPaylodString();

    // Assert
    assertEquals("WIFI:T:NOPASS;S:openNetwork;P:;;", qrPayload);
  }
  
}
