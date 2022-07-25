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

            
        }
    }
}