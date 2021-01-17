val ktorVersion = "1.5.0"
val kotlinVersion = "1.4.21"
val logbackVersion = "1.2.1"
val koinVersion = "2.2.2"

plugins {
    application
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
    id("org.flywaydb.flyway") version "5.2.4"
}

group = "com.barkatme.demo"
version = "0.0.1"

application {
    mainClassName = "com.barkatme.demo.MainKt"
}

flyway {
    user = System.getenv("DB_USER") ?: "opknemdyjrogwy"
    val port = 5432
    password = System.getenv("DB_PASSWORD") ?: "4abe6470028f4a2ed7d831d1ac4e423d450d0637182114d7ec82cc77ce684e3d"
    val uri = "ec2-34-253-148-186.eu-west-1.compute.amazonaws.com"
    val dbName = "d87v9fp3ceoqfg"
    url = System.getenv("DB_URL") ?: "postgres://$user:$password@$uri:$port/$dbName"
    baselineOnMigrate = true
    locations = arrayOf("filesystem:resources/db/migration")
}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://kotlin.bintray.com/ktor") }
    maven { url = uri("https://kotlin.bintray.com/kotlin-js-wrappers") }
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")

    koinDependencies()
    ktorDependencies()
    databaseDependencies()

    //serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.jetbrains:kotlin-css-jvm:1.0.0-pre.31-kotlin-1.2.41")
}

fun DependencyHandlerScope.koinDependencies() {
    implementation("org.koin:koin-ktor:$koinVersion")
    testImplementation("org.koin:koin-test:$koinVersion")
}

fun DependencyHandlerScope.ktorDependencies() {
    implementation("io.ktor:ktor-websockets:$ktorVersion")
    implementation("io.ktor:ktor-network:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-locations:$ktorVersion")
    implementation("io.ktor:ktor-metrics:$ktorVersion")
    implementation("io.ktor:ktor-server-sessions:$ktorVersion")
    implementation("io.ktor:ktor-auth:$ktorVersion")
    implementation("io.ktor:ktor-auth-jwt:$ktorVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}

fun DependencyHandlerScope.databaseDependencies() {
    implementation("org.jetbrains.exposed:exposed:0.17.7")
    implementation("com.zaxxer:HikariCP:3.4.5")
    implementation("org.postgresql:postgresql:42.2.18")
    implementation("org.flywaydb:flyway-core:7.3.2")
}


kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")

tasks.create("stage") {
    dependsOn("installDist")
}