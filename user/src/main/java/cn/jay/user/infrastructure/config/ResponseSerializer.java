package cn.jay.user.infrastructure.config;

import cn.jay.common.dto.Result;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class ResponseSerializer extends StdSerializer<Result> {
    public ResponseSerializer() {
        super(Result.class);
    }
    @Override
    public void serialize(Result result, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        OAuth2AccessToken oAuth2AccessToken = (OAuth2AccessToken) result.getData();
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("code", result.getCode());
        jsonGenerator.writeStringField("message", result.getMessage());

        if (Objects.nonNull(oAuth2AccessToken)) {
            jsonGenerator.writeObjectFieldStart("data");
            jsonGenerator.writeStringField("access_token", oAuth2AccessToken.getValue());
            jsonGenerator.writeStringField("token_type", oAuth2AccessToken.getTokenType());
            jsonGenerator.writeStringField("refresh_token", oAuth2AccessToken.getRefreshToken().getValue());
            jsonGenerator.writeNumberField("expires_in", oAuth2AccessToken.getExpiresIn());
            jsonGenerator.writeStringField("scope", oAuth2AccessToken.getScope().toString());
            jsonGenerator.writeStringField("jti", oAuth2AccessToken.getAdditionalInformation().get("jti").toString());
            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndObject();
    }
}