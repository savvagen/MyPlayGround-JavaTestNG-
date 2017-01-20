#!/usr/bin/env bash
java -jar selenium-server-standalone-3.0.1.jar -role webdriver -hub http://localhost:4444/grid/register -port 5556 \
-browser browserName=chrome,maxInstances=3,platform=Windows  \
-browser browserName=firefox,maxInstances=4,platform=Windows \
-browser browserName=opera,maxInstances=5,platform=Windows \
-browser "browserName=internet explorer,maxInstances=2,platform=Windows" \
-browser browserName=safari,maxInstances=5,platform=Windows