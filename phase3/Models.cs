namespace phase3.Models
{
    public class Student
{
    public int StudentNumber { get; set; }
    public string? FirstName { get; set; }
    public string? LastName { get; set; }
    public double AverageGrade { get; set; }


}

public class Grade
{
    public int StudentNumber { get; set; }

    public string? Lesson { get; set; }

    public double Score {get; set; }

}

}

