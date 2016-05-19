package controllers;


import play.mvc.*;

public class Application extends Controller {

    public static Result index() {
    	return TODO;
    }

    public static Result fail(Call call, String flashKey, String flashMessage) {
        ctx().flash().put(flashKey, flashMessage);
        return redirect(call);
    }

}