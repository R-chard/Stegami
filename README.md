# Stegami 

Stegami (A contraction of "steganography" and "images" spelt backwards ) is a Java desktop application that allows the user to hide a greyscale image into another without making any visible alterations to the carrier image. We came up with the idea of this project as we were curious if there was a digital equivalent of invisible ink, a method commonly used by spies in the past to communicate. Upon further research, we learnt about steganography and realised that there were not many projects that attempted to conceal one image in another despite its usefullness.

## Installation 📥

Installing Stegami cannot be any easier! All you have to do is to download the "stegami.exe" file and you are ready to start concealing you own images!

Alternatively, you can also choose to clone our open-sourced repository and run the program using the following commands from the root directory

```
cd main
javac Main.java
java Main
```

## How to use it 📑

__Encoding__
- Upload an image to be hidden and another to act as the carrier as per the instrcutions given
- Click on the 'Encode' button and watch as your modified image gets created instantly!
- Preview your encoded message before downloading it -- and let us know if you can spot any difference at all!
- Your secret image is now successfully created and ready to be shared

__Decoding__
- Upload a png image that has been encoded previously
- Click on the 'Decode' button to view the hidden greyscale image stored within it
- You can even store a copy by downloading the hidden image


## How it works 🔎
Stegami applies a technique known as digital steganography. Our application firstly requests a carrier image from the user and another secret image to be concealed into the carrier image. The secret image is subsequently converted to greyscale. We then manipulated the 2 least significant bits of the red, green, blue and alpha value of every pixel of the carrier image, allowing us to store another up to 256 bits of data per pixel. This is sufficient to store a secret image of identical size since each pixel in a black and white image is represented by a numeric value between 0-255. 

The carrier image appears unaltered to the human eye even when placed side to side with the original copy. It is also often very similar in size to the original image.However, when decoded by another instance of our application, a greyscale version of the secret message is restored without a reduction in image quality.

> Note: Because of how our algorithm works, Stegami only supports image files that employ loseless compression techniques like PNG or BMP. Converting the file to JPEG, for instance, would ruin the secret image. This is a possible extension of the project that we might look further into.

> Note: Stegami requires Java version 1.8.0_281 and above to run.

## Tech ️⚙️

Stegami uses the following frameworks:

- Swing - GUI widget toolkit for Java
- AWT - Contains some useful tools to manipulate bit values in image pixels

## What we have learnt 🏫

Stegami was built during the Stacshack 007 Hackathon ran by the University of St Andrews over a short period of 24 hours, despite having little experience with steganography. It was a really interesting idea and we are really proud of what we have accomplished within the time frame.


