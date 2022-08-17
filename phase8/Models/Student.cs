using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace phase8.Models
{
    public class Student
    {
        [Key]
        public int StudentNumber { get; set; }
        public string? FirstName { get; set; }
        public string? LastName { get; set; }
        public double AverageGrade { get; set; }

    }
}
