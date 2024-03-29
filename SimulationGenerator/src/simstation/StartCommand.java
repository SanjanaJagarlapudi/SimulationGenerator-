package simstation;

import mvc.*;

/* Class "StartCommand" Datalog
4/6/2023 - Sanjana Jagarlapudi: Created file
                          Added outline
4/9/2023 - Sanjana Jagarlapudi: Added method headers
4/9/2023 - Sanjana Jagarlapudi: continued implementation of execute method.
 */

public class StartCommand extends Command {
    public StartCommand(Model model) {
        super(model);
    }

    public void execute() {
        System.out.println("test");
        Simulation sim = (Simulation) model;
        sim.start();
    }
}
