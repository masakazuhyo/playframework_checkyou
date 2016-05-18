package controllers;

import java.util.List;

import models.entity.Check;
import models.request.Check.ResultPostRequest;
import play.*;
import play.data.Form;
import play.libs.F.Option;
import play.mvc.*;
import utils.ConfigUtil;
import views.html.*;

public class ChecksController extends Application {

  public static Result index() {
	  Form<ResultPostRequest> form = Form.form(ResultPostRequest.class);
	  return ok(views.html.check.index.render("hoge","hoge",form));
 // 要実装
    // 文字列は、application.confで設定したcheckyou.setting.message.title、checkyou.setting.message.questionを渡す
  }
  public static Result result() {
      Form<ResultPostRequest> form = Form.form(ResultPostRequest.class).bindFromRequest();

      //　バリデーションチェック
      // error.required
      if(form.error("name") != null && form.error("name").message().contains("error.required")) {
          return validationErrorIndexResult("名前欄が空です", form);
      }
      if(form.error("name") != null && form.error("name").message().contains("error.pattern")) {
          return validationErrorIndexResult("Twitter id形式ではありません", form);
      }
      if(form.error("name") != null && form.error("name").message().contains("error.maxLength")) {
          return validationErrorIndexResult("文字数が15文字を超えています", form);
      }
      // error.pattern
      // 要実装（返す文言："Twitter id形式ではありません")

      // error.maxLength
      // 要実装（返す文言："文字数が15文字を超えています")


      // 要実装
      // TwitterID取得
      // TwitterIDの作成したCheckインスタンス保存
      // 保存したOption型のCheckインスタンスが存在する場合、「Twitter名 +  checkYou.setting.message.resultTitle」, 保存したCheckインスタンスを返す
      // 保存したOption型のCheckインスタンスが存在しない場合、Applicationクラスのfail呼び出し（redirect先：indexビュー、key:"error", message: "診断エラーです"
      return TODO;
  }

  private static Object form(Class<ResultPostRequest> class1) {
	// TODO 自動生成されたメソッド・スタブ
	return null;
}
// バリデーションエラーメッセージを表示し、トップページへ戻る
  private static Result validationErrorIndexResult(String message, Form<ResultPostRequest> form) {
    flash("error", message);
    return badRequest(views.html.check.index.render(
        ConfigUtil.get("checkyou.setting.message.title").getOrElse(""),
        ConfigUtil.get("checkyou.setting.message.question").getOrElse(""),
        form));
  }


  // 診断結果一覧
  public static boolean recent(Integer page) {
      Option<List<Check>> result = new Check().entitiesList(page);
      Integer maxPage            = new Check().entitiesMaxPage(1);

      // 最大ページ数取得できない場合、valueを返す
      // isDefined()で判定しなくても診断件数０でもresultはtrueになるのでget()で取得してサイズ判定
      // ★下記、OptionUtilに判定ロジック追加してそちらでsizeチェック

		return result.get().size() != 0;
//				有件データをrecentビューに返す : checkyou.setting.message.recentTitle,
//				checkyou.setting.message.recentDescriptionNoneをrecentEmptyビューに返す
  }

  // 診断結果ページシェア用
  public static Result resultId(Long id) {
      Option<Check> check        = new Check(id).unique();
      return check.isDefined() ?
          ok(result.render(
                  check.get().name
                  + ConfigUtil.get("checkYou.setting.message.resultTitle").getOrElse(""), check.get()))
              : Application.fail(routes.ChecksController.index(), "error", "診断エラーです");
  }
}

