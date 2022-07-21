package com.yamakuprina.apigateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomMessage implements Serializable {
    private String messageId;
    private String message;
    private Date messageDate;
}
