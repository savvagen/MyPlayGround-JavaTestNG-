#!/bin/bash
java -Dwebdriver.chrome.driver=/home/savva/IdeaProjects/TestNgProject/drivers/chromedriver \
-Dwebdriver.gecko.driver=/home/savva/IdeaProjects/TestNgProject/drivers/geckodriver \
-Dwebdriver.opera.driver=/home/savva/IdeaProjects/TestNgProject/drivers/operadriver \
-jar selenium-server-standalone-3.0.1.jar -role node -hub http://localhost:4444/grid/register -port 5555 \
-browser browserName=chrome,maxInstances=3,platform=Linux  \
-browser browserName=firefox,maxInstances=4,platform=Linux \
-browser browserName=opera,maxInstances=5,platform=Linux \




