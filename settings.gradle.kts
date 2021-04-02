rootProject.name = "ddd-infrastructure"

include("xw-infrastructure-export")
project(":xw-infrastructure-export").projectDir = file("export/xw-infrastructure-export")

include("xw-infrastructure-uid")
include("xw-infrastructure-uid-jpa")
project(":xw-infrastructure-uid").projectDir = file("uid/xw-infrastructure-uid")
project(":xw-infrastructure-uid-jpa").projectDir = file("uid/xw-infrastructure-uid-jpa")

include("xw-infrastructure-eventbus")
include("xw-infrastructure-eventbus-spring")
include("xw-ms-sample-eventbus")
project(":xw-infrastructure-eventbus").projectDir = file("eventbus/xw-infrastructure-eventbus")
project(":xw-infrastructure-eventbus-spring").projectDir = file("eventbus/xw-infrastructure-eventbus-spring")
project(":xw-ms-sample-eventbus").projectDir = file("eventbus/xw-ms-sample-eventbus")

include("xw-ms-sample-tenancy")
include("xw-infrastructure-multi-tenancy-datasource")
include("xw-infrastructure-multi-tenancy-web")
include("xw-infrastructure-multi-tenancy-context")
project(":xw-ms-sample-tenancy").projectDir = file("tenancy/xw-ms-sample-tenancy")
project(":xw-infrastructure-multi-tenancy-datasource").projectDir = file("tenancy/xw-infrastructure-multi-tenancy-datasource")
project(":xw-infrastructure-multi-tenancy-web").projectDir = file("tenancy/xw-infrastructure-multi-tenancy-web")
project(":xw-infrastructure-multi-tenancy-context").projectDir = file("tenancy/xw-infrastructure-multi-tenancy-context")
