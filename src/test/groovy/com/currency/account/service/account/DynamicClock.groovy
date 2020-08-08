package com.currency.account.service.account

import java.time.*

class DynamicClock extends Clock {

    private Clock delegate

    DynamicClock(LocalDateTime localDateTime) {
        this.delegate = fixed(localDateTime.atZone(ZoneId.of("Poland")).toInstant(), ZoneId.of("Poland"))
    }

    @Override
    ZoneId getZone() {
        return delegate.getZone()
    }

    @Override
    Instant instant() {
        return delegate.instant()
    }

    @Override
    Clock withZone(ZoneId zone) {
        throw new UnsupportedOperationException()
    }

    @Override
    long millis() {
        return delegate.millis()
    }

    void shift(Duration duration) {
        delegate = fixed(delegate.instant() + duration, ZoneId.of("Poland"))
    }

    void changeTo(LocalDateTime localDateTime) {
        delegate = fixed(localDateTime.atZone(ZoneId.of("Poland")).toInstant(), ZoneId.of("Poland"))
    }

    static DynamicClock of(LocalDateTime localDateTime) {
        new DynamicClock(localDateTime)
    }
}
