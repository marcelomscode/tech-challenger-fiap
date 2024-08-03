package br.com.fotoexpress.fotoexpress.pedido.model.enums;

public enum Acompanhamento {

    MENSAL(1, "Acompanhamento mês a mês"),
    BIMESTRAL(2,"Acompanhamento bimestral"),
    TRIMESTRAL(3,"Acompanhamento trimestral"),
    GESTANTE(4,"Acompanhamento gestante até o nascimento"),
    AVULSOS(5,"Ensaios avulsos."),
    FAMILIA(6,"Pacote participação da família, podendo ser pais ou irmãos."),
    FAMILIAINDIVIDUAL(7,"Participação de familiar individual"),
    ;

    private final int id;
    private final String descricao;

    Acompanhamento(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;

    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public static Acompanhamento getById(Long id) {
        for (Acompanhamento acompanhamento : values()) {
            if (acompanhamento.id == id) {
                return acompanhamento;
            }
        }
        throw new IllegalArgumentException("Id inválido para Acompanhamento: " + id);
    }

    public static String getDescricaoById(Long id) {
        Acompanhamento acompanhamento = getById(id);
        return acompanhamento.getDescricao();
    }

    public static int getIdbyDescricao(String descricao) {
        for (Acompanhamento acompanhamento : values()) {
            if (acompanhamento.getDescricao().equals(descricao)) {
                return acompanhamento.getId();
            }
        }
        throw new IllegalArgumentException("Id inválido para Acompanhamento: " + descricao);
    }


}
