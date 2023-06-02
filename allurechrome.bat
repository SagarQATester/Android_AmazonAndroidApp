set "Command="
SET mypath="C:\Users\RAHUL\workspace\AmazonAndroidApp"
call allure generate C:\Users\RAHUL\workspace\AmazonAndroidApp\allure-results -c C:\Users\RAHUL\workspace\AmazonAndroidApp\allure-report -o C:\Users\RAHUL\workspace\AmazonAndroidApp\allure-report"
set "Command="
SET mypath="C:\Users\RAHUL\workspace\AmazonAndroidApp"
echo C:\Users\RAHUL\workspace\AmazonAndroidApp
for /f "tokens=2,*" %%A in ('reg query "HKEY_LOCAL_MACHINE\SOFTWARE\Clients\StartMenuInternet\Google Chrome\shell\open\command" /ve 2^>nul') do set "Command=%%~B"
if not defined Command for /f "tokens=2,*" %%A in ('reg query "HKEY_LOCAL_MACHINE\SOFTWARE\Wow6432Node\Clients\StartMenuInternet\Google Chrome\shell\open\command" /ve 2^>nul') do set "Command=%%~B"
if not defined Command echo Google Chrome was not found.
if defined Command start "Browser" "%Command%"  --allow-file-access-from-files --disable-web-security "%mypath:~0,-1%/allure-report/index.html"


