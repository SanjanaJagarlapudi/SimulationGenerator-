package swamp;
import mvc.*;
import simstation.*;
import javax.swing.*;
import java.awt.*;
/*4/10/2023 - Sanjana Jagarlapudi: Created and Finished file
*/
class FireFly extends Agent {

    private int rhythm = 5 + Utilities.rng.nextInt(11);
    private boolean flashing = false;
    private Color c = Color.BLACK;
    public FireFly(){
        super("FireFly");
    }
    // getters and setters go here
    public int getRhythm() {
        return rhythm;
    }

    public void setColor(Color c) {
        this.c = c;
    }

    public Color getColor() {
        return c;
    }

    public void setFlashing(boolean flashing) {
        this.setColor(Color.RED);
        this.flashing = flashing;
    }

    public void update() {
        // update flashing and rhythm
        FireFly neighbor = (FireFly) world.getNeighbor(this, 5.0);

        if (neighbor != null) {
            this.rhythm = neighbor.getRhythm();
            if(world.getClock() % this.rhythm == 0 && world.getClock() != 0 ){
                this.setFlashing(true);
            }
        }

        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
    }
}


public class Swamp extends Simulation {

    public void populate() {
        for(int i = 0; i < 50; i++) {
            addAgent(new FireFly());
        }
    }
    //make better show stats method
    public void showStats() {
        int numAgents = getAgents().size();
        int time = getClock();
        int numRed = 0;
        int numBlack = 0;
        for(int i = 0; i < numAgents; i++){
            FireFly f = (FireFly) getAgents().get(i);
            if((f.getColor()) == Color.RED){
                numRed++;
            }
            else{
                numBlack++;
            }
        }
        JFrame frame = new JFrame("");
        JOptionPane.showMessageDialog(frame, "#total fireflies = " + numAgents + "\nclock = " + time +
                "\nflashing fireflies: " + numRed + "\nnonflashing fireflies: " + numBlack) ;
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new SwampFactory());
        panel.display();
    }
}


class SwampView extends SimulationView {

    public SwampView(Model model) {
        super((Simulation)model);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();
        // draw each firefly
        Graphics2D gc2d = (Graphics2D) gc;
        int flyDiameter = 3;
        for(int i = 0; i < this.getSim().getAgents().size(); i++){
            FireFly fly = (FireFly) getSim().getAgents().get(i);
            gc2d.setColor(fly.getColor());
            gc2d.fillOval(fly.getX(), fly.getY(), flyDiameter, flyDiameter);
        }
        gc.setColor(oldColor);
    }
}

class SwampFactory extends SimStationFactory {
    public Model makeModel() {
        return new Swamp();
    }
    public View makeView(Model model) { return new SwampView(model); }
    public String getTitle() { return "Swamp"; }
}
