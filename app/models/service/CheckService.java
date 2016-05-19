package models.service;

import models.utils.ConfigUtil;
import models.utils.OptionUtil;
import java.util.Collections;
import java.util.List;
import static play.libs.F.*;


public class CheckService {

	  // TODO: 診断結果取得
	public Option<String> getResult(String name) {
        // 診断リスト取得（診断リストが存在しない場合、"2.1.3 Java"を返す）
        // Collectionsクラスのshuffleメソッドでランダムに診断リスト並び替え

		Option<List<String>> list = ConfigUtil.getByList("checkyou.setting.answer");
		if(list.isDefined()){
			List<String> versions = list.get();
			Collections.shuffle(versions);
			return getResultText(name, versions.get(0));
		}
		return getResultText(name, "2.1.3 Java");
    }


    // 取得した診断結果を整形（nameとversionはそのまま使用可、連結した文字列はtoString()メソッド使用）
    // 診断結果
    // 正常ケース：nameさんにオススメなPlay frameworkのバージョンは、versionです。
    // 異常ケース①：name-versionです。
    // 異常ケース②：nameさんにオススメなPlay frameworkのバージョンは、version.
    // 異常ケース③：name-version.
    private Option<String> getResultText(String name, String version) {
        StringBuilder result = new StringBuilder();
        result.append(name);
        result.append(ConfigUtil.get("checkyou.setting.message.result").getOrElse("-"));
        result.append(version);
        result.append(ConfigUtil.get("checkyou.setting.message.resultSuffix").getOrElse("."));
        System.out.println(result);
        return OptionUtil.apply(result.toString());
    }
}
