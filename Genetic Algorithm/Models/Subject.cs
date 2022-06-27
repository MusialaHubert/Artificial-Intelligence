using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lista1.Models
{
    class Subject
    {
        public List<Machine> machines = new List<Machine>();
        public int sigma;
        public float probability;

        public Subject(List<Machine> machines, int sigma)
        {
            this.machines = machines;
            this.sigma = sigma;
        }
    }
}
