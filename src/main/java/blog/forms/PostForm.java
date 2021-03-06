package blog.forms;

import javax.validation.constraints.Size;

public class PostForm {

    @Size(min=5, max=100, message = "Title must be more than 5 and smaller than 100 characters!")
    private String title;

    @Size(min=5, message = "Body must be more than 5 characters!")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
