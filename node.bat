@echo off

call adb shell test -e /sdcard/node_new/
if %errorlevel% geq 1 (
    call adb shell mkdir /sdcard/node_new/
) 
@REM goto :eof

call adb shell /system/bin/uiautomator dump --compressed /sdcard/node_new/ui_weixin_1.xml
echo %errorlevel%

call adb pull /sdcard/node_new/ui_weixin_1.xml C:\Users\hsf\Documents\Daisy_batch\nodes
echo %errorlevel%