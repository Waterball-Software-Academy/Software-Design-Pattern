package v3.defaults;

import v3.*;
import v3.factories.FloorAbstractFactory;

/**
 * @author johnny@waterballsa.tw
 */
public class BaseFloorAbstractFactory implements FloorAbstractFactory {

    @Override
    public Portal createPortal(Portable p1, Portable p2) {
        return BasePortal.makePortal(p1, p2);
    }

    @Override
    public Region createRegion(int number, Floor floor) {
        return new BaseRegion(number, floor);
    }

    @Override
    public Stage createStage(Region region) {
        return new BaseStage(region);
    }
}
