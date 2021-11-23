package restaurant;

public class Chef implements Runnable {

    private Table table;
    public String status = "waiting";

    public Chef(Table TableResponsable) {
        table = TableResponsable;
    }

    @Override
    public void run() {
        while (true) {
            cook();
            try {
                table.placeMeal();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void cook() {
        status = "cooking";
        sleepFor(3500 + (int) (Math.random() * 3000));
        status = "waiting";

        sleepFor(99);
    }

    private static void sleepFor(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
