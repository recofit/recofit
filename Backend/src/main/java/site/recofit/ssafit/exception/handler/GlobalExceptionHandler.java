package site.recofit.ssafit.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import site.recofit.ssafit.exception.*;
import site.recofit.ssafit.exception.status.*;

import javax.mail.MessagingException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final List<String> messages = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        log.warn("Method argument not valid exception occurrence: {}", messages);

        return ResponseEntity.badRequest().body(new ExceptionResponse(messages));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex) {
        final List<String> messages = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        log.warn("Constraint violation exception occurrence: {}", messages);

        return ResponseEntity.badRequest().body(new ExceptionResponse(messages));
    }

    @ExceptionHandler({MessagingException.class})
    public ResponseEntity<Object> handleMessagingException(final MessagingException ex) {
        log.error("Messaging exception occurrence: {}", ex.getMessage());

        return ResponseEntity.internalServerError().body(new ExceptionResponse(List.of(ex.getMessage())));
    }

    @ExceptionHandler({MemberException.class})
    public ResponseEntity<Object> handleAccountException(final MemberException ex) {
        final MemberStatus status = ex.getStatus();

        log.warn("Account exception occurrence: {}", status.getMessage());

        return ResponseEntity.status(status.getHttpStatus()).body(new ExceptionResponse(List.of(status.getMessage())));
    }

    @ExceptionHandler({PlaceException.class})
    public ResponseEntity<Object> handleProfileException(final PlaceException ex) {
        final PlaceStatus status = ex.getStatus();

        log.warn("Profile exception occurrence: {}", status.getMessage());

        return ResponseEntity.status(status.getHttpStatus()).body(new ExceptionResponse(List.of(status.getMessage())));
    }

    @ExceptionHandler({ReservationException.class})
    public ResponseEntity<Object> handleTilException(final ReservationException ex) {
        final ReservationStatus status = ex.getStatus();

        log.warn("TIL exception occurrence: {}", status.getMessage());

        return ResponseEntity.status(status.getHttpStatus()).body(new ExceptionResponse(List.of(status.getMessage())));
    }

    @ExceptionHandler({ReviewException.class})
    public ResponseEntity<Object> handleCommentException(final ReviewException ex) {
        final ReviewStatus status = ex.getStatus();

        log.warn("Comment exception occurrence: {}", status.getMessage());

        return ResponseEntity.status(status.getHttpStatus()).body(new ExceptionResponse(List.of(status.getMessage())));
    }

    @ExceptionHandler({VideoException.class})
    public ResponseEntity<Object> handleCommentException(final VideoException ex) {
        final VideoStatus status = ex.getStatus();

        log.warn("Comment exception occurrence: {}", status.getMessage());

        return ResponseEntity.status(status.getHttpStatus()).body(new ExceptionResponse(List.of(status.getMessage())));
    }
}