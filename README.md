Week 7
Task 13 
> 캐시 이해X 및 적용 전
> - product 등록 (@Cacheable)
> - product 조회 (@Cacheable)
> - product 수정(재고제외) @CacheEvict
> - 인기상품 조회 @Cacheable

> 캐시 이해 및 적용(개선) 후 (Cache  의 Termination Type 추가 적용)
>  - product 조회 (@Cacheable)
>  - product 현재 재고 수정 (@CacheEvict)
>  - 인기상품 조회 @Cacheable + 12시간 마다 캐시 삭제 처리(Expiration)

> Spring Cache 와 Redis Cache 소유 시간 비교
> - 레디스 캐싱 상품 조회 테스트 (https://github.com/samComeIt/hhp03_server/commit/2128f432f7fe5f004b931def93ce2cdd478d8d8d#comments)
>       - 소유 시간: 2ms (레디스 캐시 미적용: 240ms)
> - 스프링 캐싱 상품 조회 테스트 (https://github.com/samComeIt/hhp03_server/commit/9d2ec74544315584b95e4b349f11bd25c80cb1cb#comments)
>       - 소유 시간 : 8ms

> 결론
> - 캐싱 적용 대상: 속도 이슈 및 실시간성 보정이 필요없는 것 중심
> - 상품 상세 정보 조회 => Eviction (가격 같은 중요한 데이터가 기존캐시가 존재시 안 맞는 데이터로 결제 될 확률이 높기 떄문입니다)
> - 인기 상품 조회 => Expiration (실시간으로 주문이 생성하므로 실시간으로 인기 상품 조회 할때마다 속도 이슈가 생길 수 있는 확률이 높습니다) 
> - 예) Balance는 속도 이슈 및 실시간성 보정이 필요하지만 캐싱 적용이 필요하면,
> 잔액 조회시 캐싱해주지만 잔액 업데이트시 Eviction으로 적용을 할 것
> - 캐싱처리 해줘도 도움이 된다 (특히, Redis Cache 적용하는거랑 안하는거에 대한 조회 속도 차이가 큰 것으로 보였습니다.)
> 

Task 5 시나리오 분석 및 작업 계획

[프로젝트 Milestone](https://github.com/users/samComeIt/projects/2)

시나리오 요구사항 분석 자료(시퀀스 다이어그램)


![sequenceDiagram/balance/PatchPointAPI.png](sequenceDiagram/balance/PatchPointAPI.png)
![sequenceDiagram/order/PostOrderAPI.png](sequenceDiagram/order/OrderProcess.png)

시퀀스 다이어그램 더 보기(상품/주문/잔액 조회 등)
- [상품 조회](sequenceDiagram/product/GetProductAPI.png)
- [상품 상세 조회](sequenceDiagram/product/GetProductDetailAPI.png)
- [상위 상품 목록 조회](sequenceDiagram/product/GetProductListAPI.png)
- [주문 조회](sequenceDiagram/order/GetOrderAPI.png)
- [주문 생성](sequenceDiagram/order/PostOrderAPI02.png)
- [결제 생성](sequenceDiagram/payment/PostPaymentAPI.png)
- [잔액 조회](sequenceDiagram/balance/GetBalanceAPI.png)
- [잔액 충전](sequenceDiagram/balance/PatchPointAPI.png)

Taks 6 시나리오 설계 자료 제출

이커머스 ERD
![ERD.png](ERD.png)

API 명세
![API_Spec.png](API_Spec.png)

Swagger UI
![SwaggerUI.png](SwaggerUI.png)
