™import java.util.*;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

public class JavaInMemoryBenchmark {

    private static final int NUM_OPERATIONS = 1000;

    public static void main(String[] args) {
        benchmarkString();
        benchmarkList();
        benchmarkSet();
        benchmarkSortedSet();
        benchmarkHash();
        benchmarkBitmap();
        benchmarkHyperLogLog();
        benchmarkGeoSpatial();
        benchmarkStream();
    }

    private static void benchmarkString() {
        System.out.println("=== String Benchmark ===");
        Map<String, String> map = new HashMap<>();
        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                map.put("key" + i, "value" + i);
            }
        }, "Insert");

        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                map.get("key" + i);
            }
        }, "Retrieve");

        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                map.remove("key" + i);
            }
        }, "Delete");
    }

    private static void benchmarkList() {
        System.out.println("=== List Benchmark ===");
        List<String> list = new ArrayList<>();
        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                list.add("value" + i);
            }
        }, "Insert");

        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                list.get(i);
            }
        }, "Retrieve");

        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                list.remove(list.size() - 1);
            }
        }, "Delete");
    }

    private static void benchmarkSet() {
        System.out.println("=== Set Benchmark ===");
        Set<String> set = new HashSet<>();
        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                set.add("value" + i);
            }
        }, "Insert");

        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                set.contains("value" + i);
            }
        }, "Retrieve");

        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                set.remove("value" + i);
            }
        }, "Delete");
    }

    private static void benchmarkSortedSet() {
        System.out.println("=== Sorted Set Benchmark ===");
        SortedSet<Integer> sortedSet = new TreeSet<>();
        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                sortedSet.add(ThreadLocalRandom.current().nextInt());
            }
        }, "Insert");

        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                sortedSet.contains(ThreadLocalRandom.current().nextInt());
            }
        }, "Retrieve");

        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                ((TreeSet<Integer>) sortedSet).pollFirst();
            }
        }, "Delete");
    }

    private static void benchmarkHash() {
        System.out.println("=== Hash Benchmark ===");
        Map<String, String> hash = new HashMap<>();
        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                hash.put("field" + i, "value" + i);
            }
        }, "Insert");

        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                hash.get("field" + i);
            }
        }, "Retrieve");

        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                hash.remove("field" + i);
            }
        }, "Delete");
    }

    private static void benchmarkBitmap() {
        System.out.println("=== Bitmap Benchmark ===");
        BitSet bitSet = new BitSet();
        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                bitSet.set(i);
            }
        }, "Insert");

        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                bitSet.get(i);
            }
        }, "Retrieve");

        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                bitSet.clear(i);
            }
        }, "Delete");
    }

    private static void benchmarkHyperLogLog() {
        System.out.println("=== HyperLogLog Benchmark (Approximate) ===");
        Set<String> hyperLogLog = new HashSet<>();
        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                hyperLogLog.add("value" + i);
            }
        }, "Insert");
    }

    private static void benchmarkGeoSpatial() {
        System.out.println("=== GeoSpatial Benchmark ===");
        Map<String, double[]> geoData = new HashMap<>();
        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                geoData.put("location" + i, new double[]{Math.random() * 180 - 90, Math.random() * 360 - 180});
            }
        }, "Insert");

        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                geoData.get("location" + i);
            }
        }, "Retrieve");
    }

    private static void benchmarkStream() {
        System.out.println("=== Stream Benchmark ===");
        Queue<String> stream = new LinkedList<>();
        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                stream.add("message" + i);
            }
        }, "Insert");

        measureTime(() -> {
            for (int i = 0; i < NUM_OPERATIONS; i++) {
                stream.poll();
            }
        }, "Retrieve");
    }

    private static void measureTime(Runnable task, String operation) {
        Instant start = Instant.now();
        task.run();
        Instant end = Instant.now();
        System.out.println(operation + " Time: " + Duration.between(start, end).toMillis() + " ms");
    }
}
