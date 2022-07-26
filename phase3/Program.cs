using phase3.Models;
using System.Text.Json;
namespace phase3
{
    public class Program
    {
        public static void Main(string[] args)
        {
            string studentsJson = System.IO.File.ReadAllText(@"students.json");
            string gradesJson = System.IO.File.ReadAllText(@"scores.json");

            List<Student> students = JsonSerializer.Deserialize<List<Student>>(studentsJson);
            List<Grade> grades = JsonSerializer.Deserialize<List<Grade>>(gradesJson);
            
            students.ForEach(s => s.AverageGrade = grades.Where(g => g.StudentNumber == s.StudentNumber).Select(g => g.Score).Average());
            students.OrderByDescending(s => s.AverageGrade).Take(3).ToList().ForEach(s => Console.WriteLine(s.FirstName + " " + 
            s.LastName + " " + s.AverageGrade));
        }
    }
}