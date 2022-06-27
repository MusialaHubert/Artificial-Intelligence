using Lista1.Algorithms;
using Lista1.Models;
using Lista1.Utils;
using System;
using System.Collections.Generic;
using System.Linq;

namespace Lista1
{
    class Program
    {
        static List<Relation> relations = new List<Relation>();
        static ReadData data = new ReadData();
        static void Main(string[] args)
        {

            ReadRelations(2);
            RandomAlgorithm randomAlgorithm = new RandomAlgorithm();
            GeneratingSubjects generating = new GeneratingSubjects();
            GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
            randomAlgorithm.RandomAlg(1000, relations);

            var pop = generating.GenerateRandomPopulation(24, 5, 6, 100, relations);
            var best = geneticAlgorithm.GE(10, 0.7f, 0.1f, pop, relations);
            
        }
        public static void ReadRelations(int option)
        {
            relations = data.ReadRelations(option);
        }
    }
}
