# 블랙잭 게임

---

## 기능 요구사항
- 블랙잭 게임을 진행하는 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 `카드의 합`이 `21` 또는 `21에 가장 가까운 숫자를 가지는 쪽`이 이기는 게임이다.
- 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다. 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 `Ace`는 `1 또는 11로 계산`할 수 있다. 
그 외 `King`, `Queen`, `Jack`은 각각 `10으로 계산`한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다.
21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 단, 카드를 추가로 뽑아 `21을 초과할 경우 배팅 금액을 모두 잃게 된다.
- 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 배팅 금액의 1.5배를 딜러에게 받는다. 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 배팅한 금액을 돌려받는다.
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
딜러가 21을 초과하면 그 시점까지 남아있던 플레이어들은 가지고 있는 패에 상관없이 승리해 배팅 금액을 받는다.

---

## 프로그램 실행 결과
```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
A,B

A의 배팅 금액은?
10000

B의 배팅 금액은?
20000

딜러와 A, B에게 2장씩 나누어 주었습니다.
딜러 : 3 다이아몬드
A카드 : 2하트, 8스페이드
B카드 : 7클로버, K스페이드

A는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
A카드 : 2하트, 8스페이드, A클로버
A는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
B는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
B카드 : 7클로버, K스페이드

딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러 카드 : 3다이아몬드, 9클로버, 8다이아몬드 - 결과 : 20
A카드 : 2하트, 8스페이드, A클로버 - 결과 : 21
B카드 : 7클로버, K스페이드 - 결과 : 17

## 최종 수익
딜러 : 10000
A : 10000
B : 20000
```

---

## 프로그래밍 요구사항
- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
  - [https://naver.github.io/hackday-conventions-java/](https://naver.github.io/hackday-conventions-java/) : 좀 더 개선된 컨벤션 참고 문서
- 3항 연산자를 쓰지 않는다.
- else 예약어를 쓰지 않는다.
  - switch/case도 허용하지 않는다.
- 함수(또는 메소드)의 길이가 `10라인`을 넘어가지 않도록 구현한다.
  - 최대한 `10라인`을 넘지 않기 위해 노력하고, 정말 힘든 경우 `15라인까지 허용`한다.
  - 함수(또는 메소드)가 한 가지 일만 잘 하도록 구현한다.
- `indent(인덴트, 들여쓰기) depth`가 `2를 넘지 않도록 구현`한다. `1까지만 허용`한다.
  - 최대한 1을 유지하기 위해 노력하고, 정말 힘든 경우 까지 허용한다.
  - ex) `while문` 안에 `if문`이 있으면 들여쓰기는 2이다.
- `함수(또는 메소드)의 인자 수를 3개까지만 허용`한다. 4개 이상은 허용하지 않는다.

### 프로그래밍 요구사항 - 객체1
- 다음 `Card` 객체를 활용해 구현한다.
- `Card` 기본 생성자를 추가할 수 없다.
- 필드(인스턴스 변수)인 `symbol`과 `type`의 접근 제어자 `private`를 변경할 수 없다.
- `Card`에 필드(인스턴스 변수)를 추가할 수 없다.
```java
package domain.card;

/**
* 카드 한장을 의미하는 객체
*/
public class Card {

    private final Symbol symbol;
    private final Type type;
    
    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }
    
    // TODO 추가 기능 구현
}
```


### 프로그래밍 요구사항 - 객체2
- 다음 `Player` 객체를 활용해 구현해야 한다.
- `Player` 기본 생성자를 추가할 수 없다.
- 필드(인스턴스 변수)인 `name`, `bettingMoney`, `cards`의 접근 제어자 `private`를 변경할 수 없다.
- `Player`에 필드(인스턴스 변수)를 추가할 수 없다.
```java
package domain.user;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

/**
* 게임 참여자를 의미하는 객체
*/
public class Player {

    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();
    
    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }
    
    public void addCard(Card card) {
        cards.add(card);
    }
    
    // TODO 추가 기능 구현
}
```

### 프로그래밍 요구사항 - 객체3
- 다음 `Dealer` 객체를 활용해 구현해야 한다.
- `Dealer` 기본 생성자 이외 다른 생성자를 추가할 수 없다.
- 필드(인스턴스 변수)인 `cards`의 접근 제어자 `private`를 변경할 수 없다.
- `Dealer`에 필드(인스턴스 변수)를 추가할 수 없다.
```java
package domain.user;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

/**
* 게임 딜러를 의미하는 객체
*/
public class Dealer {

