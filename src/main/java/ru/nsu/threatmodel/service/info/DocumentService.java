package ru.nsu.threatmodel.service.info;

import lombok.RequiredArgsConstructor;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;
import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.entity.info.ModelAbbreviation;
import ru.nsu.threatmodel.entity.info.ModelDefinition;
import ru.nsu.threatmodel.entity.info.ModelRegulation;
import ru.nsu.threatmodel.entity.info.SystemDescriptionRepository;
import ru.nsu.threatmodel.exception.ModelException;
import ru.nsu.threatmodel.repository.info.*;
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
    private final ModelAbbreviationRepository abbreviationRepository;
    private final ModelDefinitionRepository definitionRepository;
    private final GeneralProvisionRepository generalProvisionRepository;
    private final ModelRegulationRepository modelRegulationRepository;
    private final SystemDescriptionRepository descriptionRepository;
    private final SystemInformationRepository informationRepository;

    public void createDocument(Long modelId) {
        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream fileOutputStream = new FileOutputStream(modelId + ".docx")) {

            writeAbbreviations(modelId, document);
            writeDefinitions(modelId, document);
            writeGeneralProvisions(modelId, document);
            writeSystemDescription(modelId, document);

            document.write(fileOutputStream);
        } catch (IOException e) {
            throw new ModelException(e.getMessage(), e);
        }
    }

    private void writeAbbreviations(Long modelId, XWPFDocument document) {
        List<ModelAbbreviation> abbreviations = abbreviationRepository.getByThreatModelId(modelId);

        List<String> abbreviationStrings = abbreviations
                .stream()
                .map(a -> a.getAbbreviation() +
                        " - " +
                        a.getDecoding())
                .toList();

        writeTitleText(document, "Список сокращений");
        writeMainTextMultipleLines(document, abbreviationStrings);
    }

    private void writeMainTextMultipleLines(XWPFDocument document, List<String> text) {
        TextInfo textInfo = new TextInfo(document, text, MAIN_FONT_FAMILY, MAIN_FONT_SIZE, false);
        writeTextToDocumentWithMultipleLines(textInfo);
    }

    private void writeTitleText(XWPFDocument document, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setSpacingAfter(1);
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setFontFamily(MAIN_FONT_FAMILY);
        run.setFontSize(TITLE_FONT_SIZE);
        run.setBold(true);
    }

    private void writeMainText(XWPFDocument document, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.BOTH);
        paragraph.setSpacingAfter(1);
        paragraph.setFirstLineIndent(Units.EMU_PER_DXA);
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setFontFamily(MAIN_FONT_FAMILY);
        run.setFontSize(MAIN_FONT_SIZE);
        run.setBold(false);
        run.addCarriageReturn();
    }

    private void writeTextToDocumentWithMultipleLines(TextInfo textInfo) {
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
        List<ModelDefinition> definitions = definitionRepository.getByThreatModelId(modelId);

        List<String> definitionStrings = definitions
                .stream()
                .map(d -> d.getDefinition() + " - " + d.getMeaning())
                .toList();

        TextInfo textInfo = new TextInfo(document, definitionStrings, MAIN_FONT_FAMILY, MAIN_FONT_SIZE, false);
        writeTitleText(document, "Список терминов и определений");
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

    private void writeGeneralProvisions(Long modelId, XWPFDocument document) {
        var optionalGeneralProvision = generalProvisionRepository.getByThreatModelId(modelId);
        var generalProvision = optionalGeneralProvision
                .orElseThrow(() -> new ModelException("Отсутствуют общие положения у модели с id =" + modelId));
        var regulations = modelRegulationRepository.getByThreatModelId(modelId);

        writeTitleText(document, "1. Общие положения");
        writeBoldText(document, "1.1.\tНазначение и область действия документа");
        writeMainText(document, generalProvision.getPurpose());

        writeBoldText(document, "1.2.\tНПА, методические документы, " +
                "национальные стандарты, используемые для оценки УБИ в рамках модели угроз.");
        List<String> regulationsAsStringList = regulations
                .stream()
                .map(ModelRegulation::getRegulation)
                .toList();
        writeMainTextMultipleLines(document, regulationsAsStringList);

        writeBoldText(document, "1.3.\tНаименование обладателя информации, заказчика, оператора систем и сетей");
        writeMainText(document, generalProvision.getInformationOwner());

        writeBoldText(document, "1.4.\tПодразделения, должностные лица, " +
                "ответственные за обеспечение безопасности ин-формации в ИСПДн");
        writeMainText(document, generalProvision.getResponsibleOfficials());

        writeBoldText(document, "1.5.\tНаименование организации, разработавшей модель угроз (исполнитель)");
        writeMainText(document, generalProvision.getDeveloperOrganisation());
    }

    private void writeBoldText(XWPFDocument document, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.BOTH);
        paragraph.setSpacingAfter(0);
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setFontFamily(MAIN_FONT_FAMILY);
        run.setFontSize(MAIN_FONT_SIZE);
        run.setBold(true);
    }

    private void writeSystemDescription(Long modelId, XWPFDocument document) {
        writeTitleText(document, "2.\tОПИСАНИЕ СИСТЕМ И СЕТЕЙ И ИХ ХАРАКТЕРИСТИКА КАК ОБЪЕКТОВ ЗАЩИТЫ");
        writeBoldText(document, "2.1.\tОбщие сведения о системе");
        var description = descriptionRepository.getByThreatModelId(modelId);
        var descriptionText = description.get().getGeneralInformation();
        writeMainText(document, descriptionText);
        writeMainText(document, "Таблица 2.1");
        writeSystemInformationTable(modelId, document, description.get().getSystemName());
    }

    private void writeSystemInformationTable(Long modelId, XWPFDocument document, String systemName) {
        var information = informationRepository.getByThreatModelId(modelId);
        XWPFTable table = document.createTable(information.size() + 1, 4);
        var row1 = table.getRow(0);
        writeBoldTextToCell(row1.getCell(0), "Наименование ИС");
        writeBoldTextToCell(row1.getCell(1), "Вид информации");
        writeBoldTextToCell(row1.getCell(2), "Операции");
        writeBoldTextToCell(row1.getCell(3), "Состав сведений");

        for (int i = 1; i < information.size() + 1; i++) {
            var curRow = table.getRow(i);
            var curInfo = information.get(i - 1);
            writeMainTextToCell(curRow.getCell(0), systemName);
            writeMainTextToCell(curRow.getCell(1), curInfo.getInformationType());
            writeMainTextToCell(curRow.getCell(2), curInfo.getOperations());
            writeMainTextToCell(curRow.getCell(3), curInfo.getComposition());
        }
    }

    private void writeMainTextToCell(XWPFTableCell cell, String text) {
        XWPFParagraph paragraph = cell.addParagraph();
        paragraph.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setFontFamily(MAIN_FONT_FAMILY);
        run.setFontSize(MAIN_FONT_SIZE);
        run.setBold(false);
    }

    private void writeBoldTextToCell(XWPFTableCell cell, String text) {
        XWPFParagraph paragraph = cell.addParagraph();
        paragraph.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setFontFamily(MAIN_FONT_FAMILY);
        run.setFontSize(MAIN_FONT_SIZE);
        run.setBold(true);
    }
}
