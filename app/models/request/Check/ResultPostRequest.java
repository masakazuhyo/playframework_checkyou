package models.request.Check;

import play.data.validation.*;

public class ResultPostRequest {

    @Constraints.Required
    @Constraints.Pattern("[\\w]+")  // a~z or A~Z or  _ or 1~9と同意
    @Constraints.MaxLength(15)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}