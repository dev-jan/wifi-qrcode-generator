package com.devjan.wifiqrcodegenerator;

/**
 * Represents possible wifi authentication modes that are supported by the wifi QR code
 *
 * @author Jan Bucher
 */
public enum AuthenticationMode {
  WPA("WPA"), WEP("WEP"), NOPASS("nopass");

  private final String key;

  AuthenticationMode(String key) {
    this.key = key;
  }

  public String getKey() {
    return this.key;
  }

  public static AuthenticationMode get(String key) {
    for (AuthenticationMode mode : AuthenticationMode.values()) {
      if (mode.getKey().equalsIgnoreCase(key)) {
        return mode;
      }
    }
    throw new IllegalArgumentException("Authentication mode " + key + " does not exists");
  }

}
