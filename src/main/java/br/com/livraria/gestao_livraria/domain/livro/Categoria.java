package br.com.livraria.gestao_livraria.domain.livro;

public enum Categoria {
    FICCAO,
    ROMANCE,
    BIOGRAFIA,
    TECNOLOGIA,
    EDUCACAO,
    TERROR,
    OUTROS;

    public static Categoria fromString(String categoria){
        for (Categoria c : Categoria.values()) {
            if(c.name().equalsIgnoreCase(categoria)) {
                return c;
            }
        } throw new IllegalArgumentException("Categoria inválida: " + categoria);
    }

}

