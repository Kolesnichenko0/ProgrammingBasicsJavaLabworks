package part2.labwork4.second;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObservableListOrdererTest {

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(FXCollections.observableArrayList(),
                        FXCollections.observableArrayList()),
                Arguments.of(FXCollections.observableArrayList(1.0, 2.0, 3.0),
                        FXCollections.observableArrayList(1.0, 2.0, 3.0)),
                Arguments.of(FXCollections.observableArrayList(-1.0, -2.0, -3.0),
                        FXCollections.observableArrayList(-3.0, -2.0, -1.0)),
                Arguments.of(FXCollections.observableArrayList(1.0, -2.0, 3.0, -4.0),
                        FXCollections.observableArrayList(1.0, 3.0, -4.0, -2.0))
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    @DisplayName("Should order the list correctly")
    public void testOrderList(ObservableList<Double> input,
                              ObservableList<Double> expected) {
        ObservableListOrderer.orderList(input);
        assertEquals(expected, input);
    }
}
