package com.banco.sistema_banco.entities.enums;

public enum Tipo {
    CONTA_CORRENTE(1),
    CONTA_POUPANÇA(2),
    CONTA_DE_PAGAMENTO(3),
    CONTA_SALARIO(4);

    private int code;

    private Tipo(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static Tipo valueOf(int code){
        for(Tipo value : Tipo.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Tipo Inválido de Código de Conta");
    }
}
