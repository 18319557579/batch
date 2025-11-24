@echo off
chcp 65001 >nul
setlocal enabledelayedexpansion

:MAIN_LOOP
echo ========================================
echo    批量安装 APK 到所有已连接 Android 设备
echo ========================================
echo.

:: 输入 APK 路径
set "APK="
set /p APK=请输入 APK 文件的绝对路径（可带引号）： 

if "%APK%"=="" (
    echo [ERROR] 未输入 APK 路径，请重新输入。
    echo.
    goto MAIN_LOOP
)

:: 去掉用户可能输入的引号
set "APK=%APK:"=%"

:: 检查文件是否存在
if not exist "%APK%" (
    echo [ERROR] 找不到 APK 文件：%APK%
    echo.
    goto MAIN_LOOP
)

echo.
echo [INFO] APK 文件验证成功：%APK%
echo.
echo [INFO] 正在获取设备列表...
echo.

set onlineCount=0
set index=0

:: skip=1 跳过标题行，tokens=1,2 -> 序列号 / 状态
for /f "skip=1 tokens=1,2" %%a in ('adb devices') do (
    set "serial=%%a"
    set "state=%%b"

    if not "!serial!"=="" (
        set /a index+=1

        echo ----------------------------------------
        echo [!index!] 设备序列号：!serial!
        echo 状态：!state!

        if /i "!state!"=="device" (
            set /a onlineCount+=1
            echo 开始安装 "%APK%"
            adb -s !serial! install -t "%APK%"
        ) 
        echo.
    )
)

echo ========================================

if !onlineCount! EQU 0 (
    echo [WARN] 未找到任何在线设备（无 device 状态）
) else (
    echo 已为 !onlineCount! 台在线设备尝试安装。
)

echo ========================================
goto MAIN_LOOP
