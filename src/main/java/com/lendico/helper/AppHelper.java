package com.lendico.helper;

import com.lendico.exception.custom.ValidationException;
import com.lendico.exception.error.ErrorEnum;

import java.time.LocalDateTime;

public interface AppHelper {

    static boolean isNull(Object o) {
        return o == null;
    }

    static void checkNull(Object o, ErrorEnum errorEnum) {
        if(isNull(o)) {
            throw new ValidationException(errorEnum);
        }
    }

    static boolean isLessThanEqualZero(Number number) {
        return number.intValue() <= 0;
    }

    static void checkLessThanEqualZero(Number number, ErrorEnum errorEnum) {
        if(isLessThanEqualZero(number)){
            throw new ValidationException(errorEnum);
        }
    }

    static boolean isPastDate(LocalDateTime localDateTime) {
        return LocalDateTime.now().isAfter(localDateTime);
    }

    static void checkPastDate(LocalDateTime localDateTime, ErrorEnum errorEnum) {
        if(isPastDate(localDateTime)) {
            throw new ValidationException(errorEnum);
        }
    }

    static boolean isBeforeDate(LocalDateTime startDate, LocalDateTime toDate) {
        return startDate.isBefore(toDate);
    }

    static LocalDateTime getLastDate(LocalDateTime currentDateTime, int months) {
        return currentDateTime.plusMonths(months);
    }
}
