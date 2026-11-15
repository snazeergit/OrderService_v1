package com.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
{
  "timestamp": "2026-11-15T14:20:30Z",
  "status": 404,
  "error": "Not Found",
  "code": "ORDER_NOT_FOUND",
  "message": "No order exists with the supplied ID.",
  "path": "/api/orders/12345",
}
 */

@Data
@AllArgsConstructor
public class ErrorDetails {
    private String timestamp;
    private String status;
    private String error;
    private String code;
    private String message;
    private String path;
}
