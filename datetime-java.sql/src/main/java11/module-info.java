module com.github.nhojpatrick.hamcrest.datetime.java.sql {
    exports com.github.nhojpatrick.hamcrest.datetime.java.sql;
    exports com.github.nhojpatrick.hamcrest.datetime.java.sql.internal.after
            to
            com.github.nhojpatrick.hamcrest.datetime.java.sql.tests;
    exports com.github.nhojpatrick.hamcrest.datetime.java.sql.internal.before
            to
            com.github.nhojpatrick.hamcrest.datetime.java.sql.tests;
    exports com.github.nhojpatrick.hamcrest.datetime.java.sql.internal.equals
            to
            com.github.nhojpatrick.hamcrest.datetime.java.sql.tests;
    requires com.github.spotbugs.annotations;
    requires org.slf4j;
    requires transitive com.github.nhojpatrick.hamcrest.datetime;
    requires transitive java.sql;
    requires transitive org.hamcrest;
}
