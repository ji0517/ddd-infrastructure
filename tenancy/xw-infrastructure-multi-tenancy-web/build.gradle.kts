dependencies {
    api(project(":xw-infrastructure-multi-tenancy-context"))
    implementation(deps["spring-boot.spring-boot-autoconfigure"])
    implementation(deps["spring-boot.starter-web"])
    implementation(deps["spring-boot.starter-log4j2"])

    implementation(deps["servlet-api"])
}