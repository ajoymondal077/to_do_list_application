import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList(); // ToDoList now interacts with MySQL
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.println("\n1. Add task\n2. Mark task as completed\n3. Show tasks\n4. Exit");
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    toDoList.addTask(title); // Stores task in the database
                    break;
                case "2":
                    toDoList.showTasks(); // Shows tasks from the database
                    System.out.print("Enter task number (ID) to mark as completed: ");
                    int taskId = Integer.parseInt(scanner.nextLine());
                    toDoList.markTaskAsCompleted(taskId); // Marks task as completed in the database
                    break;
                case "3":
                    toDoList.showTasks(); // Fetches and displays tasks from the database
                    break;
                case "4":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (!command.equals("4"));

        scanner.close();
    }
}
