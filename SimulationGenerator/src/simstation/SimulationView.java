package simstation;

import mvc.*;
import randomMovers.RandomWalkSimulation;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

/* Class "SimulationView" Datalog
4/4/2023 - Sanjana Jagarlapudi: Created file
4/7/2023 - Sanjana Jagarlapudi: Added method headers and some basic code
4/11/2023 - Sanjana Jagarlapudi: Minor edits
4/12/2023 - Sanjana Jagarlapudi: Edits to paintComponent, drawing agents
4/15/2023 - Sanjana Jagarlapudi: Added getSim method
 */

public class SimulationView extends View {

    Simulation sim;

    public SimulationView(Simulation s) {
        super(s);
        sim = s;
        sim.populate();
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Graphics2D gc2d = (Graphics2D) gc;
        for (Agent a : sim.getAgents()) {
            gc2d.fillRect(a.getX(), a.getY(), 5, 5);
        }
    }
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
        repaint();

        if (evt.getPropertyName() == null) {
            repaint();
            return;
        }

        if (evt.getPropertyName().equals("New") || evt.getPropertyName().equals("Open")) {
            removeAll();
            Simulation s = new RandomWalkSimulation();
            setSim(s);
        }
    }

    public Simulation getSim() {
        return sim;
    }

    public void setSim(Simulation sim) {
        this.sim = sim;
        sim.populate();
        sim.start();
    }
}
