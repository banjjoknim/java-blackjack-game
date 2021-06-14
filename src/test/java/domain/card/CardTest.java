package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @DisplayName("카드의 갯수가 52개로 잘 캐싱되었는지 테스트한다.")
    @Test
    void 캐싱된_카드가_52개인지_테스트한다() {
        // given
        List<Card> cache = Card.getCACHE();

        // when
        int cacheSize = cache.size();

        // then
        assertThat(cacheSize).isEqualTo(52);
    }

}
