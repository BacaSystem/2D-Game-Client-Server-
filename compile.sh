@ECHO OFF
TITLE CONFIG
javac src/game/physic/*.java 
javac src/game/Constant/*.java 
javac src/game/controller/*.java 
javac src/game/data/*.java 
javac src/game/launcher/*.java 
javac src/game/menuPanels/*.java 
javac src/game/window/*.java 
javac src/game/entities/*.java 
javac src/game/configReader/*.java 
javac src/game/serverConnection/*.java 
javac src/game/*.java

src/game/physic/*.class 
src/game/Constant/*.class 
src/game/controller/*.class 
src/game/data/*.class 
src/game/launcher/*.class 
src/game/menuPanels/*.class 
src/game/window/*.class 
src/game/entities/*.class 
src/game/configReader/*.class 
src/game/serverConnection/*.class 
src/game/*.class

PAUSE 
ECHO. 

jar cvfm Client.jar META-INF\\MANIFEST.MF src/game/physic/*.class src/game/Constant/*.class src/game/controller/*.class src/game/data/*.class src/game/launcher/*.class src/gamemenuPanels/*.class src/game/window/*.class src/game/entities/*.class src/game/configReader/*.class src/game/serverConnection/*.class src/game/*.class
PAUSE 
ECHO. 
java -jar Game.jar 
PAUSE 