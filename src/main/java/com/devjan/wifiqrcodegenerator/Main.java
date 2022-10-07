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
         mixinStandardHelpOptions = true,
         header = "Simple cli tool to create a QR code to connect to your wifi.")
public class Main implements Callable<Integer> {
  @Option(names = {"-v", "--verbose"}, description = "More verbose output")
  private boolean verbose = false;

  @Option(names = { "-s", "--ssid", "--name" }, required = true, description = "SSID (name) of the wifi network")
  private String ssid;

  @Option(names = { "-p", "--password" }, description = "Wifi password")
  private String password;

  @Option(names = { "-a", "--auth-mode" }, description = "Authentication mode used by the wifi (WPA, WEP or nopass)")
  private String authenticationMode = "WPA";

  @Parameters(index = "0", paramLabel = "OUTPUT-FILE", description = "Output file, currently only PNG is supported")
  private String outputFile;

  /**
   * Entry function
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    // use picocli to handle CLI args and stuff
    int exitCode = new CommandLine(new Main()).execute(args);
    System.exit(exitCode);
  }

  @Override
  public Integer call() {
    System.out.println("[+] Generating QR code...");

    WifiQrCodeGenerator generator = new WifiQrCodeGenerator();

    generator.withSsid(ssid)
             .withAuthenticationMode(authenticationMode)
             .withPassword(password)
             .toOutputFile(outputFile);
    if (verbose) {
      System.out.println("[.] Params: " + generator);
      System.out.println("[.] Raw QR Code Payload: " + generator.getPaylodString());
    }

    try {
      generator.generateQrCodePicture();

      System.out.println("[+] QR code generated. Output: " + outputFile);
      return 0;
    } catch (WriterException qrException) {
      System.err.println("[-] Error while generating QR code (" + qrException.getMessage() + ")");
      return 1;
    } catch (IOException ioException) {
      System.err.println("[-] Error while writing to file (" + ioException + ")");
      return 2;
    }
  }

}
