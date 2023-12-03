import java.util.Scanner;

class AnalogClock {
    private int hours;
    private int minutes;

    public AnalogClock(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public void increaseHour() {
        hours = (hours + 1) % 12;
    }

    public void decreaseHour() {
        hours = (hours - 1 + 12) % 12;
    }

    public void increaseMinute() {
        minutes = (minutes + 1) % 60;
    }

    public void decreaseMinute() {
        minutes = (minutes - 1 + 60) % 60;
    }

    public void displayTime() {
        System.out.printf("Години: %02d Хвилини: %02d\n", hours, minutes);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Початковий час 12:00
        AnalogClock clock = new AnalogClock(12, 0);

        int choice;
        do {
            System.out.println("Оберіть операцію:");
            System.out.println("1. Збільшити годину");
            System.out.println("2. Зменшити годину");
            System.out.println("3. Збільшити хвилину");
            System.out.println("4. Зменшити хвилину");
            System.out.println("5. Показати час");
            System.out.println("0. Вийти");
            System.out.print("Ваш вибір: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    clock.increaseHour();
                    break;
                case 2:
                    clock.decreaseHour();
                    break;
                case 3:
                    clock.increaseMinute();
                    break;
                case 4:
                    clock.decreaseMinute();
                    break;
                case 5:
                    clock.displayTime();
                    break;
                case 0:
                    System.out.println("Програма завершена.");
                    break;
                default:
                    System.out.println("Неправильний вибір. Спробуйте ще раз.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }
}
