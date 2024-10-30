@echo off
REM Check if database folder exists, if not, create it
if not exist "%~dp0database" (
    mkdir "%~dp0database"
)
REM Copy the phonebook.txt file to the database folder if it doesn't exist
if not exist "%~dp0database\phonebook.txt" (
    copy /Y "src\Phone_Book\src\database" "%~dp0database\phonebook.txt"
)
java -jar bin\PhoneBookApp.jar
pause
