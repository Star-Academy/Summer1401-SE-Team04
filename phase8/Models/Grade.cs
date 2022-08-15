using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace phase8.Models
{
    public class Grade
    {
        [Key]
        public int Id { get; set; }
        public int StudentNumber { get; set; }
        public string? Lesson { get; set; }
        public double Score { get; set; }

    }
}
