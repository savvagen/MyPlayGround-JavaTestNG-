#!/bin/bash
java -Dwebdriver.chrome.driver=C:\Users\savva\IdeaProjects\TestNgProject\drivers\chromedriver.exe \
-Dwebdriver.gecko.driver=C:\Users\savva\IdeaProjects\TestNgProject\drivers\geckodriver.exe \
-Dwebdriver.opera.driver=C:\Users\savva\IdeaProjects\TestNgProject\drivers\operadriver.exe \
-Dwebdriver.ie.driver=C:\Users\savva\IdeaProjects\TestNgProject\drivers\IEDriverServer.exe \
-jar selenium-server-standalone-3.0.1.jar -role node -hub http://localhost:4444/grid/register -port 5555 \
-browser browserName=chrome,maxInstances=3,platform=Windows  \
-browser browserName=firefox,maxInstances=4,platform=Windows \
-browser "browserName=internet explorer,maxInstances=3,platform=Windows" \
-browser browserName=opera,maxInstances=5,platform=Windows \




