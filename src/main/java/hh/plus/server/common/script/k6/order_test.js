import http from 'k6/http';
import {check, sleep} from 'k6';

import {randomIntBetween, randomItem} from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';

export let options = {
    scenarios: {
        order_scenario: {
            vus: 100, // 가상 사용자
            exec: 'order_scenario',
            executor: 'per-vu-iterations', // 각각의 가상 사용자들이 정확한 반복 횟수만큼 실행
            iterations: 1000
        }
    }
};

export function order_scenario() {

    // userId를 1부터 500 사이에서 랜덤으로 생성
    let userId = randomIntBetween(1, 500);
    let quantity = randomIntBetween(1, 5);

    // 상품정보 조회
    // 상품 선택
    // 옵션 선택
    // 카트 생성
    // 주문서 생성
    // 주문 생성

    let productId = getProduct() // 상품 조회 및 상품 선택
    let productOptionId = getProductOption(productId) // 상품 옵션 선택

    let cartId = createCart(userId, productId, productOptionId, quantity) // 카트 생성

    let orderSheetId = createOrderSheet(userId, cartId) // 주문서 생성
    let orderSheetItem = getOrderSheet(orderSheetId) // 주문서 조회

    order(orderSheetId, orderSheetItem) // 주문 생성
}

function getProduct() {

    // GET 요청 보내기
    let allProductRes = http.get(
        'http://localhost:8080/api/products?startDate=2024-08-10&endDate=2024-08-30',
        {
            tags: {name: 'popular-product'}
        }
    )

    check(allProductRes, {'is status 200': (r) => r.status === 200})

    return randomItem(allProductRes.json())['product_id']

}

function getProductOption(productId) {

    // GET 요청 보내기
    let allProductOptionRes = http.get(
        'http://localhost:8080/api/product/${productId}',
        {
            tags: {name: 'product-option'}
        }
    )

    // 요청이 성공했는지 확인
    check(allProductOptionRes, {'is status 200': (r) => r.status === 200})

    return randomItem(allProductOptionRes.json())['productOptionId']

}

function createCart(userId, productId, productOptionId, quantity) {
    let cartRequest = {
        userId: userId,
        productId: productId,
        productOptionId: productOptionId,
        quantity: quantity
    }

    // POST 요청 보내기
    let cartRes = http.post(
            'http://localhost:8080/api/cart/create',
            JSON.stringify(cartRequest),
            {
                headers: {'Content-Type': 'application/json'},
                tags: {name: 'cart'}
            }
        )

        // 요청이 성공했는지 확인
        check(orderRes, {'is status 200': (r) => r.status === 200})

        return randomItem(orderRes.json())['cartId']
}

function createOrderSheet(userId, cartId) {
    let orderSheetRequest = {
        userId: userId,
        cartId: {cartId},
    }

    // POST 요청 보내기
    let orderSheetRes = http.post(
            'http://localhost:8080/api/orderSheet/create',
            JSON.stringify(orderSheetRequest),
            {
                headers: {'Content-Type': 'application/json'},
                tags: {name: 'orderSheet'}
            }
        )

        // 요청이 성공했는지 확인
        check(orderSheetRes, {'is status 200': (r) => r.status === 200})

        return randomItem(orderSheetRes.json())['orderSheetId']
}

function getOrderSheet(orderSheetId) {
    // GET 요청 보내기
    let orderSheetDetailRes = http.get(
        'http://localhost:8080/api/orderSheet/${orderSheetId}',
        {
            tags: {name: 'orderSheet-detail'}
        }
    )

    // 요청이 성공했는지 확인
    check(orderSheetDetailRes, {'is status 200': (r) => r.status === 200})

    return orderSheetDetailRes.json()
}

function order(orderSheetId, orderItem) {

    let balance = randomIntBetween(1, 500)
    let orderRequest =
        {
            balanceId: balanceId,
            orderSheetId: orderSheetId,
            orderItemCreateRequestDto: orderItem
        }

    // POST 요청 보내기
    let orderRes = http.post(
        'http://localhost:8080/api/order/create',
        JSON.stringify(orderRequest),
        {
            headers: {'Content-Type': 'application/json'},
            tags: {name: 'order'}
        }
    )

    // 요청이 성공했는지 확인
    check(orderRes, {'is status 200': (r) => r.status === 200})
}