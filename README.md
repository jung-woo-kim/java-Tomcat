# HTTP-web-server

## Task : TCP socket 통신으로 Tomcat을 구현한다.

### DB
우선 인메모리 형식으로 구현한다.

즉, 후에 실제 데이터베이스로부터 데이터를 가져와야할 수 있으므로 인터페이스로 구현한다.

### model

User
- userId
- password
- name
- email

### webserver

Webserver
- main 메서드가 존재하는 클래스
- 사용자로부터의 요청이 있을 때 마다 새로운 스레드를 만들어낸다.
- 스레드 풀을 이용하여 많은 요청에 대비한다.

RequestHandler
- 실질적으로 작업을 수행할 스레드 클래스
- run 메서드에서 작업을 수행한다.

RequestMapper
- 요청을 그 요청을 수행할 도메인과 연결해주는 역할을 한다.
- 즉, 요청을 해석하여 메소드를 부른다.
- 다형성을 이용해 요청에 알맞은 controller로 요청 처리를 전달한다.

### Util

HttpRequest
- Http 요청을 클래스의 각 요소에 파싱하는 역할을 한다.
- 이를 통해 요청을 분석할 수 있다.

HttpResponse
- Http 응답을 작성할 수 있다.

### Controller
요청 url에 알맞는 작업을 처리한다.