using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lista1.Models
{
    class Relation
    {
        public int source;
        public int dest;
        public int cost;
        public int amount;

        public Relation(int source, int dest, int cost, int amount)
        {
            this.source = source;
            this.dest = dest;
            this.cost = cost;
            this.amount = amount;
        }
    }
}
