plugins {
    val kotlinVersion by System.getProperties()
    val springbootVersion by System.getProperties()
    val dependencyManagementVersion by System.getProperties()
    id("io.spring.dependency-management") version "$dependencyManagementVersion"
    id("org.springframework.boot") version "$springbootVersion"
}


dependencies {
    api(project(":xw-infrastructure-eventbus-spring"))

    implementation(deps["spring-boot.starter-actuator"])
    implementation(deps["spring-boot.starter-web"])
    implementation(deps["spring-boot.starter-log4j2"])

//    implementation(deps["guava"])
    implementation(deps["log.disruptor"])
    runtimeOnly(deps["spring-boot.starter-undertow"])

//    implementation(deps["spring-boot.starter-rocketmq"])
}
