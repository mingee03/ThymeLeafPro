# 🎓 주차별 과제: Spring Boot Thymeleaf Web UI & HTML Form CRUD 구현

## ① 프로젝트 소개

- **프로젝트 주제**: 학생 관리 프로그램 Web UI (Spring Boot + Thymeleaf 기반 CRUD)
- **Render 배포 URL**: (배포 후 입력 예정)
- **관리 데이터 (Student)**:
  - `id`: 고유 식별 번호 (Long, 자동 생성)
  - `name`: 학생 이름 (String)
  - `age`: 나이 (Integer)
  - `email`: 이메일 주소 (String)
  - `major`: 전공 (String)
  - `studentId`: 학번 (String)

### 프로젝트 구조

```
src/main
├── java/org/example/assign06
│   ├── controller
│   │   └── StudentController.java
│   ├── domain
│   │   └── Student.java
│   ├── dto
│   │   ├── StudentRequest.java
│   │   └── StudentResponse.java
│   ├── repository
│   │   ├── StudentRepository.java
│   │   └── StudentMemoryRepository.java
│   ├── service
│   │   └── StudentService.java
│   └── Assign06Application.java
└── resources
    ├── static/css
    │   └── style.css
    ├── templates
    │   ├── index.html
    │   └── students
    │       ├── form.html
    │       └── list.html
    └── application.properties
```

### 실행 방법

```bash
./gradlew bootRun
```
브라우저에서 `http://localhost:8080` 또는 `http://localhost:8080/students` 로 접속합니다.

### Web Endpoint 표

| HTTP Method | URL | 기능 | 반환 형식 / 이동 |
|---|---|---|---|
| GET | `/` | 루트 접속 시 목록으로 리다이렉트 | `redirect:/students` |
| GET | `/students` | 학생 전체 목록 조회 | HTML View (`students/list`) |
| GET | `/students/new` | 학생 신규 등록 폼 화면 | HTML View (`students/form`) |
| POST | `/students` | 학생 신규 데이터 등록 | `redirect:/students` (PRG) |

### 주요 화면

- **목록 화면 (`GET /students`)**
![목록 화면](./images/student_list.png)

- **등록 폼 화면 (`GET /students/new`)**
![등록 화면](./images/student_form.png)

---

## ② 이번 과제 키워드

- `@Controller`
- `Thymeleaf`
- `Model`
- `@ModelAttribute`
- `HTML Form`
- `redirect:`
- `PRG Pattern`
- `Bootstrap`
- `Layered Architecture`

---

## ③ 핵심 내용 정리

1. **`@Controller`와 `@RestController`의 차이점**:
   `@RestController`는 데이터를 JSON이나 XML 형태의 HTTP Body로 직접 반환하지만, `@Controller`는 View Template(Thymeleaf)의 논리적 뷰 이름을 반환하여 서버 사이드 렌더링을 통해 동적 HTML 화면을 생성합니다.

2. **Model 객체를 통한 View 데이터 전달**:
   Controller의 핸들러 메서드에 `Model` 파라미터를 선언하고 `model.addAttribute("변수명", 데이터)` 형태로 전달하면, 템플릿 엔진인 Thymeleaf에서 `${변수명}`을 통해 해당 데이터를 참조하고 `th:each`, `th:text` 등을 활용해 동적으로 화면에 렌더링합니다.

3. **`@ModelAttribute`를 통한 Form 데이터 바인딩**:
   HTML Form 태그에서 POST 방식으로 전송한 `application/x-www-form-urlencoded` 데이터를 `@ModelAttribute`를 통해 Controller의 Request DTO(Record/Java Class)의 필드명과 일치하는 매개변수로 자동 매핑 및 수신할 수 있습니다.

4. **PRG(Post-Redirect-Get) 패턴의 필요성과 동작 방식**:
   데이터 등록(POST) 요청을 처리한 후 결과 화면을 직접 렌더링(Forward)하지 않고 `redirect:/students`로 302 리다이렉트 응답을 반환하여, 브라우저가 목록 조회(GET)를 다시 호출하도록 유도합니다. 이를 통해 새로고침 시 동일한 Form 데이터가 중복 제출(Duplicate Form Submission)되는 현상을 방지합니다.

