# PixcelCollector

This project is an android mini-game.

## About the Game


### Objective
You have 15 seconds to collect squares with the magic ball.
1 square = 1 point.

### Rules 
Tilt the phone to move the magic ball. 
When the ball hits a square, it disappears and you gain 1 point. Another square appears at random. 
When you touch the sides you lose 1 point and the mobile phone vibrates.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

To run on your laptop, you will need:

```
Android Studio (version >= 3.1)
Git Account
Git
```

### Installing


Set up your development environment with GIT
```
Go To: File -> Settings -> Version Control -> Git
Click on test button to test "path to Git executables". 
If successful message is shown everything is ok, else navigate to git.exe from where you installed git and test again. 
```
Set up your development environment with GitHub 
```
Go to File -> Settings -> Version Control -> GitHub
Enter your email and password used to create GitHub account and click on OK button.
```
Then Clone the project from this link:
```
https://github.com/k3v0g1/PixcelCollector
Go to : Menu / VCS / Checkout from Version Control / Git
Paste this HTTPS path https://github.com/k3v0g1/PixcelCollector
Finally click on [Clone]
```
Now the project should be imported to Android Studio.

## For the next version

- Refresh the score of the table when the user writes his name (the table displays the 3 best scores, and if the user makes a better score and he enters his name in the database, it is necessary to restart a part to display the new score)
- Add different modes of difficulty. For now if the user touches a side, we reset the point counter. We could create a game mode where the magic ball would be reduced according to the number of times we touch a side.
- Better graphic elements (balls, square, game board)


## Deployment

To deploy this on a live system (mobilephone), you must have 
* An android version greater than 4.0.
* A cable to link the phone with the IDE.
* The phone must be set on "Debug Mode" in order to install APK from others sources than Android.

To have the game you simply need to compile the program when the phone is connected to the computer.


## Built With

* [AndroidStudio](https://developer.android.com/studio/index.html) - The IDE used
* [Kotlin](https://kotlinlang.org/docs/tutorials/kotlin-android.html) - The programming language
* [MVVM architecture](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) - The architecture of the project


## Authors

* **Kevin OGIER**
* **Mathieu MALARD**
*  ** Alexis Dez**
* ** Martin Dan**




