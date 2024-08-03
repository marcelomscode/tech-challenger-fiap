package br.com.fotoexpress.fotoexpress.pedido.model.enums;

public enum StatusPedido {

    EM_ANDAMENTO (1,"Em andamento"),
    CONCLUIDO (2,"Concluído"),
    CANCELADO (3,"Cancelado")
            ;

    private final int id;
    private final String descricao;

    StatusPedido(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public static StatusPedido getById(int id) {
        for (StatusPedido status : values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Id inválido para Status: " + id);
    }

    public static String getDescricaoById(int id) {
        StatusPedido status = getById(id);
        return status.getDescricao();
    }
}

