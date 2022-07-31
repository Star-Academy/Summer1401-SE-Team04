using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SimpleSearch
{
    public class FileReader
    {
        public Dictionary<string, List<string>> ReadDocs(string directoryPath)
        {
            DirectoryInfo d = new DirectoryInfo(directoryPath);
            var result = new Dictionary<string, List<string>>();
            foreach (var file in d.GetFiles())
            {
                var fileName = file.Name;
                var text = File.ReadAllText(file.FullName);
                if (text == null || text == "")
                {
                    continue;
                }
                text = text.ToUpper().Trim();
                var sb = new StringBuilder();
                foreach (char c in text)
                {
                    if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') ||  c==' ')
                    {
                        sb.Append(c);
                    }
                }
                text = sb.ToString();

                var words = text.Split(" ").ToList();


                result.Add(fileName, words);
            }
            return result;
        }
    }
}
