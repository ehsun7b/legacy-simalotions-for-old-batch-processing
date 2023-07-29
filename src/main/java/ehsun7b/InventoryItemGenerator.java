package ehsun7b;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

public class InventoryItemGenerator implements Runnable {
    private static final Logger logger = Logger.getLogger(InventoryItemGenerator.class.getSimpleName());
    private final Path path;
    private final Random rnd;
    private Boolean shouldStop = false;
    private List<Integer> ids;

    public InventoryItemGenerator(String path) {
        this.path = Paths.get(path);
        this.rnd = new Random(System.currentTimeMillis());
        this.ids = new ArrayList<>();
        logger.log(INFO, "InventoryItemGenerator created for path: " + path);
    }

    @Override
    public void run() {
        while (!shouldStop) {
            var seconds = getRandomInt(10, 40);
            logger.log(INFO, "Going to wait for " + seconds + " seconds ...");
            sleepSeconds(seconds);
            int count = getRandomInt(20, 200);
            logger.log(INFO, "Generating a file with " + count + " inventory items ...");
            var inventoryItems = generateInventoryItems(count);
            try {
                var file = writeToFile(inventoryItems);
                logger.log(INFO, "File " + file + " saved");
            } catch (IOException e) {
                logger.log(SEVERE, e.getMessage());
            }
        }
    }

    public String writeToFile(List<String> inventoryItems) throws IOException {
        Random random = new Random();
        String fileName = "inventory_" + random.nextInt(1000) + ".txt";
        Path filePath = path.resolve(fileName);

        Files.write(filePath, inventoryItems);
        return fileName;
    }

    private List<String> generateInventoryItems(int count) {
        var list = new ArrayList<String>(count);
        for (int i = 0; i < count; i++) {
            var item = generateInventoryItem();
            list.add(item);
        }
        return list;
    }

    private String generateInventoryItem() {
        final Integer id = getRandomInt(2000, 3000);

        if (ids.contains(id)) {
            // generate a delete or update item record
            if (rnd.nextBoolean()) {
                // delete record
                ids.remove(id);
                return "D," + id;
            } else {
                // generate an update item record
                final var ig = new ItemGenerator(rnd);
                final String itemName = ig.generateItemName();
                final String itemDescription = ig.generateDescription();
                final int itemStock = ig.generateStock();

                ids.add(id);
                return String.format("%d,%s,%s,%d", id, itemName, itemDescription, itemStock);
            }
        } else {
            // generate an insert item record
            final var ig = new ItemGenerator(rnd);
            final String itemName = ig.generateItemName();
            final String itemDescription = ig.generateDescription();
            final int itemStock = ig.generateStock();

            ids.add(id);
            return String.format("%d,%s,%s,%d", id, itemName, itemDescription, itemStock);
        }
    }

    private void sleepSeconds(int secondsToWait) {
        try {
            Thread.sleep(secondsToWait * 1000);
        } catch (InterruptedException e) {
            logger.log(SEVERE, e.getMessage());
        }
    }

    public void stop() {
        this.shouldStop = true;
    }

    private int getRandomInt(int min, int max) {
        return rnd.nextInt(max - min) + min;
    }
}
