public class Task {
    private String title;
    private boolean isCompleted;
    public Task(String title)
    {
        this.title = title;
        this.isCompleted = false;
    }
    public String getTitle()
    {
        return title;
    }
    public boolean isCompleted()
    {
        return isCompleted;
    }
    public void markAsCompleted()
    {
        this.isCompleted = true;
    }
    public String toString()
    {
        return (isCompleted ? "[x]" :"[]")+title;
    }
}
