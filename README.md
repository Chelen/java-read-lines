# java-read-lines

Utility classes to get all lines matching a predicate from files in a given path.

### Usage:

```java
getAllLinesFromPath("src/test/files", withNlPostcode());
```


### Classes:

 * FileUtils
 * CustomPredicates
 
 
### Explanation:

The example returns a map with file names as keys and a list of Strings (lines) as values. Custom predicates can be added in the CustomPredicates class

```java
/** Matches if String contains NL postcode. */
    public static Predicate<String> withNlPostcode() {
        final Pattern p = Pattern.compile("(NL-)?(\\d{4})\\s*([A-Z]{2})");
        return l -> p.matcher(l).find();
    }
```


### How the magic happens:

```java

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

```