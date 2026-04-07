@echo off
REM Deployment Script for Sonatype Central Publisher (Windows)
REM This script helps deploy your library to Maven Central

echo ======================================
echo   Maven Central Deployment Script
echo ======================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Maven is not installed or not in PATH.
    echo Please install Maven and add it to your PATH.
    pause
    exit /b 1
)

REM Check if GPG is installed
where gpg >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: GPG is not installed or not in PATH.
    echo Please install GPG from https://www.gnupg.org/download/
    pause
    exit /b 1
)

echo [OK] Maven found
echo [OK] GPG found
echo.

REM Check if settings.xml exists
if not exist "%USERPROFILE%\.m2\settings.xml" (
    echo WARNING: %USERPROFILE%\.m2\settings.xml not found!
    echo Please copy settings.xml.template to %USERPROFILE%\.m2\settings.xml
    echo and configure your credentials.
    pause
    exit /b 1
)

echo [OK] Maven settings found
echo.

REM Ask for confirmation
set /p CONFIRM="This will deploy the library to Maven Central. Continue? (Y/N): "
if /i not "%CONFIRM%"=="Y" (
    echo Deployment cancelled.
    pause
    exit /b 0
)

echo.
echo ======================================
echo   Starting Deployment Process
echo ======================================
echo.

REM Step 1: Clean
echo [1/4] Cleaning previous builds...
call mvn clean
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Clean failed
    pause
    exit /b 1
)

REM Step 2: Verify
echo.
echo [2/4] Running tests and verification...
call mvn verify
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Verification failed
    pause
    exit /b 1
)

REM Step 3: Deploy
echo.
echo [3/4] Deploying to Maven Central...
call mvn deploy
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Deployment failed
    pause
    exit /b 1
)

REM Step 4: Done
echo.
echo ======================================
echo   Deployment Complete!
echo ======================================
echo.
echo Next steps:
echo 1. Visit https://central.sonatype.com/publishing/deployments
echo 2. Check the status of your deployment
echo 3. Wait 15-30 minutes for it to appear on Maven Central
echo 4. Search for your artifact at https://central.sonatype.com/
echo.
echo Your artifact will be available at:
echo   https://central.sonatype.com/artifact/^<groupId^>/^<artifactId^>
echo.

pause
