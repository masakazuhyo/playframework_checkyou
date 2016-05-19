package models.service;

import play.db.ebean.*;
import play.libs.F.Option;
import java.util.*;


//モデル向けサービスのインターフェース
public interface ModelService<T extends Model> {

// TODO: メソッド要検討
	public Option<T> findById(Long id);
    public Option<T> save(T entry);
    public Option<List<T>> findWithPage(Integer pageSource);
}