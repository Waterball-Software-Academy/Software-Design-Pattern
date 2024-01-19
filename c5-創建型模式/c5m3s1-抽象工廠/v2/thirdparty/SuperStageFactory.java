package v2.thirdparty;

import v2.Region;
import v2.Stage;
import v2.factories.StageFactory;

/**
 * @author johnny@waterballsa.tw
 */
public class SuperStageFactory implements StageFactory {
    @Override
    public Stage createStage(Region region) {
        return new SuperStage();
    }
}
