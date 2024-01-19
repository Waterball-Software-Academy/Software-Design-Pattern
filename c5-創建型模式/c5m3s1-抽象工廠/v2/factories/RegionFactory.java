package v2.factories;

import v2.Floor;
import v2.Region;

/**
 * @author johnny@waterballsa.tw
 */
public interface RegionFactory {
    Region createRegion(int number, Floor floor);
}
