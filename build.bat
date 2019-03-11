@echo off
cd src
javac -d ..\bin -encoding utf-8 main\*.java compile\Strategy.java compile\StrategyManager.java
cd ..
pause
@echo on
