rootProject.name = "ddd-infrastructure"
include("xw-infrastructure-export")
include("xw-infrastructure-uid")
include("xw-infrastructure-uid-jpa")
include("xw-infrastructure-eventbus")
include("xw-infrastructure-eventbus-spring")


include("xw-ms-sample-eventbus")

project(":xw-infrastructure-export").projectDir = file("export/xw-infrastructure-export")
project(":xw-infrastructure-uid").projectDir = file("uid/xw-infrastructure-uid")
project(":xw-infrastructure-uid-jpa").projectDir = file("uid/xw-infrastructure-uid-jpa")
project(":xw-infrastructure-eventbus").projectDir = file("eventbus/xw-infrastructure-eventbus")
project(":xw-ms-sample-eventbus").projectDir = file("eventbus/xw-ms-sample-eventbus")
project(":xw-infrastructure-eventbus-spring").projectDir = file("eventbus/xw-infrastructure-eventbus-spring")
