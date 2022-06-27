using Lista1.Models;
using Lista1.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lista1.Algorithms
{
    class RandomAlgorithm
    { 
        GeneratingSubjects generator = new GeneratingSubjects();
        FitnessMethod fitnessMethod = new FitnessMethod();


        public void RandomAlg(int numberOfRandomSubject, List<Relation> relations)
        {

            int sigma = Int32.MaxValue;
            for (int i = 0; i < numberOfRandomSubject; i++)
            {
                List<Machine> machines = generator.CreateRandomSubject(24, 5, 6);           
                int currentSigma = fitnessMethod.CalculateSigma(machines, relations);

                if (currentSigma < sigma)
                {
                    sigma = currentSigma;
                }
            }

            Console.WriteLine(sigma);
        }
    }
}
