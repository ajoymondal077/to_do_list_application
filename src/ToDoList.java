import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ToDoList {
    private Connection connect() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/to_do_list_db";
        String username = "root";
        String password = "Ajoy@678";
        return DriverManager.getConnection(url,username,password);
    }
    public void addTask(String title)
    {
        String query = "INSERT INTO tasks (title) VALUES (?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.executeUpdate();
            System.out.println("Task added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void markTaskAsCompleted(int taskId) {
        String query = "UPDATE tasks SET is_completed = TRUE WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, taskId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Task marked as completed.");
            } else {
                System.out.println("Task not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void showTasks() {
        String query = "SELECT * FROM tasks";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            List<Task> tasks = new ArrayList<>();
            while (rs.next()) {
                Task task = new Task(rs.getString("title"));
                if (rs.getBoolean("is_completed")) {
                    task.markAsCompleted();
                }
                tasks.add(task);
                System.out.println(rs.getInt("id") + ". " + task);
            }
            if (tasks.isEmpty()) {
                System.out.println("No tasks found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
