package com.samax.simpleCommerce.security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.samax.simpleCommerce.common.util.InputValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterDto implements Serializable {

    @Pattern(regexp= InputValidation.RX_NAME, message = InputValidation.RX_NAME_MATCH_ERROR)
    String firstName;

    @Pattern(regexp= InputValidation.RX_NAME, message = InputValidation.RX_NAME_MATCH_ERROR)
    String lastName;

    @Email
    String email;

    @Pattern(regexp = InputValidation.RX_PASS, message = InputValidation.RX_PASS_MATCH_ERROR)
    String password;

}
