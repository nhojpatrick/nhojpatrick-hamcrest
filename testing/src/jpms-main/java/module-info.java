module com.github.nhojpatrick.hamcrest.testing {
    exports com.github.nhojpatrick.hamcrest.testing;
    requires org.junit.jupiter.api;
    requires slf4j.api; // FIXME filename-based automodules detected
    requires transitive org.hamcrest;
}
