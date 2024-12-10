package v3.factories;

import v3.*;

/**
 * @author johnny@waterballsa.tw
 */
public interface FloorAbstractFactory {
    Portal createPortal(Portable p1, Portable p2);
    Region createRegion(int number, Floor floor);
    Stage createStage(Region region);
}
