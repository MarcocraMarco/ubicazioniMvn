package it.alfasoft.cespidiMvn.dto;

public class Ubicazione {
    String nome;
    Long idUbicazione;
    public Ubicazione(String nome, Long id){
        this.nome=nome;
        idUbicazione=id;
    }
    public Ubicazione(){
    }

    public Long getIdUbicazione() {
        return idUbicazione;
    }

    public void setIdUbicazione(Long idUbicazione) {
        this.idUbicazione = idUbicazione;
    }

    public String getNomeUbicazione() {
        return nome;
    }

    public void setUbicazione(String descrizione) {
        this.nome = descrizione;
    }
}
