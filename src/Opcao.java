public abstract class Opcao {
    private String icone;

    protected Opcao(String icone) {
        this.icone = icone;
    }

    public String getIcone() {
        return icone;
    }
}
