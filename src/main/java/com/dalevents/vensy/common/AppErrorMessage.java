package com.dalevents.vensy.common;

import java.time.LocalDateTime;


public record AppErrorMessage(int statusCode, Object message, LocalDateTime timestamp, String path) {

}
