plugins {
  java
  id("org.springframework.boot") version "3.5.6"
  id("io.spring.dependency-management") version "1.1.7"
}

group = "reybo"
version = "0.0.1-SNAPSHOT"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

repositories {
  mavenCentral()
}

extra["springCloudVersion"] = "2025.0.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")

    compileOnly("org.projectlombok:lombok")
  annotationProcessor("org.projectlombok:lombok")

  implementation("org.mapstruct:mapstruct:1.5.5.Final")
  annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter")

  implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.19.1") //for LocalDateTime serialization support

  runtimeOnly("org.postgresql:postgresql")

// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui
  implementation("org.springdoc:springdoc-openapi-ui:1.6.14")

  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
  implementation("org.springframework.kafka:spring-kafka")

// https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-checkstyle-plugin

//  testImplementation("org.springframework.boot:spring-boot-starter-test")
//  testImplementation("org.springframework.kafka:spring-kafka-test")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")

  developmentOnly("org.springframework.boot:spring-boot-devtools")

    //VALIDATION
    implementation("org.springframework.boot:spring-boot-starter-validation")
    testImplementation("org.springframework.boot:spring-boot-starter-validation-test")
}

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
