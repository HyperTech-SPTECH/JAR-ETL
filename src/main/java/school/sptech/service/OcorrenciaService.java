package school.sptech.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import school.sptech.repository.OcorrenciaRepository;
import school.sptech.mapper.OcorrenciaMapper;
import school.sptech.model.OcorrenciaDto;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;

public class OcorrenciaService {

    private static final Logger log = LogManager.getLogger(OcorrenciaService.class);
    private final OcorrenciaRepository dao = new OcorrenciaRepository();
    private final OcorrenciaMapper mapper = new OcorrenciaMapper();

    public void executar(InputStream inputStream, String nomeArquivo) {
        String status = "SUCESSO";
        String erroMsg = null;
        Timestamp dataInicio = new Timestamp(System.currentTimeMillis());

        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            log.info("Processando: {}", nomeArquivo);
            iterarPlanilha(workbook.getSheetAt(0));
        } catch (Exception e) {
            status = "ERRO";
            erroMsg = e.getMessage();
            log.error("Erro no processamento: ", e);
        }
    }

    private void iterarPlanilha(Sheet sheet) {
        Iterator<Row> rowIterator = sheet.iterator();

        Map<String, Integer> headerMap = extrairHeader(rowIterator);

        List<OcorrenciaDto> batch = new ArrayList<>();
        int batchSize = 1000;

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            OcorrenciaDto dto = mapper.toDto(row, headerMap);

            if (dto != null) {
                batch.add(dto);
            }

            if (batch.size() >= batchSize) {
                enviarParaBanco(batch);
                batch.clear();
            }
        }

        if (!batch.isEmpty()) {
            enviarParaBanco(batch);
        }
    }

    private Map<String, Integer> extrairHeader(Iterator<Row> rowIterator) {
        Map<String, Integer> headerMap = new HashMap<>();

        if (rowIterator.hasNext()) {
            Row headerRow = rowIterator.next();
            for (Cell cell : headerRow) {
                String nomeColuna = cell.getStringCellValue().trim();
                headerMap.put(nomeColuna, cell.getColumnIndex());
            }
        }
        return headerMap;
    }

    private void enviarParaBanco(List<OcorrenciaDto> batch) {
        log.info("Enviando lote de {} registros para o banco de dados...", batch.size());
        dao.salvarLote(batch);
    }
}