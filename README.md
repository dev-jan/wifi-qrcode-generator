# wifi-qrcode-generator
Simple CLI application to generate QR Codes for wifi-connections.

![build status](https://travis-ci.org/dev-jan/wifi-qrcode-generator.svg?branch=master)
![MIT license](https://img.shields.io/github/license/dev-jan/wifi-qrcode-generator.svg)

![qr code](example.png)

Do you want to create a QR code for your wifi so you don't have to type in your
password to every device? There are several online services for this, but do
you really trust an online service? The solution is this offline generator.

# How to use?
Download the repository:
```
git clone https://github.com/dev-jan/wifi-qrcode-generator.git
cd wifi-qrcode-generator
```

Install the Java Developer Kit (JDK) by yourself (if it's not already installed on your computer)

Build...
```
./gradlew installDist
```

Run...
```
# Linux
./build/install/wifi-qrcode-generator/bin/wifi-qrcode-generator

# Windows
./build/install/wifi-qrcode-generator/bin/wifi-qrcode-generator.bat
```

Example command:
```
$ ./build/install/wifi-qrcode-generator/bin/wifi-qrcode-generator --ssid "SomeNetwork" -p "asdf" -a WPA example.png
[+] Generating QR code...
[+] QR code generated. Output: example.png
```

Now you can print out the QR code and let others connect to your wifi without typing your 20-digit password into there phones :smiley:
