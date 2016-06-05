package postcodes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Files util.
 */
public final class FilesUtil {

    private static final  Map<String, List<String>> map = new HashMap<>();

    /**
     * Retrieves all lines matching the predicate from files in given directory.
     *
     * @return  A Map with file names as keys and a list of lines as values.
     */
    public static Map<String, List<String>> getAllLinesFromPath(final String pathName, final Predicate<String> predicate) {
        try {
            Files.walk(Paths.get(pathName))
                .filter(p -> !p.toFile().isDirectory())
                .forEach(p -> addLines(p, predicate));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    private static void addLines(Path file, Predicate<String> p) {
        List<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(file)) {
            lines = stream
                    .filter(p)
                    .collect(toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!lines.isEmpty()) map.put(file.toFile().getName(), lines);
    }
}
