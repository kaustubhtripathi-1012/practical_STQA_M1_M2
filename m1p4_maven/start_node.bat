@echo off
REM Run on the node machine (replace HUB_IP). Use same machine = localhost
java -jar ..\..\..\STQA_Libs\selenium-server-4.25.0.jar node --hub http://localhost:4444 --detect-drivers true
