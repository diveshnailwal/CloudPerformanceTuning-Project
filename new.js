class Student {
    constructor(id, name) {
        this.id = id;
        this.name = name;
    }

    getId() {
        return this.id;
    }

    getName() {
        return this.name;
    }
}

class Course {
    constructor(code, name) {
        this.code = code;
        this.name = name;
    }

    getCode() {
        return this.code;
    }

    getName() {
        return this.name;
    }
}

class AttendanceManagementSystem {
    constructor() {
        this.students = new Map();
        this.courses = new Map();
        this.attendance = new Map();
    }

    addStudent(id, name) {
        this.students.set(id, new Student(id, name));
    }

    addCourse(code, name) {
        this.courses.set(code, new Course(code, name));
    }

    markAttendance(studentId, courseCode, isPresent) {
        if (this.students.has(studentId) && this.courses.has(courseCode)) {
            if (!this.attendance.has(courseCode)) {
                this.attendance.set(courseCode, new Map());
            }
            this.attendance.get(courseCode).set(studentId, isPresent);
        } else {
            console.log("Invalid student or course ID.");
        }
    }

    generateAttendanceReport(courseCode) {
        if (this.courses.has(courseCode)) {
            const course = this.courses.get(courseCode);
            const attendanceRecord = this.attendance.get(courseCode);

            console.log(`Attendance Report for ${course.getName()} (${course.getCode()}):`);
            attendanceRecord.forEach((isPresent, studentId) => {
                const student = this.students.get(studentId);
                const status = isPresent ? "Present" : "Absent";
                console.log(`${student.getName()} (${studentId}): ${status}`);
            });
        } else {
            console.log("Invalid course code.");
        }
    }
}

const ams = new AttendanceManagementSystem();
const readline = require("readline");
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
});

function mainMenu() {
    console.log("Attendance Management System Menu:");
    console.log("1. Add Student");
    console.log("2. Add Course");
    console.log("3. Mark Attendance");
    console.log("4. Generate Attendance Report");
    console.log("5. Exit");
}

mainMenu();

rl.on("line", (line) => {
    switch (line.trim()) {
        case "1":
            rl.question("Enter Student ID: ", (id) => {
                rl.question("Enter Student Name: ", (name) => {
                    ams.addStudent(id, name);
                    console.log("Student added successfully.");
                    mainMenu();
                });
            });
            break;
        case "2":
            rl.question("Enter Course Code: ", (code) => {
                rl.question("Enter Course Name: ", (name) => {
                    ams.addCourse(code, name);
                    console.log("Course added successfully.");
                    mainMenu();
                });
            });
            break;
        case "3":
            rl.question("Enter Student ID: ", (studentId) => {
                rl.question("Enter Course Code: ", (courseCode) => {
                    rl.question("Is the student present (true/false)? ", (isPresent) => {
                        ams.markAttendance(studentId, courseCode, isPresent === "true");
                        mainMenu();
                    });
                });
            });
            break;
        case "4":
            rl.question("Enter Course Code: ", (courseCode) => {
                ams.generateAttendanceReport(courseCode);
                mainMenu();
            });
            break;
        case "5":
            console.log("Exiting the program.");
            rl.close();
            break;
        default:
            console.log("Invalid choice. Please try again.");
            mainMenu();
            break;
    }
});
