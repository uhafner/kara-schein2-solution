package edu.hm.hafner.kara;

import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.provider.Arguments;

import de.i8k.karalight.Kara;
import de.i8k.karalight.test.TestKaraController;
import de.i8k.karalight.world.RepresentationMode;
import de.i8k.karalight.world.World;

import static org.assertj.core.api.Assertions.*;

/**
 * Baseclass for KaraLight tests.
 *
 * @author Ullrich Hafner
 */
public abstract class AbstractKaraTest {
    static Arguments asGivenAndExpectedWorlds(final String file) {
        var selector = file + ".world";
        var fileName = "Assignment" + selector;
        var start = new World(fileName);
        var expected = new World("Expected" + selector);
        return Arguments.of(Named.of(start.getName(), start), Named.of(fileName, expected));
    }

    static World readGivenWorld(final String file) {
        return new World("Assignment" + (file + ".world"));
    }

    static World readExpectedWorld(final String file) {
        return new World("Expected" + (file + ".world"));
    }

    static void verifyAssignment(final World start, final Consumer<String[]> solution, final World expected) {
        executeHeadlessWithWorld(start);

        solution.accept(new String[0]);

        assertThat(start.getRepresentation(RepresentationMode.NONE))
                .isEqualTo(expected.getRepresentation(RepresentationMode.NONE));
    }

    static TestKaraController executeHeadlessWithWorld(final World start) {
        var controller = new TestKaraController(start);
        Kara.setController(controller);
        return controller;
    }

    protected static Stream<Arguments> createStreamOfWorlds(final String... worlds) {
        return Stream.of(worlds).map(AbstractKaraTest::asGivenAndExpectedWorlds);
    }

    static class CountingController extends TestKaraController {
        CountingController(final World world) {
            super(world);
        }
    }
}
