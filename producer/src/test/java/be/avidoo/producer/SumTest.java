package be.avidoo.producer;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SumTest {

    @Test
    void sum() {
        assertThat(1 + 1).isEqualTo(2);
    }
}
