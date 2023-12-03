import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShiftColumnsUI extends JFrame {
    private JTextField dimensionField;
    private JTextArea matrixArea;
    private JTextArea resultArea;

    public ShiftColumnsUI() {
        setTitle("ShiftColumns Program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel dimensionLabel = new JLabel("Enter matrix dimension (n):");
        dimensionField = new JTextField(10);
        inputPanel.add(dimensionLabel);
        inputPanel.add(dimensionField);

        JPanel matrixPanel = new JPanel(new BorderLayout());
        JLabel matrixLabel = new JLabel("Enter matrix elements (separated by space, rows separated by newline):");
        matrixArea = new JTextArea(10, 30);
        JScrollPane matrixScrollPane = new JScrollPane(matrixArea);
        matrixPanel.add(matrixLabel, BorderLayout.NORTH);
        matrixPanel.add(matrixScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton processButton = new JButton("Process");
        buttonPanel.add(processButton);

        JPanel resultPanel = new JPanel(new BorderLayout());
        JLabel resultLabel = new JLabel("Result:");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        resultPanel.add(resultLabel, BorderLayout.NORTH);
        resultPanel.add(resultScrollPane, BorderLayout.CENTER);

        mainPanel.add(inputPanel);
        mainPanel.add(matrixPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(resultPanel);

        add(mainPanel);

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int n = Integer.parseInt(dimensionField.getText());
                    String matrixText = matrixArea.getText();
                    String[] elements = matrixText.split("\\s+");

                    if (elements.length != n * n) {
                        throw new IllegalArgumentException("Incorrect number of matrix elements entered");
                    }

                    int[][] matrix = new int[n][n];
                    int index = 0;
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            matrix[i][j] = Integer.parseInt(elements[index++]);
                        }
                    }

                    // Find column with minimum element
                    int minCol = 0;
                    int minElement = matrix[0][0];
                    for (int j = 0; j < n; j++) {
                        for (int i = 0; i < n; i++) {
                            if (matrix[i][j] < minElement) {
                                minElement = matrix[i][j];
                                minCol = j;
                            }
                        }
                    }

                    // Create shifted matrix
                    int[][] shiftedMatrix = new int[n][n];
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            shiftedMatrix[i][j] = matrix[i][(j + n - minCol) % n];
                        }
                    }

                    // Display the shifted matrix in resultArea
                    StringBuilder result = new StringBuilder();
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            result.append(shiftedMatrix[i][j]).append(" ");
                        }
                        result.append("\n");
                    }
                    resultArea.setText(result.toString());

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ShiftColumnsUI.this, "Invalid input format for numbers", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(ShiftColumnsUI.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace(); // Handle other exceptions here
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ShiftColumnsUI().setVisible(true);
            }
        });
    }
}
