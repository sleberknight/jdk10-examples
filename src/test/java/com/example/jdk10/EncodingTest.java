package com.example.jdk10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * = encodes as %3D
 * a space encodes as +
 * @ encodes as %40
 * % encodes as %25
 * $ encodes as %24
 */
@DisplayName("URLEncoder/URLDecoder")
class EncodingTest {

    @Test
    @DisplayName("should encode in application/x-www-form-urlencoded format")
    void shouldEncode() {
        var queryString = "key1=value 1&key2=value@2&key3=value%3&key4=value$4";

        var encoded = URLEncoder.encode(queryString, StandardCharsets.UTF_8);
        
        assertThat(encoded)
                .isEqualTo("key1%3Dvalue+1%26key2%3Dvalue%402%26key3%3Dvalue%253%26key4%3Dvalue%244");
    }

    @Test
    @DisplayName("should decode from application/x-www-form-urlencoded format")
    void shouldDecode() {
        var encoded = "key1%3Dvalue+1%26key2%3Dvalue%402%26key3%3Dvalue%253%26key4%3Dvalue%244";

        var decoded = URLDecoder.decode(encoded, StandardCharsets.UTF_8);

        assertThat(decoded).isEqualTo("key1=value 1&key2=value@2&key3=value%3&key4=value$4");
    }
}
