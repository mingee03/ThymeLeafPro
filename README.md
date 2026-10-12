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
   - 캡처 가이드: 브라우저에서 `http://localhost:8080/students` 에 접속하여 테이블에 등록된 학생 목록이 표시되는 전체 화면을 캡처하세요.
   - ![학생 목록 화면](./images/student_list.png)

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
   - [작성란]: 

2. **Controller에서 `Model`을 이용하여 데이터를 View로 전달하는 방법**
   - [작성란]: 

3. **Thymeleaf를 이용하여 Collection을 HTML에 출력하는 방법 (`th:each`, `th:text`)**
   - [작성란]: 

4. **HTML Form 데이터를 `@ModelAttribute`로 전달받는 방법**
   - [작성란]: 

5. **`redirect:`와 PRG(Post-Redirect-Get) Pattern의 동작 방식 및 필요성**
   - [작성란]: 

---

## ④ Weekly Report

### Key Learning
> **[작성 가이드]** 이번 주 배운 핵심 내용 3가지를 작성하세요.
1. [작성란]: 
2. [작성란]: 
3. [작성란]: 

### Web UI Flow
`Browser (HTTP GET /students)` ➔ `StudentController` ➔ `StudentService` ➔ `StudentRepository` ➔ `Memory` ➔ `Model` ➔ `Thymeleaf` ➔ `HTML`
> **[작성 가이드]** 위 흐름에 대해 각 계층의 역할과 데이터가 전달되는 과정을 본인의 말로 설명하세요.
- [작성란]: 

### Form Flow
`HTML Form (POST /students)` ➔ `StudentController` ➔ `StudentService` ➔ `StudentRepository` ➔ `Memory` ➔ `redirect:/students` ➔ `GET /students` ➔ `HTML`
> **[작성 가이드]** Form 입력 데이터가 서버로 전달되어 등록된 후, 다시 목록 화면으로 이동하기까지의 흐름을 설명하세요.
- [작성란]: 

### Problem & Solution
> **[작성 가이드]** 구현 중 발생한 문제와 해결 방법을 1가지 작성하세요.
- **문제**: [작성란]
- **해결**: [작성란]

### Code Review
> **[작성 가이드]** 구현한 코드 중 가장 중요하다고 생각한 코드 1개와 그 이유를 작성하세요.
- **선정 코드**:
```java
// [작성란: 여기에 선정한 코드를 넣으세요]
```
- **선정 이유**: [작성란]

### AI Usage
> **[작성 가이드]** AI를 활용한 부분과 생성된 코드를 직접 확인 및 수정한 부분을 작성하세요.
- **AI 활용 부분**: [작성란]
- **직접 확인 및 수정한 부분**: [작성란]

### Reflection
> **[작성 가이드]** 새롭게 알게 된 점 또는 궁금한 점 1가지를 작성하세요.
- [작성란]: 

### 건의사항
- [작성란]: 
