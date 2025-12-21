package br.com.techchallenge.rating.function;

import br.com.techchallenge.rating.exception.ValidationException;
import br.com.techchallenge.rating.model.api.RatingRequest;
import br.com.techchallenge.rating.model.api.RatingResult;
import br.com.techchallenge.rating.service.RatingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

public class CreateCourseRatingFunction {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final RatingService service = new RatingService();

    @FunctionName("CreateCourseRatingFunction")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST},
                    route = "ratings",
                    authLevel = AuthorizationLevel.ANONYMOUS
            ) HttpRequestMessage<Optional<String>> request, final ExecutionContext context) {

        context.getLogger().info("CreateCourseRatingFunction - request received");

        try {
            String body = validateBody(request);
            RatingRequest dto = parseRequestBody(body);
            RatingResult result = service.create(dto);
            return request.createResponseBuilder(HttpStatus.CREATED)
                    .header("Content-Type", "application/json")
                    .body(result)
                    .build();
        } catch (ValidationException | IllegalArgumentException e) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage())
                    .build();
        } catch (Exception e) {
            context.getLogger().severe("Unexpected error: " + e.getMessage());
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error.")
                    .build();
        }
    }

    private RatingRequest parseRequestBody(String body) {
        try {
            return mapper.readValue(body, RatingRequest.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JSON");
        }
    }

    private String validateBody(HttpRequestMessage<Optional<String>> request) {
        String body = request.getBody().orElse(null);
        if (body == null || body.isBlank()) {
            throw new IllegalArgumentException ("Body required. Send JSON with rating and description");
        }
        return body;
    }
}
