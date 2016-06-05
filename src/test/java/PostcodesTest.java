import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static postcodes.CustomPredicates.withNlPostcode;
import static postcodes.FilesUtil.getAllLinesFromPath;

/**
 * Created by pablo on 5-6-16.
 */
public class PostcodesTest {

    @Test public void testNLPostcodePredicate(){
        assertTrue(withNlPostcode().test("with one postcode 2015AA"));
        assertTrue(withNlPostcode().test("like this one : NL-1000 AP"));
        assertTrue(withNlPostcode().test("1522  VX"));

        assertFalse(withNlPostcode().test("No PC here"));
        assertFalse(withNlPostcode().test("No PC 1722 here"));
        assertFalse(withNlPostcode().test(""));
        assertFalse(withNlPostcode().test(" "));
    }

    @Test public void testValidPostCodes(){
        final Map<String, List<String>> map =  getAllLinesFromPath("src/test/files", withNlPostcode());

        map.forEach((key, val) -> {
            System.out.println("key = " + key);
            for (String s : val) System.out.println(s);
        });

        assertThat(map.entrySet().size(), is(equalTo(2)));
        assertThat(map.values().stream().flatMap(Collection::stream).count(), is(equalTo(6L)));

    }

}
