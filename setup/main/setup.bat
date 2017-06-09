@echo off&setlocal
for %%i in ("%~dp0..") do set "folder=%%~fi"
cd %folder%\main
start "" "setup.jar"