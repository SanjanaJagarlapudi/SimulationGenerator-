package antsWithSugar;

import simstation.*;
import mvc.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/* Class "FlockingSimulation" Datalog
4/16/2023 - Sanjana Jagarlapudi: Created and Finished File
*/

public class SugarScape extends Simulation {
    HashSet<Point> pods;

    public SugarScape() {
        super();
        pods = new HashSet<>();

        Point p;
        for (int i = 0; i < 80; i++) {
            p = new Point(Utilities.rng.nextInt(241), Utilities.rng.nextInt(241));
            pods.add(p);
        }
    }
    public void populate() {
        for (int i = 0; i < 40; i++) {
            addAgent(new Ant());
        }
    }

    public HashSet<Point> getPods() {
        return pods;
    }

    public boolean contains(Point pod) {return pods.contains(pod); }

    public boolean remove(Point pod) {return pods.remove(pod); }

    public Iterator<Agent> agentIterator() {return getAgents().iterator(); }

    public Iterator<Point> podIterator() {return pods.iterator(); }

    public void showStats() {
        int numAgents = getAgents().size();
        int time = getClock();

        int fit3 = 0;
        int fit4 = 0;
        int fit5 = 0;
        int fit6 = 0;
        int higher = 0;

        for (Agent a : getAgents()) {
            Ant ant = (Ant) a;
            switch (ant.getFitness()) {
                case 3:
                    fit3++;
                    break;
                case 4:
                    fit4++;
                    break;
                case 5:
                    fit5++;
                    break;
                case 6:
                    fit6++;
                    break;
                default:
                    higher++;

            }
        }

        JFrame frame = new JFrame("");
        JOptionPane.showMessageDialog(frame, "Fitness Numbers: "  + "\nfitness 3: "
                + fit3 + "\nfitness 4: " + fit4 + "\nfitness 5: " + fit5 + "\nfitness 6: "
                + fit6 + "\nhigher: " + higher + "\n\n#agents = " + numAgents + "\n#pods = " + pods.size()
                + "\nclock = " + time);
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new SugarScapeFactory());
        panel.display();
    }
}

class Ant extends Agent {
    int fitness;
    Point loc;

    public Ant() {
        super("Ant");
        heading = Heading.random();
        fitness = 3;
        loc = new Point(getX(), getY());
    }

    public void update() {
        SugarScape sim = (SugarScape) world;
        loc = new Point(getX(), getY());
        synchronized(sim) {
            if (sim.contains(loc)) {
                sim.remove(loc);
                fitness++;
            }
        }

        heading = Heading.random();
        move(fitness);
    }

    public synchronized int getFitness() {
        return fitness;
    }

    public synchronized void setFitness(int newFitness) {
        fitness = newFitness;
    }
}

class SugarScapeView extends SimulationView {
    public SugarScapeView(Model m) {
        super((Simulation) m);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        SugarScape sim = (SugarScape) model;
        synchronized(sim) {
            gc.setColor(Color.RED);
            for (Agent a : sim.getAgents()) {
                Ant ant = (Ant) a;
                gc.fillOval(ant.getX(), ant.getY(), ant.getFitness(), ant.getFitness());
            }
            gc.setColor(Color.BLACK);
            for (Point p : sim.getPods()) {
                gc.fillOval((int) p.getX(), (int) p.getY(), 5, 5);
            }
        }
    }
}

class SugarScapeFactory extends SimStationFactory {
    public Model makeModel() {
        return new SugarScape();
    }

    public View makeView(Model m) {
        return new SugarScapeView(m);
    }

    public String getTitle() {
        return "SugarScape";
    }
}