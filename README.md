# Different ways to deserialize json in spring kafka

- docker 폴더는 kafka docker compose와 kafka topic create문이 존재합니다.
- customDefaultConsumerFactory, customDeserializer, customTypeConverter는 각각 구현방법이 다른 컨슈머 모듈이다.
- customProducer는 이벤트를 발행하는 Producer이다. Test를 통해 이벤트를 발행한다.

## How to run
0. docker 폴더 안 명령어를 통해 컨테이너와 토픽을 생성한다.
 1. 원하는 컨슈머 모듈을 각각 실행시킨다
2. 프로듀서 Test를 통해 이벤트를 발행한다.
3. 각 컨슈머가 이벤트를 역직렬화하는지 확인한다.