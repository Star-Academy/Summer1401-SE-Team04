using Microsoft.EntityFrameworkCore;
using phase8.Models;
using System;
using System.Collections.Generic;

namespace phase8
{
    public class PostContext : DbContext
    {
        public DbSet<Student> Students { get; set; }
        public DbSet<Grade> Grades { get; set; }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseNpgsql($"Server=127.0.0.1;Port=5432;Database=postgres;User Id=postgres;Password=basilisk79");
        }
    }
}
