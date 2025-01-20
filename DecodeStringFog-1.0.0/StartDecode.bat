@echo off

java -Dfile.encoding=UTF-8 -jar DecodeStringFog-1.0.0.jar %CD%\SOURCE_CIPHERTEXT
@REM echo 当前目录是: %CD%\SOURCE_CIPHERTEXT

pause