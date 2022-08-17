using phase9.IService;
using SimpleSearch;

namespace phase9.Service
{
    public class SearchService : ISearchService
    {
        private readonly Dictionary<string, List<string>> _invertedIndex;
        private readonly string directoryPath = "C:\\work\\internship\\Summer1401-SE-Team04\\phase5\\DataBase";
        public SearchService() 
        {
            var wordsInFiles = new FileReader().ReadDocs(directoryPath);
            _invertedIndex = new InvertedIndex().MakeInvertedIndex(wordsInFiles);
        }
        public List<string>? Search(List<string> searchWords)
        {
            var res = new AdvanceSearch().GetFileNamesBySearchWords(searchWords, _invertedIndex);
            return res;
        }
    }
}
