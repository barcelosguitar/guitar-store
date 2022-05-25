package br.com.barcelos_projects.enums;

public enum Payment {
    CREDIT_CARD("Cartão de Crédito"),
    TICKET("Boleto"),
    PIX ("PIX");

    private String description;

    private Payment(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    @Override
    public String toString(){
        return this.description;
    }
}   