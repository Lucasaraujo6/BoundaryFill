import javax.swing.JFrame;

public class BoundaryFill extends JFrame {

    public BoundaryFill() {
        initGui();
    }

    private void initGui() {
        setTitle("BoundaryFill");
        setSize(750, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Desenho desenho = new Desenho();
        add(desenho);

        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        new BoundaryFill();
    }
}
