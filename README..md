# install below dependencies (set environment paths for JDK and MVN)

jdk - https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
maven - https://maven.apache.org/download.cgi#Installation
.webm to .mp4 convertor - ffmpeg - https://ffmpeg.org/download.html

# run below command to build and execute via maven (environment and browser are optional parameters)

mvn clean test -Denv=<env> -Dbrowser=<browser>

# run below command to launch the allure report

mvn allure:serve
