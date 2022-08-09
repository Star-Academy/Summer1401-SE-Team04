using FluentAssertions;


namespace SimpleSearch.Test
{
    public class AdvancedSearchTest
    {
        private readonly AdvanceSearch _advanceSearch;
        public AdvancedSearchTest()
        {
            _advanceSearch = new AdvanceSearch();
        }

        [Theory]
        [MemberData(nameof(SearchData))]
        public void AdvancedSearch_NormalSearchWords_FilesWithWords(List<string> searchWords, List<string> expected)
        {
            var invertedIndex = new Dictionary<string, List<string>>
            {
                { "A", new List<string>() { "1", "2" } },
                { "B", new List<string>() { "1", "2" } },
                { "C", new List<string>() { "1" } },
                { "D", new List<string>() { "2" } },
                { "F", new List<string>() { "2" } }
            };
            var result = _advanceSearch.GetFileNamesBySearchWords(searchWords, invertedIndex);
            result.Should().BeEquivalentTo(expected);
        }

        public static IEnumerable<object[]> SearchData =>
            new List<object[]>
            {
                    new object[] { new List<string>() { "A","B" }, new List<string>() { "1","2" } },
            };
    }
}
