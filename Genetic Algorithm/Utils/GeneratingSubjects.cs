using Lista1.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lista1.Utils
{
    class GeneratingSubjects
    {
        FitnessMethod fitnessMethod = new FitnessMethod();
        public List<Machine> CreateRandomSubject(int machineNumber, int x, int y)
        {
            List<Point> points = new List<Point>();
            for (int i = 0; i < x; i++)
            {
                for (int j = 0; j < y; j++)
                {
                    Point point = new Point(i, j);
                    points.Add(point);
                }
            }

            var rnd = new Random();
            points = points.OrderBy(item => rnd.Next()).ToList();

            List<Machine> machines = new List<Machine>();
            for (int i = 0; i < machineNumber; i++)
            {
                Machine machine = new Machine(i, points[i].x, points[i].y);
                machines.Add(machine);
            }
            return machines;
        }

        public List<Subject> GenerateRandomPopulation(int machineNumber, int x, int y, int sizeOfPopulation, List<Relation> relations)
        {
            List<Subject> population = new List<Subject>();

            for(int i = 0; i < sizeOfPopulation; i ++)
            {
                List<Machine> machines = CreateRandomSubject(machineNumber, x, y);
                int sigma = fitnessMethod.CalculateSigma(machines, relations);
                population.Add(new Subject(machines, sigma));
            }

            return population;
        }
    }
}
