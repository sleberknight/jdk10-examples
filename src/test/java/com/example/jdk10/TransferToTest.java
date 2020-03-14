package com.example.jdk10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TransferTo")
class TransferToTest {

    @Test
    void shouldTransferToWriter() throws IOException {
        var phrase = "The red fox jumped over the lazy brown dog";
        var reader = new StringReader(phrase);
        var writer = new StringWriter();
        var transferCount = reader.transferTo(writer);

        assertThat(transferCount).isEqualTo(42);
        assertThat(writer.toString()).isEqualTo(phrase);
    }
}
