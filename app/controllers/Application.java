package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    // 診断失敗時に呼び出されるコントローラ
    public static Result fail(Call call, String flashKey, String flashMessage) {
        ctx().flash().put(flashKey, flashMessage);
        return redirect(call);
    }

 // TODOをつくる

}
