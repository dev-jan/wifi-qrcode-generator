package com.devjan.wifiqrcodegenerator;

import java.io.IOException;
import java.util.concurrent.Callable;
import com.google.zxing.WriterException;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

/**
 * Main starter class called by the CLI.
 *
 * @author Jan Bucher
 */
@Command(name = "wifi-qrcode-generator",
         header = "Simple cli tool to create a QR code to connect to your wifi.")
public class Main implements Callable<Void> {
  @Option(names = {"-v", "--verbose"}, description = "More verbose output")
  private boolean verbose = false;

  @Option(names = { "-s", "--ssid", "--name" }, required = true, description = "SSID (name) of the wifi network")
  private String ssid;

  @Option(names = { "-p", "--password" }, description = "Wifi password")
  private String password;

  @Option(names = { "-a", "--auth-mode" }, description = "Authentication mode used by the wifi (WPA, WEP or nopass")
  private String authenticationMode = "WPA";

  @Parameters(index = "0", paramLabel = "OUTPUT-FILE", description = "Output file, currently only PNG is supported")
  private String outputFile;

  /**
   * Entry function
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    // use picocli to handle CLI args and stuff
    CommandLine.call(new Main(), System.err, args);
  }

  @Override
  public Void call() {
    System.out.println("Generating QR code...");

    WifiQrCodeGenerator generator = new WifiQrCodeGenerator();

    generator.withSsid(ssid)
             .withAuthenticationMode(authenticationMode)
             .withPassword(password)
             .toOutputFile(outputFile);
    if (verbose) {
      System.out.println(generator.toString());
    }

    try {
      generator.generateQrCodePicture();

      System.out.println("QR code generated. Output: " + outputFile);
    } catch (WriterException qrException) {
      System.err.println("Error while generating QR code (" + qrException.getMessage() + ")");
    } catch (IOException ioExeption) {
      System.err.println("Error while writing to file (" + ioExeption + ")");
    }
    return null;
  }

}
