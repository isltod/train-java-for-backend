package kr.co.ordermanagement.domain.order;

import kr.co.ordermanagement.domain.exception.NotCancelableStateException;

public enum State {

    CREATED {
        @Override
        void checkCanelable() {
            // 얘만 아무것도 안한다...
        }
    },
    SHIPPING,
    COMPLETED,
    CANCELED;

    /*
     이렇게 뭘 만들어두면 다른데서 this.state.checkCanelable() 처럼 호출할 때,
      그 상태(CREATED, SHIPPING... 등)가 이 함수를 실행하는 모양...
      위에 CREATED만 재정의해서 아무것도 안하고, 나머지는 다 예외 발생
     */
    void checkCanelable() {
        throw new NotCancelableStateException("이미 취소되었거나 취소할 수 없는 주문상태입니다.");
    }

}
