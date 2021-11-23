package restaurant;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Viewer extends Canvas implements Runnable {

    private ArrayList<Chef> chefs = new ArrayList<Chef>();
    private ArrayList<Client> clients = new ArrayList<Client>();
    private Table table;

    private BufferedImage asleepStatus;
    private BufferedImage cookingChef;
    private BufferedImage eatingClient;
    private BufferedImage emptyPlate;
    private BufferedImage foodPlate;

    public Viewer() {
        setBackground(Color.white);
        setSize(500, 500);

        setSource();
    }

    public void addChef(Chef chef) {
        chefs.add(chef);
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void addTable(Table table) {
        this.table = table;
    }

    public void paint(Graphics g) {

        //DRAW CHEFS
        for (int chefN = 0; chefN < chefs.size(); chefN++) {
            if (chefs.get(chefN).status == "waiting") {
                g.drawImage(asleepStatus, 10 + (chefN * 75), 50, null);

            } else {
                g.drawImage(cookingChef, 10 + (chefN * 75), 50, null);
            }
        }

        //DRAW CLIENTS
        for (int clientN = 0; clientN < clients.size(); clientN++) {
            if (clients.get(clientN).status == "waiting") {
                g.drawImage(asleepStatus, 10 + (clientN * 75), 225, null);

            } else {
                g.drawImage(eatingClient, 10 + (clientN * 75), 225, null);
            }
        }

        //PRINT TABLE
        for (int plate = 0; plate < table.mealsCapacity; plate++) {
            if (plate >= table.availableMeals) {
                g.drawImage(emptyPlate, 10 + (plate * 45), 150, this);

            } else {
                g.drawImage(foodPlate, 10 + (plate * 45), 150, this);
            }
        }
    }

    private void setSource() {

        String imgRoute = "src/img/";
        try {
            asleepStatus = ImageIO.read(new File(imgRoute + "asleepIcon.png"));
            emptyPlate = ImageIO.read(new File(imgRoute + "emptyPlate.png"));
            foodPlate = ImageIO.read(new File(imgRoute + "foodPlate.png"));
            cookingChef = ImageIO.read(new File(imgRoute + "cookingChef.png"));
            eatingClient = ImageIO.read(new File(imgRoute + "eatingClient.png"));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            this.paint(this.getGraphics());
            try {
                sleep(30);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
