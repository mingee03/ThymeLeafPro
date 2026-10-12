# 🎓 주차별 과제: Spring Boot Thymeleaf Web UI & HTML Form CRUD 구현

## ① 프로젝트 소개

- **프로젝트 주제**: 학생 관리 프로그램 Web UI (Spring Boot + Thymeleaf 기반 CRUD)
- **Render 배포 URL**: [작성란: 여기에 Render 배포 URL을 입력하세요 (예: https://...)]
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
애플리케이션 실행 후 브라우저에서 `http://localhost:8080` 또는 `http://localhost:8080/students` 로 접속합니다.

### Web Endpoint 표

| HTTP Method | URL | 기능 | 반환 형태 / 동작 |
|---|---|---|---|
| GET | `/` | 루트 접속 시 목록으로 리다이렉트 | `redirect:/students` |
| GET | `/students` | 학생 전체 목록 화면 | HTML View (`students/list`) |
| GET | `/students/new` | 학생 신규 등록 폼 화면 | HTML View (`students/form`) |
| POST | `/students` | 학생 데이터 등록 및 리다이렉트 | `redirect:/students` (PRG) |

### 주요 화면 및 실행 결과 Screenshot

> **[스크린샷 첨부 가이드]**
> 프로젝트 루트의 `images/` 폴더에 캡처한 이미지 파일을 저장하고 아래 경로와 연결하세요.

1. **학생 목록 화면 (`GET /students`)**
   

2. **학생 등록 Form 화면 (`GET /students/new`)**
   - 캡처 가이드: `http://localhost:8080/students/new` 에 접속하여 이름, 나이, 이메일, 전공, 학번 입력 필드가 구성된 폼 화면을 캡처하세요.
   - ![학생 등록 화면](./images/student_form.png)

3. **등록 후 리다이렉트(PRG) 확인 화면 (`POST /students` ➔ `redirect:/students`)**
   - 캡처 가이드: 새 학생 정보를 입력하고 등록 버튼을 눌렀을 때, `/students` 로 리다이렉트되어 새로 추가된 데이터가 테이블에 정상 출력된 화면을 캡처하세요.
   - ![등록 완료 화면](./images/student_registered.png)

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

> **[작성 가이드]** 이번 과제를 통해 학습하고 이해한 핵심 내용을 3~5개 작성하세요.

1. **`@Controller`와 `@RestController`의 차이점**
   - 컨트롤러는 화면을 반환하기 위해 사용하고, 레스트 컨트롤러는 JSON등의 데이터를 반환한다.

2. **Controller에서 `Model`을 이용하여 데이터를 View로 전달하는 방법**
   - Controller의 메서드 파라미터로 Model객체를 선언하고, key, value 형태로 저장한 다음,
   - 스프링에서는 Model 객체를 View에 넘겨주고, Thymeleaf에서는 ${key}형태로 서버에서 전달된 데이터에 접근 가능하다. 

3. **Thymeleaf를 이용하여 Collection을 HTML에 출력하는 방법 (`th:each`, `th:text`)**
   - th:each: List, Map 등의 요소를 반복 순회할 때 사용합니다.
   - th:text: 태그 내부의 텍스트를 모델 데이터로 동적 치환한다.
    
4. **HTML Form 데이터를 `@ModelAttribute`로 전달받는 방법**
   - 브라우저에서 HTML Form이 POST 방식으로 제출되면 데이터가 application/x-www-form-urlencoded 형식의 쿼리 스트링 형태로 전송
   - 컨트롤 메소드 내에 선언하면 스프링이 폼 input 태그의 name과 DTO의 필드명을 매핑하여 자동으로 객체를 생성한다. 
5. **`redirect:`와 PRG(Post-Redirect-Get) Pattern의 동작 방식 및 필요성**
   - POST후 redirect:/students를 반환한다. 서버는 redirect응답을 보내고, 브라우저가 자동으로 목록조회 URL로 리퀘스트한다. 
   - redirect하지 않는다면 새로고침을 누를 때, 직전의 요청이 다시 전송되어 데이터가 중복 등록된다. 
---

## ④ Weekly Report

### Key Learning
> **[작성 가이드]** 이번 주 배운 핵심 내용 3가지를 작성하세요.
1. redirect를 하지 않아 데이터가 중복되는 치명적인 버그를 알게 되어 그의 필요성에 대해 배웠습니다. 
2. 폼의 속성과 DTO를 일치시켜 편리하게 캡슐화 하는 법을 배웠습니다. 
3. Thymeleaf 연동으로 백엔드 데이터를 HTML화면에 동적으로 업데이트하는 방법을 배웠습니다. 

### Web UI Flow
`Browser (HTTP GET /students)` : 브라우저가 서버로 HTTP 리퀘스트 ➔ `StudentController`: 직접 데이터 조회하지 않고, Service의 findAll(null) 조회 ➔
`StudentService` 리포지토리 인터페이스의 findAll() 호출 ➔ `StudentRepository`: LinkedHashMap의 모든 객체 꺼내 리스트 형태로 반환➔ `Memory` 도메인 엔티티를 DTO로 변환하여 컨트롤에 전달 후 Model객체에 데이터 저장 ➔ 
`Model`: 스프링이 템플릿 파일을 찾는다  ➔ `Thymeleaf`: thymeleaf가 model에 담긴 리스트 순회하며 테이블 행 생성, 데이터 치환이 완료된 순수 정적 HTML 문서가 메모리 상에 완성 ➔ `HTML`:서버는 완성된 HTML 문서를 HTTP 200 OK 상태 코드와 함께 브라우저로 응답

### Form Flow
`HTML Form (POST /students)`: 사용자가 이름, 나이, 이메일, 전공, 학번을 입력하고 submit 버튼을 누르면, 브라우저는 POST /students HTTP 요청을 전송 ➔ `StudentController`: create 메서드가 호출되어 레코드의 생성자 매개변수가 자동매핑 후 DTO객체 생성. ➔ `StudentService`: 요청 DTO를 Student객체로 변환하여 repository의 save() 호출 
➔ `StudentRepository`: nextId++로 고유 번호 부여, 링크드 해시 맵에 데이터 보관 ➔ `Memory` ➔ `redirect:/students`서버는 HTML 즉시 반환하지 않고, 헤더에 Location: /students를 담아 브라우저에게 전송 ➔ `GET /students`: 헤더를 확인하고, GET 요청을 서버로 새로 발생 
➔ `HTML`: 방금 메모리에 저장된 데이터를 바로 렌더링한 목록화면 표시.

### Problem & Solution
- **문제**: 새로고침 시 POST가 중복되는 오류
- **해결**: redirect를 반환하도록 하여 PRG 패턴 적용

### Code Review
- **선정 코드**:
```java
@PostMapping("/students")
public String create(@ModelAttribute StudentRequest request) {
    studentService.create(request);
    return "redirect:/students";
}
```
- **선정 이유**: HTML Form 데이터 바인딩과 PRG 패턴이기 때문입니다
.
### AI Usage
- **AI 활용 부분**: Bootstrap 5를 활용한 반응형 학생 목록 테이블(list.html)과 깔끔한 등록 폼(form.html)의 레이아웃 디자인 및 기본 골격 코드를 생성하는 데 AI 도구를 활용했습니다
- **직접 확인 및 수정한 부분**: AI가 생성한 HTML 폼 코드의 <input name="..."> 속성들이 백엔드 DTO인 StudentRequest의 필드명(name, age, email, major, studentId)과 1:1로 정확하게 일치하는지 직접 전수 검증하고 불일치하는 부분을 수정했습니다.

### Reflection
- 저번 과제에서는 Postman으로 데이터를 생성하고 조회하느라 제가 제대로 뭘 만든건지 잘 잡히지 않았는데, 이번 과제에서는 
- UI까지 있으니 기능을 더 편리하게 이해할 수 있었습니다. 

### 건의사항
- 없습니다. 
