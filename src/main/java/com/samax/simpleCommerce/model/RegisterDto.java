package com.samax.simpleCommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.samax.simpleCommerce.util.InputValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import static com.samax.simpleCommerce.util.InputValidation.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterDto implements Serializable {

    @Pattern(regexp= RX_NAME, message = RX_NAME_MATCH_ERROR)
    String firstName;

    @Pattern(regexp=RX_NAME, message = RX_NAME_MATCH_ERROR)
    String lastName;

    @Email
    String email;

    @Pattern(regexp = RX_PASS, message = RX_PASS_MATCH_ERROR)
    String password;

}