5. **Layered Architecture 유지**:
   Web UI 계층(Controller, View)이 추가되더라도 기존 비즈니스 로직(Service) 및 데이터 저장소(Repository) 계층의 인터페이스와 구현을 독립적으로 유지함으로써, 관심사의 분리와 모듈성을 극대화했습니다.

---

## ④ Weekly Report

### Key Learning
1. **Server-Side Rendering(SSR)과 Thymeleaf 문법**: `th:text`, `th:each`, `th:action`, `th:href` 등을 적용해 Java 컬렉션 데이터를 HTML 테이블과 동적으로 연동하는 방법을 배웠습니다.
2. **HTML Form과 `@ModelAttribute` 데이터 전송 흐름**: Form input의 `name` 속성과 DTO의 필드명을 일치시켜 객체 단위로 데이터를 편리하게 수신하는 원리를 학습했습니다.
3. **PRG(Post-Redirect-Get) 패턴의 실무적 중요성**: 데이터 생성 후 redirect를 사용해야 하는 이유와 브라우저 새로고침 시의 중복 제출 문제를 방지하는 패턴을 체득했습니다.

### Web UI Flow
`Browser (HTTP GET /students)` ➔ `StudentController` ➔ `StudentService.findall()` ➔ `StudentRepository.findAll()` ➔ `Memory (LinkedHashMap)` ➔ `Model.addAttribute("students", ...)` ➔ `Thymeleaf Engine` ➔ `HTML 생성 및 Browser 응답`

### Form Flow
`HTML Form (POST /students)` ➔ `StudentController.create(@ModelAttribute)` ➔ `StudentService.create()` ➔ `StudentRepository.save()` ➔ `Memory (LinkedHashMap)` ➔ `HTTP 302 Redirect (/students)` ➔ `Browser (GET /students)` ➔ `최신 학생 목록 화면 출력`

### Problem & Solution
- **문제**: 기존 도메인(`Book`)에서 학생 관리 프로그램(`Student`)으로 변경하면서 DTO, Service, Controller, Thymeleaf 템플릿 간의 필드명 및 URL 불일치 오류가 발생할 가능성이 있었습니다.
- **해결**: `Student` 엔티티와 Record 기반의 `StudentRequest`, `StudentResponse`의 필드명(`name`, `age`, `email`, `major`, `studentId`)을 엄격히 통일하고, HTML Form input 태그의 `name` 속성을 DTO 매개변수와 일치시킴으로써 데이터 누락 없이 깔끔하게 바인딩되도록 구성했습니다.

### Code Review
- **선정 코드**: `StudentController.java`의 등록 및 리다이렉트 메서드
```java
@PostMapping("/students")
public String create(@ModelAttribute StudentRequest request) {
    studentService.create(request);
    return "redirect:/students";
}
```
- **이유**: 사용자가 입력한 Form 데이터를 DTO로 깔끔하게 캡슐화하여 전달받은 후, 비즈니스 계층에 위임하고 `redirect:` 접두사를 활용해 PRG 패턴을 완벽히 구현한 핵심 컨트롤러 로직이기 때문입니다.

### AI Usage
- Bootstrap 5 반응형 레이아웃 및 폼 UI의 스타일 구조 생성에 AI 도구를 활용했습니다.
- 생성된 템플릿 코드에서 Thymeleaf 속성(`th:action`, `th:each`, `th:text`, `th:href`)과 Controller의 Model 변수명(`students`), DTO 필드명(`name`, `age`, `email`, `major`, `studentId`)이 완벽히 일치하는지 직접 검증하고 정제하여 적용했습니다.

### Reflection
- REST API만을 작성하던 이전 실습과 달리 서버 사이드 렌더링 템플릿 엔진을 직접 연동해 보며 웹 개발의 전반적인 요청-응답 라이프사이클을 훨씬 직관적으로 이해할 수 있었습니다.
