module com.github.nhojpatrick.hamcrest.datetime {
    exports com.github.nhojpatrick.hamcrest.datetime;
    exports com.github.nhojpatrick.hamcrest.datetime.flags;
    exports com.github.nhojpatrick.hamcrest.datetime.internal.after
            to
            com.github.nhojpatrick.hamcrest.datetime.test;
    exports com.github.nhojpatrick.hamcrest.datetime.internal.before
            to
            com.github.nhojpatrick.hamcrest.datetime.test;
    exports com.github.nhojpatrick.hamcrest.datetime.internal.equals
            to
            com.github.nhojpatrick.hamcrest.datetime.test;
    requires com.github.spotbugs.annotations;
    requires org.slf4j;
    requires transitive org.hamcrest;
}