    private final List<Card> cards = new ArrayList<>();
    
    public Dealer() {}
    
    public void addCard(Card card) {
        cards.add(card);
    }
    
    // TODO 추가 기능 구현
}
```

### 프로그래밍 요구사항(선택, 필수 아님)
- 기본적으로 제공하는 `Card`, `Player`, `Dealer` 객체에 예외 처리를 한다.
- `Player`와 `Dealer`를 구현하다보면 중복 코드가 발생할 수 있다. 중복 코드를 제거해 본다.
  - 힌트 : Java의 상속을 활용해 중복을 제거해본다.

---

## 구현할 기능 목록

### 참가자 이름
- [ ] 참가자 이름 입력 기능
  - [ ] 공백 예외 처리
  - [ ] 중복되는 이름 예외 처리


### 배팅 금액
- [ ] 입력한 금액으로 배팅 금액 생성 기능
  - [ ] 0보다 큰 금액만 입력이 가능하도록 예외 처리


### 카드
- [ ] 카드 생성 기능
- [ ] 각각의 문양별로 `Ace ~ King` 까지 13개의 카드만 생성 가능하도록 예외 처리
- [ ] 캐싱된 카드 사용하도록 처리
- [ ] 카드 셔플 기능
- [ ] 딜러, 플레이어에게 순차적으로 카드 나누어주는 기능
- [ ] 남은 카드가 존재할 시에만 더 나누어줄 수 있도록 예외 처리
- [ ] 카드를 나누어줄 시 남은 카드에서 제외되도록 처리
- [ ] 게임 시작시 플레이어와 딜러에게 카드를 순서대로 2장씩 나누어준다. 


### 플레이어
- [ ] 입력한 값으로 플레이어를 생성한다.
- [ ] 플레이어는 보유 카드의 숫자 합이 21이 되지 않았을 경우 계속해서 카드를 뽑을 수 있다(해당 플레이어의 턴이 넘어가지 않으며 계속 뽑는다).
- [ ] 플레이어 별로 순차적으로 입력값에 따라 추가로 카드를 더 받을지, 받지 않을지 선택할 수 있다.
- [ ] 보유 카드의 숫자 합 계산 기능
  - [ ] 플레이어가 보유한 `Ace` 카드의 경우 자신에게 최대한 유리한 경우로, 1 또는 11로 계산한다.
  - [ ] 딜러가 보유한 `Ace` 카드의 경우. 버스트되지 않는 한 무조건 11로 계산한다(버스트 된다면 1로 계산한다).


### 딜러
- [ ] 딜러는 보유 카드의 숫자 합이 16이하일 경우 무조건 카드를 받아야 한다.


### 게임 결과
- [ ] 계산된 숫자 합을 통한 승패 결정 기능
  - [ ] 플레이어가 처음에 받은 2장의 카드 숫자 합이 21일 경우, `블랙잭`으로 승리한다.
  - [ ] 딜러가 `블랙잭`인 경우 플레이어 또한 `블랙잭`이 아닌 이상 무조건 딜러가 승리한다.
  - [ ] 플레이어 또는 딜러의 보유 카드의 숫자 합이 21보다 클 경우 카드 보유자는 패배한다.
  - [ ] 딜러가 버스트로 패배할 경우 살아있는 플레이어는 모두 승리한다.
  - [ ] 플레이어의 보유 카드 숫자 합과 딜러의 보유 카드 숫자 합이 동일할 경우 비긴다


### 수익
- [ ] 승패에 따른 수익 계산 기능
  - [ ] 플레이어가 `블랙잭`으로 승리할 경우 배팅 금액의 `1.5배`의 수익을 얻는다.
  - [ ] 플레이어가 `블랙잭`이 아닌 경우로 승리할 경우 배팅 금액 만큼의 수익을 얻는다.
  - [ ] 딜러가 승리할 경우 플레이어는 배팅 금액을 잃는다(딜러가 가져간다).
  - [ ] 플레이어가 딜러와 비길경우 수익도 손해도 없다.


### 출력
- [ ] 모든 플레이어의 카드 뽑기가 끝나면 딜러의 나머지 하나의 카드 또한 오픈한다.
- [ ] 플레이어는 2장의 카드를 모두 오픈하고, 딜러는 한장의 카드만 오픈한다.
- [ ] 딜러와 플레이어의 수익 결과를 모두 출력한다.


---

## 피드백 및 개선사항

---

## 참고자료


---

















