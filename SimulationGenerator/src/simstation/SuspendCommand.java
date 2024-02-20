package simstation;

import mvc.*;

/* Class "SuspendCommand" Datalog
4/6/2023 - Sanjana Jagarlapudi: Created file
                          Added outline
4/9/2023 - Sanjana Jagarlapudi: Added method headers
4/9/2023 - Sanjana Jagarlapudi: continued implementation of execute method.
 */

public class SuspendCommand extends Command {
    public SuspendCommand(Model model) {
        super(model);
    }

    public void execute() {
        Simulation sim = (Simulation) model;
        sim.suspend();
    }
}
