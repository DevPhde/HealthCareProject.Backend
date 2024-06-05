package com.HealthCare.controllers;

import com.HealthCare.exceptions.UnauthorizedException;
import com.HealthCare.inputModels.UserAuthorizationInputModel;
import com.HealthCare.services.users.UserAuthorizationHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserAuthorizationHandler userAuthorizationHandler;

    public UserController(UserAuthorizationHandler userAuthorizationHandler) {
    this.userAuthorizationHandler = userAuthorizationHandler;
    }

    @Operation(summary = "Authorize user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authorized",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\"userType\": Doctor, \"hash\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c\"}"
                            )
                    )),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "Credenciais Inválidas."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "Erro interno, tente novamente mais tarde."
                            )
                    )
            )
    })
        @PostMapping("/authorize")
        public ResponseEntity<Object> AuthorizeUser(@RequestBody @Valid UserAuthorizationInputModel inputModel){

        try {
           Object authorizedUser = userAuthorizationHandler.handle(inputModel);
           return ResponseEntity.ok().body(authorizedUser);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno, tente novamente mais tarde.");
        }
    }
}
