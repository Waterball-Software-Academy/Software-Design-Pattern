package v3.thirdparty;

import v3.*;

import java.util.List;

import static java.util.Collections.emptyList;

/**
 * @author johnny@waterballsa.tw
 */
public class SuperStage extends Stage {
    @Override
    protected List<Question> loadQuestions() {
        return loadQuestionViaAPI("https://super.intel.tw/api");
    }

    protected List<Question> loadQuestionViaAPI(String api) {
        // 假裝是從小華服務的 API 中讀取
        if (api.equals("https://super.intel.tw/api")) {
            return List.of(new Question("水球潘院長幾歲？", "26"),
                    new Question("小華智慧的理想是什麼？", "征服世界"),
                    new Question("小華智慧最得意的作法是什麼？", "Super Crawler"),
                    new Question("小華最喜歡的設計模式是什麼？", "Adapter Pattern")
            );
        }
        return emptyList();
    }

}
