import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Desenho extends Canvas {

    private int sleepTime, width, height, newColor, newColorValue, firstColor, qntyColors, xInicial, yInicial,
            widthPart, heightPart;
    private int[][] zone;
    private Color color;

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        declarationSpace();

        createZoneValues();

        drawColorsMap(g);
        // enterToProceed(); //se quiser começar a pintar após apertar enter

        setNewnewColorValue(g, xInicial, yInicial);

        recursivePaintCaller(g, xInicial, yInicial);

        print();
    }

    /**
     * Local destinado a atribuir valores às variáveis do programa.
     * 
     * @author LucasAP
     */
    private void declarationSpace() {
        width = 10; // quantidade de blocos horizontais

        height = 10; // quantidade de blocos verticais

        qntyColors = 2; // quantidade de cores a aparecer na tela

        newColor = 1; // a cor anterior deve ser menor ou igual que a quantidade de cores.

        sleepTime = 500; // tempo entre as pinturas

        // coordenadas a começar pintando, 0 < xInicial < width e 0 < yInicial < height
        xInicial = (int) width / 2;
        yInicial = (int) height / 2;
    }

    /**
     * Atribui valores às variáveis dependentes das entradas do usuário
     * 
     * @param g
     * @param xInicial
     * @param yInicial
     * @author LucasAP
     */
    private void setNewnewColorValue(Graphics g, int xInicial, int yInicial) {
        newColorValue = ((int) 255 / qntyColors - 1) * newColor;
        firstColor = zone[xInicial][yInicial];
        color = new Color(newColorValue, newColorValue, 0);
    }

    /**
     * Método recursivo para alterar a imagem e o vetor de informações das cores.
     * 
     * @param
     * @author LucasAP
     */
    private void recursivePaintCaller(Graphics g, int i, int j) {

        if (zone[i][j] != firstColor || zone[i][j] == newColorValue) {
            return;
        }
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
        }

        g.setColor(color); // creio que a localização disso poderia ser outra (processada menos vezes)

        zone[i][j] = newColorValue;

        g.fillRect(i * widthPart, j * heightPart, widthPart, heightPart);

        if (i + 1 < width && zone[i + 1][j] == firstColor) {
            recursivePaintCaller(g, i + 1, j);
        }
        if (j + 1 < height && zone[i][j + 1] == firstColor) {
            recursivePaintCaller(g, i, j + 1);
        }
        if (i - 1 >= 0 && zone[i - 1][j] == firstColor) {
            recursivePaintCaller(g, i - 1, j);
        }

        if (j - 1 >= 0 && zone[i][j - 1] == firstColor) {
            recursivePaintCaller(g, i, j - 1);
        }
    }

    /**
     * Preenche os espaços do vetor com valores aleatórios na escala de cores do
     * amarelo.
     * 
     * @author LucasAP
     */
    private void createZoneValues() {
        zone = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                zone[i][j] = ((int) 255 / qntyColors - 1) * ThreadLocalRandom.current().nextInt(0, qntyColors);
                System.out.print(zone[i][j] + "\t");
            }
            System.out.println("");
        }

        firstColor = zone[xInicial][yInicial];
    }

    /**
     * Método destinado a preencher o Frame com os "pixels"
     * 
     * @author LucasAP
     * @param g
     */
    private void drawColorsMap(Graphics g) {
        widthPart = (int) 740 / width;
        heightPart = (int) 720 / height;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g.setColor(new Color(zone[i][j], zone[i][j], 0));
                g.fillRect(i * widthPart, j * heightPart, widthPart, heightPart);
            }
        }

    }

    /**
     * Método para aumentar o controle do usuário.
     * Possibilita executar a alteração apenas após a tecla Enter ser pressionada.
     * 
     * @author LucasAP
     */
    private void enterToProceed() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pressione enter para colorir o gráfico.");
        scanner.nextLine();
        scanner.close();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Imprime na interface de comando de linha os valores armazenados no vetor. Não
     * trata exceções.
     * 
     * @author LucasAP
     */
    private void print() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(zone[i][j] + "\t");
            }
            System.out.println("");
        }
    }

};
