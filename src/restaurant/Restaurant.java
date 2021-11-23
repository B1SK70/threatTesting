package restaurant;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Restaurant extends JFrame {

    public Restaurant() {
        setWindowParameters();

        // ------------- CREATE ELEMENTS -----------
        Table table = new Table();

        Chef chef1 = new Chef(table);
        Chef chef2 = new Chef(table);
        Chef chef3 = new Chef(table);

        Client client1 = new Client(table);
        Client client2 = new Client(table);

        // ------------ CREATE THREADS ------------
        Thread aliveChef1 = new Thread(chef1);
        Thread aliveChef2 = new Thread(chef2);
        Thread aliveChef3 = new Thread(chef3);

        Thread aliveClient1 = new Thread(client1);
        Thread aliveClient2 = new Thread(client2);

        // ------------ START THREADS -------------
        aliveChef1.start();
        aliveChef2.start();
        aliveChef3.start();

        aliveClient1.start();
        aliveClient2.start();

        // ------------ ADD ELEMENTS TO VIEWER -------------
        Viewer viewer = new Viewer();

        viewer.addChef(chef1);
        viewer.addChef(chef2);
        viewer.addChef(chef3);

        viewer.addClient(client1);
        viewer.addClient(client2);

        viewer.addTable(table);

        //START VIEWER
        this.add(viewer);

        Thread viewerThread = new Thread(viewer);
        viewerThread.run();
    }

    private void setWindowParameters() {
        this.setVisible(true);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        new Restaurant();

    }

}
