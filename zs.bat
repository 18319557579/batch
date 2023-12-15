@echo off

::获得必要的参数
setlocal
:loop
IF NOT "%1"=="" (
    IF /i "%1"=="-src" (
        SET src=%2
    )
    IF /i "%1"=="-ks" (
        SET ks=%2
    )
    IF /i "%1"=="-alias" (
        SET alias=%2
    )
    IF /i "%1"=="-kspass" (
        SET kspass=%2
    ) 
    IF /i "%1"=="-aliaspass" (
        SET aliaspass=%2
    )
    IF /i "%1"=="-toolsdir" (
        SET toolsdir=%2
    )
    SHIFT
    SHIFT
    GOTO :loop
)

::获得源文件的路径
for %%I in (%src%) do (
    set srcdir=%%~dpI
)

::创建输出目录
set targetdir=%srcdir%zipalign_then_sign\
if not exist %targetdir% (
    md %targetdir%
)

::进行4K对齐
cd %toolsdir%
set zipalignedfile=%targetdir%zipaligned.apk
echo -*- zipalignedfile: %zipalignedfile%

call zipalign -p -f 4 %src% %zipalignedfile%
if %errorlevel% geq 1 (
    echo -*- zipalign error
    goto :eof
) else (
    echo -*- zipalign success
)

::进行签名（好像apksigner本身就有4K对齐的能力，不过懒得改了，就用zipalign算了）（好像不能有中文路径）
cd %toolsdir%
set signedfile=%targetdir%signed.apk
echo -*- signedfile: %signedfile%

call apksigner sign --ks %ks% --ks-key-alias %alias% --ks-pass pass:%kspass% --key-pass pass:%aliaspass% --in %zipalignedfile% --out %signedfile%
if %errorlevel% geq 1 (
    echo -*- apksigner error
    goto :eof
) else (
    echo -*- apksigner success
)

endlocal



goto annotation
使用示范
zs -src C:\Users\hsf\Documents\Daisy_keys\signsomething\加固后，无签名\GSF6_231113_1758_301_jiagu.apk -ks C:\Users\hsf\Documents\Daisy_keys\signsomething\inquiredemand.jks -alias seeabout -kspass dsgf5we4w48r8344sadf4a4ewf -aliaspass dsgf5we4w48r8344sadf4a4ewf -toolsdir C:\Users\hsf\AppData\Local\Android\Sdk\build-tools\34.0.0

结果打印（正常结果）
-*- zipalignedfile: C:\Users\hsf\Documents\Daisy_keys\signsomething\加固后，无签名\zipalign_sign\zipaligned.apk
-*- zipalign success
-*- signedfile: C:\Users\hsf\Documents\Daisy_keys\signsomething\加固后，无签名\zipalign_sign\signed.apk
-*- apksigner success

结果打印（传入的文件不是apk，无法进行对齐）
-*- zipalignedfile: C:\Users\hsf\AppData\Local\Android\Sdk\build-tools\34.0.0\zipalign_sign\zipaligned.apk
Unable to open 'C:\Users\hsf\AppData\Local\Android\Sdk\build-tools\34.0.0\libbcc.dll' as zip archive
-*- zipalign error

:annotation