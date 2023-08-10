package com.funnycode.onlineshop.exception;

import com.funnycode.onlineshop.model.CustomError;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OnlineShopException extends RuntimeException {
    HttpStatus status;
    CustomError error;

    public static OnlineShopException notFoundException(String message) {
        return OnlineShopException.builder().status(HttpStatus.NOT_FOUND)
                .error(CustomError.builder()
                        .code("404")
                        .message(message)
                        .build())
                .build();
    }

    public static OnlineShopException badRequestException(String message) {
        return OnlineShopException.builder().status(HttpStatus.BAD_REQUEST)
                .error(CustomError.builder()
                        .code("400")
                        .message(message)
                        .build())
                .build();
    }

}
