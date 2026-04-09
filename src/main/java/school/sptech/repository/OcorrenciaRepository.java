package school.sptech.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import school.sptech.config.DatabaseConfig;
import school.sptech.model.OcorrenciaDto;

import java.sql.*;
import java.util.List;

public class OcorrenciaRepository {
    private static final Logger log = LogManager.getLogger(OcorrenciaRepository.class);

    public void salvarLote(List<OcorrenciaDto> lote) {
        String sql = "INSERT INTO tb_ocorrencias (municipio, natureza, data_hora, dia_semana, periodo, " +
                "bairro, cep, logradouro, num_logradouro, cidade, latitude, longitude, grupo, " +
                "sub_grupo, abordagem, valor_carga_descricao) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        log.debug("Preparando PreparedStatement para lote de {} registros.", lote.size());

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            for (OcorrenciaDto dto : lote) {
                pstmt.setString(1, dto.getMunicipio());
                pstmt.setString(2, dto.getNatureza());
                pstmt.setTimestamp(3, dto.getDataHora() != null ? Timestamp.valueOf(dto.getDataHora()) : null);
                pstmt.setString(4, dto.getDiaSemana());
                pstmt.setString(5, dto.getPeriodo());
                pstmt.setString(6, dto.getBairro());
                pstmt.setString(7, dto.getCep());
                pstmt.setString(8, dto.getLogradouro());

                if (dto.getNumLogradouro() != null) pstmt.setInt(9, dto.getNumLogradouro());
                else pstmt.setNull(9, Types.INTEGER);

                pstmt.setString(10, dto.getCidade());

                if (dto.getLatitude() != null) pstmt.setDouble(11, dto.getLatitude());
                else pstmt.setNull(11, Types.DOUBLE);

                if (dto.getLongitude() != null) pstmt.setDouble(12, dto.getLongitude());
                else pstmt.setNull(12, Types.DOUBLE);

                pstmt.setString(13, dto.getGrupo());
                pstmt.setString(14, dto.getSubGrupo());
                pstmt.setString(15, dto.getAbordagem());
                pstmt.setString(16, dto.getValorCarga());

                pstmt.addBatch();
            }
            log.info("Inserindo batch de dados na tabela Ocorrencia");
            pstmt.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            log.error("Falha ao executar lote no banco: {}", e.getMessage(), e);
            throw new RuntimeException("Erro na persistência do lote", e);        }
    }
}