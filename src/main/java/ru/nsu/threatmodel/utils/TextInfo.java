package ru.nsu.threatmodel.utils;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.List;

public record TextInfo(XWPFDocument document,
                       List<String> textStrings,
                       String fontFamily,
                       Integer fontSize,
                       boolean isBold) {
}
