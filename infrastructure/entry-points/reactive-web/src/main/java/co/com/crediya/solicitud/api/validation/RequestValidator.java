package co.com.crediya.solicitud.api.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

@Component
@RequiredArgsConstructor
public class RequestValidator {

    private final Validator validator;

    public <T> String validate(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        Map<String, List<String>> groupedErrors = new LinkedHashMap<>();

        for (ConstraintViolation<T> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            String jsonName = resolveJsonPropertyName(dto.getClass(), fieldName);
            groupedErrors
                    .computeIfAbsent(jsonName, k -> new ArrayList<>())
                    .add(violation.getMessage());
        }

        // Concatenar los mensajes como un solo string: campo=mensaje1, campo2=mensaje2
        List<String> errorMessages = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : groupedErrors.entrySet()) {
            String combined = entry.getKey() + "=" + String.join("; ", entry.getValue());
            errorMessages.add(combined);
        }

        return String.join(", ", errorMessages);
    }

    private String resolveJsonPropertyName(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            JsonProperty annotation = field.getAnnotation(JsonProperty.class);
            if (annotation != null && !annotation.value().isEmpty()) {
                return annotation.value();
            }
        } catch (NoSuchFieldException ignored) {
        }
        return fieldName;
    }
}
