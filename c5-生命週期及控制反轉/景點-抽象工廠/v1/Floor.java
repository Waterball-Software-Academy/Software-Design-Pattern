package v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.min;
import static java.util.Collections.shuffle;

/**
 * @author johnny@waterballsa.tw
 */
public class Floor implements Portable {
    private final static Random RANDOM = new Random();

    protected final Floor nextFloor;

    protected final int maxRegions;
    protected final List<Region> regions = new ArrayList<>();
    protected final Region initialRegion;
    protected final Game game;
    protected final String name;

    public Floor(Game game, String name, Floor nextFloor) {
        this(game, name, nextFloor, 5);
    }

    public Floor(Game game, String name, Floor nextFloor, int maxRegions) {
        this.game = game;
        this.name = name;
        this.nextFloor = nextFloor;
        this.initialRegion = spawnNewRegion();
        if (maxRegions == 0) {
            throw new IllegalStateException("#maxRegions cannot less than 0");
        }
        this.maxRegions = maxRegions;
    }

    @Override
    public void access(Player player) {
        System.out.printf("Enter %s%n", this.getName());
        player.setCurrentFloor(this);
        initialRegion.access(player);
        while (!game.isGameOver() && player.isAt(this)) {
            System.out.printf("==== [%s] %s ====%n", getName(), player.getCurrentRegion().getName());
            if (player.isAlive()) {
                // 隨機發生三件事之一
                handlePostStageEvents(player);
                Portal portal = player.selectPortal(player.getCurrentRegion().getPortals());
                portal.access(player);
            } else {
                game.over();
            }
        }
    }

    private void handlePostStageEvents(Player player) {
        if (!hasSpaceForMorePortal()) {
            randomlyDestroyOnePortal();
        }

        // 視情況和機率做選擇，遵守限制條件。
        int choice = regions.size() == 1 ? (RANDOM.nextBoolean() ? 1 : 2) :
                maxRegions == regions.size() ? (RANDOM.nextBoolean() ? 0 : 2) :
                        RANDOM.nextInt(3);

        switch (choice) {
            // 生成一盞傳送門 (spawn a portal)，連結隨機兩個地區之間。
            case 0 -> Portal.makePortal(randomlyPickRegionsHaveSpaceForPortals(2));
            // 生成一個新地區，再生成一盞傳送門 (spawn a portal) 連接玩家目前所在地區與此生成地區之間。
            case 1 -> Portal.makePortal(player.getCurrentRegion(), spawnNewRegion());
            // 隨機在一地區中，生成通往下一樓層的傳送門
            case 2 -> Portal.makePortal(
                    randomlyPickRegionsHaveSpaceForPortals(1).get(0),
                    nextFloor);
        }
    }

    private void randomlyDestroyOnePortal() {
        List<Portal> portals = regions.stream()
                .flatMap(r -> r.getPortals().stream()).toList();
        Portal portal = portals.get(RANDOM.nextInt(portals.size()));
        portal.destroy();
    }

    private boolean hasSpaceForMorePortal() {
        return regions.stream()
                .anyMatch(Region::hasSpaceForMorePortals);
    }

    private List<Region> randomlyPickRegionsHaveSpaceForPortals(int regions) {
        List<Region> temp = new ArrayList<>(this.regions.stream()
                .filter(Region::hasSpaceForMorePortals).toList());
        shuffle(temp);
        return temp.subList(0, min(temp.size(), regions));
    }

    private Region spawnNewRegion() {
        Region newRegion = new Region(regions.size(), this);
        regions.add(newRegion);
        System.out.printf("New region: %s%n", newRegion.getName());
        return newRegion;
    }

    @Override
    public String getName() {
        return name;
    }
}
