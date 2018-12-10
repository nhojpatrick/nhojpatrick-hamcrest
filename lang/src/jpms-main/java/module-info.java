module com.github.nhojpatrick.hamcrest.lang {
    exports com.github.nhojpatrick.hamcrest.lang;
    requires slf4j.api; // FIXME filename-based automodules detected
    requires transitive hamcrest.core; // FIXME filename-based automodules detected
}
