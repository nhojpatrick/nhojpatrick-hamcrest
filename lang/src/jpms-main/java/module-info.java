module com.github.nhojpatrick.hamcrest.lang {
    exports com.github.nhojpatrick.hamcrest.lang;
    requires slf4j.api; // FIXME filename-based automodules detected
    requires transitive org.hamcrest;
}
