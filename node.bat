@echo off

::获得参数
setlocal enabledelayedexpansion
:loop
IF NOT "%1"=="" (
    IF /i "%1"=="-phoneStoreDir" (
        SET phoneStoreDir=%2
    )
    IF /i "%1"=="-fileName" (
        SET fileName=%2
    )
    IF /i "%1"=="-computerStoreDdir" (
        SET computerStoreDdir=%2
    )
    IF /i "%1"=="-resolvePluginDir" (
        SET resolvePluginDir=%2
    )
    IF /i "%1"=="-outputTxtDir" (
        SET outputTxtDir=%2
    ) 
    SHIFT
    SHIFT
    GOTO :loop
)

echo phoneStoreDir: %phoneStoreDir%
echo computerStoreDdir: %computerStoreDdir%
echo resolvePluginDir: %resolvePluginDir%
echo outputTxtDir: %outputTxtDir%

if defined phoneStoreDir (echo var=%phoneStoreDir%) else echo var尚未定义


::1 先判断在手机中是否有存放目录，如果没有则新建一个
if not defined phoneStoreDir (set phoneStoreDir=/sdcard/node_new/)
if defined phoneStoreDir (echo var=%phoneStoreDir%) else echo var尚未定义

call adb shell test -e %phoneStoreDir%
echo 错误码1: %errorlevel%
if %errorlevel% geq 1 (
    call adb shell mkdir %phoneStoreDir%
) 


::2 将当前页面的节点生成一个xml文件，并保存到手机的存放目录中
if not defined fileName (
    set timeStr=
    call :getTimeStr timeStr
    set fileName=node_!timeStr!
    echo timeStr:!timeStr!, fileName:!fileName!
)


echo 输出手机中的路径: %phoneStoreDir%%fileName%.xml
call adb shell /system/bin/uiautomator dump --compressed %phoneStoreDir%%fileName%.xml
echo 错误码2: %errorlevel%
if %errorlevel% geq 1 (
    echo -*- dump error
    goto :eof
)

::3 将xml文件从手机中拉取到电脑中
call adb pull /sdcard/node_new/ui_weixin_10.xml C:\Users\hsf\Documents\phone_node_info
echo 错误码3: %errorlevel%
if %errorlevel% geq 1 (
    echo -*- pull error
    goto :eof
)

::4 用自己的Gradle插件resolveNodeXml解析xml文件，生成简要的txt
cd C:\Users\hsf\AndroidStudioProjects\Test_422
call gradlew resolveNodeXml -PinputFile=C:\Users\hsf\Documents\phone_node_info\ui_weixin_10.xml -Dorg.gradle.java.home=C:\Users\hsf\.jdks\corretto-11.0.21
echo 错误4: %errorlevel%
if %errorlevel% geq 1 (
    echo -*- resolveNodeXml error
    goto :eof
)

goto :eof

::获取当前时间字符串的函数(参数:%1用来回传获得的时间串)
:getTimeStr
set get_time=powershell -Command "Get-Date -Format 'yyyyMMddHHmmss'"
for /F "delims=" %%G in ('%get_time%') do (
    set result=%%G
)
echo 获取到的时间: %result%
set %1=%result%
goto :eof

endlocal


::注释
goto annotation
功能：将一个未签名的包，先进行4K对齐，然后签名

1 -phoneStoreDir 手机中的存储目录，可不传，不传就用设定好的
2 -fileName 文件名，可不传，不传就随机生成
2 -computerStoreDdir 电脑中的存储路径，必传
3 -resolvePluginDir 解析xml文件的Gradle插件路径，可不传，不传的话就不进行解析
4 -outputTxtDir 解析成txt文件的路径，可不传

:annotation