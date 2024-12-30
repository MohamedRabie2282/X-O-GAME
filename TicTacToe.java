
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {

    private JButton[][] buttons = new JButton[4][4];
    private boolean isXTurn = true;

    public TicTacToe() {
        setTitle("X O Game (4x4)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new GridLayout(4, 4));
        setLocationRelativeTo(null);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD , 40));
                buttons[i][j].setBackground(new Color(245, 245, 220));
                buttons[i][j].setForeground(Color.black);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (!clickedButton.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "This cell is already taken.\nPlease choose another one.");
            return;
        }

        clickedButton.setText(isXTurn ? "X" : "O");
        isXTurn = !isXTurn;

        if (checkWin()) {
            JOptionPane.showMessageDialog(this, (isXTurn ? "O" : "X") + " wins!\nCongratulations.");
            resetGame();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!\nTry again.");
            resetGame();
        }
    }

    private boolean checkWin() {
        // تحقق من الصفوف والأعمدة
        for (int i = 0; i < 4; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText())
                    && buttons[i][1].getText().equals(buttons[i][2].getText())
                    && buttons[i][2].getText().equals(buttons[i][3].getText())
                    && !buttons[i][0].getText().equals("")) {
                return true;
            }

            if (buttons[0][i].getText().equals(buttons[1][i].getText())
                    && buttons[1][i].getText().equals(buttons[2][i].getText())
                    && buttons[2][i].getText().equals(buttons[3][i].getText())
                    && !buttons[0][i].getText().equals("")) {
                return true;
            }
        }

        // تحقق من الأقطار
        if (buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && buttons[2][2].getText().equals(buttons[3][3].getText())
                && !buttons[0][0].getText().equals("")) {
            return true;
        }

        if (buttons[0][3].getText().equals(buttons[1][2].getText())
                && buttons[1][2].getText().equals(buttons[2][1].getText())
                && buttons[2][1].getText().equals(buttons[3][0].getText())
                && !buttons[0][3].getText().equals("")) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j].setText("");
            }
        }
        isXTurn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
