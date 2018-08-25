package com.devjan.wifiqrcodegenerator;

import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * Generator for QR codes to connect to a wifi network.
 * For more details of the generation: https://github.com/zxing/zxing/wiki/Barcode-Contents (section "Wifi Network config")
 *
 * @author Jan Bucher
 */
public class WifiQrCodeGenerator {
  /**
   * Used to separate the field key from the field value
   */
  private static final char SEPARATOR_VALUE = ':';
  /**
   * Used to separate multiple key:value pairs
   */
  private static final char SEPARATOR_FIELD = ';';

  private static final String QR_WIFI = "WIFI";
  private static final String QR_WIFI_AUTHENTICATION_TYP = "T";
  private static final String QR_WIFI_SSID = "S";
  private static final String QR_WIFI_PASSWORD = "P";

  private static final int QR_CODE_SIZE = 1600;
  private static final String QR_CODE_FILE_FORMAT = "PNG";

  private String ssid;
  private String password;
  private AuthenticationMode authenticationMode;
  private File outputFile;

  public void generateQrCodePicture() throws WriterException, IOException {
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    Map<EncodeHintType, ErrorCorrectionLevel> hints = new EnumMap<>(EncodeHintType.class);
    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

    BitMatrix bitMatrix = qrCodeWriter.encode(getPaylodString(), BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE, hints);
    MatrixToImageWriter.writeToPath(bitMatrix, QR_CODE_FILE_FORMAT, outputFile.toPath());
  }

  protected String getPaylodString() {
    StringBuilder payloadBuilder = new StringBuilder();
    payloadBuilder.append(QR_WIFI).append(SEPARATOR_VALUE)
                  .append(QR_WIFI_AUTHENTICATION_TYP).append(SEPARATOR_VALUE).append(authenticationMode).append(SEPARATOR_FIELD)
                  .append(QR_WIFI_SSID).append(SEPARATOR_VALUE).append(ssid).append(SEPARATOR_FIELD)
                  .append(QR_WIFI_PASSWORD).append(SEPARATOR_VALUE).append(StringUtils.isNotEmpty(password) ? password : "").append(SEPARATOR_FIELD)
                  // TODO: Hidden network config
                  .append(SEPARATOR_FIELD);
    return payloadBuilder.toString();
  }

  public WifiQrCodeGenerator withSsid(String ssid) {
    this.ssid = QrEncodingUtils.encodeString(ssid);
    return this;
  }

  public WifiQrCodeGenerator withPassword(String password) {
    this.password = QrEncodingUtils.encodeString(password);
    return this;
  }

  public WifiQrCodeGenerator withAuthenticationMode(String authMode) {
    return this.withAuthenticationMode(AuthenticationMode.get(authMode));
  }

  public WifiQrCodeGenerator withAuthenticationMode(AuthenticationMode mode) {
    this.authenticationMode = mode;
    return this;
  }

  public WifiQrCodeGenerator toOutputFile(String outputFileName) {
    this.outputFile = new File(outputFileName);
    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("ssid", ssid)
        .append("password", password)
        .append("authenticationMode", authenticationMode)
        .append("outputFile", outputFile)
        .toString();
  }

}
