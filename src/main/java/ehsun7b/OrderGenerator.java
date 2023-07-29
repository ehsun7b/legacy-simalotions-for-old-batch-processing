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

public class OrderGenerator implements Runnable {
    private static final Logger logger = Logger.getLogger(OrderGenerator.class.getSimpleName());
    private final Path path;
    private final Random rnd;
    private Boolean shouldStop = false;
    private List<Integer> orderIds;

    public OrderGenerator(String path) {
        this.path = Paths.get(path);
        this.rnd = new Random(System.currentTimeMillis());
        this.orderIds = new ArrayList<>();
        logger.log(INFO, "OrderGenerator created for path: " + path);
    }

    @Override
    public void run() {
        while (!shouldStop) {
            var seconds = getRandomInt(10, 40);
            logger.log(INFO, "Going to wait for " + seconds + " seconds ...");
            sleepSeconds(seconds);
            int count = getRandomInt(20, 200);
            logger.log(INFO, "Generating a file with " + count + " order records ...");
            var orderRecords = generateOrderRecords(count);
            try {
                var file = writeToFile(orderRecords);
                logger.log(INFO, "File " + file + " saved");
            } catch (IOException e) {
                logger.log(SEVERE, e.getMessage());
            }
        }
    }

    public String writeToFile(List<String> orderRecords) throws IOException {
        Random random = new Random();
        String fileName = "order_" + random.nextInt(1000) + ".txt";
        Path filePath = path.resolve(fileName);

        Files.write(filePath, orderRecords);
        return fileName;
    }

    private List<String> generateOrderRecords(int count) {
        var list = new ArrayList<String>(count);
        for (int i = 0; i < count; i++) {
            var orderRecord = generateOrderRecord();
            list.add(orderRecord);
        }
        return list;
    }

    private String generateOrderRecord() {
        final Integer orderId = getRandomInt(4000, 5000);
        final Integer customerId = getRandomInt(1, 1000);
        final Integer itemId = getRandomInt(2000, 3000);
        final Integer count = getRandomInt(1, 20);

        if (orderIds.contains(orderId)) {
            if (rnd.nextBoolean()) {
                // generate a delete order record
                orderIds.remove(orderId);
                return orderId + "";
            } else {
                // generate an update order record
                orderIds.add(orderId);
                return String.format("%d,%d,%d,%d", orderId, customerId, itemId, count);
            }
        } else {
            // generate an insert order record
            orderIds.add(orderId);
            return String.format("%d,%d,%d,%d", orderId, customerId, itemId, count);
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


