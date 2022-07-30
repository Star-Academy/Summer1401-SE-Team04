using Phase3.Models;
using System.Text.Json;
namespace Phase3;
public class Program
{
    public static void Main(string[] args)
    {
        var studentsJson = File.ReadAllText(@"students.json");
        var gradesJson = File.ReadAllText(@"scores.json");

        var students = JsonSerializer.Deserialize<List<Student>>(studentsJson);
        var grades = JsonSerializer.Deserialize<List<Grade>>(gradesJson);

        foreach(Student student in students)
        {
            student.AverageGrade = grades.Where(g => g.StudentNumber == student.StudentNumber).Select(g => g.Score).Average();
        }

        var superiorStudents = students.OrderByDescending(s => s.AverageGrade).Take(3);
        foreach (Student s in superiorStudents)
        {
            Console.WriteLine(s.FirstName + " " + s.LastName + " : " + s.AverageGrade);
        }
    }
}