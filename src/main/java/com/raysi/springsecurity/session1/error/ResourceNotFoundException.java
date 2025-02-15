package com.raysi.springsecurity.session1.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
    private String errorMessage;
    private String errorCode;
}
