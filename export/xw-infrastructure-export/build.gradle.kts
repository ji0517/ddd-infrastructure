dependencies {
    implementation(deps["spring-boot.spring-boot-autoconfigure"])
    implementation(deps["spring-boot.starter-web"])
    implementation(deps["spring-boot.starter-data-redis"])
    implementation(deps["commons.commons-lang3"])
    implementation(deps["commons.commons-csv"])
    implementation(deps["log.slf4j-api"])
    implementation(deps["spring-boot.mybatis"])
    implementation(deps["jsqlparser"])
    implementation(deps["spring-boot.pagehelper"])
}
//
//// fatJar for external usage
val fatJar = task("fatJar", type = Jar::class) {
//    baseName = "${project.name}-fat"
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
}