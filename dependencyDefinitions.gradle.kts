//var deps:Map<*,*> by extra
//
//deps = mapOf(
//        "junit" to "junit:junit:4.12",
//        "dubbo" to "com.alibaba:dubbo:2.5.7",
//        "zkclient" to "com.101tec:zkclient:0.10",
//        "kryo" to "com.esotericsoftware.kryo:kryo:2.24.0",
//        "kryo-serializers" to "de.javakaffee:kryo-serializers:0.42"
//)

//    compile((ext["deps"] as Map<*, *>)["dubbo"] as String)
//    compile((ext["deps"] as Map<*, *>)["zkclient"] as String)
//    testCompile((ext["deps"] as Map<*, *>)["junit"] as String)

extra.deps {

    // for testing
    //group ref:deps["mockito.inline"]

    "poi" {
        "poi"("org.apache.poi:poi:4.1.2")
        "poi-ooxml"("org.apache.poi:poi-ooxml:4.1.2")
        "poi-ooxml-schemas"("org.apache.poi:poi-ooxml-schemas:4.1.2")
        "poi-examples"( "org.apache.poi:poi-examples:4.1.2")
    }

    "log" {
        "slf4j-api"("org.slf4j:slf4j-api:1.7.30")
        "log4j-slf4j-impl"("org.apache.logging.log4j:log4j-slf4j-impl:2.13.3")
        "log4j2"("org.apache.logging.log4j:log4j-core:2.13.3")
        "disruptor"("com.lmax:disruptor:3.4.2")
    }


    "db" {
        "druid"("com.alibaba:druid:1.1.22")
    }

    "undertow" {
        "core"("io.undertow:undertow-core:2.1.3.Final")
        "servlet"("io.undertow:undertow-servlet:2.1.3.Final")
    }


    "zookeeper"{
        "zkclient"("com.101tec:zkclient:0.11")
        "curator"("org.apache.curator:curator-framework:5.0.0")
    }

    "servlet-api"("javax.servlet:javax.servlet-api:3.1.0")

    "jyaml"("org.jyaml:jyaml:1.3")

    "mysqlconnector"("mysql:mysql-connector-java:8.0.20")

    "spring"{
        "spring-oxm"("org.springframework:spring-oxm:5.2.9.RELEASE")
        "spring-tx"("org.springframework:spring-tx:5.2.9.RELEASE")
        "spring-context-support"("org.springframework:spring-context-support:5.2.9.RELEASE")
        "spring-data-redis"("org.springframework.data:spring-data-redis:2.3.4.RELEASE")
        "spring-web"("org.springframework:spring-web:5.2.9.RELEASE")
        "spring-core"("org.springframework:spring-core:5.2.9.RELEASE")
    }
    "commons"{
        "commons-pool2"("org.apache.commons:commons-pool2:2.8.0")
        "commons-io"("commons-io:commons-io:2.7")
        "commons-lang3"("org.apache.commons:commons-lang3:3.10")
        "commons-codec"("commons-codec:commons-codec:1.14")
        "commons-text"("org.apache.commons:commons-text:1.8")
        "commons-collections4"("org.apache.commons:commons-collections4:4.4")
        "commons-csv"("org.apache.commons:commons-csv:1.8")
        "commons-net"("commons-net:commons-net:3.7.1")
    }



    "spring-boot" {
        "starter"("org.springframework.boot:spring-boot-starter")
        "starter-web"("org.springframework.boot:spring-boot-starter-web")
        "starter-data-redis"("org.springframework.boot:spring-boot-starter-data-redis")
        "starter-jdbc"("org.springframework.boot:spring-boot-starter-jdbc")
        "starter-log4j2"("org.springframework.boot:spring-boot-starter-log4j2")
        "starter-actuator"("org.springframework.boot:spring-boot-starter-actuator")
        "starter-thymeleaf"("org.springframework.boot:spring-boot-starter-thymeleaf")
        "devtools"("org.springframework.boot:spring-boot-devtools")
        "starter-tomcat"("org.springframework.boot:spring-boot-starter-tomcat")
        "starter-undertow"("org.springframework.boot:spring-boot-starter-undertow")
        "starter-cache"("org.springframework.boot:spring-boot-starter-cache")
        "starter-aop"("org.springframework.boot:spring-boot-starter-aop")
        "starter-log4j2"("org.springframework.boot:spring-boot-starter-log4j2")
        "starter-integration"("org.springframework.boot:spring-boot-starter-integration")
        "spring-boot-loader"("org.springframework.boot:spring-boot-loader")
        "configuration-processor"("org.springframework.boot:spring-boot-configuration-processor:2.3.1.RELEASE")
        "spring-boot-autoconfigure"      ("org.springframework.boot:spring-boot-autoconfigure:2.3.1.RELEASE")
        "starter-webflux"("org.springframework.boot:spring-boot-starter-webflux")
        "starter-data-jpa"("org.springframework.boot:spring-boot-starter-data-jpa")


        "starter-test"("org.springframework.boot:spring-boot-starter-test")
        "mybatis"("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.3")
        "pagehelper"("com.github.pagehelper:pagehelper-spring-boot-starter:1.2.13")
        "starter-rocketmq"("org.apache.rocketmq:rocketmq-spring-boot-starter:2.1.1")
    }

    "spring-cloud"{
        "starter-config"("org.springframework.cloud:spring-cloud-starter-config")
        "starter-gateway"("org.springframework.cloud:spring-cloud-starter-gateway")
        "netflix-sidecar"("org.springframework.cloud:spring-cloud-netflix-sidecar")
        "starter-hystrix"("org.springframework.cloud:spring-cloud-starter-hystrix")
        "starter-feign"("org.springframework.cloud:spring-cloud-starter-feign")
        "starter-hystrix-dashboard"("org.springframework.cloud:spring-cloud-starter-hystrix-dashboard")
    }


    "thymeleaf"{
        "thymeleaf"("org.thymeleaf:thymeleaf:3.0.11.RELEASE")
//        "thymeleaf-spring4"("org.thymeleaf:thymeleaf-spring4:3.0.9.RELEASE")
//        "thymeleaf-layout-dialect"("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:2.2.2")
//        "thymeleaf-extras-shiro"("com.github.theborakompanioni:thymeleaf-extras-shiro:2.0.0")
    }


    "jackson" {
        "jackson-core"("com.fasterxml.jackson.core:jackson-core:2.11.0")
        "jackson-databind"("com.fasterxml.jackson.core:jackson-databind:2.11.0")
        "jackson-annotations"("com.fasterxml.jackson.core:jackson-annotations:2.11.0")
        "jackson-data-formatxml"("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.11.0")
        "jackson-dataformat-smile"("com.fasterxml.jackson.dataformat:jackson-dataformat-smile:2.11.0")
        "jackson-dataformat-yaml"("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.11.0")
        "jackson-dataformat-cbor"("com.fasterxml.jackson.dataformat:jackson-dataformat-cbor:2.11.0")
        "jackson-module-jaxb-annotations"("com.fasterxml.jackson.module:jackson-module-jaxb-annotations:2.11.0")
        "jackson-module-kotlin"("com.fasterxml.jackson.module:jackson-module-kotlin")
    }

    "validation"{
        "hibernate-validator"("org.hibernate.validator:hibernate-validator:6.1.5.Final")
        "validation-api"("javax.validation:validation-api:2.0.1.Final")
    }

    "quartz"("org.quartz-scheduler:quartz:2.3.2")
    "xxl-job-core"("com.xuxueli:xxl-job-core:2.2.0")


    "mybatis"("org.mybatis:mybatis:3.5.5")

    "kotlin-reflect"("org.jetbrains.kotlin:kotlin-reflect")
    "kotlin-stdlib"("org.jetbrains.kotlin:kotlin-stdlib")


    "junit5" {
        "junit-jupiter-api"("org.junit.jupiter:junit-jupiter-api:5.6.2")
        "junit-jupiter-engine"("org.junit.jupiter:junit-jupiter-engine:5.6.2")
    }

    "swagger" {
        "xwswagger-starter"("xwswagger-spring-boot-starter-1.0")
        "swagger2"("io.springfox:springfox-swagger2:2.9.2")
        "swagger-ui"("io.springfox:springfox-swagger-ui:2.9.2")
    }

    "mapstruct" {
        "mapstruct-processor"("org.mapstruct:mapstruct-processor:1.4.1.Final")
        "mapstruct"("org.mapstruct:mapstruct:1.4.1.Final")
    }


    "ojdbc7"("ojdbc7-12.1.0.2")
    "uid"("xw-infrastructure-uid-jpa")

    "bcprov-ext-jdk15on"("org.bouncycastle:bcprov-ext-jdk15on:1.60")
    "jjwt"("io.jsonwebtoken:jjwt:0.9.1")
    "java-jwt"("com.auth0:java-jwt:3.10.3")
    "reactor-core"("io.projectreactor:reactor-core:3.3.6.RELEASE")
    "guava"("com.google.guava:guava:30.0-jre")
    "jaxb-api"("javax.xml.bind:jaxb-api:2.3.1")
    "activation"("javax.activation:activation:1.1.1")
    "jaxb-runtime"("org.glassfish.jaxb:jaxb-runtime:2.3.3")
    "aviator"("com.googlecode.aviator:aviator:5.0.1")
    "asm"("org.ow2.asm:asm:8.0.1")
    "rocketmq-client"("org.apache.rocketmq:rocketmq-client:4.7.0")
    "rocketmq-common"("org.apache.rocketmq:rocketmq-common:4.7.0")
    "eos4j"("org.beykery:eos4j:1.0.0")
    "automaton"("dk.brics:automaton:1.12-1")

    "lombok" {
        "lombok-processor"("org.projectlombok:lombok:1.18.12")
        "lombok"("org.projectlombok:lombok:1.18.12")
    }

    "shiro"{
        "shiro-core"("org.apache.shiro:shiro-core:1.6.0")
        "shiro-web"("org.apache.shiro:shiro-web:1.6.0")
        "shiro-spring"("org.apache.shiro:shiro-spring:1.6.0")
        "shiro-ehcache"("org.apache.shiro:shiro-ehcache:1.6.0")
        "thymeleaf-extras-shiro"("com.github.theborakompanioni:thymeleaf-extras-shiro:2.0.0")
    }

    "jsqlparser"("com.github.jsqlparser:jsqlparser:3.1")
    "kryo"("com.esotericsoftware:kryo:4.0.2")
    "jsch"("com.jcraft:jsch:0.1.55")
    "joda-time"("joda-time:joda-time:2.10")
    "pinyin4j"("com.belerweb:pinyin4j:2.5.0")
    "httpclient"("org.apache.httpcomponents:httpclient:4.5.13")
    "okhttp"("com.squareup.okhttp3:okhttp:4.9.0")

    "security-jwt"("org.springframework.security:spring-security-jwt:1.1.1.RELEASE")
    "security-oauth2"("org.springframework.security.oauth:spring-security-oauth2:2.5.0.RELEASE")

    "jpa-spec"("com.github.wenhao:jpa-spec:3.2.5")

    "h2"("com.h2database:h2:1.4.200")
}
