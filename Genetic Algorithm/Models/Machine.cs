using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lista1.Models
{
    class Machine
    {
        public int id;
        public int x;
        public int y;

        public Machine(int id, int x, int y)
        {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        public override bool Equals(object obj)
        {
            return obj is Machine machine &&
                   x == machine.x &&
                   y == machine.y;
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(x, y);
        }
    }
}
