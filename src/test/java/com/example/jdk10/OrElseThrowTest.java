package com.example.jdk10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("OrElseThrow")
class OrElseThrowTest {

    @Test
    void shouldNotThrowWhenOptionalIsPresent() {
        var personOptional = Optional.of(new Person("Bob", "Smith"));

        assertThatCode(personOptional::orElseThrow)
                .doesNotThrowAnyException();
    }

    @Test
    void shouldThrowWhenOptionalIsEmpty() {
        var personOptional = Optional.<Person>empty();

        assertThatThrownBy(personOptional::orElseThrow)
                .isExactlyInstanceOf(NoSuchElementException.class);
    }
}
