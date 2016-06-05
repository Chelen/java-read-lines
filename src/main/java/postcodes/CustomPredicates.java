package postcodes;

import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Custom String Predicates.
 */
public class CustomPredicates {

    /** Matches if String contains NL postcode. */
    public static Predicate<String> withNlPostcode() {
        final Pattern p = Pattern.compile("(NL-)?(\\d{4})\\s*([A-Z]{2})");
        return l -> p.matcher(l).find();
    }

}
