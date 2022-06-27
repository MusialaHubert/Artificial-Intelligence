using Lista1.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lista1.Utils
{
    class FitnessMethod
    {
        

        public int CalculateSigma(List<Machine> machines , List<Relation> relations)
        {
            int sigma = 0;
            for (int i = 0; i < machines.Count; i++)
            {
                for (int j = 0; j < machines.Count; j++)
                {
                    if (i != j)
                    {
                        int d = Math.Abs(machines[i].x - machines[j].x) + Math.Abs(machines[i].y - machines[j].y);
                        var rel = relations.Find(r => r.source == i && r.dest == j);
                        if (rel is not null)
                        {
                            sigma += d * rel.cost * rel.amount;
                        }
                    }
                }
            }
            return sigma;
        }
    }
}
