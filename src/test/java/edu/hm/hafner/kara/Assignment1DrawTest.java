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
import de.i8k.karalight.world.RepresentationMode;
import de.i8k.karalight.world.World;

import static org.assertj.core.api.Assertions.*;

class Assignment1DrawTest extends AbstractKaraTest {
    @MethodSource
    @ParameterizedTest(name = "{index} => Male in der Welt {0} an Position {1}x{2} ein Rechteck der Größe {3}x{4}")
    @DisplayName("Male Rechteck")
    @Timeout(value = 2, threadMode = ThreadMode.SEPARATE_THREAD)
    void shouldSolveAssignment1a(final String name, final int x0, final int y0, final int width, final int height) {
        var start = readGivenWorld(name);
        var controller = new TestKaraController(start);
        controller.prepareInput(String.valueOf(x0));
        controller.prepareInput(String.valueOf(y0));
        controller.prepareInput(String.valueOf(width));
        controller.prepareInput(String.valueOf(height));

        Kara.setController(controller);

        Assignment1Draw.main();

        assertThat(start.getRepresentation(RepresentationMode.NONE))
                .as("Male in der Welt %s an Position %dx%d ein Rechteck der Größe %dx%d", start.getName(), x0, y0, width, height)
                .isEqualTo(new World(String.format("Expected%s-%d-%d-%d-%d.world", name, x0, y0, width, height)).getRepresentation(RepresentationMode.NONE));
    }

    static Stream<Arguments> shouldSolveAssignment1a() {
        return Stream.of(
                Arguments.of("1Draw-L", 4, 1, 5, 3),
                Arguments.of("1Draw-M", 0, 0, 2, 3),
                Arguments.of("1Draw-M", 1, 0, 3, 3),
                Arguments.of("1Draw-M", 0, 1, 4, 5),
                Arguments.of("1Draw-L", 12, 0, 3, 3),
                Arguments.of("1Draw-L", 3, 6, 10, 10),
                Arguments.of("1Draw-L", 1, 8, 3, 3)
        );
    }
}

