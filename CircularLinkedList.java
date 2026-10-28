
import java.util.Scanner;

class Player {
    String name;
    Player next;

    Player(String name) {
        this.name = name;
        this.next = null;
    }
}

class CircularPlayerList {
    private Player head;
    private Player tail;
    private Player current;

    public void insertPlayer(String name) {
        Player newPlayer = new Player(name);

        if (head == null) {
            head = tail = current = newPlayer;
            tail.next = head;
        } else {
            tail.next = newPlayer;
            tail = newPlayer;
            tail.next = head;
        }

        System.out.println(name + " added to the game.");
    }

    public void deletePlayer(String name) {
        if (head == null) {
            System.out.println("No players in the game.");
            return;
        }

        Player previous = tail;
        Player temp = head;

        do {
            if (temp.name.equalsIgnoreCase(name)) {
                if (temp == head && temp == tail) {
                    head = tail = current = null;
                } else {
                    if (temp == head) {
                        head = head.next;
                        tail.next = head;
                    } else if (temp == tail) {
                        previous.next = head;
                        tail = previous;
                    } else {
                        previous.next = temp.next;
                    }

                    if (current == temp) {
                        current = temp.next;
                    }
                }

                System.out.println(name + " removed from the game.");
                return;
            }

            previous = temp;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Player not found.");
    }

    public void displayPlayers() {
        if (head == null) {
            System.out.println("No players in the game.");
            return;
        }

        Player temp = head;
        System.out.println("\nPlayers in the circular list:");

        do {
            System.out.print(temp.name + " -> ");
            temp = temp.next;
        } while (temp != head);

        System.out.println("(back to " + head.name + ")");
    }

    public void nextTurn() {
        if (current == null) {
            System.out.println("No players available.");
            return;
        }

        System.out.println("Current turn: " + current.name);
        current = current.next;
    }
}

public class CircularLinkedList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularPlayerList list = new CircularPlayerList();
        int choice = 0;

        do {
            System.out.println("\n===== CIRCULAR PLAYER GAME =====");
            System.out.println("1. Insert Player");
            System.out.println("2. Delete Player");
            System.out.println("3. Display Players");
            System.out.println("4. Next Turn");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter player name: ");
                    String name = sc.nextLine().trim();

                    if (name.isEmpty()) {
                        System.out.println("Player name cannot be empty.");
                    } else {
                        list.insertPlayer(name);
                    }
                    break;

                case 2:
                    System.out.print("Enter player name to delete: ");
                    String deleteName = sc.nextLine().trim();
                    list.deletePlayer(deleteName);
                    break;

                case 3:
                    list.displayPlayers();
                    break;

                case 4:
                    list.nextTurn();
                    break;

                case 5:
                    System.out.println("Game ended.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
