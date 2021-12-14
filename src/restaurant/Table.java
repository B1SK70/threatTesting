package restaurant;

public class Table {

    public int availableMeals = 0;
    public int mealsCapacity = 10;

    synchronized public void placeMeal() throws InterruptedException {
        while (availableMeals >= mealsCapacity) {
            wait();
        }
        availableMeals++;
        notifyAll();
    }

    synchronized public void takeMeal() throws InterruptedException {
        while (availableMeals <= 0) {
            wait();
        }
        availableMeals--;
        notifyAll();
    }
}
