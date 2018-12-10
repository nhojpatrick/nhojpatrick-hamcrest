module com.github.nhojpatrick.hamcrest.datetime {
    exports com.github.nhojpatrick.hamcrest.datetime;
    exports com.github.nhojpatrick.hamcrest.datetime.flags;
    requires slf4j.api; // FIXME filename-based automodules detected
    requires transitive hamcrest.core; // FIXME filename-based automodules detected
}
