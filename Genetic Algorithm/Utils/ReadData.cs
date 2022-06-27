using Lista1.Models;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lista1.Utils
{
    class ReadData
    {
        public List<Relation> ReadRelations(int option)
        {
            List<Cost> costs = JsonConvert.DeserializeObject<List<Cost>>(File.ReadAllText("D:\\Studia\\Semestr6\\SI\\Lista1\\Lista1\\Lista1\\hard_cost.json"));
            List<Amount> amounts = JsonConvert.DeserializeObject<List<Amount>>(File.ReadAllText("D:\\Studia\\Semestr6\\SI\\Lista1\\Lista1\\Lista1\\hard_flow.json"));

            switch (option)
            {
                case 0:
                    costs = JsonConvert.DeserializeObject<List<Cost>>(File.ReadAllText("D:\\Studia\\Semestr6\\SI\\Lista1\\Lista1\\Lista1\\easy_cost.json"));
                    amounts = JsonConvert.DeserializeObject<List<Amount>>(File.ReadAllText("D:\\Studia\\Semestr6\\SI\\Lista1\\Lista1\\Lista1\\easy_flow.json"));
                    break;
                case 1:
                    costs = JsonConvert.DeserializeObject<List<Cost>>(File.ReadAllText("D:\\Studia\\Semestr6\\SI\\Lista1\\Lista1\\Lista1\\flat_cost.json"));
                    amounts = JsonConvert.DeserializeObject<List<Amount>>(File.ReadAllText("D:\\Studia\\Semestr6\\SI\\Lista1\\Lista1\\Lista1\\flat_flow.json"));
                    break;
                case 2:
                    costs = JsonConvert.DeserializeObject<List<Cost>>(File.ReadAllText("D:\\Studia\\Semestr6\\SI\\Lista1\\Lista1\\Lista1\\hard_cost.json"));
                    amounts = JsonConvert.DeserializeObject<List<Amount>>(File.ReadAllText("D:\\Studia\\Semestr6\\SI\\Lista1\\Lista1\\Lista1\\hard_flow.json"));
                    break;              
            }
            List<Relation> relations = new List<Relation>();
            for (int i = 0; i < costs.Count; i++)
            {
                Relation rel = new Relation(costs[i].source, costs[i].dest, costs[i].cost, amounts[i].amount);
                relations.Add(rel);
            }
            return relations;
        }
    }
}
