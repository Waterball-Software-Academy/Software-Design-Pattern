package v2.defaults;

import v2.BaseStage;
import v2.Question;
import v2.Region;
import v2.Stage;
import v2.factories.StageFactory;

/**
 * @author johnny@waterballsa.tw
 */
public class BaseStageFactory implements StageFactory {
    @Override
    public Stage createStage(Region region) {
        return new BaseStage(region);
    }
}
