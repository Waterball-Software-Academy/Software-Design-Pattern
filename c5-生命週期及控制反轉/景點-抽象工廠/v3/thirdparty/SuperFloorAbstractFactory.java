package v3.thirdparty;

import v3.*;
import v3.factories.FloorAbstractFactory;

/**
 * @author johnny@waterballsa.tw
 */
public class SuperFloorAbstractFactory implements FloorAbstractFactory {
    @Override
    public Portal createPortal(Portable p1, Portable p2) {
        return new SuperPortal(BasePortal.makePortal(p1, p2));
    }

    @Override
    public Region createRegion(int number, Floor floor) {
        return new SuperRegion(new BaseRegion(number, floor));
    }

    @Override
    public Stage createStage(Region region) {
        return new SuperStage();
    }
}
