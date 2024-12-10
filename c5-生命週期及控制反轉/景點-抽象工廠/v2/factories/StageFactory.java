package v2.factories;

import v2.Region;
import v2.Stage;

/**
 * @author johnny@waterballsa.tw
 */
public interface StageFactory {
    Stage createStage(Region region);
}
