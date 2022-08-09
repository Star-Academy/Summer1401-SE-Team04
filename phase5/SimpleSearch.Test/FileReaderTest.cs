using FluentAssertions;

namespace SimpleSearch.Test
{
    public class FileReaderTest
    {
        private readonly FileReader _fileReader;
        public FileReaderTest()
        {
            _fileReader = new FileReader();
        }

        [Theory]
        [InlineData("C:\\work\\internship\\Summer1401-SE-Team04\\phase5\\DataBase_Dev")]
        public void ReadDocs_ReadDocuments_WordsInFiles(string path)
        {

            var result = _fileReader.ReadDocs(path);
            var expected = new Dictionary<string, List<string>>
            {
                {"1", new List<string>() { "A", "B", "C" } },
                {"2", new List<string>() { "A", "B", "E", "F" } },

            };
            result.Should().BeEquivalentTo(expected);
        }
    }
}