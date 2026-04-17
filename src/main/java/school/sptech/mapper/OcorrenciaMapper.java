package school.sptech.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import school.sptech.model.OcorrenciaDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class OcorrenciaMapper {
    private static final Logger log = LogManager.getLogger(OcorrenciaMapper.class);
    private final DataFormatter formatter = new DataFormatter();

    public OcorrenciaDto toDto(Row row, Map<String, Integer> headerMap){
        try {
            OcorrenciaDto dto = new OcorrenciaDto();
            dto.setMunicipio(getValue(row, headerMap, "MUNICIP_CIRCUNSCRICAO"));
            dto.setNatureza(getValue(row, headerMap, "NATUREZA_APURADA"));
            dto.setDataHora(getDateTime(row, headerMap, "DATA_HORA"));
            dto.setDiaSemana(getValue(row, headerMap, "DIA_SEMANA"));
            dto.setPeriodo(getValue(row, headerMap, "PERIODO"));
            dto.setBairro(getValue(row, headerMap, "BAIRRO"));
            dto.setCep(getValue(row, headerMap, "CEP"));
            dto.setLogradouro(getValue(row, headerMap, "LOGRADOURO"));
            dto.setNumLogradouro(getInt(row, headerMap, "NUMERO_LOGRADOURO"));
            dto.setCidade(getValue(row, headerMap, "CIDADE"));
            dto.setLatitude(getDouble(row, headerMap, "LATITUDE"));
            dto.setLongitude(getDouble(row, headerMap, "LONGITUDE"));
            dto.setGrupo(getValue(row, headerMap, "GRUPO"));
            dto.setSubGrupo(getValue(row, headerMap, "SUBGRUPO"));
            dto.setAbordagem(getValue(row, headerMap, "ABORDAGEM"));
            dto.setValorCarga(getValue(row, headerMap, "VALOR DA CARGA"));
            return dto;
        } catch (Exception e){
            System.err.println("Erro ao processar linha " + row.getRowNum() + ": " + e.getMessage());
            log.warn("Erro ao processar linha {}", row.getRowNum(), e);
            return null;
        }
    }

    private String getValue(Row row, Map<String, Integer> headerMap, String column) {
        Integer index = headerMap.get(column);
        if(index == null) return null;
        Cell cell = row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if(cell == null) return null;

        String value = formatter.formatCellValue(cell).trim();

        if(column.equals("CEP")) {
            value = value.replaceAll("[^0-9]", "");

            if (value.matches("0+") || value.isEmpty()) {
                return null;
            }
            if (value.length() > 8) {
                value = value.substring(0, 8);
            }
        }

        return value.toLowerCase();
    }

    private LocalDateTime getDateTime(Row row, Map<String, Integer> headerMap, String column) {
        Integer idx = headerMap.get(column);
        if (idx == null) return null;

        Cell cell = row.getCell(idx, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if (cell == null) return null;

        // Verifica se a célula está formatada como data no Excel
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getLocalDateTimeCellValue();
        }

        // Caso a data esteja como TEXTO na planilha (ex: "2023-10-25 15:30:00")
        if (cell.getCellType() == CellType.STRING) {
            try {
                return LocalDateTime.parse(cell.getStringCellValue(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            } catch (Exception e) {
                log.warn("Erro ao transformar data!", e);
                return null; // Ou trate o erro de formato
            }
        }

        return null;
    }

    private Integer getInt(Row row, Map<String, Integer> headerMap, String column) {
        Integer idx = headerMap.get(column);
        if (idx == null) return null;

        Cell cell = row.getCell(idx, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if (cell == null) return null;

        // 1. Se a célula for numérica (padrão do Excel para números)
        if (cell.getCellType() == CellType.NUMERIC) {
            // O Excel retorna Double, então convertemos para int
            return (int) cell.getNumericCellValue();
        }

        // 2. Se a célula for texto (ex: alguém digitou "123" como string)
        if (cell.getCellType() == CellType.STRING) {
            try {
                String value = cell.getStringCellValue().trim();
                return value.isEmpty() ? null : Integer.parseInt(value);
            } catch (NumberFormatException e) {
                log.warn("Erro ao formatar número INT", e);
                return null; // Ou logar erro de "formato inválido"
            }
        }

        return null;
    }

    private Double getDouble(Row row, Map<String, Integer> headerMap, String column) {
        Integer idx = headerMap.get(column);
        if(idx == null) return null;

        Cell cell = row.getCell(idx, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if (cell == null) return null;

        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        }

        if (cell.getCellType() == CellType.STRING) {
            try {
                String value = cell.getStringCellValue().trim().replace(",", ".");
                if (value.isEmpty() || value.equalsIgnoreCase("NULL")) {
                    return null;
                }
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                log.warn("Erro ao formatar valor '{}' na coluna '{}' como DOUBLE",
                        cell.getStringCellValue(), column);
                return null;
            }
        }
        return null;
    }
}
