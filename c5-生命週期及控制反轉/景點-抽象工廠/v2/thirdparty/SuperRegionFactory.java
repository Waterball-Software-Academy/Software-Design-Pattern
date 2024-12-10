package v2.thirdparty;

import v2.BaseRegion;
import v2.Floor;
import v2.Region;
import v2.factories.StageFactory;
import v2.factories.RegionFactory;

/**
 * @author johnny@waterballsa.tw
 */
public class SuperRegionFactory implements RegionFactory {
    private final StageFactory stageFactory;

    public SuperRegionFactory(StageFactory stageFactory) {
        this.stageFactory = stageFactory;
    }

    @Override
    public Region createRegion(int number, Floor floor) {
        return new SuperRegion(new BaseRegion(number, floor, stageFactory));
    }
}
