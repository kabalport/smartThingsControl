# SmartThings 및 Aqara Hub 통합 프로젝트

이 프로젝트는 SmartThings 및 Aqara Hub를 사용하여 스마트 플러그와 IR 센서를 제어하는 방법을 보여줍니다. 이를 통해 선풍기나 기타 전자 기기를 스마트하게 제어할 수 있습니다.

## 프로젝트 소개

이 프로젝트는 Spring Boot를 사용하여 SmartThings API와 Aqara Hub API를 통해 스마트 플러그를 켜고 끄거나, IR 신호를 보내어 전자 기기를 제어하는 기능을 제공합니다. 이를 통해 스마트홈 환경을 더욱 편리하게 만들 수 있습니다.

## 기능 목록

- 스마트 플러그 켜기 (`/smartthings/plug/on`)
- 스마트 플러그 끄기 (`/smartthings/plug/off`)
- 등록된 모든 디바이스 목록 조회 (`/smartthings/devices`)
- 스마트 플러그 상태 조회 (`/smartthings/plug/status`)
- 스마트 플러그 에너지 사용량 조회 (`/smartthings/plug/energy`)
- Aqara 허브 재시작 (`/smartthings/hub/restart`)

## 사전 준비

### 필요 조건

- Java 17
- Gradle
- SmartThings API 토큰
- SmartThings 디바이스 ID (스마트 플러그, IR 센서, 허브)

### 설정 파일

`src/main/resources/application.properties` 파일을 다음과 같이 설정합니다:

```properties
# Application name
spring.application.name=demo1

# SmartThings API token
smartthings.api.token=YOUR_SMARTTHINGS_API_TOKEN

# SmartThings device ID for the Aqara IR sensor
smartthings.irsensor.id=YOUR_IR_SENSOR_DEVICE_ID

# SmartThings device ID for the Aqara hub
smartthings.hub.id=YOUR_HUB_DEVICE_ID
```

## 설치 및 실행 방법

### 1. 프로젝트 클론

먼저, 이 프로젝트를 클론합니다:

```bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY_NAME.git
cd YOUR_REPOSITORY_NAME
```

### 2. 종속성 설치 및 애플리케이션 실행

다음 명령을 사용하여 프로젝트 종속성을 설치하고 애플리케이션을 실행합니다:

```bash
./gradlew bootRun
```

애플리케이션이 성공적으로 실행되면, 다음과 같은 메시지가 표시됩니다:

```
Tomcat started on port(s): 8080 (http) with context path ''
```

### 3. API 사용 방법

#### 스마트 플러그 켜기

```bash
curl -X POST http://localhost:8080/smartthings/plug/on
```

#### 스마트 플러그 끄기

```bash
curl -X POST http://localhost:8080/smartthings/plug/off
```

#### 등록된 모든 디바이스 목록 조회

```bash
curl http://localhost:8080/smartthings/devices
```

#### 스마트 플러그 상태 조회

```bash
curl http://localhost:8080/smartthings/plug/status
```

#### 스마트 플러그 에너지 사용량 조회

```bash
curl http://localhost:8080/smartthings/plug/energy
```

#### Aqara 허브 재시작

```bash
curl -X POST http://localhost:8080/smartthings/hub/restart
```

## 결론

이 프로젝트를 통해 스마트홈 디바이스를 쉽게 제어할 수 있습니다. SmartThings와 Aqara Hub를 활용하여 다양한 스마트홈 기능을 구현할 수 있으며, 이를 통해 생활을 더욱 편리하게 만들 수 있습니다.

## 기여 방법

이 프로젝트에 기여하고 싶다면, 다음 단계를 따라주세요:

1. 이 리포지토리를 포크합니다.
2. 새로운 브랜치를 만듭니다 (`git checkout -b feature-branch`).
3. 변경 사항을 커밋합니다 (`git commit -am 'Add new feature'`).
4. 푸시합니다 (`git push origin feature-branch`).
5. 풀 리퀘스트를 생성합니다.

## 라이선스

이 프로젝트는 MIT 라이선스에 따라 배포됩니다. 자세한 내용은 `LICENSE` 파일을 참조하세요.
