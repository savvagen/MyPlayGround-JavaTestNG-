#!/bin/bash
cd /home/savva/IdeaProjects/TestNgProject
java -jar selenium-server-standalone-3.0.1.jar -role node -hub http://127.0.0.1:4444/grid/login-tests
browser browserName=firefox,maxInstances=4,platform=LINUX -Dwebdriver.firefox.driver=/home/savva/IdeaProjects/TestNgProjects/drivers/geckodriver
browser bowserName=internet explorer,maxInstances=2,platform=LINUX -Dwebdriver.ie.driver=/home/savva/IdeaProjects/TestNgProject/drivers/IEDriverServer.exe
browser browserName=chrome,maxInstances=3,platform=LINUX -Dwebdriver.chrome.driver=/home/savva/IdeaProjects/TestNgProject/drivers/chromedriver
