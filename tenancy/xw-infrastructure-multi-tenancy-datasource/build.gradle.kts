dependencies {

    api(project(":xw-infrastructure-multi-tenancy-context"))
    implementation(deps["spring-boot.spring-boot-autoconfigure"])
    implementation(deps["spring-boot.starter-jdbc"])
}