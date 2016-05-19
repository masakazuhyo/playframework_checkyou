package models.service;

import models.entity.*;
import play.libs.F.Option;
import models.utils.*;
import play.db.ebean.Model.Finder;
import java.util.*;

import com.avaje.ebean.PagingList;


public class CheckModelService implements ModelService<Check> {

	final static Integer LIMIT = 5;

    public static CheckModelService use() {
        return new CheckModelService();
    }



    @Override
    public Option<Check> findById(Long id) {
    	//Option<Check> check = OptionUtil.apply(Check.find.byId(id));
    	//rerutn check;
    	Finder<Long, Check> find = new Finder<Long, Check>(Long.class, Check.class);
        return OptionUtil.apply(find.byId(id));
    }


    @Override
    public Option<Check> save(Check entry) {
    	//Option<Check> check = OptionUtil.apply(entry);
    	//return check;
    	Option<Check>check  = OptionUtil.apply(entry);
    	if(check.isDefined()){
    		entry.save();
    		if(OptionUtil.apply(entry.id).isDefined()) {
    			return OptionUtil.apply(entry);
    		}
//    		return OptionUtil.apply(entry.id.);
    	}
    	return OptionUtil.none();
    }

    @Override
    public Option<List<Check>> findWithPage(Integer pageSource) {

        // Ebeanではページが0から始まるためページ番号調整
        Integer pageNum = (pageSource - 1 < 0)? 0 : pageSource - 1;
        // findPagingListを使用し, 指定したページ番号、指定ページ表示件数（LIMIT）、作成日昇順のOption<Check>のListを取得
        //List<Check> c = Check.find.orderBy("created desc").findPagingList(LIMIT).getPage(pageNum).getList();
        //return OptionUtil.apply(c);
        Finder<Long, Check> find = new Finder<Long, Check>(Long.class, Check.class);
        return OptionUtil.apply(find.orderBy("created desc").findPagingList(LIMIT).getPage(pageNum).getList());
    }

    public Option<Integer> getMaxPage() {
        Finder<Long, Check> find = new Finder<Long, Check>(Long.class, Check.class);
        PagingList<Check> pagingList = find.orderBy("created desc").findPagingList(LIMIT);
        return OptionUtil.apply(pagingList.getTotalPageCount());
        // getTotalPageCountを使用して最大ページ数取得
    }

}