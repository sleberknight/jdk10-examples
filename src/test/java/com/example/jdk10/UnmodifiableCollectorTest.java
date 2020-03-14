package com.example.jdk10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;
import static java.util.stream.Collectors.toUnmodifiableMap;
import static java.util.stream.Collectors.toUnmodifiableSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

// Suppressing because IntelliJ "knows" we are using immutable collections...
@SuppressWarnings("ConstantConditions")
@DisplayName("UnmodifiableCollectors")
class UnmodifiableCollectorTest {

    @Test
    void shouldCreateUnmodifiableList() {
        var numbers = integers()
                .limit(10)
                .collect(toUnmodifiableList());

        assertThatThrownBy(() -> numbers.add(11))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void shouldCreateUnmodifiableMap() {
        var words = List.of(
                "the", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "brown", "dog"
        );

        Map<String, Integer> wordCounts = words.stream()
                .collect(toUnmodifiableMap(word -> word, word -> 1, Integer::sum));

        assertThatThrownBy(() -> wordCounts.put("red", 5))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void shouldCreateUnmodifiableSet() {
        var numbers = integers()
                .limit(10)
                .collect(toList());
        var moreNumbers = integers()
                .limit(15)
                .collect(toList());

        assertThat(numbers.addAll(moreNumbers)).isTrue();

        var uniqueNumbers = numbers.stream().collect(toUnmodifiableSet());

        assertThatThrownBy(() -> uniqueNumbers.add(42))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    private Stream<Integer> integers() {
        return Stream.iterate(1, integer -> integer + 1);
    }
}
