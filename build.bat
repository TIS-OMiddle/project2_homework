@echo off
cd src
javac -d ..\bin -classpath ..\lib\java-cup-11b-runtime.jar -encoding utf-8 main\*.java compile\*.java grammar\*.java
cd ..
pause
@echo on
