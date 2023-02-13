# HTTP-web-server

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

RequestHandler
- 실질적으로 작업을 수행할 스레드 클래스
- OutputStream을 DataDouputStream으로 감싸 사용한다.
- Header 작성 메서드
- Body 작성 메서드