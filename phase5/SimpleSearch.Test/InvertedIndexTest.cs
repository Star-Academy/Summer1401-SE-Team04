using FluentAssertions;

namespace SimpleSearch.Test
{
    public class InvertedIndexTest
    {
        private readonly InvertedIndex _invertedIndex;
        public InvertedIndexTest()
        {
            _invertedIndex = new InvertedIndex();
        }

        [Fact]
        public void MakeInvertedIndex_CreateInvertedIndex_InvertedIndex()
        {
            var words_in_files = new Dictionary<string, List<string>>
            {
                {"1", new List<string>() { "a", "b", "c" } },
                {"2", new List<string>() { "a", "b", "d", "f" } },

            };
            var result = _invertedIndex.MakeInvertedIndex(words_in_files);
            var expected = new Dictionary<string, List<string>>
            {
                { "a", new List<string>() { "1", "2" } },
                { "b", new List<string>() { "1", "2" } },
                { "c", new List<string>() { "1" } },
                { "d", new List<string>() { "2" } },
                { "f", new List<string>() { "2" } }
            };
            result.Should().BeEquivalentTo(expected);
        }
    }
}