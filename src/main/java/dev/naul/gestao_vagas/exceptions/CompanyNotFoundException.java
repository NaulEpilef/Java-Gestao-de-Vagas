package dev.naul.gestao_vagas.exceptions;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException() {
        super("Company no found");
    }
}
