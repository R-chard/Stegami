# Stegami 

Stegami (A contraction of "steganography" and "images" spelt backwards ) is a Java desktop application that allows the user to hide an image into another with minimal visible alterations to the carrier image. We came up with the idea of this project as we were curious if there was a digital equivalent of invisible ink, a method commonly used by spies in the past to communicate. Upon further research, we learnt about steganography and realised that there were not many projects that attempted to conceal one image in another despite its usefullness.

## Installation 📥

Installing Stegami cannot be any easier! All you have to do is to download the "stegami.exe" file and you are ready to start concealing you own images!

Alternatively, you can also choose to clone our open-sourced repository and run the program using the following commands from the root directory

```
javac */*.java
java main.Main
```

## How to use it 📑

__Encoding__
- Upload an image to be hidden and another to act as the carrier as per the instrcutions given
- Click on the 'Encode' button and watch as your modified image gets created instantly!
- Preview your encoded message before downloading it -- and let us know if you can spot any difference at all!
- Your secret image is now successfully created and ready to be shared

__Decoding__
- Upload a png image that has been encoded previously
- Click on the 'Decode' button to view the hidden image stored within it
- You can even store a copy by downloading the hidden image


## How it works 🔎
Stegami applies a technique known as digital steganography implemented with our unique algorithm. Our application firstly requests a carrier image from the user and another secret image to be concealed into the carrier image. We then retrieve the most significant 4 bits of the red, green, blue and alpha value of the secret image and store them into the least significant 4 bits of the carrier image. When we decode the encoded carrier image, the 4 bits stored are retrieved and used to reconstruct the secret image. 

The changes in the quality of both images are minimal, since the most significant 4 bits account for 240 out of the 256 possible values (2^8 - 2^4). Fruthermore, we set the least significant 4 bits to its median value. Assuming the last 4 bits has an equal probability of being every value. This leads to an average 8/128 (6.3%) reduction in image quality. Changes in image quality and is often hard to spot unless placed side to side with the original image. It is also often very similar in size to the original image (slight fluctuations during to how lossless algorithms work). 

> Note: Because of how our algorithm works, Stegami only supports image files that employ loseless compression techniques like PNG or BMP. Converting the file to JPEG, for instance, would ruin the secret image. This is a possible extension of the project that we might look further into.

> Note: Stegami requires Java version 1.8.0_281 and above to run.

## Tech ️⚙️

Stegami uses the following frameworks:

- Swing - GUI widget toolkit for Java
- AWT - Contains some useful tools to manipulate bit values in image pixels

## What we have learnt 🏫

Stegami was built during the Stacshack 007 Hackathon ran by the University of St Andrews over a short period of 24 hours, despite having little experience with steganography. It was a really interesting idea and we are really proud of what we have accomplished within the time frame.

## Executable files

We also provide windows executable file for our application. The product required JRE.
