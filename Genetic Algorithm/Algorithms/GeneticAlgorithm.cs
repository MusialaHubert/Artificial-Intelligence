using Lista1.Models;
using Lista1.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lista1.Algorithms
{
    class GeneticAlgorithm
    {
        Random rand = new Random();
        FitnessMethod fitnessMethod = new FitnessMethod();

        public Subject GE(int iterations, float crossoverP, float mutationP, List<Subject> population, List<Relation> relations)
        {
            int bestResult = Int32.MaxValue;
            Subject bestSubject = null;
            List<Subject> newPopulation = new List<Subject>();

            for(int i = 0; i < iterations; i ++)
            {
                while(newPopulation.Count != population.Count)
                {
                    Subject father = Tournament(50, population);
                    Subject mother = Tournament(50, population);
                    //Subject father = Roulette(population);
                    //Subject mother = Roulette(population);
                    Subject child;

                    if(rand.NextDouble() < crossoverP)
                    {
                        child = CrossOver(father, mother);
                    }
                    else
                    {
                        child = father;
                    }

                    if(rand.NextDouble() <= mutationP)
                    {
                        child = Mutation(child);
                    }

                    child.sigma = fitnessMethod.CalculateSigma(child.machines, relations);
                    newPopulation.Add(child);
                }

                population = new List<Subject>(newPopulation);

                int best = Int32.MaxValue;
                int worst = 0;
                int avg = 0;
                foreach(Subject s in population)
                {
                    if(bestResult > s.sigma)
                    {
                        bestResult = s.sigma;
                        bestSubject = s;
                    }
                    if(best > s.sigma)
                    {
                        best = s.sigma;
                    }

                    if(worst < s.sigma)
                    {
                        worst = s.sigma;
                    }
                    avg += s.sigma;
                }
                newPopulation.Clear();
                //Console.WriteLine(avg/population.Count);
                //Console.WriteLine(best);
                //Console.WriteLine(worst);
                Console.WriteLine(bestResult);
            }
            return bestSubject;
        }
        public Subject Tournament(int N, List<Subject> population)
        {
            var rnd = new Random();
            population = population.OrderBy(item => rnd.Next()).ToList();
            
            if (N < 1 || N > population.Count) N = 5;

            Subject winner = population[0];

            for(int i = 1; i < N; i++)
            {
                if(population[i].sigma < winner.sigma)
                {
                    winner = population[i];
                }
            }

            return winner;
        }

        public Subject Roulette(List<Subject> population)
        {
            CalculateRouletteProbability(population);

            float drawValue = (float) rand.NextDouble();

            Subject winner = null;
            float currentRouletteValue = 0;
            foreach(var subject in population)
            {
                currentRouletteValue += subject.probability;
                if (currentRouletteValue >= drawValue)
                {
                    winner = subject;
                    break;
                }       
            }
            return winner;
        }

        public Subject CrossOver(Subject parent1, Subject parent2)
        {
            Subject child = new Subject(new List<Machine>(), 0);

            int partition = parent1.machines.Count / 2;

            for(int i = 0; i < partition; i++)
            {
                child.machines.Add(parent1.machines[i]);
            }

            for( int i = 0; i < parent2.machines.Count; i++)
            {
                if(!child.machines.Contains(parent2.machines[i]))
                {
                    child.machines.Add(parent2.machines[i]);
                }
            }
            return child;
        }

        public Subject Mutation(Subject subject)
        {
            int index1 = rand.Next(subject.machines.Count);
            int index2 = rand.Next(subject.machines.Count);
            while (index1 == index2)
                index2 = rand.Next(subject.machines.Count);

            var tmp = subject.machines[index1];
            subject.machines[index1] = subject.machines[index2];
            subject.machines[index2] = tmp;

            return subject;
        }

        private void CalculateRouletteProbability(List<Subject> population)
        {
            int sumOfSigma = 0;

            foreach(var subject in population)
            {
                sumOfSigma += subject.sigma;
            }

            foreach(var subject in population)
            {
                subject.probability = (1 - ((float) subject.sigma / sumOfSigma)) / (population.Count - 1);
            }
        }
    }
}
