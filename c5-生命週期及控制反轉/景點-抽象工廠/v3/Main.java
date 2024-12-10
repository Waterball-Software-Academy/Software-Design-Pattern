package v3;

import v2.defaults.BasePortalFactory;
import v2.defaults.BaseRegionFactory;
import v2.defaults.BaseStageFactory;
import v2.thirdparty.SuperPortalFactory;
import v2.thirdparty.SuperRegionFactory;
import v2.thirdparty.SuperStageFactory;
import v3.defaults.BaseFloorAbstractFactory;
import v3.factories.FloorAbstractFactory;
import v3.thirdparty.SuperFloorAbstractFactory;

/**
 * @author johnny@waterballsa.tw
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        FloorAbstractFactory baseFactory = new BaseFloorAbstractFactory();
        FloorAbstractFactory superFactory = new SuperFloorAbstractFactory();

        TopFloor topFloor = new TopFloor(game, baseFactory);
        Floor f3 = new Floor(game, "Super Floor 3", topFloor, superFactory);
        Floor f2 = new Floor(game, "Super Floor 2", f3, superFactory);
        Floor f1 = new Floor(game, "Floor 1", f2, baseFactory);

        game.setFloor1(f1);
        game.start();
    }
}
