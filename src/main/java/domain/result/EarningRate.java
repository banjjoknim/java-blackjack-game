package domain.result;

import domain.user.Profit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public enum EarningRate {
    ONE_POINT_FIVE(GameResult.BLACKJACK, BigDecimal.valueOf(1.5)),
    ONE(GameResult.WIN, BigDecimal.ONE),
    ZERO(GameResult.DRAW, BigDecimal.ZERO),
    MINUS_ONE(GameResult.LOSE, BigDecimal.valueOf(-1));

    private static final int SCALE = 0;

    private GameResult gameResult;
    private BigDecimal earningRate;

    EarningRate(GameResult gameResult, BigDecimal earningRate) {
        this.gameResult = gameResult;
        this.earningRate = earningRate;
    }

    public static EarningRate from(GameResult gameResult) {
        return Arrays.stream(EarningRate.values())
                .filter(earningRate -> earningRate.gameResult == gameResult)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 게임 결과에 대한 수익률이 존재하지 않습니다."));
    }

    public Profit calculateProfit(BigDecimal amount) {
        return new Profit(amount.multiply(earningRate)
                .setScale(SCALE, RoundingMode.HALF_EVEN));
    }
}
