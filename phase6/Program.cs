using SimpleSearch;
var directoryPath = "C:\\work\\internship\\Summer1401-SE-Team04\\phase5\\DataBase";

var wordsInFiles = new FileReader().ReadDocs(directoryPath);
var invertedIndex = new InvertedIndex().MakeInvertedIndex(wordsInFiles);
var inputString = Console.ReadLine().ToUpper();
var searchWords = inputString.Split(' ').ToList();
var finalRes = new AdvanceSearch().GetFileNamesBySearchWords(searchWords, invertedIndex);

if (finalRes == null || finalRes.Count == 0)
{
    Console.WriteLine("words was not found!");
    return;
}

foreach (var fileName in finalRes)
{
    Console.WriteLine(fileName);
}