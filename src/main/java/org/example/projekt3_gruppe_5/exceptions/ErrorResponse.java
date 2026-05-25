package org.example.projekt3_gruppe_5.exceptions;
import java.time.Instant;
import java.util.List;
public class ErrorResponse {
    private Instant timestamp = Instant.now();
    private int status;
    private String error;
    private String message;
    private List<String> details;
}