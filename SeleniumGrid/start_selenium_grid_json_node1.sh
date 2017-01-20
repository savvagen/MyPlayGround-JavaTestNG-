#!/bin/bash
java -Dwebdriver.chrome.driver=/home/savva/IdeaProjects/TestNgProject/drivers/chromedriver \
-Dwebdriver.gecko.driver=/home/savva/IdeaProjects/TestNgProject/drivers/geckodriver \
-Dwebdriver.opera.driver=/home/savva/IdeaProjects/TestNgProject/drivers/operadriver \
-jar selenium-server-standalone-3.0.1.jar -role node -nodeConfig jsonConfig1.json
