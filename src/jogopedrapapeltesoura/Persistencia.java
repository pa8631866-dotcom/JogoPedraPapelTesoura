package jogopedrapapeltesoura;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Persistencia {
    private static String NomeDoArquivo = "db.txt";
    private static String Separador = "-";

    private int[] pontos = { 0, 0 };

    public Persistencia() {
        File arquivo = new File(NomeDoArquivo);

        try {
            arquivo.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Scanner scanner = new Scanner(arquivo)) {
            if (scanner.hasNextLine()) {
                String dado =  scanner.nextLine();

                var padrao = Pattern.compile("^(\\d+)" + Separador + "(\\d+)$");
                var match = padrao.matcher(dado);
                if (match.find()) {
                    var pontos1 = Integer.parseInt(match.group(1));
                    var pontos2 = Integer.parseInt(match.group(2));

                    if (pontos1 >= 0 && pontos2 >= 0 &&
                        pontos1 <= Motor.PontosAteVitoria -1 && pontos2 <= Motor.PontosAteVitoria -1) {
                        pontos[0] = pontos1;
                        pontos[1] = pontos2;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int[] getPontos() {
        return pontos;
    }

    public void setPontos(int pontos1, int pontos2) {
        pontos[0] = pontos1;
        pontos[1] = pontos2;

        try (FileWriter escritor = new FileWriter(NomeDoArquivo)) {
            escritor.write(pontos1 + Separador + pontos2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
