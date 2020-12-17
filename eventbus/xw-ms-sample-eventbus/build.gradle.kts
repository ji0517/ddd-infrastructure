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
    implementation(deps["spring-boot.starter-data-jpa"])
    implementation(deps["spring-boot.starter-log4j2"])

//    implementation(deps["guava"])
    implementation(deps["log.disruptor"])
    implementation(deps["jpa-spec"])

    runtimeOnly(deps["spring-boot.starter-undertow"])
    runtimeOnly(deps["h2"])

//    implementation(deps["spring-boot.starter-rocketmq"])
}
