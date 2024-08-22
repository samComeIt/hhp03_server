import http from 'k6/http';
import {check, sleep} from 'k6';

import {randomIntBetween, randomItem} from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';

export let options = {
    scenarios: {
        order_scenario: {
            vus: 100, // 가상 사용자
            exec: 'balance_scenario',
            executor: 'per-vu-iterations', // 각각의 가상 사용자들이 정확한 반복 횟수만큼 실행
            iterations: 1000
        }
    }
};

export function balance_scenario() {
    // userId를 1부터 500 사이에서 랜덤으로 생성
    let balanceId = randomIntBetween(1, 500);

    // 잔액 충전
    charge(balanceId)
}

function charge(balanceId) {

    let chargingBalance = randomIntBetween(10, 100) * 1000

    // PATCH 요청 보내기
    let chargeRes = http.patch(
        `http://localhost:8080/api/balance/${balanceId}`,
        JSON.stringify({chargingBalance}),
        {
            tags: {name: 'charge'}
        }
    )

    // 요청이 성공했는지 확인
    check(chargeRes, {'is status 200': (r) => r.status === 200});
}