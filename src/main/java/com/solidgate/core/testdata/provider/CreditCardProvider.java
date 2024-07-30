package com.solidgate.core.testdata.provider;

import com.solidgate.core.model.CreditCardDto;
import com.solidgate.core.testdata.reader.JsonReader;

public class CreditCardProvider implements Provider<CreditCardDto> {

    private static final String FILE_PATH = "testdata/credit-cards.json";
    private final JsonReader<CreditCardDto> jsonReader = new JsonReader<>(FILE_PATH, CreditCardDto.class);

    @Override
    public CreditCardDto provide() {
        return jsonReader.readAll().getFirst();
    }
}
