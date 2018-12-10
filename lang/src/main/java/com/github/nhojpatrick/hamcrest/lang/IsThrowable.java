package com.github.nhojpatrick.hamcrest.lang;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class IsThrowable<T extends Throwable>
        extends TypeSafeMatcher<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsThrowable.class);

    public static <T> Matcher<T> throwable(final Class<?> clazz) {
        LOGGER.debug("IsThrowable#throwable((Class<?>) {})", clazz);
        return new IsThrowable(clazz);
    }

    public static <T> Matcher<T> throwable(final String message) {
        LOGGER.debug("IsThrowable#throwable((String) {})", message);
        return new IsThrowable(message);
    }

    public static <T> Matcher<T> throwable(final Class clazz, final String message) {
        LOGGER.debug("IsThrowable#throwable((Class<?>) {}, (String) {})", clazz, message);
        return new IsThrowable(clazz, message);
    }

    private boolean validClazz = true;
    private boolean validMessage = true;

    private final Optional<Class<?>> clazz;
    private final Optional<String> message;

    private IsThrowable(final Class<?> clazz) {
        this.clazz = Optional.ofNullable(clazz);
        this.message = Optional.empty();
    }

    private IsThrowable(final String message) {
        this.clazz = Optional.empty();
        this.message = Optional.ofNullable(message);
    }

    private IsThrowable(final Class<?> clazz, final String message) {
        this.clazz = Optional.ofNullable(clazz);
        this.message = Optional.ofNullable(message);
    }

    @Override
    public void describeMismatchSafely(final T item, final Description description) {

        if (this.clazz.isPresent()
                && this.message.isPresent()) {
            description.appendText("was Throwable of class ");
            description.appendValue(item.getClass().getName());
            description.appendText(" with message ");
            description.appendValue(item.getMessage());

        } else if (this.clazz.isPresent()) {
            description.appendText("was Throwable of class ");
            description.appendValue(item.getClass().getName());

        } else if (this.message.isPresent()) {
            description.appendText("was Throwable with message ");
            description.appendValue(item.getMessage());
        }
    }

    @Override
    public void describeTo(final Description description) {

        if (this.clazz.isPresent()
                && this.message.isPresent()) {
            description.appendText("Throwable of class ");
            description.appendValue(this.clazz.get().getName());
            description.appendText(" with message ");
            description.appendValue(this.message.get());

        } else if (this.clazz.isPresent()) {
            description.appendText("Throwable of class ");
            description.appendValue(this.clazz.get().getName());

        } else if (this.message.isPresent()) {
            description.appendText("Throwable with message ");
            description.appendValue(this.message.get());
        }
    }

    @Override
    protected boolean matchesSafely(final T item) {

        if (clazz.isPresent()) {
            this.validClazz = clazz.get().isInstance(item);
        }

        if (item != null
                && message.isPresent()) {
            final String itemMessage = item.getMessage();
            this.validMessage = this.message.get()
                    .equals(itemMessage);
        }

        return this.validClazz
                && this.validMessage;
    }

}
