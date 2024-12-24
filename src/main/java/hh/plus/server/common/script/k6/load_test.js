import http from 'k6/http';
import { sleep, check } from 'k6';
let tps = new Trend('transactions_per_second');
import { Gauge, Trend } from 'k6/metrics';

export default function () {
    let start = new Date().getTime();

    let response = http.get('http://localhost:8080/api/balance');

    tps.add(new Date().getTime() - start);

    check(response, {
        'is status 200': (r) => r.status === 200,
        'is response time below 200ms': (r) => r.timings.duration < 200,
    });


    sleep(1);
}
