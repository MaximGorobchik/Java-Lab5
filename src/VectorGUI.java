import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class VectorGUI extends JFrame {
    private JButton btn_suma = new JButton("Розрахувати суму векторів");
    private JButton btn_subs = new JButton("Розрахувати різницю векторів");
    private JButton btn_multiplication = new JButton("Розрахувати мн. векторів на число");
    private JPanel FunctionPanel = new JPanel();
    private JPanel InputPanel = new JPanel();
    private JLabel vectorAInput = new JLabel("Вектор А");
    private JTextField vectorAField = new JTextField();
    private JLabel vectorBInput = new JLabel("Вектор B");
    private JTextField vectorBField = new JTextField();
    private JLabel numberLabel = new JLabel("Число");
    private JTextField numberField = new JTextField();
    ArrayList<Integer> v1 = new ArrayList<>();
    ArrayList<Integer> v2 = new ArrayList<>();
    public VectorGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 150);
        setLocationRelativeTo(null);
        InputPanel.add(vectorAInput); InputPanel.add(vectorAField);
        InputPanel.add(vectorBInput); InputPanel.add(vectorBField);
        InputPanel.add(numberLabel); InputPanel.add(numberField);
        add(InputPanel, BorderLayout.NORTH);
        vectorAField.setPreferredSize(new Dimension(200, 20));
        vectorBField.setPreferredSize(new Dimension(200, 20));
        numberField.setPreferredSize(new Dimension(100, 20));
        btn_suma.setFont(new Font("Arial", Font.BOLD, 20));
        btn_subs.setFont(new Font("Arial", Font.BOLD, 20));
        btn_multiplication.setFont(new Font("Arial", Font.BOLD, 20));
        FunctionPanel.add(btn_suma);
        FunctionPanel.add(btn_subs);
        FunctionPanel.add(btn_multiplication);
        FunctionPanel.setBorder(BorderFactory.createTitledBorder("Vector"));
        add(FunctionPanel, BorderLayout.CENTER);
        btn_suma.addActionListener(new ButtonSumaEventListener());
        btn_subs.addActionListener(new ButtonSubtractionEventListener());
        btn_multiplication.addActionListener(new ButtonMultiplicationEventListener());
        setVisible(true);
    }
    void CheckInput()
    {
        v1.removeAll(v1); v2.removeAll(v2);
        if (vectorAField.getText().equals("") && vectorBField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Два пустих поля!", "", JOptionPane.ERROR_MESSAGE);
            vectorAField.setText(null);
            vectorBField.setText(null);
            numberField.setText(null);
        } else if (vectorAField.getText().equals("") || vectorBField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Одне пусте поле!", "", JOptionPane.ERROR_MESSAGE);
            vectorAField.setText(null);
            vectorBField.setText(null);
            numberField.setText(null);
        }
        else {
            String A = vectorAField.getText();
            String B = vectorBField.getText();
            StringTokenizer ATokenizer = new StringTokenizer(A);
            StringTokenizer BTokenizer = new StringTokenizer(B);
            String ALine = null;
            String BLine = null;
            while (ATokenizer.hasMoreTokens()) {
                ALine = ATokenizer.nextToken();
                try {
                    int number = Integer.parseInt(ALine);
                    v1.add(number);
                } catch (NumberFormatException er) {
                    JOptionPane.showMessageDialog(null, "Вектор А не є числовим!", "", JOptionPane.ERROR_MESSAGE);
                    vectorAField.setText(null);
                    vectorBField.setText(null);
                    numberField.setText(null);
                }
            }
            while (BTokenizer.hasMoreTokens()) {
                BLine = BTokenizer.nextToken();
                try {
                    int number = Integer.parseInt(BLine);
                    v2.add(number);
                } catch (NumberFormatException er) {
                    JOptionPane.showMessageDialog(null, "Вектор B не є числовим!", "", JOptionPane.ERROR_MESSAGE);
                    vectorAField.setText(null);
                    vectorBField.setText(null);
                    numberField.setText(null);
                }
            }
            if(v1.size() != v2.size())
            {
                JOptionPane.showMessageDialog(null, "Вектори не однакові за розміром!", "", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }
    public class ButtonSumaEventListener implements ActionListener //SUMA
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            CheckInput();
            ArrayList<Integer> suma = new ArrayList<>();
            String message = "";
            message += "A = { ";
            for (int i = 0; i < v1.size(); i++) {
                        message += v1.get(i) + " ";
            }
            message += "}\n" + "B = { ";
            for (int i = 0; i < v2.size(); i++) {
                        message += v2.get(i) + " ";
            }
            message += "}\n" + "Сума векторів = { ";
            for (int i = 0; i < v1.size(); i++) {
                        suma.add(v1.get(i) + v2.get(i));
                        message += suma.get(i) + " ";
            }
            message += "}";
            JOptionPane.showMessageDialog(null, message, "Результат", JOptionPane.INFORMATION_MESSAGE);
            vectorAField.setText(null);
            vectorBField.setText(null);
            numberField.setText(null);
        }
    }
    public class ButtonSubtractionEventListener implements  ActionListener //SUBTRACTION
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            CheckInput();
            String message = "";
            message += "A = { ";
            for(int i = 0; i < v1.size(); i++)
            {
                message += v1.get(i) + " ";
            }
            message += "}\n" + "B = { ";
            for(int i = 0; i < v1.size(); i++)
            {
                message += v2.get(i) + " ";
            }
            message += "}\n" + "Різниця векторів:\n" + "A - B = { ";
            ArrayList<Integer> subtraction1 = new ArrayList<>();
            ArrayList<Integer> subtraction2 = new ArrayList<>();
            for(int i = 0; i < v1.size(); i++) {
                subtraction1.add(v1.get(i) - v2.get(i));
                message += subtraction1.get(i) + " ";
            }
            message += "}\n" + "B - A = { ";
            for(int i = 0; i < v1.size(); i++)
            {
                subtraction2.add(v2.get(i) - v1.get(i));
                message += subtraction2.get(i) + " ";
            }
            message += "}";
            JOptionPane.showMessageDialog(null,message,"Результат", JOptionPane.INFORMATION_MESSAGE);
            vectorAField.setText(null);
            vectorBField.setText(null);
            numberField.setText(null);
        }
    }
    public class ButtonMultiplicationEventListener implements  ActionListener //MULTIPLICATION
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            CheckInput();
            if (numberField.getText().contains(" ")) {
                JOptionPane.showMessageDialog(null, "Число повинно бути без пробіла!", "", JOptionPane.ERROR_MESSAGE);
            }
            else if(numberField.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Число пусте!", "", JOptionPane.ERROR_MESSAGE);
            }
            else {
                String message = "";
                message += "A = { ";
                for (int i = 0; i < v1.size(); i++) {
                    message += v1.get(i) + " ";
                }
                message += "}\n" + "B = { ";
                for (int i = 0; i < v1.size(); i++) {
                    message += v2.get(i) + " ";
                }
                message += "}\n" + "Множення векторів на " + numberField.getText() + ":" + "\nA = { ";
                ArrayList<Integer> multiplication1 = new ArrayList<>();
                ArrayList<Integer> multiplication2 = new ArrayList<>();
                int number = Integer.parseInt(numberField.getText());
                for (int i = 0; i < v1.size(); i++) {
                    multiplication1.add(v1.get(i) * number);
                    message += multiplication1.get(i) + " ";
                }
                message += "}\n" + "B = { ";
                for (int i = 0; i < v1.size(); i++) {
                    multiplication2.add(v2.get(i) * number);
                    message += multiplication2.get(i) + " ";
                }
                message += "}";
                JOptionPane.showMessageDialog(null, message, "Результат", JOptionPane.INFORMATION_MESSAGE);
                vectorAField.setText(null);
                vectorBField.setText(null);
                numberField.setText(null);
            }
        }
    }
}
