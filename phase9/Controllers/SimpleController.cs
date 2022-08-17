using Microsoft.AspNetCore.Mvc;
using phase9.IService;
using phase9.Service;
using System.Text.Json;

namespace phase9.Controllers
{
    [ApiController]
    [Route("[controller]/[Action]")]
    public class SimpleController : Controller
    {
        private readonly ISearchService _searchService;
        public SimpleController(ISearchService searchService)
        {
            _searchService = searchService;
        }
        [HttpGet]
        public string Get()
        {
            return "Hello world!";
        }

        [HttpGet]
        public string Search(string word)
        {
            var searchwords = new List<string>
            {
                word.ToUpper()
            };
            var res = _searchService.Search(searchwords);
            return JsonSerializer.Serialize(res);
        }
    }
}
