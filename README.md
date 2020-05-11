# digidiary
This is a native multi-language android application used to keep your diaries in a digital form. This app is written in Java and uses SQLite Database. The project will be modified with comments for explanation.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
You need to install git from here:
https://git-scm.com/downloads

### Installing
1- open git bash, terminal, or cmd

2- navigate to the destination directory

3 write:
```
git clone https://github.com/NourhanGehad/digidiary.git
```
5- open the new created folder in Android Studio or any other ide

## Features:

* Adding diaries (with dates and images), editing and deleting them.
* Viewing your list of diaries
* Viewing the details of a diary in a single page
* Adding and removing a diary to/from favourites list
* Viewing your favourite diaries list
* A bin to restore or permenantly delete a previously deleted diary
* Choosing between 6 languages
* A Calendar screen to navigate to any date and get diaries of the selected date

## Used in this app:
* Navigation Component ( Navigation graph )
* Single activity app concept
* RecyclerView and RecyclerView adapter
* SQLite Database
* SQLiteOpenHelper
* Sharedpreferences for localization
* Dialogs: DatePickerDialog, AlertDialog

