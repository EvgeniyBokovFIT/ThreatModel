package ru.nsu.threatmodel.service.info;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;
import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.entity.info.AbbreviationModified;
import ru.nsu.threatmodel.entity.info.DefinitionModified;
import ru.nsu.threatmodel.exception.ModelException;
import ru.nsu.threatmodel.repository.info.ModifiedAbbreviationRepository;
import ru.nsu.threatmodel.repository.info.ModifiedDefinitionRepository;
import ru.nsu.threatmodel.utils.TextInfo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private static final String MAIN_FONT_FAMILY = "Times New Roman";
    private static final Integer MAIN_FONT_SIZE = 14;
    private static final Integer TITLE_FONT_SIZE = 16;
    private final ModifiedAbbreviationRepository abbreviationRepository;
    private final ModifiedDefinitionRepository definitionRepository;

    public void createDocument(Long modelId) {
        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream fileOutputStream = new FileOutputStream(modelId + ".docx")) {

            writeAbbreviations(modelId, document);
            writeDefinitions(modelId, document);

            document.write(fileOutputStream);
        } catch (IOException e) {
            throw new ModelException(e.getMessage(), e);
        }
    }

    private void writeAbbreviations(Long modelId, XWPFDocument document) {
        List<AbbreviationModified> abbreviations = abbreviationRepository.getByThreatModelId(modelId);

        List<String> abbreviationStrings = abbreviations
                .stream()
                .map(a -> a.getAbbreviation() +
                        " - " +
                        a.getDecoding())
                .toList();

        writeTitleText(document, "Список сокращений");
        writeMainText(document, abbreviationStrings);
    }

    private void writeMainText(XWPFDocument document, List<String> text) {
        TextInfo textInfo = new TextInfo(document, text, MAIN_FONT_FAMILY, MAIN_FONT_SIZE, false);
        writeTextToDocument(textInfo);
    }

    private void writeTitleText(XWPFDocument document, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setSpacingAfter(0);
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setFontFamily(MAIN_FONT_FAMILY);
        run.setFontSize(TITLE_FONT_SIZE);
        run.setBold(true);
    }

    private void writeTextToDocument(TextInfo textInfo) {
        XWPFParagraph paragraph = textInfo.document().createParagraph();
        paragraph.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun run = paragraph.createRun();

        textInfo.textStrings()
                .forEach(s -> {
            run.setText(s);
            run.addCarriageReturn();
        });
        run.setFontFamily(textInfo.fontFamily());
        run.setFontSize(textInfo.fontSize());
        run.setBold(textInfo.isBold());
    }

    private void writeDefinitions(Long modelId, XWPFDocument document) {
        List<DefinitionModified> definitions = definitionRepository.getByThreatModelId(modelId);

        List<String> definitionStrings = definitions
                .stream()
                .map(d -> d.getDefinition() + " - " + d.getMeaning())
                .toList();

        TextInfo textInfo = new TextInfo(document, definitionStrings, MAIN_FONT_FAMILY, MAIN_FONT_SIZE, false);
        writeTitleText(document, "Список терминов и сокращений");
        writeNumberedList(textInfo);
    }

    private void writeNumberedList(TextInfo textInfo) {
        CTAbstractNum cTAbstractNum = CTAbstractNum.Factory.newInstance();
        cTAbstractNum.setAbstractNumId(BigInteger.valueOf(0));

        CTLvl cTLvl = cTAbstractNum.addNewLvl();
        cTLvl.setIlvl(BigInteger.valueOf(0));
        cTLvl.addNewNumFmt().setVal(STNumberFormat.DECIMAL);
        cTLvl.addNewLvlText().setVal("%1.");
        cTLvl.addNewStart().setVal(BigInteger.valueOf(1));

        XWPFDocument document = textInfo.document();
        XWPFAbstractNum abstractNum = new XWPFAbstractNum(cTAbstractNum);
        XWPFNumbering numbering = document.createNumbering();
        BigInteger abstractNumID = numbering.addAbstractNum(abstractNum);
        BigInteger numID = numbering.addNum(abstractNumID);

        textInfo.textStrings().forEach(s -> {
            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setNumID(numID);
            XWPFRun run = paragraph.createRun();
            run.setText(s);
            run.setFontFamily(textInfo.fontFamily());
            run.setFontSize(textInfo.fontSize());
            run.setBold(textInfo.isBold());
        });
    }
}
