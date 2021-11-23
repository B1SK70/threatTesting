package restaurant;

public class Client implements Runnable {

    private Table table;
    public String status = "waiting";

    public Client(Table TableResponsable) {
        table = TableResponsable;
    }

    @Override
    public void run() {
        while (true) {
            try {
                table.takeMeal();
            } catch (Exception e) {
                System.out.println(e);
            }

            eat();
        }
    }

    private void eat() {
        status = "eating";
        sleepFor(3500 + (int) (Math.random() * 3000));
        status = "waiting";

        //DIGESTION TIME
        sleepFor(99);
    }

    private static void sleepFor(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
