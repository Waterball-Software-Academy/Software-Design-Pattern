package v2.defaults;

import v2.BaseRegion;
import v2.Floor;
import v2.Region;
import v2.factories.StageFactory;
import v2.factories.RegionFactory;

/**
 * @author johnny@waterballsa.tw
 */
public class BaseRegionFactory implements RegionFactory {
    private final StageFactory stageFactory;

    public BaseRegionFactory(StageFactory stageFactory) {
        this.stageFactory = stageFactory;
    }

    @Override
    public Region createRegion(int number, Floor floor) {
        return new BaseRegion(number, floor, stageFactory);
    }
}
