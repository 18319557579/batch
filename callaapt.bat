@echo off

%1\aapt dump strings %2 > %3\strings.txt
%1\aapt dump badging %2 > %3\badging.txt
%1\aapt dump permissions %2 > %3\permissions.txt
%1\aapt dump resources %2 > %3\resources.txt
%1\aapt dump configurations %2 > %3\configurations.txt
%1\aapt list %2 > %3\list.txt

@REM 用于跳转到目录
explorer %3



goto annotation

功能: 打印出apk中关于资源文件的信息

dump strings: 打印APK中资源表字符串池的内容。
dump badging: apk的门面数据：app名称、图标、权限、sdk、启动activity等
dump permissions: 打印APK中的权限。
dump resources: 从APK打印资源表。（关键）
dump configurations: 打印APK中的资源配置类别
list: 以列出 APK 文件中的所有文件和资源路径，好像不包括string

%1: 传aapt.exe/aapt2.exe所在路径
%2: 传apk/jar/zip文件
%3: 传生成文件的目录

使用示范：
callaapt C:\Users\hsf\AppData\Local\Android\Sdk\build-tools\34.0.0 C:\Users\hsf\Desktop\temp\app-debug.apk C:\Users\hsf\Desktop\new

:annotation