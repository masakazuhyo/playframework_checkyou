package models.entity;

import javax.persistence.*;
import play.db.ebean.*;
import play.data.validation.*;
import java.util.Date;
import play.data.format.*;
import models.service.CheckModelService;
import play.libs.F.Option;
import java.util.*;

@Entity
public class Check extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String result;

    @Formats.DateTime(pattern="yyyy/MM/dd")
    public Date created;

    @Formats.DateTime(pattern="yyyy/MM/dd")
    public Date modified;

    @Transient  // 永続化しないフィールドを定義。Transient付けないとDBのフィールドとして処理されようとしてエラーになる
    private CheckModelService checkModelService = new CheckModelService();


    // コンストラクタ設定
    public Check() {}

    public Check(String name) {
        this.name = name;
    }

    // 検索用オブジェクト生成のためのコンストラクタ
    /*
      使用例. id=10のデータを取り出す
      Check check = new Check(10).method();
    */
    public Check(Long id) {
        this.id = id;
    }

    public Check(String name, String result) {
        this.name = name;
        this.result = result;
        this.created = new Date();
        this.modified = new Date();
    }

    public String result() {
        // TODO: 返り値、返り値型後で決める
        // TODO: DB処理など
          return "TODO";
      }


      // 結果を保存
    public Option<Check> store() {
        return checkModelService.save(this);
    }

      // idに該当するものを検索
      public Option<Check> unique() {
          return checkModelService.findById(id);
      }

      public static Finder<Long, Check> find =
    		    new Finder<Long, Check>(Long.class, Check.class);

     @Override
     public String toString() {
  	    return ("[id:" + id + ", name:" + name + ",result:" + result +
    		   ",created:" + created + ",modified:" + modified + "]");
     }

     public Option<List<Check>> entitiesList(Integer page) {
    	 return checkModelService.findWithPage(page);
     }

     public Integer entitiesMaxPage(Integer value) {
         // CheckModelServiceのgetMaxPage呼び出し。最大ページ数取得できない場合、valueを返す
    	 if(checkModelService.getMaxPage().isDefined()){
    		 return checkModelService.getMaxPage().get();
    	 }else{
    		 return value;
    	 }
     }


}