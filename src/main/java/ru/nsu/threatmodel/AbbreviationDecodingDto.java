package ru.nsu.threatmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbbreviationDecodingDto {
    private String abbreviation;
    private String decoding;
}
