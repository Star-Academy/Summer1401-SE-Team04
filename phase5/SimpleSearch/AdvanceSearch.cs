using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SimpleSearch
{
    public class AdvanceSearch
    {
        public List<string>? GetFileNamesBySearchWords(List<string> searchWords, Dictionary<string, List<string>> invertedIndex)
        {
            var positiveSearchWords = new Dictionary<string, List<string>>();
            var negativeSearchWords = new Dictionary<string, List<string>>();
            var normalSearchWords = new Dictionary<string, List<string>>();
            foreach (var searchWord in searchWords)
            {
                if (searchWord.StartsWith("+"))
                {
                    var rawSearchWord = searchWord.Substring(1);
                    positiveSearchWords.Add(rawSearchWord, invertedIndex.FirstOrDefault(a=>a.Key == rawSearchWord).Value);
                }
                else if (searchWord.StartsWith("-"))
                {
                    var rawSearchWord = searchWord.Substring(1);
                    negativeSearchWords.Add(rawSearchWord, invertedIndex.FirstOrDefault(a => a.Key == rawSearchWord).Value);
                }
                else
                {
                    normalSearchWords.Add(searchWord, invertedIndex.FirstOrDefault(a => a.Key == searchWord).Value);
                }
            }

            return CreateFinalList(normalSearchWords, positiveSearchWords, negativeSearchWords);
        }
        private List<string>? CreateFinalList(Dictionary<string, List<string>> normalSearchWords,
            Dictionary<string, List<string>> positiveSearchWords, Dictionary<string, List<string>> negativeSearchWords)
        {
            var firstNormalSearchWord = normalSearchWords.FirstOrDefault();
            if (firstNormalSearchWord.Value == null)
            {
                return null;
            }
            var result = new List<string>();
            foreach (var fileName in firstNormalSearchWord.Value)
            {
                bool flag = true;
                foreach (var normalList in normalSearchWords.Values)
                {
                    if (normalList == null)
                    {
                        continue;
                    }
                    if (!normalList.Contains(fileName))
                    {
                        flag = false;
                        break;
                    }
                }
                foreach (var negativeList in negativeSearchWords.Values)
                {
                    if (negativeList == null)
                    {
                        continue;
                    }
                    if (negativeList.Contains(fileName))
                    {
                        flag = false;
                        break;
                    }
                }
                bool positiveFlag = false;
                foreach (var positiveList in positiveSearchWords.Values)
                {
                    if (positiveList == null)
                    {
                        continue;
                    }
                    if (positiveList.Contains(fileName))
                    {
                        positiveFlag = true;
                        break;
                    }
                }
                if (!positiveFlag && positiveSearchWords.Count != 0)
                {
                    flag = false;
                }
                if (flag)
                {
                    result.Add(fileName);
                }
            }
            return result;
        }
    }
}
