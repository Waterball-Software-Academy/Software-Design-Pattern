package v2;

import v2.defaults.BasePortalFactory;
import v2.defaults.BaseRegionFactory;
import v2.defaults.BaseStageFactory;
import v2.factories.PortalFactory;
import v2.factories.RegionFactory;
import v2.factories.StageFactory;
import v2.thirdparty.SuperPortalFactory;
import v2.thirdparty.SuperRegionFactory;
import v2.thirdparty.SuperStageFactory;

/**
 * @author johnny@waterballsa.tw
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        StageFactory baseStageFactory = new BaseStageFactory();
        PortalFactory basePortalFactory = new BasePortalFactory();
        RegionFactory baseRegionFactory = new BaseRegionFactory(baseStageFactory);
        SuperStageFactory superStageFactory = new SuperStageFactory();
        SuperRegionFactory superRegionFactory = new SuperRegionFactory(superStageFactory);
        SuperPortalFactory superPortalFactory = new SuperPortalFactory();

        TopFloor topFloor = new TopFloor(game, baseRegionFactory, basePortalFactory);
        Floor f3 = new Floor(game, "Super Floor 3", topFloor, superRegionFactory, superPortalFactory);
        Floor f2 = new Floor(game, "Super Floor 2", f3, superRegionFactory, superPortalFactory);
        Floor f1 = new Floor(game, "Floor 1", f2, baseRegionFactory, basePortalFactory);

        game.setFloor1(f1);
        game.start();
    }
}
