package hello.core;

import lombok.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HelloLombokTest {

    private String title;
    private String desc;

    @Test
    @DisplayName("Hello Lombok")
    void lombokTest() {
        HelloLombokTest hlt = new HelloLombokTest();
        System.out.println("hlt = " + hlt);

        hlt.setTitle("this is title");
        hlt.setDesc("this is desc");
        String title = hlt.getTitle();
        String desc = hlt.getDesc();

        System.out.println("hlt = " + hlt);
        System.out.println("title = " + title+ "desc = "+desc);

        assertThat(title).isEqualTo("this is title");
        assertThat(desc).isEqualTo("this is desc");
    }
}
