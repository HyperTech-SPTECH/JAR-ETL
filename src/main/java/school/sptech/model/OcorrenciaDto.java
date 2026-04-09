package school.sptech.model;

import java.time.LocalDateTime;

public class OcorrenciaDto {

    private String municipio;
    private String natureza;
    private LocalDateTime dataHora;
    private String diaSemana;
    private String periodo;
    private String bairro;
    private String cep;
    private String logradouro;
    private Integer numLogradouro;
    private String cidade;
    private Double longitude;
    private Double latitude;
    private String grupo;
    private String subGrupo;
    private String abordagem;
    private String valorCarga;

    public OcorrenciaDto(String municipio, String natureza, LocalDateTime dataHora, String diaSemana, String periodo
            , String bairro, String cep, String logradouro, Integer numLogradouro, String cidade, Double longitude
            , Double latitude, String grupo, String subGrupo, String abordagem, String valorCarga) {
        this.municipio = municipio;
        this.natureza = natureza;
        this.dataHora = dataHora;
        this.diaSemana = diaSemana;
        this.periodo = periodo;
        this.bairro = bairro;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numLogradouro = numLogradouro;
        this.cidade = cidade;
        this.longitude = longitude;
        this.latitude = latitude;
        this.grupo = grupo;
        this.subGrupo = subGrupo;
        this.abordagem = abordagem;
        this.valorCarga = valorCarga;
    }

    public OcorrenciaDto() {
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumLogradouro() {
        return numLogradouro;
    }

    public void setNumLogradouro(Integer numLogradouro) {
        this.numLogradouro = numLogradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getSubGrupo() {
        return subGrupo;
    }

    public void setSubGrupo(String subGrupo) {
        this.subGrupo = subGrupo;
    }

    public String getAbordagem() {
        return abordagem;
    }

    public void setAbordagem(String abordagem) {
        this.abordagem = abordagem;
    }

    public String getValorCarga() {
        return valorCarga;
    }

    public void setValorCarga(String valorCarga) {
        this.valorCarga = valorCarga;
    }

    @Override
    public String toString() {
        return "OcorrenciaDto{" +
                "municipio='" + municipio + '\'' +
                ", natureza='" + natureza + '\'' +
                ", dataHora=" + dataHora +
                ", diaSemana='" + diaSemana + '\'' +
                ", periodo='" + periodo + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numLogradouro=" + numLogradouro +
                ", cidade='" + cidade + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", grupo='" + grupo + '\'' +
                ", subGrupo='" + subGrupo + '\'' +
                ", abordagem='" + abordagem + '\'' +
                ", valorCarga='" + valorCarga + '\'' +
                '}';
    }
}