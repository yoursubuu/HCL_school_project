Simple School project

Folders:
- src: Java source files (com.school.model, com.school.dao, com.school.app)
- bin: compiled classes (created by javac -d bin ...)

How to compile and run (on Windows cmd):

1) Compile:

    javac -d bin src\com\school\model\Student.java src\com\school\dao\StudentDao.java src\com\school\app\MainApp.java

2) Run:

    java -cp bin com.school.app.MainApp

Data file: The application writes serialized students to a file named `students.dat` in the current working directory.
