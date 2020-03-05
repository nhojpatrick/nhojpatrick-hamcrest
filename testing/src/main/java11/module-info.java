module com.github.nhojpatrick.hamcrest.testing {
    exports com.github.nhojpatrick.hamcrest.testing;
    exports com.github.nhojpatrick.hamcrest.testing.util;
    requires com.github.spotbugs.annotations;
    requires org.junit.jupiter.api;
    requires org.slf4j;
    requires transitive org.hamcrest;
}
