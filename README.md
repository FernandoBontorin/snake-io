# Snake IO (classical game)
![Game Screenshot](./docs/img/Snake1.png)

## Introduction
This is a for fun project of the classical game snake built in Java. Inspired by this [video](https://www.youtube.com/watch?v=CsJmQ32i02o)

### Play
Download the latest release on releases and run with [Java 8](https://www.java.com/pt_BR/download/). Double click in downloaded file, if don't start or get some error, try run in command line (java -jar snake-io-1.0.jar). After started, you need click on window to start playing. Get some fun!

#### Commands
* ↑ Go to up
* ← Go to left
* → Go to right
* ↓ Go to down
* *ESC* close game.
* any other key pause the game.

#### Rules
Don't cross bounds and don't move cross yourself.

### Developer
Feel free to copy or pull requests some changes. You will need some requirements
* Java 8(u241)
* Gradle

To run when you are in development
```bash
./gradlew clean jar
java -jar build/libs/snake-io-$VERION.jar
```
