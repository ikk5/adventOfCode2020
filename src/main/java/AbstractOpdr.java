public abstract class AbstractOpdr {
    public void answerOpdr(int opdrNr) {
        System.out.println(String.format("Opdr%dA:", opdrNr));
        opdrA();
        System.out.println(String.format("Opdr%dB:", opdrNr));
        opdrB();
    }

    public abstract void opdrA();

    public abstract void opdrB();
}
