package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CardTest {

    @DisplayName("생성된 카드가 잘 초기화 되었는지 테스트한다.")
    @Test
    void 생성된_카드가_잘_초기화_된다() {
        // given
        Symbol symbol = Symbol.DIAMOND;
        Type type = Type.QUEEN;

        // when
        Card card = Card.of(symbol, type);

        // then
        assertAll(
                () -> assertThat(card.getSymbol()).isEqualTo(symbol),
                () -> assertThat(card.getType()).isEqualTo(type)
        );
    }

    @DisplayName("카드의 갯수가 52개로 잘 캐싱되었는지 테스트한다.")
    @ParameterizedTest
    @EnumSource(Symbol.class)
    void 캐싱된_카드가_52개인지_테스트한다(Symbol symbol) {
        // given
        List<Card> cache = Card.getCACHE();

        // when
        int cacheSize = cache.size();
        long symbolCount = cache.stream()
                .filter(card -> card.getSymbol() == symbol)
                .count();

        // then
        assertAll(
                () -> assertThat(cacheSize).isEqualTo(52),
                () -> assertThat(symbolCount).isEqualTo(Type.values().length)
        );
    }

    @DisplayName("카드가 ACE 타입일 경우, 카드가 ACE 타입인지 검증하는 기능을 테스트한다.")
    @Test
    void 카드가_ACE_타입이다() {
        // given
        Card card = Card.of(Symbol.DIAMOND, Type.ACE);

        // when
        boolean isAce = card.isAce();

        // then
        assertThat(isAce).isTrue();
    }

    @DisplayName("카드가 ACE 타입이 아닐 경우, 카드가 ACE 타입인지 검증하는 기능을 테스트한다.")
    @Test
    void 카드가_ACE_타입이_아니다() {
        // given
        Card card = Card.of(Symbol.DIAMOND, Type.KING);

        // when
        boolean isAce = card.isAce();

        // then
        assertThat(isAce).isFalse();
    }

}
