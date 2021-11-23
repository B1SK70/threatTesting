package restaurant;

public class Table {

    public int availableMeals = 0;
    public int mealsCapacity = 10;

    synchronized public void placeMeal() throws InterruptedException {
        boolean placedMeal = false;

        while (!placedMeal) {
            if (availableMeals < mealsCapacity) {
                availableMeals++;
                notifyAll();
                placedMeal = true;
            } else {
                wait();
            }
        }
    }

    synchronized public void takeMeal() throws InterruptedException {
        boolean takenMeal = false;

        while (!takenMeal) {

            if (availableMeals > 0) {
                availableMeals--;
                notifyAll();
                takenMeal = true;
            } else {
                wait();
            }
        }
    }
}
