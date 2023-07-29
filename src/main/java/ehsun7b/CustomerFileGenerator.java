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

public class CustomerFileGenerator implements Runnable {
    private static final Logger logger = Logger.getLogger(CustomerFileGenerator.class.getSimpleName());
    private final Path path;
    private final Random rnd;
    private Boolean shouldStop = false;
    private List<Integer> ids;

    public CustomerFileGenerator(String path) {
        this.path = Paths.get(path);
        this.rnd = new Random(System.currentTimeMillis());
        this.ids = new ArrayList<>();
        logger.log(INFO, "CustomerFileGenerator created for path: " + path);
    }

    @Override
    public void run() {
        while (!shouldStop) {
            var seconds = getRandomInt(10, 40);
            logger.log(INFO, "Going to wait for " + seconds + " seconds ...");
            sleepSeconds(seconds);
            int count = getRandomInt(20, 200);
            logger.log(INFO, "Generating a file with " + count + " records ...");
            var records = generateRecords(count);
            try {
                var file = writeToFile(records);
                logger.log(INFO, "File " + file + " saved");
            } catch (IOException e) {
                logger.log(SEVERE, e.getMessage());
            }
        }
    }


    public String writeToFile(List<String> records) throws IOException {
        Random random = new Random();
        String fileName = "customer_" + random.nextInt(1000) + ".txt";
        Path filePath = path.resolve(fileName);

        Files.write(filePath, records);
        return fileName;
    }

    private List<String> generateRecords(int count) {
        var list = new ArrayList<String>(count);
        for (int i = 0; i < count; i++) {
            var record = generateRecord();
            list.add(record);
        }
        return list;
    }

    private String generateRecord() {
        final Integer id = getRandomInt(1, 1000);

        if (ids.contains(id)) {
            // generate a delete record
            ids.remove(id);
            return "*," + id;
        } else {
            // generate a insert record
            final var ng = new NameGenerator(rnd);
            final var ag = new AddressGenerator(rnd);
            ids.add(id);
            return String.format("%d,%s,%s", id, ng.generateFullName(), ag.generateAddress());
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
