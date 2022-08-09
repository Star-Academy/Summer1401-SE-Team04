namespace SimpleSearch
{
    public class InvertedIndex
    {
        public Dictionary<string, List<string>> Index { get; set; }
        public InvertedIndex()
        {
            Index = new Dictionary<string, List<string>>();
        }
        private void AddWordToInvertedIndex(string word, string fileName)
        {

            var files = Index.FirstOrDefault(a =>a.Key == word).Value;
            if (files == null)
            {
                var fileNames = new List<string>
                {
                    fileName
                };
                Index.Add(word, fileNames);
                return;
            }
            else
            {
                foreach (var file in files)
                {
                    if (file == fileName)
                    {
                        return;
                    }

                }
            }
            files.Add(fileName);

        }
        public Dictionary<string, List<string>> MakeInvertedIndex(Dictionary<string, List<string>> words_in_files)
        {
            foreach (var fileName in words_in_files.Keys)
            {
                var words = words_in_files.First(a => a.Key == fileName).Value;
                foreach (var word in words)
                {
                    AddWordToInvertedIndex(word, fileName);
                }
            }
            return Index;
        }
    }
}