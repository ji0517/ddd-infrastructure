plugins {
    val kotlinVersion by System.getProperties()
    val springbootVersion by System.getProperties()
    val dependencyManagementVersion by System.getProperties()
    id("io.spring.dependency-management") version "$dependencyManagementVersion"
    id("org.springframework.boot") version "$springbootVersion"
}


dependencies {
    implementation(project(":xw-infrastructure-multi-tenancy-web"))
    implementation(project(":xw-infrastructure-multi-tenancy-datasource"))

    implementation(deps["spring-boot.starter-actuator"])
    implementation(deps["spring-boot.starter-web"])
    implementation(deps["spring-boot.starter-data-jpa"])
    implementation(deps["spring-boot.starter-log4j2"])

//    implementation(deps["guava"])
    implementation(deps["log.disruptor"])
    implementation(deps["jpa-spec"])

    runtimeOnly(deps["spring-boot.starter-undertow"])
    runtimeOnly(deps["mysqlconnector"])


//    implementation(deps["spring-boot.starter-rocketmq"])
}
