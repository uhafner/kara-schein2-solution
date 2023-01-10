package edu.hm.hafner.kara;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import de.i8k.karalight.Kara;
import de.i8k.karalight.test.TestKaraController;

import static org.assertj.core.api.Assertions.*;

class Assignment2CountTest extends AbstractKaraTest {
    @MethodSource
    @ParameterizedTest(name = "{index} => Finde alle {1} Blätter in der Welt {0}")
    @DisplayName("Zähle alle Blätter")
    @Timeout(value = 2, threadMode = ThreadMode.SEPARATE_THREAD)
    void shouldSolveAssignment2a(final String name, final int expectedLeaves) {
        var world = readGivenWorld(name);
        var controller = new TestKaraController(world);

        Kara.setController(controller);

        Assignment2Count.main();

        assertThat(world.deliverMessages())
                .as("Die Welt %s sollte %d Blätter haben", world.getName(), expectedLeaves)
                .anySatisfy(message -> assertThat(message).matches("[^0-9]*" + expectedLeaves + "[^0-9]*"));
    }

    static Stream<Arguments> shouldSolveAssignment2a() {
        return Stream.of(
                Arguments.of("2Count-M-0", 0),
                Arguments.of("2Count-M-1", 1),
                Arguments.of("2Count-M-5", 5),
                Arguments.of("2Count-M-8", 8),
                Arguments.of("2Count-L-0", 0),
                Arguments.of("2Count-L-4", 4),
                Arguments.of("2Count-L-20", 20),
                Arguments.of("2Count-L-60", 60),
                Arguments.of("2Count-S-1", 1),
                Arguments.of("2Count-S-2", 2),
                Arguments.of("2Count-S-3", 3)
        );
    }
}

