@echo off
cd src
javac -d ..\bin -encoding utf-8 main\*.java compile\*.java
cd ..
pause
@echo on
