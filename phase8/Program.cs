using phase8;
using phase8.Models;

var db = new PostContext();
var students = db.Students.ToList();
var grades = db.Grades.ToList();

foreach (Student student in students)
{
    student.AverageGrade = grades.Where(g => g.StudentNumber == student.StudentNumber).Select(g => g.Score).Average();
}

var superiorStudents = students.OrderByDescending(s => s.AverageGrade).Take(3);
foreach (Student s in superiorStudents)
{
    Console.WriteLine(s.FirstName + " " + s.LastName + " : " + s.AverageGrade);
}