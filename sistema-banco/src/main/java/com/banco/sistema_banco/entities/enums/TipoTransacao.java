package com.banco.sistema_banco.entities.enums;

public enum TipoTransacao {
    DEBITO(1),
    SAQUE(2),
    PIX(3),
    TED(4),
    DOC(5);

    private int code;

    private TipoTransacao(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static TipoTransacao valueOf(int code){
        for(TipoTransacao value : TipoTransacao.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Código de Transferência Inválido de Conta");
    }
}
