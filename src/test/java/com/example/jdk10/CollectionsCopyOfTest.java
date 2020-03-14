package com.example.jdk10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

// Suppressing because IntelliJ "knows" we are using immutable collections...
@SuppressWarnings("ConstantConditions")
@DisplayName("CopyOf")
class CollectionsCopyOfTest {

    @Test
    void shouldCreateCopyOfList() {
        var numbers = integers()
                .limit(5)
                .collect(toList());

        var copy = List.copyOf(numbers);

        assertThat(copy).isEqualTo(numbers);

        assertThatThrownBy(() -> copy.add(11))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void shouldCreateCopyOfMap() {
        var shipsByCharacter = Map.of(
                "Luke", "X-Wing",
                "Han", "Millennium Falcon",
                "Wedge", "X-Wing",
                "Vader", "TIE Advanced X-1",
                "Leia", "Tantive IV"
        );

        var copy = Map.copyOf(shipsByCharacter);

        assertThat(copy).isEqualTo(shipsByCharacter);

        assertThatThrownBy(() -> copy.put("Boba Fett", "Slave I"))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void shouldCreateCopyOfSet() {
        var numbers = integers()
                .limit(25)
                .collect(toSet());

        var copy = Set.copyOf(numbers);

        assertThat(copy).isEqualTo(numbers);

        assertThatThrownBy(() -> copy.add(42))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    private Stream<Integer> integers() {
        return Stream.iterate(1, integer -> integer + 1);
    }
}
