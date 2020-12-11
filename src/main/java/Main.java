public class Main {
    private static int opdrNr;

    public static void main(String[] args) {
        for (int i = 10; i <= 25; i++) {
            try {
                long startTime = System.currentTimeMillis();
                opdrNr = i;
                Class clazz = Class.forName(String.format("Opdr%d", i));
                AbstractOpdr opdr = (AbstractOpdr)clazz.newInstance();
                opdr.answerOpdr(opdrNr);
                System.out.println(String.format("Opdr%d opgelost in %d ms", opdrNr, System.currentTimeMillis() - startTime));
                System.out.println();
            } catch (ClassNotFoundException e) {
                break;
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
