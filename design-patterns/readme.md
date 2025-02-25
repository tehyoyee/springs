## Observer Pattern

- ApplicationEvent 를 상속해 이벤트 클래스를 만듦.
- ApplicationEventPublisher 객체가 eventPublish
- @EventListener 메서드가 이벤트 로직을 수행



## Strategy Pattern

- 기존의 코드르 바꾸지 않고 서비스 로직의 전략을 바꿔 사용할 수 있다.
- 서비스 인스턴스의 생성자를 @Qualifier를 통해 원하는 전략 인스턴스를 사용할 수 있게 된다.


