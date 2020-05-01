#!/bin/bash

cd ../

jar cvfm Game.jar game/META-INF/MANIFEST.MF game/*.class game/physic/*.class game/Constant/*.class game/controller/*.class game/data/*.class game/launcher/*.class game/view/*.class game/window/*.class game/entities/*.class game/configReader/*.class game/serverConnection/*.class game/interfaces/*.class game/manager/*.class

jar cvfm Server.jar server/META-INF/MANIFEST.MF server/configReader/*.class server/*.class

